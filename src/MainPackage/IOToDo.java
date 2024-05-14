package MainPackage;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.File;

public final  class  IOToDo {
    public static final void  writeTask(ToDo toDo) {
        File directory = new File("Data/" + toDo.getTopic() + ".bin");
        try {
            FileOutputStream fos = new FileOutputStream(directory);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(toDo);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static final ToDo readTask(String fileName) {
        File directory = new File("Data/" + fileName);
        if(directory.exists()) {
            try {
                FileInputStream fis = new FileInputStream(directory);
                ObjectInputStream ois = new ObjectInputStream(fis);
                return (ToDo) ois.readObject();
            } catch(IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else System.out.println("directory is missing");

        return null;
    }

    public static final String[] getList(String path) {
        return new File(path).list();
    }
}
