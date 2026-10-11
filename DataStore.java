package resqnet.service;
import java.io.*;
import java.util.ArrayList;
public class DataStore {
    public static <T> void saveData(
            ArrayList<T> data,
            String fileName
    ) {
        try (
            ObjectOutputStream out =
                new ObjectOutputStream(
                    new FileOutputStream(fileName)
                )
        ) {
            out.writeObject(data);
} catch (IOException e) {
    System.out.println(
                "Error Saving Data: " + e.getMessage()
            );}}
    @SuppressWarnings("unchecked")
    public static <T> ArrayList<T> loadData(
            String fileName
    ) {
        File file = new File(fileName);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (
            ObjectInputStream in =
                new ObjectInputStream(
                    new FileInputStream(file)
                )
        ) {
            return (ArrayList<T>) in.readObject();
} catch (
            IOException | ClassNotFoundException e
        ) {
            System.out.println(
                "Error Loading Data: " + e.getMessage()
            );
            return new ArrayList<>();
        }}}