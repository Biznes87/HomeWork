import java.io.*;
import java.io.File;

/**
 * Created by dex on 7/19/16.
 */
public class FileWork {

    final static String PATH = "/home/dex/Documents/study/JAVA2/lesson6/";
    final static String UNIC = new java.util.Date ().toString ();
    final static String STR = PATH + UNIC+".log";
    public static void fileGen (){

        File genFile = new File(STR);
        try {
            FileWriter writer = new FileWriter(STR, true);
            BufferedWriter bufferWriter = new BufferedWriter(writer);
            bufferWriter.write(UNIC + "\n");
            bufferWriter.close();   // Закрываем соединения
        }
        catch (IOException e) {
            System.out.println(e);
        }

    }

    public static void append (String text){
        try {
            FileWriter writer = new FileWriter(STR, true);
            BufferedWriter bufferWriter = new BufferedWriter(writer);
            bufferWriter.write(text);
            bufferWriter.close();   // Закрываем соединения
        }
        catch (IOException e) {
            System.out.println(e);
        }

    }
    }

