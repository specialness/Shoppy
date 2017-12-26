package com.example.darks.shopper;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface RetrofitApi {
    public final String BASE_URL="http://arduinophptest-com.stackstaging.com/";

    @GET("connection.php")
    Call<List<Product>> getProducts();
}
