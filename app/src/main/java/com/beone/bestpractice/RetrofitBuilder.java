package com.beone.bestpractice;

import com.beone.bestpractice.remote.ApiService;
import com.beone.bestpractice.utility.LiveDataCallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {
  private static final RetrofitBuilder ourInstance = new RetrofitBuilder();
  private static final String BASE_URL ="https://corona.lmao.ninja/v2/";
        //"http://192.168.0.107:8080/testapp/";
  public static RetrofitBuilder getInstance() {
    return ourInstance;
  }

  private RetrofitBuilder() {

  }
  private static OkHttpClient client = new OkHttpClient.Builder()

          // establish connection to server
          .connectTimeout(6, TimeUnit.SECONDS)

          // time between each byte read from the   server
          .readTimeout(6, TimeUnit.SECONDS)

          // time between each byte sent to server
          .writeTimeout(6, TimeUnit.SECONDS)

          .retryOnConnectionFailure(true)

          .build();

  public static ApiService getApiService(){
    return new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(LiveDataCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService.class);
  }

}
