import model.game.GameCategory;
import model.game.GameDifficulty;
import model.game.GameParameters;
import model.game.GameType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ScannerContentLoader {
    private Scanner scanner = new Scanner(System.in);

    void loadNumberOfQuestions(GameParameters gameParameters){
        do {
            try {
                System.out.println("Podaj liczbę pytań:");
                String line = scanner.nextLine();
                int numberOfQuestions = Integer.parseInt(line);
                if (numberOfQuestions < 1 || numberOfQuestions > 50) {
                    System.out.println("Ilość pytań musi być wieksza od o i mniejsza od 50.");
                    continue;
                }
                gameParameters.setNumberOfQuestions(numberOfQuestions);
            } catch (NumberFormatException e) {
                System.err.println("Niepoprawna liczba.");
            }
        } while (gameParameters.getNumberOfQuestions() == null);
    }

    private GameCategory checkIfIdIsInEnum(int categoryId) {
        return Arrays.stream(GameCategory.values())
                .filter(gameCategory -> gameCategory.getId() == categoryId)
                .findFirst()
                .orElse(null);
    }

    void loadCategory(GameParameters gameParameters){
        do {
            try {
                System.out.println("Podaj kategorię [wpisz identyfikator]:");

                Arrays.asList(GameCategory.values())
                        .forEach(gameCategory -> System.out.println(gameCategory.getId() + " - " + gameCategory.getName()));

                String line = scanner.nextLine();
                int categoryId = Integer.parseInt(line);
                GameCategory gameCategory = checkIfIdIsInEnum(categoryId);
                if (gameCategory != null) {
                    gameParameters.setCategory(gameCategory);
                } else {
                    System.err.println("Zły identyfikator kategorii.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Zły identyfikator kategorii.");            }
        } while (gameParameters.getCategory() == null);
    }

    public void printEnum() {
        char[] tab = new char[] {'a'};
        Arrays.stream(GameDifficulty.values())
                .forEach(gameDifficulty -> System.out.println(tab[0]++ + ") - " + gameDifficulty.toString()));
    }

    private Map<Character, GameDifficulty> getMap_Character_GameDifficulty(){
        Map<Character, GameDifficulty> characterGameDifficultyMap = new HashMap<>();
        char[] tab = new char[] {'a'};
        Arrays.stream(GameDifficulty.values())
                .forEach(gameDifficulty -> characterGameDifficultyMap.put(tab[0]++, gameDifficulty));
        return characterGameDifficultyMap;
    }

    void loadDifficulty(GameParameters gameParameters) {
        do {
            System.out.println("Wybierz poziom trudności:");
            Map<Character, GameDifficulty> characterGameDifficultyMap = getMap_Character_GameDifficulty();
            characterGameDifficultyMap.forEach((k, v) -> System.out.println(k + ") " + v.toString()));

            char sign = scanner.next().toLowerCase().charAt(0);
            if (sign >= 'a' && sign < ('a' + GameDifficulty.values().length)) {
                GameDifficulty gameDifficulty = characterGameDifficultyMap.get(sign);
                gameParameters.setDifficulty(gameDifficulty);
            } else {
                System.err.println("Bad choice.");
            }
        } while (gameParameters.getDifficulty() == null);
    }

    private Map<Character, GameType> getMap_Character_GameType(){
        Map<Character, GameType> characterGameTypeMap = new HashMap<>();
        char[] tab = new char[] {'a'};
        Arrays.stream(GameType.values())
                .forEach(gameType -> characterGameTypeMap.put(tab[0]++, gameType));
        return characterGameTypeMap;
    }

    void loadType(GameParameters gameParameters) {
        do {
            System.out.println("Wybierz typ gry:");
            Map<Character, GameType> characterGameTypeMap =getMap_Character_GameType();
            characterGameTypeMap.forEach((k, v) -> System.out.println(k + ") " + v.toString().toLowerCase()));

            char sign = scanner.next().toLowerCase().charAt(0);
            if (sign >= 'a' && sign < ('a' + GameType.values().length)){
                GameType gameType = characterGameTypeMap.get(sign);
                gameParameters.setType(gameType);
            } else {
                System.err.println("Bad choice.");
            }
        } while (gameParameters.getType() == null);
    }
}
