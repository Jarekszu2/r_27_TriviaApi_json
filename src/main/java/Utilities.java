import com.google.gson.Gson;
import model.modelsFromApi.TriviaResponse;
import org.apache.commons.lang3.StringEscapeUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

class Utilities {
    private static final Gson GSON = new Gson();

    private String loadApiContentByStream(String apiUrl) {
        final StringBuilder stringBuilder = new StringBuilder();
        String apiContent = null;

        try {
            URL url = new URL(apiUrl);
            try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()))) {
                bufferedReader.lines().forEach(stringBuilder::append);
                apiContent = stringBuilder.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return apiContent;
    }

    TriviaResponse getTriviaApiResponse(String apiUrl) {
        String apiContent = loadApiContentByStream(apiUrl);
        TriviaResponse triviaResponse = GSON.fromJson(apiContent, TriviaResponse.class);
        triviaResponse.getResults()
                .forEach(triviaQuestion -> triviaQuestion.setQuestion(StringEscapeUtils.unescapeHtml4(triviaQuestion.getQuestion())));
        return triviaResponse;
    }
}
