package com.hshop.data;

/**
 * Created by awidiyadew on 12/09/16.
 */
public class BaseItem {
    private String mName,mId;

    public BaseItem(String name,String id) {
        mName = name;
        mId=id;
    }

    public String getName() {
        return mName;
    }
    public String getId() {
        return mId;
    }
}
