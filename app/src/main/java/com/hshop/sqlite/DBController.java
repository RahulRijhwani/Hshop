package com.hshop.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBController extends SQLiteOpenHelper {

    Context context;

    public DBController(Context context) {
        super(context, "hshopcoi_hshop.db", null, 1);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
      /*  String tbl_subcategory,tbl_category,tbl_product,tbl_product_details;

        tbl_category = "CREATE TABLE `tbl_category` (`cat_id` int(22) NOT NULL ,`cat_cit_id` int(22) NOT NULL,`cat_name` varchar(255) NOT NULL,`cat_offer` varchar(255) NOT NULL,`cat_image` varchar(255) NOT NULL,`cat_description` text NOT NULL,`cat_position` int(22) NOT NULL,`cat_status` enum('Active','Inactive') NOT NULL,`cat_admin_id` int(22) NOT NULL,`cat_createdate` datetime NOT NULL,`cat_update` datetime NOT NULL,PRIMARY KEY (`cat_id`))";
        tbl_subcategory="CREATE TABLE `tbl_subcategory` (`sub_id` int(22) NOT NULL , `sub_cit_id` int(22) NOT NULL, `sub_cat_id` int(22) NOT NULL, `sub_name` varchar(255) NOT NULL, `sub_offer` varchar(255) NOT NULL, `sub_image` varchar(255) NOT NULL, `sub_description` text NOT NULL, `sub_position` int(22) NOT NULL, `sub_status` enum('Active','Inactive') NOT NULL, `sub_admin_id` int(22) NOT NULL, `sub_createdate` datetime NOT NULL, `sub_update` datetime NOT NULL, `sub_poster` varchar(255) NOT NULL, PRIMARY KEY (`sub_id`))";
        tbl_product="CREATE TABLE `tbl_product` ( `pro_id` int(22) NOT NULL AUTO_INCREMENT, `f_id` int(11) DEFAULT NULL, `pro_cit_id` int(22) NOT NULL, `pro_cat_id` int(22) NOT NULL, `pro_subcat_id` int(22) NOT NULL, `pro_name` varchar(255) NOT NULL, `pro_image` varchar(255) NOT NULL, `pro_rating` varchar(255) NOT NULL, `pro_description` text NOT NULL, `pro_features` text NOT NULL, `pro_disclaimer` text NOT NULL, `pro_position` int(22) NOT NULL, `pro_status` enum('Active','Inactive') NOT NULL, `pro_admin_id` int(22) NOT NULL, `pro_createdate` datetime NOT NULL, `pro_update` datetime NOT NULL, PRIMARY KEY (`pro_id`))";
        tbl_product_details="CREATE TABLE `tbl_product_details` ( `tpd_id` int(11) NOT NULL AUTO_INCREMENT, `tpd_pro_id` int(11) NOT NULL, `tpd_image` varchar(255) NOT NULL, `tpd_actual_cost` double NOT NULL, `tpd_offer_cost` double NOT NULL, `tpd_offer` double NOT NULL, `tpd_unit` varchar(255) NOT NULL, `tpd_status` enum('Active','Inactive') NOT NULL, `tpd_admin_by` int(22) NOT NULL, `tpd_createdate` datetime NOT NULL, `tpd_update` datetime NOT NULL, PRIMARY KEY (`tpd_id`))";

        db.execSQL(tbl_category);
        db.execSQL(tbl_subcategory);
        db.execSQL(tbl_product);
        db.execSQL(tbl_product_details);
*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
