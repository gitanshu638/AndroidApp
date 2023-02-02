package com.example.gowheely;

import static com.example.gowheely.R.id.rv_myOrders;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.gowheely.Adapter.CategoryAdapter;
import com.example.gowheely.Adapter.OrderAdapter;
import com.example.gowheely.Model.CategoryModel;
import com.example.gowheely.Model.OrderModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MyOrderActivity extends AppCompatActivity {
    private RecyclerView rvMyOrders;
    private FloatingActionButton backPress;
    private TextView appbarTitle;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        rvMyOrders=findViewById(R.id.rv_myOrders);
        backPress=findViewById(R.id.fab_backPress);

        appbarTitle=findViewById(R.id.txt_appbarTitle);

        appbarTitle.setText("My Orders");

        backPress.setOnClickListener(view -> MyOrderActivity.super.onBackPressed());

        List<OrderModel> orderModelList = new ArrayList<>();
        orderModelList.add(new OrderModel(R.drawable.store,"0","2022-11-23","Britannia"));
        orderModelList.add(new OrderModel(R.drawable.vegetables,"0","2021-10-22","Daily Fresh"));
        orderModelList.add(new OrderModel(R.drawable.fruits,"0","2020-05-12","Daily Fresh"));
        orderModelList.add(new OrderModel(R.drawable.beverages,"0","2022-08-08","Coca-Cola"));
        orderModelList.add(new OrderModel(R.drawable.canned,"0","2022-07-16","Farm Fresh"));
        orderModelList.add(new OrderModel(R.drawable.store,"0","2022-09-09","Britannia"));
        orderModelList.add(new OrderModel(R.drawable.vegetables,"0","2020-02-13","Daily Fresh"));
        orderModelList.add(new OrderModel(R.drawable.fruits,"0","2021-12-21","Daily Fresh"));
        orderModelList.add(new OrderModel(R.drawable.beverages,"0","2022-12-03","Coca-Cola"));
        orderModelList.add(new OrderModel(R.drawable.canned,"0","2020-10-05","Farm Fresh"));


        OrderAdapter categoryAdapter = new OrderAdapter(this,orderModelList);
        rvMyOrders.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1, RecyclerView.VERTICAL, false);
        rvMyOrders.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL)); // divider below all adapter items
        rvMyOrders.setLayoutManager(layoutManager);
        rvMyOrders.setItemAnimator(new DefaultItemAnimator());
        rvMyOrders.setAdapter(categoryAdapter);

    }


}