package model.modelsToGame;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.modelsFromApi.TriviaQuestion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Pojedyncze pytanie w grze
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GameQuestion {
    private String question;
    private List<GameAnswer> answers;
    private GameAnswer selectedAnswer;

    public GameQuestion(TriviaQuestion triviaQuestion) {
        this.question = triviaQuestion.getQuestion();
        this.selectedAnswer = null;
        this.answers = new ArrayList<>();

        this.answers.add(new GameAnswer(triviaQuestion.getCorrect_answer(), true));
        this.answers.addAll(triviaQuestion
                .getIncorrect_answers()
                .stream()
                // zmieniam stringa na obiekt GameAnswer
                .map(stringIncorrectAnswer -> new GameAnswer(stringIncorrectAnswer, false))
                .collect(Collectors.toList()));

        // "tasowanie" elementów (zostaną rozlosowane w innej kolejności niż kolejność dodania)
        Collections.shuffle(this.answers);
    }

    @Override
    public String toString() {
        char[] sign = new char[] {'a'};
        StringBuilder stringBuilder = new StringBuilder();
//        answers.forEach(gameAnswer -> stringBuilder.append(sign[0]++ + ") " + gameAnswer.getContent() + "\n"));
        answers.forEach(gameAnswer -> stringBuilder.append(sign[0]++));
        answers.forEach(gameAnswer -> stringBuilder.append(") "));
        answers.forEach(gameAnswer -> stringBuilder.append(gameAnswer.getContent()));
        answers.forEach(gameAnswer -> stringBuilder.append("\n"));
        return "Question: " + question + "\n\n" + stringBuilder.toString();
    }
}
