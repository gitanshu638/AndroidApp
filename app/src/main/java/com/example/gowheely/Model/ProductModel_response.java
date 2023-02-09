package com.example.gowheely.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductModel_response {
    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("weight")
    @Expose
    private String weight;
    @SerializedName("product_code")
    @Expose
    private String productCode;
    @SerializedName("cost")
    @Expose
    private String cost;
    @SerializedName("quantity")
    @Expose
    private String quantity;
    @SerializedName("category_id")
    @Expose
    private String categoryId;
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getWeight() {
        return weight;
    }
    public void setWeight(String weight) {
        this.weight = weight;
    }
    public String getProductCode() {
        return productCode;
    }
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
    public String getCost() {
        return cost;
    }
    public void setCost(String cost) {
        this.cost = cost;
    }
    public String getQuantity() {
        return quantity;
    }
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    public String getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
