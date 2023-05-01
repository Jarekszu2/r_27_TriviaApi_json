package model.modelsToGame;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GameAnswer {
    private String content;
    private boolean isCorrect;

    @Override
    public String toString() {
        return content;
    }
}
