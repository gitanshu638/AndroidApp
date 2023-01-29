package com.example.gowheely;

import static com.example.gowheely.R.id.rv_myOrders;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MyOrderActivity extends AppCompatActivity {
    private RecyclerView rvMyOrders;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        rvMyOrders=findViewById(rv_myOrders);


    }


}