
package com.hshop.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class AllCart {

    @SerializedName("status")
    @Expose
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getSaving() {
        return saving;
    }

    public void setSaving(String saving) {
        this.saving = saving;
    }

    public String getSavingoffer() {
        return savingoffer;
    }

    public void setSavingoffer(String savingoffer) {
        this.savingoffer = savingoffer;
    }

    public List<AllCartProduct> getProduct() {
        return product;
    }

    public void setProduct(List<AllCartProduct> product) {
        this.product = product;
    }

    public String getShipping() {
        return shipping;
    }

    public void setShipping(String shipping) {
        this.shipping = shipping;
    }

    public String getTotal_shipping_cost() {
        return total_shipping_cost;
    }

    public void setTotal_shipping_cost(String total_shipping_cost) {
        this.total_shipping_cost = total_shipping_cost;
    }

    @SerializedName("shipping")
    @Expose
    private String shipping;

    @SerializedName("total_shipping_cost")
    @Expose
    private String total_shipping_cost;

    @SerializedName("total")
    @Expose
    private String total;

    @SerializedName("saving")
    @Expose
    private String saving;

    @SerializedName("savingoffer")
    @Expose
    private String savingoffer;

    @SerializedName("product")
    @Expose
    private List<AllCartProduct> product = new ArrayList<AllCartProduct>();



}
