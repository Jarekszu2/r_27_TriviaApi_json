import model.models_urlParameters.GameCategory;
import model.models_urlParameters.GameDifficulty;
import model.models_urlParameters.GameParameters;
import model.models_urlParameters.GameType;

class TriviaApiUrlBuilder {

    private static final String BASE_URL = "https://opentdb.com/api.php?amount={amount}";
    private final StringBuilder builder;

    TriviaApiUrlBuilder() {
        builder = new StringBuilder(BASE_URL); // tworzę bilder, który jako poczatkowy URL ma BASE_URL
    }

    private void appendNumberOfQuestions(int number) {
        if (builder.toString().contains("{amount}")){
            int position = builder.toString().indexOf("{amount}");
            builder.replace(position, position + 8, String.valueOf(number));
        }
    }

    private void appendCategory(GameCategory gameCategory) {
        // gameCategory.getId() == -1 to wartość ANY
        if (!builder.toString().contains("&category=") && gameCategory.getId() != -1) {
            builder.append("&category=");
            builder.append(gameCategory.getId());
        } else if (builder.toString().contains("&category=")) {
            System.out.println("Kategoria została już dopisana.");
        }
    }

    private void appendDifficulty(GameDifficulty gameDifficulty) {
        // gameDifficulty.getId() == -1 to wartość ANY
        if (!builder.toString().contains("&difficulty=") && gameDifficulty != GameDifficulty.ANY){
            builder.append("&difficulty=");
            builder.append(gameDifficulty.toString().toLowerCase());
        }else if (builder.toString().contains("&difficulty=")){
            System.err.println("Poziom trudności został już wybrany.");
        }
    }

    private void appendType(GameType gameType) {
        // gameType.getId() == -1 to wartość ANY
        if (!builder.toString().contains("&type=") && gameType != GameType.ANY) {
            builder.append("&type=");
            builder.append(gameType.toString().toLowerCase());
        } else if (builder.toString().contains("&type=")){
            System.err.println("Typ odpowiedzi został juz wybrany.");
        }
    }

    void loadGameParameters(GameParameters gameParameters){
        appendNumberOfQuestions(gameParameters.getNumberOfQuestions());
        appendCategory(gameParameters.getCategory());
        appendDifficulty(gameParameters.getDifficulty());
        appendType(gameParameters.getType());
    }

    String getGameUrl(){
        return builder.toString();
    }
}
