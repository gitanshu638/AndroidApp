package com.example.gowheely;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.hardware.lights.LightState;
import android.os.Bundle;
import android.widget.TextView;

import com.example.gowheely.Adapter.CouponAdapter;
import com.example.gowheely.Model.CouponModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Coupon extends AppCompatActivity {

    private TextView appbarTitle;
    private FloatingActionButton backPress;
    private RecyclerView rvCoupon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);
        intViews();
        initListeners();

        appbarTitle.setText("Coupons");

        List<CouponModel> couponModels = new ArrayList<>();
        couponModels.add(new CouponModel("1","DIWALI20","This is dummy description ","Rs. 500","2022-11-11","Rs. 100"));
        couponModels.add(new CouponModel("1","DIWALI20","This is dummy description ","Rs. 500","2022-11-11","Rs. 100"));
        couponModels.add(new CouponModel("1","DIWALI20","This is dummy description ","Rs. 500","2022-11-11","Rs. 100"));
        couponModels.add(new CouponModel("1","DIWALI20","This is dummy description ","Rs. 500","2022-11-11","Rs. 100"));

        CouponAdapter couponAdapter = new CouponAdapter(this,couponModels);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rvCoupon.setHasFixedSize(true);
        rvCoupon.setLayoutManager(gridLayoutManager);
        rvCoupon.setAdapter(couponAdapter);
    }

    private void initListeners() {
        backPress.setOnClickListener(v->super.onBackPressed());

    }

    private void intViews() {
        appbarTitle = findViewById(R.id.txt_appbarTitle);
        backPress = findViewById(R.id.fab_backPress);
        rvCoupon = findViewById(R.id.rv_couponContainer);
    }
}