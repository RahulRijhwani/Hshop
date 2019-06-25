
package com.hshop.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class AllProductSubCategoryUnitDetailsList {

    public String getTpd_image() {
        return tpd_image;
    }

    public void setTpd_image(String tpd_image) {
        this.tpd_image = tpd_image;
    }

    String tpd_image;

    public String getTpd_id() {
        return tpd_id;
    }

    public void setTpd_id(String tpd_id) {
        this.tpd_id = tpd_id;
    }

    public String getTpd_pro_id() {
        return tpd_pro_id;
    }

    public void setTpd_pro_id(String tpd_pro_id) {
        this.tpd_pro_id = tpd_pro_id;
    }

    public String getTpd_actual_cost() {
        return tpd_actual_cost;
    }

    public void setTpd_actual_cost(String tpd_actual_cost) {
        this.tpd_actual_cost = tpd_actual_cost;
    }

    public String getTpd_offer_cost() {
        return tpd_offer_cost;
    }

    public void setTpd_offer_cost(String tpd_offer_cost) {
        this.tpd_offer_cost = tpd_offer_cost;
    }

    public String getTpd_offer() {
        return tpd_offer;
    }

    public void setTpd_offer(String tpd_offer) {
        this.tpd_offer = tpd_offer;
    }

    public String getTpd_unit() {
        return tpd_unit;
    }

    public void setTpd_unit(String tpd_unit) {
        this.tpd_unit = tpd_unit;
    }

    @SerializedName("tpd_id")
    @Expose
    private String tpd_id;

    @SerializedName("tpd_pro_id")
    @Expose
    private String tpd_pro_id;

    @SerializedName("tpd_actual_cost")
    @Expose
    private String tpd_actual_cost;

    @SerializedName("tpd_offer_cost")
    @Expose
    private String tpd_offer_cost;
    @SerializedName("tpd_offer")
    @Expose
    private String tpd_offer;

    @SerializedName("tpd_unit")
    @Expose
    private String tpd_unit;





}
