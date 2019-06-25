
package com.hshop.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class AllHome {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("offer")
    @Expose
    private List<AllHomeOffertList> offer = new ArrayList<AllHomeOffertList>();

    @SerializedName("category")
    @Expose
    private List<AllHomeCategoryList> category = new ArrayList<AllHomeCategoryList>();

    @SerializedName("subcategory")
    @Expose
    private List<AllHomeSubcategoryList> subcategory = new ArrayList<AllHomeSubcategoryList>();

    @SerializedName("product")
    @Expose
    private List<AllHomeProductList> product = new ArrayList<AllHomeProductList>();

    public List<AllLatestHomeProductList> getLatestproduct()
    {
        return latestproduct;
    }

    public void setLatestproduct(List<AllLatestHomeProductList> latestproduct) {
        this.latestproduct = latestproduct;
    }

    @SerializedName("latestproduct")
    @Expose
    private List<AllLatestHomeProductList> latestproduct = new ArrayList<AllLatestHomeProductList>();

    public List<AllProductUnitDetailsList> getProductunit() {
        return productunit;
    }

    public void setProductunit(List<AllProductUnitDetailsList> productunit) {
        this.productunit = productunit;
    }

    private List<AllProductUnitDetailsList> productunit = new ArrayList<AllProductUnitDetailsList>();

    @SerializedName("expandcat")
    @Expose
    private List<AllHomeExpandCatList> expandcat = new ArrayList<AllHomeExpandCatList>();

    public List<AllHomeOffertList> getOffer() {
        return offer;
    }

    public void setOffer(List<AllHomeOffertList> offer) {
        this.offer = offer;
    }

    public List<AllHomeCategoryList> getCategory() {
        return category;
    }

    public void setCategory(List<AllHomeCategoryList> category) {
        this.category = category;
    }

    public List<AllHomeSubcategoryList> getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(List<AllHomeSubcategoryList> subcategory) {
        this.subcategory = subcategory;
    }

    public List<AllHomeProductList> getProduct() {
        return product;
    }

    public void setProduct(List<AllHomeProductList> product) {
        this.product = product;
    }

    public List<AllHomeExpandCatList> getExpandcat() {
        return expandcat;
    }

    public void setExpandcat(List<AllHomeExpandCatList> expandcat) {
        this.expandcat = expandcat;
    }

    /**
     * @return The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return The data
     */



}
