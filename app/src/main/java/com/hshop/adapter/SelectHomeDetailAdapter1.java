package com.hshop.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;
import com.hshop.R;
import com.hshop.models.AllHomeOffertList;
import com.bumptech.glide.Glide;
import com.hshop.shopping.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SelectHomeDetailAdapter1 extends RecyclerView.Adapter<SelectHomeDetailAdapter1.GmailVH> {
    Context context;
    OnItemClickListener clickListener;
    List<AllHomeOffertList> getallHomeAllOfferLists;

    public SelectHomeDetailAdapter1(Context context, List<AllHomeOffertList> getallHomeAllOfferLists) {
        this.context = context;
        this.getallHomeAllOfferLists = getallHomeAllOfferLists;
    }
    @Override
    public GmailVH onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_home4, viewGroup, false);
        return new GmailVH(view);
    }
    @Override
    public void onBindViewHolder(final GmailVH gmailVH, final int i) {
        //  gmailVH.title.setText("Sample Test");
        Resources res = context.getResources();
      //  String text2 = String.format(res.getString(R.string.txt_message22), "Examiner", "58 Posts", "Rs. 9300-34800/- grade Pay: Rs .4600/-", "Degree in law", "30 Years", "2016-03-31");
        String text1 = String.format(res.getString(R.string.txt_message223), getallHomeAllOfferLists.get(i).getOff_image());
        final String text2 = String.format(res.getString(R.string.txt_message223), getallHomeAllOfferLists.get(i).getOff_name());
        final String id=String.format(res.getString(R.string.txt_message223), getallHomeAllOfferLists.get(i).getOff_id());

        CharSequence styledText1 = Html.fromHtml(text1);
        CharSequence styledText2 = Html.fromHtml(text2);

        gmailVH.h_p_offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context,Product.class).putExtra("offer",id).putExtra("offer_name",text2));
            }
        });


        try
        {
            Picasso.with(context).load(getallHomeAllOfferLists.get(i).getOff_image()).resize(600, 200).into(gmailVH.h_p_offer);
        }
        catch (Exception e)
        {

        }


    }
    @Override
    public int getItemCount() {
        return getallHomeAllOfferLists == null ? 0 : getallHomeAllOfferLists.size();
    }
    class GmailVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView h_p_offer;

        public GmailVH(View itemView) {

            super(itemView);

            h_p_offer = (ImageView) itemView.findViewById(R.id.offer_name);
            h_p_offer.setOnClickListener(this);

        }
        @Override
        public void onClick(View v) {
            clickListener.onItemClick(v, getAdapterPosition());
        }
    }
    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }
    public void SetOnItemClickListener(final OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }
}


