package practicumopdracht.data;

import practicumopdracht.MainApplication;
import practicumopdracht.models.DragQueen;
import practicumopdracht.models.Show;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TextDragQueenDAO extends DragQueenDAO {

    private File file = new File("resources/dragqueen.txt");

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
                Show belongsTo = MainApplication.getShowDAO().getById(Integer.parseInt(scanner.nextLine()));
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
            return false;
        } catch (IOException e){
            e.printStackTrace();
            System.err.println("File already in use");
            return false;
        }
    }
}
