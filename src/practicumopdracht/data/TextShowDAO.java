package practicumopdracht.data;

import practicumopdracht.MainApplication;
import practicumopdracht.models.DragQueen;
import practicumopdracht.models.Show;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

public class TextShowDAO extends ShowDAO {
    private File file = new File("resources/show.txt");

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
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("Couldn't find file");
            return false;
        } catch (IOException e){
            e.printStackTrace();
            System.err.println("File already in use");
            return false;
        }
    }

    @Override
    public boolean load() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);

            while(scanner.hasNextLine()) {
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
            return false;
        } catch (IOException e){
            e.printStackTrace();
            System.err.println("File already in use");
            return false;
        }
    }
}