package ru.devcorvette.infinitescroll.model;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClient {
    private static final String ROOT_URL = "http://109.111.162.236:8083/api/v2/";

    /**
     * @return Retrofit Instance
     */
    public static Retrofit getRetrofitInstance() {
        return new Retrofit
                .Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * @return API Service
     */
    public static ApiService getApiService() {
        return getRetrofitInstance().create(ApiService.class);
    }
}
