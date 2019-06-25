package com.hshop.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hshop.R;
import com.squareup.picasso.Picasso;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

/**
 * Created by Nilay on 10/18/2017.
 */

public class OSViewHolder extends GroupViewHolder    {

    private TextView h_e_cname;
    private TextView h_e_description;
    private TextView h_e_offer;
    private ImageView h_e_arrow;
    private ImageView h_e_image;
    private CardView h_e_card;
    Context context;
    public View itm;
    public boolean exch=false;




    public OSViewHolder(View itemView) {
        super(itemView);

        h_e_cname = (TextView) itemView.findViewById(R.id.h_e_cname);
        h_e_description = (TextView) itemView.findViewById(R.id.h_e_description);
        h_e_offer = (TextView) itemView.findViewById(R.id.h_e_offer);
        h_e_arrow = (ImageView) itemView.findViewById(R.id.h_e_arrow);
        h_e_image = (ImageView) itemView.findViewById(R.id.h_e_image);
        h_e_card = (CardView) itemView.findViewById(R.id.card_view);

    }

    @Override
    public void expand() {
        h_e_arrow.setBackgroundResource(R.drawable.ic_arrow_down);
        h_e_card.setBackgroundResource(R.color.bg4);
        exch=false;
        // h_e_arrow.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_down_arrow, 0);
        Log.i("Adapter", "expand");
        //collapse();
    }

    @Override
    public void collapse() {

        if(exch==true) {
            OSViewHolder osv=new OSViewHolder(itm);
            osv.expand();
        }
        Log.i("Adapter", "collapse");
        exch=true;
        itm = itemView;
        h_e_arrow.setBackgroundResource(R.drawable.ic_arrow_up);
        h_e_card.setBackgroundResource(R.color.bg9);
        //h_e_cname.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_up_arrow, 0);
    }

    public void setGroupName(ExpandableGroup group) {
        String full_data = group.getTitle();
        String[] seperatr_data = full_data.split("!-");
        h_e_cname.setText(seperatr_data[0]);
        h_e_description.setText(Html.fromHtml(seperatr_data[1]));
        h_e_offer.setText(seperatr_data[2]);
        // Glide.with(context).load(seperatr_data[3]).into(h_e_image);
         try
         {
              Picasso.with(itemView.getContext()).load(seperatr_data[3]).into(h_e_image);
              Log.d("fgh",seperatr_data[3]);
         }
         catch (Exception e)
         {
             Log.d("fgh",seperatr_data[3]);
             Log.d("fgh",e.toString());
         }
    }
}
