package encryption;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public class EncryptionController {

    private final String fileName;
    private final int key;
    private String data;
    private final Encryption encryption = new Encryption();
    private final Decryption decryption = new Decryption();

    public EncryptionController(String fileName, int key) {
        this.fileName = fileName;
        this.key = key;

        initData();
    }

    public void encrypt() {
        String encrypted = encryption.encrypt(data, key);
        writeToFile("encrypted.txt", encrypted);
    }

    public void decrypt() {
        String decrypted = decryption.decrypt(data, key);
        writeToFile("decrypted.txt", decrypted);
    }

    private void initData() {
        Path file = Path.of(fileName);
        try {
            data = Files.readString(file);
        } catch (IOException e) {
            System.out.println("No file found with name: " + fileName);
        }
    }

    private void writeToFile(String fileName, String data) {

        try (PrintWriter writer = new PrintWriter(fileName)) {
            writer.print(data);
        } catch (FileNotFoundException e) {
            System.out.println("Failed to write to file " + e.getMessage());
        }

    }

}
