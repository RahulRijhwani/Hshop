package com.hshop.adapter;


import android.content.Context;
import android.text.Html;
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

public class PhoneViewHolder extends ChildViewHolder {
    private TextView h_e_s_name;
    private TextView h_e_s_offer;
    private TextView h_e_s_details;
    private ImageView h_e_s_image;
    Context context;
    String sub_id;
    public PhoneViewHolder(View itemView) {
        super(itemView);
        h_e_s_name = (TextView) itemView.findViewById(R.id.h_e_s_name);
       // h_e_s_offer = (TextView) itemView.findViewById(R.id.h_e_s_offer);
        //h_e_s_details = (TextView) itemView.findViewById(R.id.h_e_s_details);
        h_e_s_image = (ImageView) itemView.findViewById(R.id.h_e_s_image);
    }
    public void onBind(final Phone phone, ExpandableGroup group) {
        Log.d("fgh", "group - "+String.valueOf(group));
        String full_data = phone.getName();
        String[] seperatr_data = full_data.split("!-");
        h_e_s_name.setText(seperatr_data[0]);
       // h_e_s_offer.setText(seperatr_data[2]);
        try
        {
            h_e_s_details.setText(Html.fromHtml(seperatr_data[4]));
        }
        catch (Exception e)
        {

        }

        sub_id = seperatr_data[3];
        Log.d("fgh",sub_id);
        try
        {
            Picasso.with(context).load(seperatr_data[1]).into(h_e_s_image);
        }
        catch (Exception e)
        {
        }
    }
}
