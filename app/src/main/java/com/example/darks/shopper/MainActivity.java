package com.example.darks.shopper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    ListView lstview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fresco.initialize(getApplicationContext());
        Retrofit retro = new Retrofit.Builder()
                .baseUrl(RetrofitApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        lstview = (ListView) findViewById(R.id.listViewProducts);
        RetrofitApi retrofitapi = retro.create(RetrofitApi.class);
        Call<List<Product>> callproducts = retrofitapi.getProducts();
        callproducts.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> products = response.body();
                customAdapter adp = new customAdapter(MainActivity.this,R.layout.product,products);
                lstview.setAdapter(adp);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"error "+t.getCause(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
