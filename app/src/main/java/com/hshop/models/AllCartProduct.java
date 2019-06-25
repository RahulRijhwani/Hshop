
package com.hshop.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class AllCartProduct {

    public String getQtyadd() {
        return qtyadd;
    }

    public void setQtyadd(String qtyadd) {
        this.qtyadd = qtyadd;
    }

    String qtyadd;

    @SerializedName("ode_id")
    @Expose
    private String ode_id;

    @SerializedName("ode_pro_id")
    @Expose
    private String ode_pro_id;

    @SerializedName("pro_name")
    @Expose
    private String pro_name;

    @SerializedName("ode_actual_cost")
    @Expose
    private String ode_actual_cost;

    @SerializedName("ode_offer_cost")
    @Expose
    private String ode_offer_cost;

    @SerializedName("ode_offer")
    @Expose
    private String ode_offer;

    @SerializedName("ode_quantity")
    @Expose
    private String ode_quantity;

    @SerializedName("ode_total")
    @Expose
    private String ode_total;

    @SerializedName("pro_unit")
    @Expose
    private String pro_unit;

    public String getOde_id() {
        return ode_id;
    }

    public void setOde_id(String ode_id) {
        this.ode_id = ode_id;
    }

    public String getOde_pro_id() {
        return ode_pro_id;
    }

    public void setOde_pro_id(String ode_pro_id) {
        this.ode_pro_id = ode_pro_id;
    }

    public String getPro_name() {
        return pro_name;
    }

    public void setPro_name(String pro_name) {
        this.pro_name = pro_name;
    }

    public String getOde_actual_cost() {
        return ode_actual_cost;
    }

    public void setOde_actual_cost(String ode_actual_cost) {
        this.ode_actual_cost = ode_actual_cost;
    }

    public String getOde_offer_cost() {
        return ode_offer_cost;
    }

    public void setOde_offer_cost(String ode_offer_cost) {
        this.ode_offer_cost = ode_offer_cost;
    }

    public String getOde_offer() {
        return ode_offer;
    }

    public void setOde_offer(String ode_offer) {
        this.ode_offer = ode_offer;
    }

    public String getOde_quantity() {
        return ode_quantity;
    }

    public void setOde_quantity(String ode_quantity) {
        this.ode_quantity = ode_quantity;
    }

    public String getOde_total() {
        return ode_total;
    }

    public void setOde_total(String ode_total) {
        this.ode_total = ode_total;
    }

    public String getPro_unit() {
        return pro_unit;
    }

    public void setPro_unit(String pro_unit) {
        this.pro_unit = pro_unit;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @SerializedName("user_id")
    @Expose
    private String user_id;

    public String getPro_image() {
        return pro_image;
    }

    public void setPro_image(String pro_image) {
        this.pro_image = pro_image;
    }

    @SerializedName("pro_image")
    @Expose
    private String pro_image;

    public String getOde_tpd_id() {
        return ode_tpd_id;
    }

    public void setOde_tpd_id(String ode_tpd_id) {
        this.ode_tpd_id = ode_tpd_id;
    }

    @SerializedName("ode_tpd_id")
    @Expose
    private String ode_tpd_id;




}
