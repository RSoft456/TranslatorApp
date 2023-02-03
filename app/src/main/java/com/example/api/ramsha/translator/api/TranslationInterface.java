package com.example.api.ramsha.translator.api;

import com.google.gson.JsonArray;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface TranslationInterface {
    @POST("translate_a/single?client=gtx&dt=t&ie=UTF-8&oe=UTF-8")
    Call<JsonArray> getTrans(
            @Query("sl") String sl,
            @Query("tl") String tl,
            @Query("q") String q
    );
}
