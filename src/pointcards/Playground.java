package pointcards;

import org.json.JSONObject;

import pointcards.filesystem.JSONReader;
import pointcards.game.IGame;
import pointcards.game.IGameFactory;
import pointcards.game.pointsalad.GameFactory;
import pointcards.io.input.IInput;
import pointcards.io.input.LocalConsoleInput;
import pointcards.settings.GameSettings;
import pointcards.settings.OptionalGameSettings;
import pointcards.settings.ProgramSettings;
import pointcards.utils.Args;

public class Playground {
    public static void main(final String[] args) {
        JSONReader reader = new JSONReader("./PSManifest.json");
        try {
            var manifest = reader.readJsonManifest();
            System.out.println(manifest.toString());

            JSONObject meta = manifest.getJSONObject("meta");
            var version = meta.getNumber("version");
            if (version.intValue() == 1) {
                System.out.println("We support this!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}