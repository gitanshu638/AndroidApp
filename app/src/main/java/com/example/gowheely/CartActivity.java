package com.example.gowheely;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.gowheely.Adapter.CartAdapter;
import com.example.gowheely.Adapter.OrderAdapter;
import com.example.gowheely.Model.CartModel;
import com.example.gowheely.Model.OrderModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private RecyclerView rvMyCart;
    private FloatingActionButton backPress;
    private TextView appbarTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        backPress=findViewById(R.id.fab_backPress);

        appbarTitle=findViewById(R.id.txt_appbarTitle);

        appbarTitle.setText("My Cart");

        backPress.setOnClickListener(view -> CartActivity.super.onBackPressed());

        List<CartModel> cartModelList = new ArrayList<>();
        cartModelList.add(new CartModel(R.drawable.store,"Store","WallMart","1","Rs. 1200","Rs. 1300", "Good Product with bast quality!"));
        cartModelList.add(new CartModel(R.drawable.vegetables,"Store","Daily Fresh","2","Rs. 120","Rs. 140", "Good Product with bast quality!"));
        cartModelList.add(new CartModel(R.drawable.fruits,"Store","Daily Fresh","5","Rs. 300","Rs. 350", "Good Product with bast quality!"));
        cartModelList.add(new CartModel(R.drawable.canned,"Store","Farm Fresh","3","Rs. 200","Rs. 220", "Good Product with bast quality!"));
        cartModelList.add(new CartModel(R.drawable.cleaner,"Store","Cleaners","2","Rs. 150","Rs. 180", "Good Product with bast quality!"));


        rvMyCart = findViewById(R.id.rv_cart);
        CartAdapter cartAdapter = new CartAdapter(this,cartModelList);
        rvMyCart.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1, RecyclerView.VERTICAL, false);
        rvMyCart.setLayoutManager(layoutManager);
        rvMyCart.setItemAnimator(new DefaultItemAnimator());
        rvMyCart.setAdapter(cartAdapter);
    }
}