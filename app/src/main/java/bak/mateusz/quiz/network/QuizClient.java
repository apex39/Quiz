package bak.mateusz.quiz.network;

import bak.mateusz.quiz.models.QuizzesSet;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by timo on 11.04.17.
 */

public interface QuizClient {
    @GET("quizzes/0/100")
    Call<QuizzesSet> getQuizzes();
}
