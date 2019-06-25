package com.hshop.adapter;


import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hshop.R;
import com.squareup.picasso.Picasso;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

/**
 * Created by Nilay on 10/18/2017.
 */

public class PhoneViewHolder1 extends ChildViewHolder {
    private TextView od_offer;
    private TextView od_name;
    private TextView od_actual_cost;
    private TextView od_offer_cost;
    private TextView od_unit;
    private TextView od_quntity;
    private TextView od_total;
    private ImageView od_image;
    Context context;
    String sub_id;
    public PhoneViewHolder1(View itemView) {
        super(itemView);
        od_offer = (TextView) itemView.findViewById(R.id.od_offer);
        od_name = (TextView) itemView.findViewById(R.id.od_name);
        od_actual_cost = (TextView) itemView.findViewById(R.id.od_actual_cost);
        od_offer_cost = (TextView) itemView.findViewById(R.id.od_offer_cost);
        od_unit = (TextView) itemView.findViewById(R.id.od_unit);
        od_quntity = (TextView) itemView.findViewById(R.id.od_quntity);
        od_total = (TextView) itemView.findViewById(R.id.od_total);
        od_name = (TextView) itemView.findViewById(R.id.od_name);
        od_image = (ImageView) itemView.findViewById(R.id.od_image);
    }
    public void onBind(final Phone1 phone, ExpandableGroup group) {
        Log.d("fgh", "group - " + String.valueOf(group));
        String full_data = phone.getName();
        String[] seperatr_data = full_data.split("!-");
        od_offer.setText(seperatr_data[3]);
        od_name.setText(seperatr_data[0]);
        od_actual_cost.setText(seperatr_data[1]);
        od_offer_cost.setText(seperatr_data[2]);
        od_unit.setText("Unit :- "+seperatr_data[6]);
        od_quntity.setText(seperatr_data[4]);
        od_total.setText("₹ "+seperatr_data[5]);
        sub_id = seperatr_data[3];
        try {
            Picasso.with(context).load(seperatr_data[7]).into(od_image);
        } catch (Exception e) {
        }
    }
}
