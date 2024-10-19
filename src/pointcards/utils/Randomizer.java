package pointcards.utils;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Randomizer {
    private static final Random random = new Random(123);

    public static final <T> List<T> shuffle(List<T> list) {
        Collections.shuffle(list, random);
        return list;
    }

    public static void setSeed(long seed) {
        random.setSeed(seed);
    }

    public static int nextInt(int bound) {
        return random.nextInt(bound);
    }
}
