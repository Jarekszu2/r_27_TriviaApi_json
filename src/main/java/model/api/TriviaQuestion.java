package model.api;

import lombok.Data;

import java.util.List;

@Data
class TriviaQuestion {

    private String category;
    private String type;
    private String difficulty;
    private String question;
    private String correct_answer;
    private List<String> incorrect_answers;
}
