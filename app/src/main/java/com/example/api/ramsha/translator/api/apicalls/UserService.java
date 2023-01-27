package com.example.api.ramsha.translator.api.apicalls;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserService {
    //https://gorest.co.in/public/v2/users
    @GET("public/v2/users")
    Call<List<Users>> getAllUsers();

    @GET("public/v2/users/{id}")
    Call<Users> getUserDetail(@Path("id") int i);
}
