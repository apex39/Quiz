package bak.mateusz.quiz.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by timo on 11.04.17.
 */

public class ClientGenerator {
    public static final String API_BASE_URL = "http://quiz.o2.pl/api/v1/";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder;


    static QuizClient createService() {
        builder =
                new Retrofit.Builder()
                        .baseUrl(API_BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit.create(QuizClient.class);
    }

}