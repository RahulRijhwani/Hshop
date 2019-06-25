
package com.hshop.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class AllSearch {

    @SerializedName("status")
    @Expose
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<AllSearchProduct> getData() {
        return data;
    }

    public void setData(List<AllSearchProduct> data) {
        this.data = data;
    }

    @SerializedName("data")
    @Expose
    private List<AllSearchProduct> data = new ArrayList<AllSearchProduct>();



}
