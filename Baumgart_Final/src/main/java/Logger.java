import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {

    private final String fileName;

    public Logger(String fileName) {
        this.fileName = fileName;
    }

    public void logAndPrint(String message) {
        System.out.print(message);
        writeToFile(message);
    }

    public void logAndPrintln(String message) {
        System.out.println(message);
        writeToFile(message);
    }

    private void writeToFile(String message) {
        try {
            FileWriter fw = new FileWriter(fileName, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(message);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
