package practicumopdracht.data;

import practicumopdracht.MainApplication;
import practicumopdracht.models.DragQueen;
import practicumopdracht.models.Show;

import java.io.*;

/**
 *  The DAO that handles the loading and saving of the object version of the dragqueen data
 *
 * @ Author Frank van der Velde
 */
public class ObjectDragQueenDAO extends DragQueenDAO {
    private final File file = new File("resources/dragQueen.obj");

    @Override
    public boolean save() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            /**
             * Write the amount of shows at the start of the file
              */
            objectOutputStream.writeInt(super.objects.size());

            for (DragQueen dragqueen : super.objects) {
                objectOutputStream.writeInt(MainApplication.getShowDAO().getIdFor(dragqueen.getBelongsTo()));
                objectOutputStream.writeObject(dragqueen);
            }
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("Couldn't find file");
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("IO operations failed");
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

            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            /**
             * Read the amount of shows stored at the start of the file
             */
            int showNumber = objectInputStream.readInt();

            for (int i = 0; i < showNumber; i++) {
                Show show = MainApplication.getShowDAO().getById(objectInputStream.readInt());
                DragQueen dragQueen = (DragQueen) objectInputStream.readObject();
                dragQueen.setBelongsTo(show);
                super.objects.add(dragQueen);
            }
            objectInputStream.close();
            fileInputStream.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("Couldn't find file");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("File already in use");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Other exception");
        }
        return false;
    }
}
