
package com.hshop.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class AllHomeSubcategoryList {

    public String getSub_id() {
        return sub_id;
    }

    public void setSub_id(String sub_id) {
        this.sub_id = sub_id;
    }

    public String getSub_name() {
        return sub_name;
    }

    public void setSub_name(String sub_name) {
        this.sub_name = sub_name;
    }

    public String getSub_offer() {
        return sub_offer;
    }

    public void setSub_offer(String sub_offer) {
        this.sub_offer = sub_offer;
    }

    public String getSub_image() {
        return sub_image;
    }

    public void setSub_image(String sub_image) {
        this.sub_image = sub_image;
    }

    public String getSub_description() {
        return sub_description;
    }

    public void setSub_description(String sub_description) {
        this.sub_description = sub_description;
    }

    public String getSub_cat_id() {
        return sub_cat_id;
    }

    public void setSub_cat_id(String sub_cat_id) {
        this.sub_cat_id = sub_cat_id;
    }

    @SerializedName("sub_id")
    @Expose
    private String sub_id;

    @SerializedName("sub_name")
    @Expose
    private String sub_name;

    @SerializedName("sub_offer")
    @Expose
    private String sub_offer;

    @SerializedName("sub_image")
    @Expose
    private String sub_image;

    @SerializedName("sub_description")
    @Expose
    private String sub_description;

    @SerializedName("sub_cat_id")
    @Expose
    private String sub_cat_id;

}
