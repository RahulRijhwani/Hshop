package com.hshop.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hshop.R;
import com.hshop.models.AllHomeExpandCatList;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SelectHomeExpandAdapter2 extends RecyclerView.Adapter<SelectHomeExpandAdapter2.GmailVH> {
    Context context;
    OnItemClickListener clickListener;
    List<AllHomeExpandCatList> getallHomeAllExpandLists;

    public SelectHomeExpandAdapter2(Context context, List<AllHomeExpandCatList> getallHomeAllExpandLists) {
        this.context = context;
        this.getallHomeAllExpandLists = getallHomeAllExpandLists;
    }
    @Override
    public GmailVH onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_home1, viewGroup, false);
        return new GmailVH(view);
    }
    @Override
    public void onBindViewHolder(final GmailVH gmailVH, final int i) {
        //  gmailVH.title.setText("Sample Test");
        Resources res = context.getResources();
      //  String text2 = String.format(res.getString(R.string.txt_message22), "Examiner", "58 Posts", "Rs. 9300-34800/- grade Pay: Rs .4600/-", "Degree in law", "30 Years", "2016-03-31");
        String text1 = String.format(res.getString(R.string.txt_message223), getallHomeAllExpandLists.get(i).getCat_name());
        String text2 = String.format(res.getString(R.string.txt_message223), getallHomeAllExpandLists.get(i).getCat_description());

        CharSequence styledText1 = Html.fromHtml(text1);
        CharSequence styledText2 = Html.fromHtml(text2);

        gmailVH.h_e_cname.setText(styledText1);
        gmailVH.h_e_description.setText(styledText2);
        Picasso.with(context).load(getallHomeAllExpandLists.get(i).getCat_image()).into(gmailVH.h_e_image);

    }
    @Override
    public int getItemCount() {
        return getallHomeAllExpandLists == null ? 0 : getallHomeAllExpandLists.size();
    }
    class GmailVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView h_e_image;
        TextView h_e_cname;
        TextView h_e_description;

        public GmailVH(View itemView) {

            super(itemView);

            h_e_image = (ImageView) itemView.findViewById(R.id.h_e_image);
            h_e_cname = (TextView) itemView.findViewById(R.id.h_e_cname);
            h_e_description = (TextView) itemView.findViewById(R.id.h_e_description);
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


