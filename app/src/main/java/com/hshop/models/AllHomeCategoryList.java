
package com.hshop.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class AllHomeCategoryList {

    @SerializedName("cat_id")
    @Expose
    private String cat_id;

    @SerializedName("cat_name")
    @Expose
    private String cat_name;

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public String getCat_offer() {
        return cat_offer;
    }

    public void setCat_offer(String cat_offer) {
        this.cat_offer = cat_offer;
    }

    public String getCat_image() {
        return cat_image;
    }

    public void setCat_image(String cat_image) {
        this.cat_image = cat_image;
    }

    public String getCat_description() {
        return cat_description;
    }

    public void setCat_description(String cat_description) {
        this.cat_description = cat_description;
    }

    @SerializedName("cat_offer")
    @Expose
    private String cat_offer;

    @SerializedName("cat_image")
    @Expose
    private String cat_image;

    @SerializedName("cat_description")
    @Expose
    private String cat_description;



}
