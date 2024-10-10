package pointcards;

import org.json.JSONObject;

import pointcards.game.pointsalad.Veggie;
import pointcards.game.pointsalad.manifest.ManifestParser;
import pointcards.io.JSONFileReader;

public class Playground {
    public static void main(final String[] args) {
        // JSONFileReader reader = new JSONFileReader("./PSManifestV1.json");
        JSONFileReader reader = new JSONFileReader("./PointSaladManifestV1.json");
        try {
            var manifest = reader.readJsonFile();

            JSONObject meta = manifest.getJSONObject("meta");
            var version = meta.getNumber("version");

            if (version.intValue() == 1) {
                System.out.println("We support this!");
            }

            var cards = manifest.getJSONObject("cards");
            Object[] veggies = cards.keySet().toArray();
            System.out.println(veggies.toString());
            // int total = 0;
            for (Object veggie : veggies) {
                var array = cards.getJSONArray(veggie.toString());
                System.out.printf("%d cards for %s\n", array.length(), veggie);
                // total += array.length();
            }
            // System.out.println(total);
            // return;
            // var parser = new ManifestParser(manifest);
            // var cards = parser.getCards();
            // System.out.println(cards.size());

            // System.out.printf("Parsed %d cards\n", cards.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}