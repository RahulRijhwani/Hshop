
package com.hshop.models;

import java.util.HashMap;
import java.util.Map;

public class UserActiveInfo {

    private String status;
    private Integer data;
    private String message;
    private String offerposter;

    public String getOfferposterid() {
        return offerposterid;
    }

    public void setOfferposterid(String offerposterid) {
        this.offerposterid = offerposterid;
    }

    private String offerposterid;

    public String getOfferpostername() {
        return offerpostername;
    }

    public void setOfferpostername(String offerpostername) {
        this.offerpostername = offerpostername;
    }

    private String offerpostername;


    public String getOfferposter() {
        return offerposter;
    }

    public void setOfferposter(String offerposter) {
        this.offerposter = offerposter;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 
     * @return
     *     The data
     */
    public Integer getData() {
        return data;
    }

    /**
     * 
     * @param data
     *     The data
     */
    public void setData(Integer data) {
        this.data = data;
    }

    /**
     * 
     * @return
     *     The message
     */
    public String getMessage() {
        return message;
    }

    /**
     * 
     * @param message
     *     The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
