package com.example.api.ramsha.translator.api.apicalls;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static String TRANSLATE_BASE_URL = "https://translate.googleapis.com/";
    public TranslationInterface getTranslationInterface(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TRANSLATE_BASE_URL)
                .client(makeOkHTTPClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(TranslationInterface.class);
    }

    private OkHttpClient makeOkHTTPClient(){
        return new OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS).readTimeout(60,TimeUnit.SECONDS).writeTimeout(60,TimeUnit.SECONDS).build();

    }
}
