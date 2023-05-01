import model.modelsFromApi.TriviaResponse;
import model.modelsToGame.GameQuestion;
import model.modelsToGame.TriviaGame;
import model.models_urlParameters.GameParameters;

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
//        System.out.println(triviaResponse);

        if (triviaResponse.getResponse_code() == 0) {
            TriviaGame game = new TriviaGame(triviaResponse);

            while (!game.gameEnded()){
                GameQuestion gameQuestion = game.getCurrentGameQuestion();

                System.out.println(gameQuestion.getQuestion());
                System.out.println();
                char[] tab = new char[] {'a'};
                gameQuestion.getAnswers()
                        .forEach(gameAnswer -> System.out.println(tab[0]++ + ") " + gameAnswer.getContent()));

                String answer = scannerContentLoader.loadAnswer();

                game.submitAnswer(answer);
            }
            long score = game.getGameQuestionList().stream()
                    .filter(gameQuestion -> gameQuestion.getSelectedAnswer().isCorrect())
                    .count();
            System.out.println("Result: " + score + "/" + game.getGameQuestionList().size() + ".");
        }
    }


}
