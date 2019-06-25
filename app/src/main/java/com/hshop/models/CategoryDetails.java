package com.hshop.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nilay on 10/21/2017.
 */

public class CategoryDetails {

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<AllHomeSubcategoryList> getData() {
        return data;
    }

    public void setData(List<AllHomeSubcategoryList> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("data")
    @Expose
    private List<AllHomeSubcategoryList> data = new ArrayList<AllHomeSubcategoryList>();
    @SerializedName("message")
    @Expose
    private String message;

}
