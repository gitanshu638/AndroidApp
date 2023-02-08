package com.example.gowheely.service;

import com.example.gowheely.Model.CategoryModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API_interface {

    @GET("/category")
    Call<CategoryModel> callCatgories();

    Call<CategoryModel> doCallCategories();
}