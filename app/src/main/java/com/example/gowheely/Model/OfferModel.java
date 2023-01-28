package com.example.gowheely.Model;

public class OfferModel {

    // IMage from drawable
    private int offerImage;
    private String offerPrice;
    private String offerConstraint;

    public OfferModel(int offerImage, String offerPrice, String offerConstraint) {
        this.offerImage = offerImage;
        this.offerPrice = offerPrice;
        this.offerConstraint = offerConstraint;
    }

    public int getOfferImage() {
        return offerImage;
    }

    public void setOfferImage(int offerImage) {
        this.offerImage = offerImage;
    }

    public String getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(String offerPrice) {
        this.offerPrice = offerPrice;
    }

    public String getOfferConstraint() {
        return offerConstraint;
    }

    public void setOfferConstraint(String offerConstraint) {
        this.offerConstraint = offerConstraint;
    }
}
