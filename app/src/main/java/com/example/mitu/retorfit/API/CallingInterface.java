package com.example.mitu.retorfit.API;

import android.support.v4.util.ArrayMap;

import com.example.mitu.retorfit.model.ModelClass;
import com.example.mitu.retorfit.model.PostModelClass;
import com.example.mitu.retorfit.model.SecondModelClass;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by mitu on 4/19/16.
 */
public interface CallingInterface {
    @GET("/Banner/AppBanner")
    public Call<List<ModelClass>> getDataFromApi();

    @GET("/AppApi/Categories/GetCategories")
    public Call<SecondModelClass> secondCall();

    @POST("/CustomerAccess/Login/")
    Call<PostModelClass> postCall(@Body ArrayMap<String, String> rasel);

    @GET("/Test/ChachingTest")
    public Call<String> secondCall2();
}
