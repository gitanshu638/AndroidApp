package com.example.gowheely.Model;

public class CouponModel {
    private String id;
    private String coupon_code;
    private String description;
    private String min_value;
    private String valid_to;
    private String discount_value;

    public CouponModel(String id, String coupon_code, String description, String min_value, String valid_to, String discount_value) {
        this.id = id;
        this.coupon_code = coupon_code;
        this.description = description;
        this.min_value = min_value;
        this.valid_to = valid_to;
        this.discount_value = discount_value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCoupon_code() {
        return coupon_code;
    }

    public void setCoupon_code(String coupon_code) {
        this.coupon_code = coupon_code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMin_value() {
        return min_value;
    }

    public void setMin_value(String min_value) {
        this.min_value = min_value;
    }

    public String getValid_to() {
        return valid_to;
    }

    public void setValid_to(String valid_to) {
        this.valid_to = valid_to;
    }

    public String getDiscount_value() {
        return discount_value;
    }

    public void setDiscount_value(String discount_value) {
        this.discount_value = discount_value;
    }
}
