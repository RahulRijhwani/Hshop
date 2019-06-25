
package com.hshop.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class AllExpandOrderList {

    public String getOrd_id() {
        return ord_id;
    }

    public void setOrd_id(String ord_id) {
        this.ord_id = ord_id;
    }

    public String getOrd_totalcost() {
        return ord_totalcost;
    }

    public void setOrd_totalcost(String ord_totalcost) {
        this.ord_totalcost = ord_totalcost;
    }

    public String getOrd_totalquntity() {
        return ord_totalquntity;
    }

    public void setOrd_totalquntity(String ord_totalquntity) {
        this.ord_totalquntity = ord_totalquntity;
    }

    public String getOrd_date() {
        return ord_date;
    }

    public void setOrd_date(String ord_date) {
        this.ord_date = ord_date;
    }

    public String getOrd_status() {
        return ord_status;
    }

    public void setOrd_status(String ord_status) {
        this.ord_status = ord_status;
    }

    public List<AllExpandOrderProductList> getOrderdetails() {
        return orderdetails;
    }

    public void setOrderdetails(List<AllExpandOrderProductList> orderdetails) {
        this.orderdetails = orderdetails;
    }

    @SerializedName("ord_id")
    @Expose
    private String ord_id;

    @SerializedName("ord_totalcost")
    @Expose
    private String ord_totalcost;

    @SerializedName("ord_totalquntity")
    @Expose
    private String ord_totalquntity;

    @SerializedName("ord_date")
    @Expose
    private String ord_date;

    @SerializedName("ord_status")
    @Expose
    private String ord_status;


    @SerializedName("orderdetails")
    @Expose
    private List<AllExpandOrderProductList> orderdetails = new ArrayList<AllExpandOrderProductList>();



}
