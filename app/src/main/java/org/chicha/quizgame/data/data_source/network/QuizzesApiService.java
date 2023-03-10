package org.chicha.quizgame.data.data_source.network;

import java.util.List;

import org.chicha.quizgame.BuildConfig;
import org.chicha.quizgame.domain.dto.QuizDto;
import org.chicha.quizgame.domain.enums.CategoryEnum;
import org.chicha.quizgame.domain.enums.DifficultyEnum;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface QuizzesApiService {
    String URL = "questions/?apiKey=" + BuildConfig.QUIZZES_KEY;

    @GET(URL + "&limit=20")
    Call<List<QuizDto>> getAllQuizzes();

    // with tags
    @GET(URL+ "&limit=10")
    Call<List<QuizDto>> getQuizzesByTags(@Query("tags") String tagType);

    // with category
    @GET(URL)
    Call<List<QuizDto>> getQuizzesByCategory(@Query("category") String category);

    // with difficulty
    @GET(URL)
    Call<List<QuizDto>> getQuizzesByDifficulty(@Query("difficulty") DifficultyEnum difficultyId);

}
