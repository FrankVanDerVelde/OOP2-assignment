package practicumopdracht.data;

import practicumopdracht.models.Show;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;

/**
 *  The DAO that handles the text version of the show data
 *
 * @ Author Frank van der Velde
 */
public class TextShowDAO extends ShowDAO {
    private final File file = new File("resources/show.txt");

    @Override
    public boolean save() {
        try {
            PrintWriter pw = new PrintWriter(file);
            for (Show show : super.objects) {
                pw.println(show.getName());
                pw.println(show.getLocation());
                pw.println(show.getDate());
                pw.println(show.getIsKidsFriendly());
            }
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("Couldn't find file");
            return false;
        }
        return true;
    }

    @Override
    public boolean load() {
        try {
            /**
             * Try to create the file incase it doesn't exist
             */
            if (file.createNewFile()) {
                System.out.println("File has been created because it did not exist while attempting to load.");
            }
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String name = scanner.nextLine();
                String location = scanner.nextLine();
                LocalDate date = LocalDate.parse(scanner.nextLine());
                boolean kidsFriendly = Boolean.parseBoolean(scanner.nextLine());
                Show show = new Show(name, location, date, kidsFriendly);
                super.objects.add(show);
            }
            scanner.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("Couldn't find file");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("File already in use");
        }
        return false;
    }
}
