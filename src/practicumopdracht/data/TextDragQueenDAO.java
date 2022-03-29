package practicumopdracht.data;

import practicumopdracht.MainApplication;
import practicumopdracht.models.DragQueen;
import practicumopdracht.models.Show;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *  The DAO that handles the text version of the dragqueen data
 *
 * @ Author Frank van der Velde
 */
public class TextDragQueenDAO extends DragQueenDAO {

    private final File file = new File("resources/dragQueen.txt");

    @Override
    public boolean save() {
        try {
            PrintWriter pw = new PrintWriter(file);
            for (DragQueen dragQueen : super.objects) {
                pw.println(MainApplication.getShowDAO().getIdFor(dragQueen.getBelongsTo()));
                pw.println(dragQueen.getDragName());
                pw.println(dragQueen.getRealName());
                pw.println(dragQueen.getAge());
                pw.println(dragQueen.getGender());
                pw.println(dragQueen.getHomeTown());
                pw.println(dragQueen.getSalary());
                pw.println(dragQueen.getBio());
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
                Show belongsTo = null;
                try  {
                    belongsTo = MainApplication.getShowDAO().getById(Integer.parseInt(scanner.nextLine()));
                } catch (Exception e) {
                    System.err.println("Error while parsing dragqueen belongs to");
                }

                String dragName = scanner.nextLine();
                String realName = scanner.nextLine();
                int age = Integer.parseInt(scanner.nextLine());
                String gender = scanner.nextLine();
                String homeTown = scanner.nextLine();
                double salary = Double.parseDouble(scanner.nextLine());
                String bio = scanner.nextLine();
                DragQueen dragQueen = new DragQueen(belongsTo, dragName, realName, age, gender, homeTown, salary, bio);
                super.objects.add(dragQueen);
            }
            scanner.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("Couldn't find file");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("File already in use");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Other when loading dragqueen text");
        }

        return false;
    }
}
