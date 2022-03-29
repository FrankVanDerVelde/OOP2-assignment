package practicumopdracht.data;

import practicumopdracht.models.Show;

import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;

/**
 *  The DAO that handles the loading and saving of the binary data file
 *  The file writes the amount of shows it has and then all the data for the shows
 * @ Author Frank van der Velde
 */
public class BinaryShowDAO extends ShowDAO {
    private final File file = new File("resources/show.dat");

    @Override
    public boolean save() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);

            /**
             * Write the amount of shows into the file first
             */
            dataOutputStream.writeInt(super.objects.size());

            for (Show show : super.objects) {
                dataOutputStream.writeUTF(show.getName());
                dataOutputStream.writeUTF(show.getLocation());
                dataOutputStream.writeUTF(show.getDate().toString());
                dataOutputStream.writeBoolean(show.getIsKidsFriendly());
            }
            dataOutputStream.close();
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
        Scanner scanner = null;
        try {
            /**
             * Try to create the file incase it doesn't exist
              */
            if (file.createNewFile()) {
                System.out.println("File has been created because it did not exist while attempting to load.");
            }

            FileInputStream fileInputStream = new FileInputStream(file);
            DataInputStream dataInputStream = new DataInputStream(fileInputStream);

            /**
             * Read the amount of shows into the file first
             */
            int showNumber = dataInputStream.readInt();

            for (int i = 0; i < showNumber; i++) {
                String name = dataInputStream.readUTF();
                String location = dataInputStream.readUTF();
                LocalDate date = LocalDate.parse(dataInputStream.readUTF());
                boolean kidsFriendly = dataInputStream.readBoolean();
                Show show = new Show(name, location, date, kidsFriendly);
                super.objects.add(show);
            }
            dataInputStream.close();
            fileInputStream.close();
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
