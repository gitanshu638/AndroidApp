package com.example.gowheely;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.gowheely.Model.CategoryModel;
import com.example.gowheely.service.API_client;
import com.example.gowheely.service.API_interface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home_page extends AppCompatActivity {
    private API_interface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
    }
}
//        call categoryAPI
//        apiInterface = API_client.getClient().create(API_interface.class);        /**         GET List Resources         **/
//        Call<CategoryModel> call = apiInterface.doCallCategories();
//        call.enqueue(new Callback<CategoryModel>() {
//            @Override
//            public void onResponse(Call<CategoryModel> call, Response<CategoryModel> response) {
//                Log.d("TAG", response.code() + "");
//                String displayResponse = "";
//                CategoryModel resource = response.body();
//                Integer text = resource.page;
//                Integer total = resource.total;
//                Integer totalPages = resource.totalPages;
//                List<CategoryModel.Datum> datumList = resource.data;
//                displayResponse += text + " Page\n" + total + " Total\n" + totalPages + " Total Pages\n";
//                for (CategoryModel.Datum datum : datumList) {
//                    displayResponse += datum.id + " " + datum.name + " " + datum.pantoneValue + " " + datum.year + "\n";
//                }               // responseText.setText(displayResponse);            }            @Override            public void onFailure(Call<CategoryModel> call, Throwable t) {                call.cancel();            }        });    }}

