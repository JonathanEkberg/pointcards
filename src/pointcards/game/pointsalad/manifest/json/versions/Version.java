package pointcards.game.pointsalad.manifest.json.versions;

/**
 * The {@code Version} enum represents different versions of the point salad
 * game manifest.
 * Currently, it only supports version 1.
 */
public enum Version {
    /**
     * Version 1 of the point salad game manifest.
     */
    V1;

    /**
     * Converts an integer representation of a version to its corresponding
     * {@code Version} enum.
     *
     * @param version the integer representation of the version
     * @return the corresponding {@code Version} enum
     * @throws IllegalArgumentException if the version is unknown
     */
    public static Version intVersionToVersion(int version) {
        switch (version) {
            case 1:
                return V1;
            default:
                throw new IllegalArgumentException("Unknown version " + version);
        }
    }
}
