package encryption;

public class Encryption {

    //encrypting text with with ascii table by shifting text by a certain key amount
    public String encrypt(String toEncrypt, int key) {
        StringBuilder encrypted = new StringBuilder();

        final int MIN = 0; //starting ascii
        final int MAX = 127; //ending ascii

        for (char c : toEncrypt.toCharArray()) {
            int shiftedIx = c + key;
            char letter;

            //check if we need to wrap around back to 0
            if (shiftedIx > MAX) {
                letter = wrapAround(shiftedIx, MIN, MAX);
            } else {
                letter = (char) shiftedIx;
            }

            encrypted.append(letter);
        }

        return encrypted.toString();
    }


    //if when adding key to ascii position goes over max, this method wraps back to start
    private char wrapAround(int idx, int min, int max) {
        idx = idx - max;
        return (char) (min + idx - 1);
    }

}
