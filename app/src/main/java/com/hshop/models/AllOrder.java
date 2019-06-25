
package com.hshop.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class AllOrder {

    @SerializedName("status")
    @Expose
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<AllExpandOrderList> getUserorder() {
        return userorder;
    }

    public void setUserorder(List<AllExpandOrderList> userorder) {
        this.userorder = userorder;
    }

    @SerializedName("userorder")
    @Expose
    private List<AllExpandOrderList> userorder = new ArrayList<AllExpandOrderList>();






}
