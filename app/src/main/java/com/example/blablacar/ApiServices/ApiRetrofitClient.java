package com.example.blablacar.ApiServices;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRetrofitClient {
    private static ApiRetrofitClient restClient;
    private final ApiEndPointCall apiCall;
    private static final String baseUrl = "https://bus-api.blablacar.com/v3/";
    private static final String APIKEY = "crtObqwLS_zfBjWs-nb_aQ";
    private ApiRetrofitClient() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        OkHttpClient httpClient = httpClientBuilder
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request().newBuilder().addHeader("Authorization", "Token "+APIKEY).build();
                        return chain.proceed(request);
                    }
                })
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl).client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiCall = retrofit.create(ApiEndPointCall.class);
    }

    public static ApiRetrofitClient getInstance() {
        if (restClient == null) {
            restClient = new ApiRetrofitClient();
        }
        return restClient;
    }

    public ApiEndPointCall getApiCall() {
        return apiCall;
    }
}
