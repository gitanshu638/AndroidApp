package com.example.gowheely;

import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.example.gowheely.Adapter.OfferAdapter;
import com.example.gowheely.Model.OfferModel;

import java.util.ArrayList;
import java.util.List;

import kotlin.DeepRecursiveFunction;

public class MainActivity extends AppCompatActivity {

    ImageView burgerIcon;
    DrawerLayout drawer;

    RecyclerView rvOffers;

    private List<OfferModel> offerModelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        burgerIcon = findViewById(R.id.iv_burgerIcon);
        drawer = findViewById(R.id.drawer_layout);
        burgerIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                drawer.openDrawer(Gravity.LEFT);

            }
        });

        //RecyclerVie for offers starts here
        offerModelList.add(new OfferModel(R.drawable.store, "Rs 500 Cashback", "Get 10% on order above Rs 15000"));
        offerModelList.add(new OfferModel(R.drawable.canned, "Rs 300 Cashback", "Get 15% on order above Rs 5000"));
        offerModelList.add(new OfferModel(R.drawable.beverages, "Rs 200 Cashback", "Get 5% on order above Rs 1000"));
        offerModelList.add(new OfferModel(R.drawable.vegetables, "Rs 100 Cashback", "Get 10% on order above Rs 500"));

        rvOffers = findViewById(R.id.rv_offers);
        OfferAdapter offerAdapter = new OfferAdapter(this,offerModelList);
        rvOffers.setHasFixedSize(true);
        GridLayoutManager manager = new GridLayoutManager(this, 1, RecyclerView.HORIZONTAL, false);
        rvOffers.setLayoutManager(manager);
        rvOffers.setItemAnimator(new DefaultItemAnimator());
        rvOffers.setAdapter(offerAdapter);

    }


    @Override
    public void onBackPressed(){
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(Gravity.LEFT);
        }else {

            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            adb.setTitle("Confirm Exit?");
            adb.setMessage("Are you sure you want to exit?");
            adb.setCancelable(false);

            adb.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            adb.setNegativeButton("No", null);

            AlertDialog alertDialog = adb.create();
            alertDialog.show();
        }
    }
}