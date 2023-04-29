import model.api.TriviaResponse;
import model.game.GameDifficulty;
import model.game.GameParameters;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println();

        TriviaApiUrlBuilder triviaApiUrlBuilder = new TriviaApiUrlBuilder();
        ScannerContentLoader scannerContentLoader = new ScannerContentLoader();
        GameParameters gameParameters = new GameParameters();
        Utilities utilities = new Utilities();

        scannerContentLoader.loadNumberOfQuestions(gameParameters);
        scannerContentLoader.loadCategory(gameParameters);
        scannerContentLoader.loadDifficulty(gameParameters);
        scannerContentLoader.loadType(gameParameters);
        triviaApiUrlBuilder.loadGameParameters(gameParameters);

        System.out.println();
        String gameUrl = triviaApiUrlBuilder.getGameUrl();
        System.out.println(gameUrl);

        System.out.println();
        TriviaResponse triviaResponse = utilities.getTriviaApiResponse(gameUrl);
        System.out.println(triviaResponse);

//        System.out.println();
//        int test1 = 5;
//        System.out.println(scannerContentLoader.checkIfIdIsInEnum(test1));
//
//        System.out.println();
//        int test2 = 23;
//        System.out.println(scannerContentLoader.checkIfIdIsInEnum(test2));

//        System.out.println();
//        scannerContentLoader.printEnum();
//
//        System.out.println();
//        Map<Character, GameDifficulty> characterGameDifficultyMap = scannerContentLoader.getMap_Character_GameDifficulty();
//        characterGameDifficultyMap.forEach((k, v) -> System.out.println(k + ") " + v.toString()));
    }


}
