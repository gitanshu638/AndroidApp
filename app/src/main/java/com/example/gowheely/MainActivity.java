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
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.example.gowheely.Adapter.CategoryAdapter;
import com.example.gowheely.Adapter.DealsAdapter;
import com.example.gowheely.Adapter.OfferAdapter;
import com.example.gowheely.Adapter.SliderAdapter;
import com.example.gowheely.Model.CategoryModel;
import com.example.gowheely.Model.DealsModel;
import com.example.gowheely.Model.OfferModel;
import com.example.gowheely.Model.SliderModel;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.android.material.slider.Slider;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import kotlin.DeepRecursiveFunction;

public class MainActivity extends AppCompatActivity {

    ImageView burgerIcon;
    DrawerLayout drawer;

    RecyclerView rvOffers, rvCategories, rvDeals;

    private List<OfferModel> offerModelList = new ArrayList<>();
    private List<DealsModel> dealsModelList = new ArrayList<>();

    com.smarteist.autoimageslider.SliderView sliderView;

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


        //Image slider
        SliderModel[] sliderModels=new SliderModel[]{
                new SliderModel(R.drawable.canned),
                new SliderModel(R.drawable.vegetables),
                new SliderModel(R.drawable.store),
                new SliderModel(R.drawable.beverages),
                new SliderModel(R.drawable.fruits),
        };

        sliderView = findViewById(R.id.imageSlider);
        SliderAdapter sliderAdapter = new SliderAdapter(sliderModels, this);
        sliderView.setAutoCycleDirection(com.smarteist.autoimageslider.SliderView.LAYOUT_DIRECTION_LTR);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setAutoCycle(true);
        sliderView.setScrollTimeInSec(4);
        sliderView.startAutoCycle();

        //Category Products starts here

        List<CategoryModel> categoryModelList = new ArrayList<>();
        categoryModelList.add(new CategoryModel(R.drawable.store, "Store"));
        categoryModelList.add(new CategoryModel(R.drawable.vegetables, "Vegetables"));
        categoryModelList.add(new CategoryModel(R.drawable.fruits, "Fruits"));
        categoryModelList.add(new CategoryModel(R.drawable.canned, "Canned Products"));
        categoryModelList.add(new CategoryModel(R.drawable.beverages, "Beverages"));
        categoryModelList.add(new CategoryModel(R.drawable.dairy, "Dairy Products"));

        rvCategories = findViewById(R.id.rv_categories);
        CategoryAdapter categoryAdapter = new CategoryAdapter(this,categoryModelList);
        rvCategories.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1, RecyclerView.HORIZONTAL, false);
        rvCategories.setLayoutManager(layoutManager);
        rvCategories.setItemAnimator(new DefaultItemAnimator());
        rvCategories.setAdapter(categoryAdapter);


        dealsModelList.add(new DealsModel(R.drawable.store, "Store", "Premium","3 Days","Rs 399", "Rs 499","-25%"));
        dealsModelList.add(new DealsModel(R.drawable.vegetables, "Vegetables", "Premium","3 Days","Rs 399", "Rs 499","-5%"));
        dealsModelList.add(new DealsModel(R.drawable.fruits, "Fruits", "Premium","3 Days","Rs 399", "Rs 499","-10%"));
        dealsModelList.add(new DealsModel(R.drawable.beverages, "Beverages", "Premium","3 Days","Rs 399", "Rs 499","-15%"));
        dealsModelList.add(new DealsModel(R.drawable.canned, "Canned", "Premium","3 Days","Rs 399", "Rs 499","-10%"));
        dealsModelList.add(new DealsModel(R.drawable.dairy, "Dairy", "Premium","3 Days","Rs 399", "Rs 499","-20%"));



        rvDeals = findViewById(R.id.rv_deals);
        DealsAdapter dealsAdapter = new DealsAdapter(this,dealsModelList);
        rvDeals.setHasFixedSize(true);
        GridLayoutManager manager1 = new GridLayoutManager(this, 1, RecyclerView.VERTICAL, false);
        rvDeals.setLayoutManager(manager1);
        rvDeals.setItemAnimator(new DefaultItemAnimator());
        rvDeals.setAdapter(dealsAdapter);

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

    private class SliderView {
    }
}