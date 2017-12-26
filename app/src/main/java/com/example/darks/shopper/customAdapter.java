package com.example.darks.shopper;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;




public class customAdapter extends ArrayAdapter<Product> {
    private static final String TAG = "customAdapter";
    List<Product> products;
    public customAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Product> objects) {
        super(context, resource, objects);
        products = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        SimpleDraweeView simpleDraweeView;
        TextView title;
        TextView quantity;
        TextView price;
        if(v==null)
        {
            LayoutInflater inf = LayoutInflater.from(getContext());
            v=inf.inflate(R.layout.product,parent,false);
        }
        simpleDraweeView = (SimpleDraweeView)v.findViewById(R.id.productImage);
        title = (TextView) v.findViewById(R.id.productTitle);
        quantity = (TextView) v.findViewById(R.id.productQuantity);
        price = (TextView) v.findViewById(R.id.productPrice);
        if(simpleDraweeView!=null)
        {
            String imagepath = RetrofitApi.BASE_URL+ products.get(position).getImagePath();
            Log.e(TAG, "getView: "+imagepath );
            simpleDraweeView.setImageURI(imagepath);
        }
        if(title!=null)
        {
            title.setText(products.get(position).getTitle());
        }
        if(quantity!=null)
        {
            quantity.setText(String.format("Qty: %d",products.get(position).getQuantity()) );
        }
        if(price!=null)
        {
            price.setText(String.format("price: %.2f $",products.get(position).getPrice()));
        }
        return v;
    }
}
