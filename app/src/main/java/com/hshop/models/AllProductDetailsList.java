
package com.hshop.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class AllProductDetailsList {


    public String getQtyadd() {
        return qtyadd;
    }

    public void setQtyadd(String qtyadd) {
        this.qtyadd = qtyadd;
    }

    String qtyadd;

    public String getPro_actualcost() {
        return pro_actualcost;
    }

    public void setPro_actualcost(String pro_actualcost) {
        this.pro_actualcost = pro_actualcost;
    }

    public String getPro_description() {
        return pro_description;
    }

    public void setPro_description(String pro_description) {
        this.pro_description = pro_description;
    }

    public String getPro_disclaimer() {
        return pro_disclaimer;
    }

    public void setPro_disclaimer(String pro_disclaimer) {
        this.pro_disclaimer = pro_disclaimer;
    }

    public String getPro_features() {
        return pro_features;
    }

    public void setPro_features(String pro_features) {
        this.pro_features = pro_features;
    }

    public String getPro_id() {
        return pro_id;
    }

    public void setPro_id(String pro_id) {
        this.pro_id = pro_id;
    }

    public String getPro_image() {
        return pro_image;
    }

    public void setPro_image(String pro_image) {
        this.pro_image = pro_image;
    }

    public String getPro_name() {
        return pro_name;
    }

    public void setPro_name(String pro_name) {
        this.pro_name = pro_name;
    }


    public String getCart_val() {
        return cart_val;
    }

    public void setCart_val(String cart_val) {
        this.cart_val = cart_val;
    }

    String cart_val;

    public String getPro_offer() {
        return pro_offer;
    }

    public void setPro_offer(String pro_offer) {
        this.pro_offer = pro_offer;
    }

    public String getPro_offercost() {
        return pro_offercost;
    }

    public void setPro_offercost(String pro_offercost) {
        this.pro_offercost = pro_offercost;
    }

    public String getPro_rating() {
        return pro_rating;
    }

    public void setPro_rating(String pro_rating) {
        this.pro_rating = pro_rating;
    }

    public String getPro_unit() {
        return pro_unit;
    }

    public void setPro_unit(String pro_unit) {
        this.pro_unit = pro_unit;
    }

    @SerializedName("pro_actualcost")
    @Expose
    private String pro_actualcost;

    @SerializedName("pro_description")
    @Expose
    private String pro_description;

    @SerializedName("pro_disclaimer")
    @Expose
    private String pro_disclaimer;

    @SerializedName("pro_features")
    @Expose
    private String pro_features;

    @SerializedName("pro_id")
    @Expose
    private String pro_id;

    @SerializedName("pro_image")
    @Expose
    private String pro_image;

    @SerializedName("pro_name")
    @Expose
    private String pro_name;

    @SerializedName("pro_offer")
    @Expose
    private String pro_offer;

    @SerializedName("pro_offercost")
    @Expose
    private String pro_offercost;

    @SerializedName("pro_rating")
    @Expose
    private String pro_rating;

    @SerializedName("pro_unit")
    @Expose
    private String pro_unit;

    public String getPro_store() {
        return pro_store;
    }

    public void setPro_store(String pro_store) {
        this.pro_store = pro_store;
    }

    @SerializedName("pro_store")
    @Expose
    private String pro_store;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @SerializedName("user_id")
    @Expose
    private String user_id;




}
