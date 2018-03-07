package me.najmsheikh.buttonchallenge.network;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Najm Sheikh <hello@najmsheikh.me> on 3/5/18.
 */

public class RetrofitServiceGenerator {

    private static final String BASE_URL = "http://fake-button.herokuapp.com/";

    // Retrofit2 throws an error if the body is empty for status 200 response.
    // Added a custom interceptor to mediate. See issue #1554 @ gh:square/retrofit
    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(chain -> {
                Request request = chain.request();
                Response response = chain.proceed(request);
                if (response.code() == 200 && response.body().contentLength() < 1) {
                    ResponseBody body = ResponseBody.create(MediaType.parse("application/json"), "[]");
                    return response.newBuilder().body(body).build();
                }
                return response;
            })
            .build();

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
