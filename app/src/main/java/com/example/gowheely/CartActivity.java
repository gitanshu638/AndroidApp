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
import com.example.gowheely.Model.ProductModel_request;
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



    }
}