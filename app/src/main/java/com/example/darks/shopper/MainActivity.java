package com.example.darks.shopper;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.AbsListView;
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
    SwipeRefreshLayout srl;
    //TODO:: verify the layloading by adding more items to db
    //TODO:: add a scrolllistener to the listview so when end is reached, fetch more items
    //TODO:: add a navigationView with categories and sign in
    //TODO:: add a billing API
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fresco.initialize(getApplicationContext());
        lstview = (ListView) findViewById(R.id.listViewProducts);
        srl = (SwipeRefreshLayout)findViewById(R.id.refreshList);
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshList(lstview);
                Toast.makeText(getApplicationContext(),"refreshing",Toast.LENGTH_SHORT).show();
                srl.setRefreshing(false);
            }
        });
        refreshList(lstview);
        lstview.setOnScrollListener(new LazyLoader() {
            @Override
            public void loadMore(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                Product p = (Product)lstview.getItemAtPosition(totalItemCount-1);
                Log.e(TAG, "loadMore: "+p.getID());
            }
        });
    }
    public void refreshList(final ListView lstview)
    {
        Retrofit retro = new Retrofit.Builder()
                .baseUrl(RetrofitApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitApi retrofitapi = retro.create(RetrofitApi.class);
        Call<List<Product>> callproducts = retrofitapi.getProducts();
        callproducts.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> products = response.body();
                Log.e(TAG, "onResponse: "+products.size() );
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
