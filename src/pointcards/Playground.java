package pointcards;

import org.json.JSONObject;

import pointcards.game.pointsalad.manifest.ManifestParser;
import pointcards.io.JSONFileReader;

public class Playground {
    public static void main(final String[] args) {
        JSONFileReader reader = new JSONFileReader("./PSManifest.json");
        try {
            var manifest = reader.readJsonManifest();

            JSONObject meta = manifest.getJSONObject("meta");
            var version = meta.getNumber("version");

            if (version.intValue() == 1) {
                System.out.println("We support this!");
            }

            var parser = new ManifestParser(manifest);
            var cards = parser.getCards();
            System.out.println(cards.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}