package com.example.mitu.retorfit;

import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;

import com.example.mitu.retorfit.API.CallingInterface;
import com.example.mitu.retorfit.model.ModelClass;
import com.example.mitu.retorfit.model.PostModelClass;
import com.example.mitu.retorfit.model.SecondModelClass;



import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    @InjectView(R.id.firstOne)
    TextView textView;
    @InjectView(R.id.firstTwo)
    ImageView textView2;
    @InjectView(R.id.firstThree)
    ImageView textView3;
    @InjectView(R.id.firstfour)
    ImageView textView4;
    @InjectView(R.id.firstfive)
    ImageView textView5;

    String BASE_URL = "http://blaa.blaa.com";



    String BASE_URL_TWO = "http://127.0.02";
    CallingInterface callingInterface;
    CallingInterface callRetro;
    CallingInterface postCallingService;
    CallingInterface testclass;
    private Cache mCache;
    private OkHttpClient mOkHttpClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        mCache = new Cache(new File(this.getCacheDir(), "responses"), 10 * 1024 * 1024);
        mOkHttpClient = new OkHttpClient.Builder().cache(mCache).build();

        //retrofit is start from hear
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL).
                client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();



        Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl(BASE_URL_TWO)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        testclass = retrofit2.create(CallingInterface.class);
        Call<String> callForString = testclass.secondCall2();
        callForString.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                textView.setText(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }


        });

        callingInterface = retrofit.create(CallingInterface.class);
        callRetro = retrofit.create(CallingInterface.class);

        postCallingService = retrofit.create(CallingInterface.class);
        ArrayMap<String, String> dicMap = new ArrayMap();
        dicMap.put("Email", "kazal.k1@gmail.com");
        dicMap.put("Password", "123");
        Call<PostModelClass> postModelClassCall = postCallingService.postCall(dicMap);
        postModelClassCall.enqueue(new Callback<PostModelClass>() {

            @Override
            public void onResponse(Call<PostModelClass> call, Response<PostModelClass> response) {

            }

            @Override
            public void onFailure(Call<PostModelClass> call, Throwable t) {

            }
        });

        Call<SecondModelClass> callForSecondTime = callRetro.secondCall();
        callForSecondTime.enqueue(new Callback<SecondModelClass>() {
            @Override
            public void onResponse(Call<SecondModelClass> call, Response<SecondModelClass> response) {
                Toast.makeText(getBaseContext(), response.body().getCategories().get(0).getCategoryName(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<SecondModelClass> call, Throwable t) {

            }


        });

        Call<List<ModelClass>> call = callingInterface.getDataFromApi();
        call.enqueue(new Callback<List<ModelClass>>() {
            @Override
            public void onResponse(Call<List<ModelClass>> call, Response<List<ModelClass>> response) {
                setImage(response, 0, textView5);
                setImage(response, 1, textView2);
                setImage(response, 2, textView3);
                setImage(response, 3, textView4);
            }

            @Override
            public void onFailure(Call<List<ModelClass>> call, Throwable t) {

            }


            void setImage(Response<List<ModelClass>> response, int location, ImageView imageView) {

                Picasso.with(getBaseContext()).load(response.body().get(location).getImageLink()).into(imageView);
            }


        });








    }

 
}
