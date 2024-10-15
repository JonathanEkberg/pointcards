package pointcards.game.pointsalad.manifest.json.versions;

public enum Version {
    V1;

    public static Version intVersionToVersion(int version) {
        switch (version) {
            case 1:
                return V1;
            default:
                throw new IllegalArgumentException("Unknown version " + version);
        }
    }
}
