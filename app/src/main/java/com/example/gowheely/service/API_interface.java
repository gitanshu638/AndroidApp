package com.example.gowheely.service;

import com.example.gowheely.Model.CategoryModel;
import com.example.gowheely.Model.ProductModel_response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface API_interface {

    @POST("/product")
    Call<ProductModel_response> callProducts();

    @GET("/product")
    Call<ProductModel_response> callGetAllProducts();

    @GET("/product/{id}")
    Call<ProductModel_response> callAetIdProducts();

    @PUT("/product/{id}")
    Call<ProductModel_response> callPutIdProducts();
}