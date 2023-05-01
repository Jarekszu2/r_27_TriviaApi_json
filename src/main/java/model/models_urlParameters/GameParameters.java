package model.models_urlParameters;

import lombok.Data;

@Data
public class GameParameters {
    private Integer numberOfQuestions;
    private GameCategory category;
    private GameDifficulty difficulty;
    private GameType type;
}
