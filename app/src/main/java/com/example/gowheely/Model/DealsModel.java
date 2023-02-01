package com.example.gowheely.Model;

public class DealsModel {
    private int dealsImage;
    private String dealsTitle;
    private String dealsType;
    private String dealsStorageLife;
    private String discountedPrice;
    private String dealsOriginalPrice;
    private String dealsDiscountPercentage;

    public DealsModel(int dealsImage, String dealsTitle, String dealsType, String dealsStorageLife, String discountedPrice, String dealsOriginalPrice, String dealsDiscountPercentage) {
        this.dealsImage = dealsImage;
        this.dealsTitle = dealsTitle;
        this.dealsType = dealsType;
        this.dealsStorageLife = dealsStorageLife;
        this.discountedPrice = discountedPrice;
        this.dealsOriginalPrice = dealsOriginalPrice;
        this.dealsDiscountPercentage = dealsDiscountPercentage;
    }

    public int getDealsImage() {
        return dealsImage;
    }

    public void setDealsImage(int dealsImage) {
        this.dealsImage = dealsImage;
    }

    public String getDealsTitle() {
        return dealsTitle;
    }

    public void setDealsTitle(String dealsTitle) {
        this.dealsTitle = dealsTitle;
    }

    public String getDealsType() {
        return dealsType;
    }

    public void setDealsType(String dealsType) {
        this.dealsType = dealsType;
    }

    public String getDealsStorageLife() {
        return dealsStorageLife;
    }

    public void setDealsStorageLife(String dealsStorageLife) {
        this.dealsStorageLife = dealsStorageLife;
    }

    public String getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(String discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public String getDealsOriginalPrice() {
        return dealsOriginalPrice;
    }

    public void setDealsOriginalPrice(String dealsOriginalPrice) {
        this.dealsOriginalPrice = dealsOriginalPrice;
    }

    public String getDealsDiscountPercentage() {
        return dealsDiscountPercentage;
    }

    public void setDealsDiscountPercentage(String dealsDiscountPercentage) {
        this.dealsDiscountPercentage = dealsDiscountPercentage;
    }
}
