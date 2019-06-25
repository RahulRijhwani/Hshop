
package com.hshop.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class AllSearchProduct {


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

    public String getPro_unit() {
        return pro_unit;
    }

    public void setPro_unit(String pro_unit) {
        this.pro_unit = pro_unit;
    }

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

    @SerializedName("pro_unit")
    @Expose
    private String pro_unit;

    public String getPro_actualcost() {
        return pro_actualcost;
    }

    public void setPro_actualcost(String pro_actualcost) {
        this.pro_actualcost = pro_actualcost;
    }

    @SerializedName("pro_actualcost")
    @Expose
    private String pro_actualcost;


}
