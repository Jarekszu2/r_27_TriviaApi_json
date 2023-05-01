package model.modelsToGame;

import lombok.Data;
import model.modelsFromApi.TriviaResponse;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Klasa odpowiedzialna za przechowywanie stanu gry (nr pytania, jakie są pytania, jakie są odpowiedzi, itd.
 */
@Data
public class TriviaGame {
    private List<GameQuestion> gameQuestionList;
    private int currentQuestionNumber = 0;

    public TriviaGame(TriviaResponse triviaResponse) {
        gameQuestionList = triviaResponse.getResults()
                .stream()
                .map(GameQuestion::new)
                .collect(Collectors.toList());
    }

    public boolean gameEnded(){
        return currentQuestionNumber >= gameQuestionList.size();
    }

    public GameQuestion getCurrentGameQuestion(){
        return gameQuestionList.get(currentQuestionNumber);
    }

    public void submitAnswer(String answer){
        // użytkownik podaje np. "A", "b", "c"
        // po odjęciu 'a' (97) mamy indeks pozycji odpowiedzi np. 2 (odpowiedż nr 3 (0, 1, 2))
        int answerNumber = answer.toLowerCase().charAt(0) - 'a';

        if (gameQuestionList.get(currentQuestionNumber).getAnswers().size() > answerNumber) {
            // wybraliśmy istniejącą opcję
            GameQuestion currentQuestion = gameQuestionList.get(currentQuestionNumber);
            GameAnswer answerTheCurrentQuestion = currentQuestion.getAnswers().get(answerNumber);
            currentQuestion.setSelectedAnswer(answerTheCurrentQuestion);
            if (answerTheCurrentQuestion.isCorrect()){
                System.out.println("Good.");
            } else {
                System.err.println("Bad, proper answer is:");
                List<GameAnswer> answersCurrentQuestion = currentQuestion.getAnswers();
                answersCurrentQuestion.stream()
                        .filter(GameAnswer::isCorrect)
                        .findAny()
                        .ifPresent(System.out::println);
            }
            System.out.println();

            currentQuestionNumber++;
        } else {
            System.err.println("Nie ma takiej odpowiedzi.");
        }
    }

    public void printScore() {

    }
}
