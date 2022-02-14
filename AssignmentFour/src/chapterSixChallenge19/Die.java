package chapterSixChallenge19;

import java.util.Random;

public class Die {

    private final int size;
    private final Random random;

    public Die(int size) {
        this.size = size;
        this.random = new Random();
    }

    public int roll() {
        return random.nextInt(size) + 1;
    }

}
