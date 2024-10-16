package pointcards;

import org.json.JSONObject;

import pointcards.game.pointsalad.Veggie;
import pointcards.game.pointsalad.criteria.ICriteria;
import pointcards.game.pointsalad.criteria.criterias.CriteriaAtLeast;
import pointcards.game.pointsalad.criteria.criterias.CriteriaEach;
import pointcards.game.pointsalad.criteria.criterias.CriteriaMost;
import pointcards.game.pointsalad.criteria.criterias.CriteriaEven;
import pointcards.game.pointsalad.criteria.criterias.CriteriaFewest;
import pointcards.game.pointsalad.criteria.criterias.CriteriaFewestTotal;
import pointcards.game.pointsalad.criteria.criterias.*;
import pointcards.game.pointsalad.manifest.json.JSONManifestParser;
import pointcards.io.JSONFileReader;

public class Playground {
    public static void main(final String[] args) {
        ICriteria[] criterias = new ICriteria[] {
                // new CriteriaMost(Veggie.CABBAGE, 3),
                //
                // new CriteriaEach(new Veggie[] { Veggie.CABBAGE }, 3),
                // new CriteriaEach(new Veggie[] { Veggie.CABBAGE, Veggie.CARROT }, 3),
                //
                // new CriteriaAtLeast(3, 5),
                //
                // new CriteriaEven(Veggie.CABBAGE, 5),
                //
                // new CriteriaFewest(Veggie.CABBAGE, 5),
                //
                // new CriteriaFewestTotal(5),
                //
                // new CriteriaMostTotal(5),
                //
                // new CriteriaOdd(Veggie.CABBAGE, 5),
                //
                // new CriteriaPer(Veggie.CABBAGE, 5),
                //
                // new CriteriaPerMissingType(5)
                //
                // new CriteriaSet(6)
        };

        for (ICriteria criteria : criterias) {
            System.out.println(criteria.toString());
        }
        // JSONFileReader reader = new JSONFileReader("./PSManifestV1.json");
        // // JSONFileReader reader = new JSONFileReader("./PointSaladManifestV1.json");
        // try {
        // var manifest = reader.readJsonFile();

        // JSONObject meta = manifest.getJSONObject("meta");
        // var version = meta.getNumber("version");

        // if (version.intValue() == 1) {
        // System.out.println("We support this!");
        // }

        // // var cards = manifest.getJSONObject("cards");
        // // Object[] veggies = cards.keySet().toArray();
        // // System.out.println(veggies.toString());
        // // // int total = 0;
        // // for (Object veggie : veggies) {
        // // var array = cards.getJSONArray(veggie.toString());
        // // System.out.printf("%d cards for %s\n", array.length(), veggie);
        // // // total += array.length();
        // // }
        // // System.out.println(total);
        // // return;
        // var parser = new JSONManifestParser(manifest);
        // var cards = parser.getCards();
        // System.out.println(cards.size());

        // System.out.printf("Parsed %d cards\n", cards.size());
        // } catch (Exception e) {
        // e.printStackTrace();
        // }
    }
}