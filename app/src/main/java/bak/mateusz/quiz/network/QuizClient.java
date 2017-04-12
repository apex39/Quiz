package bak.mateusz.quiz.network;

import bak.mateusz.quiz.models.QuizzesSet;
import bak.mateusz.quiz.models.quiz.Quiz;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by timo on 11.04.17.
 */

public interface QuizClient {
    @GET("quizzes/0/100")
    Call<QuizzesSet> getQuizzes();

    @GET("quiz/{quizId}/0")
    Call<Quiz> getQuiz(@Path("quizId") long quizId);
}
