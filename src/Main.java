import javafx.collections.transformation.SortedList;

import java.awt.*;
import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

    static ArrayList<File> text = new ArrayList<File>();

    public static void main(String[] args) {

        /** Please change directory of catalog **/

        putFile(text, "..//dir_test");
        Collections.sort(text, (o1, o2) -> (Integer.valueOf(o1.getName().substring(0, o1.getName().lastIndexOf("."))) > Integer.valueOf(o2.getName().substring(0, o2.getName().lastIndexOf("."))) ? 1 :
                (Integer.valueOf(o1.getName().substring(0, o1.getName().lastIndexOf("."))) < Integer.valueOf(o2.getName().substring(0, o2.getName().lastIndexOf(".")))) ? -1 : 0));
        text.forEach(System.out::println);
        aggragateTxt(text);

    }

    public static List<File> putFile(ArrayList<File> list, String directory) {
        File file = new File(directory);
        File[] tmpArrFile = file.listFiles();
        for (int i = 0; i < tmpArrFile.length; i++) {
            if (tmpArrFile[i].isDirectory()) putFile(list, directory + "/" + tmpArrFile[i].getName());
            if (tmpArrFile[i].isFile() && isTxt(tmpArrFile[i])) list.add(tmpArrFile[i]);
        }
        return list;
    }

    public static boolean isTxt(File file) {
        if (file.getName().substring(file.getName().lastIndexOf(".") + 1).equals("txt"))
            return true;
        else return false;
    }

    public static void aggragateTxt(ArrayList<File> list) {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("agregateFile.txt", false)))) {
            for (File f : list) {
                writer.write(String.valueOf(Files.readAllLines(Paths.get(f.getPath()))).replace(", ", "\n").replace("]", "\n").replace("[", ""));
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
