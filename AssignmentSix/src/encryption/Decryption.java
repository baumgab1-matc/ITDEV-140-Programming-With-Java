package encryption;

public class Decryption {

    //Shifts the encrypted letters back to original place using key
    public String decrypt(String toDecrypt, int key) {
        StringBuilder decrypted = new StringBuilder();

        final int MIN = 0;
        final int MAX = 127;

        for (char c : toDecrypt.toCharArray()) {
            int shiftedIdx = c - key;
            char letter;

            //check if shiftedIdx when below 0
            //if it did, wrap around back to MAX
            if (shiftedIdx < MIN) {
                shiftedIdx = MAX + shiftedIdx;
                letter = (char) (shiftedIdx + 1);
            } else {
                letter = (char) shiftedIdx;
            }

            decrypted.append(letter);
        }

        return decrypted.toString();
    }

}
