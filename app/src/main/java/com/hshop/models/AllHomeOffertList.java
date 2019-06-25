
package com.hshop.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class AllHomeOffertList {

    @SerializedName("off_id")
    @Expose
    private String off_id;

    @SerializedName("off_image")
    @Expose
    private String off_image;

    @SerializedName("off_name")
    @Expose
    private String off_name;

    @SerializedName("off_description")
    @Expose
    private String off_description;

    /*@SerializedName("off_sub_id")
    @Expose
    private String off_sub_id;

    public String getSub_name() {
        return sub_name;
    }

    public void setSub_name(String sub_name) {
        this.sub_name = sub_name;
    }

    @SerializedName("sub_name")
    @Expose
    private String sub_name;

    public String getOff_sub_id() {
        return off_sub_id;
    }

    public void setOff_sub_id(String off_sub_id) {
        this.off_sub_id = off_sub_id;
    }
*/
    public String getOff_id() {
        return off_id;
    }

    public void setOff_id(String off_id) {
        this.off_id = off_id;
    }

    public String getOff_image() {
        return off_image;
    }

    public void setOff_image(String off_image) {
        this.off_image = off_image;
    }

    public String getOff_name() {
        return off_name;
    }

    public void setOff_name(String off_name) {
        this.off_name = off_name;
    }

    public String getOff_description() {
        return off_description;
    }

    public void setOff_description(String off_description) {
        this.off_description = off_description;
    }
}
