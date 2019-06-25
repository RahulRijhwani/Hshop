package com.hshop.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Nilay on 10/21/2017.
 */

public class ProductNameList {

    public String getPro_name() {
        return pro_name;
    }

    public void setPro_name(String pro_name) {
        this.pro_name = pro_name;
    }

    @SerializedName("pro_name")
    @Expose
    private String pro_name;

}
