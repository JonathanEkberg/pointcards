package pointcards.utils;

import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * The Randomizer class provides utility methods for random operations.
 * It includes methods for shuffling a list, setting a seed for reproducibility,
 * and generating random integers within a specified bound.
 * 
 * The class uses a single instance of java.util.Random initialized with a
 * default seed.
 */
public class Randomizer {
    private static final Random random = new Random(123);

    /**
     * Shuffles the elements of the provided list using a deterministic random
     * sequence.
     * 
     * @param <T>  The type of elements in the list.
     * @param list The list to shuffle.
     * @return The shuffled list.
     */
    public static final <T> List<T> shuffle(List<T> list) {
        Collections.shuffle(list, random);
        return list;
    }

    /**
     * Sets the seed of the random number generator.
     * This can be used to ensure reproducibility of random sequences.
     * 
     * @param seed The seed to set.
     */
    public static void setSeed(long seed) {
        random.setSeed(seed);
    }

    /**
     * Returns a random integer between 0 (inclusive) and the specified bound
     * (exclusive).
     * 
     * @param bound The upper bound (exclusive) for the random number.
     * @return A random integer between 0 (inclusive) and the specified bound
     *         (exclusive).
     */
    public static int nextInt(int bound) {
        return random.nextInt(bound);
    }
}