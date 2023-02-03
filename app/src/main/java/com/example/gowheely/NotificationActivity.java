package com.example.gowheely;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.example.gowheely.Adapter.NotificationAdapter;
import com.example.gowheely.Adapter.OrderAdapter;
import com.example.gowheely.Model.NotificationModel;
import com.example.gowheely.Model.OrderModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {
    private RecyclerView notiRec;
    private FloatingActionButton backPress;
    private TextView appbarTitle;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        notiRec=findViewById(R.id.rec_notification);

        backPress=findViewById(R.id.fab_backPress);
        appbarTitle=findViewById(R.id.txt_appbarTitle);

        appbarTitle.setText("Notifications");

        backPress.setOnClickListener(view -> NotificationActivity.super.onBackPressed());

        List<NotificationModel> notificationModels = new ArrayList<>();
        notificationModels.add(new NotificationModel("Diwali Offer","2022-01-27",getResources().getString(com.google.zxing.client.android.R.string.zxing_msg_default_status)));
        notificationModels.add(new NotificationModel("Holi Offer","2021-03-16",getResources().getString(com.google.zxing.client.android.R.string.zxing_msg_default_status)));
        notificationModels.add(new NotificationModel("Independence Day Offer","201-08-10",getResources().getString(com.google.zxing.client.android.R.string.zxing_msg_default_status)));
        notificationModels.add(new NotificationModel("Duessera Offer","2020-10-20",getResources().getString(com.google.zxing.client.android.R.string.zxing_msg_default_status)));
        notificationModels.add(new NotificationModel("Republic Day Offer","2021-01-20",getResources().getString(com.google.zxing.client.android.R.string.zxing_msg_default_status)));
        notificationModels.add(new NotificationModel("Independence Day Offer","2019-08-10",getResources().getString(com.google.zxing.client.android.R.string.zxing_msg_default_status)));
        notificationModels.add(new NotificationModel("Duessera Offer","2019-10-10",getResources().getString(com.google.zxing.client.android.R.string.zxing_msg_default_status)));




        NotificationAdapter notificationAdapter = new NotificationAdapter(this,notificationModels);
        notiRec.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1, RecyclerView.VERTICAL, false);
        notiRec.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL)); // divider below all adapter items
        notiRec.setLayoutManager(layoutManager);
        notiRec.setItemAnimator(new DefaultItemAnimator());
        notiRec.setAdapter(notificationAdapter);


    }
}