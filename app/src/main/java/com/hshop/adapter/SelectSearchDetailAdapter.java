package com.hshop.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hshop.R;
import com.hshop.models.AllSearchProduct;

import java.util.List;


public class SelectSearchDetailAdapter extends RecyclerView.Adapter<SelectSearchDetailAdapter.GmailVH>{
    Context context;
    OnItemClickListener clickListener;
    List<AllSearchProduct> getallSearchProduct;
    public SelectSearchDetailAdapter(Context context, List<AllSearchProduct> getallSearchProduct) {
        this.context = context;

        this.getallSearchProduct = getallSearchProduct;
    }

    @Override
    public GmailVH onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_search, viewGroup, false);
        return new GmailVH(view);
    }

    @Override
    public void onBindViewHolder(final GmailVH gmailVH, final int i) {
        //  gmailVH.title.setText("Sample Test");

        Resources res = context.getResources();
      //  String text2 = String.format(res.getString(R.string.txt_message22), "Examiner", "58 Posts", "Rs. 9300-34800/- grade Pay: Rs .4600/-", "Degree in law", "30 Years", "2016-03-31");
        //String text1 = String.format(res.getString(R.string.txt_message223), getallSearchProduct.get(i).getPro_id());

        //String text4 = String.format(res.getString(R.string.txt_message223), getallSearchProduct.get(i).getPro_actualcost());
        //String text5 = String.format(res.getString(R.string.txt_message223),getallSearchProduct.get(i).getPro_offercost());
        String text6 = String.format(res.getString(R.string.txt_message223), getallSearchProduct.get(i).getPro_name());
        //String text7 = String.format(res.getString(R.string.txt_message223), getallSearchProduct.get(i).getPro_unit());


        gmailVH.h_p_name.setText(text6);
//        gmailVH.h_p_actual_cost.setText(text4);
    //    gmailVH.h_p_offer_cost.setText(text5);
     //   gmailVH.txt_unit.setText(text7);
        try
        {
         //   Glide.with(context).load(getallSearchProduct.get(i).getPro_image()).into(gmailVH.h_p_image);

        }catch (Exception e)
        {

        }



    }
    @Override
    public int getItemCount() {
        return getallSearchProduct == null ? 0 : getallSearchProduct.size();
    }


    class GmailVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView h_p_name;
        TextView h_p_actual_cost;
        TextView h_p_offer_cost;
        TextView txt_unit;
        ImageView h_p_image;
        CardView card_view;


        public GmailVH(View itemView) {
            super(itemView);

    //        h_p_image = (ImageView) itemView.findViewById(R.id.h_p_image);
            h_p_name = (TextView) itemView.findViewById(R.id.h_p_name);
      //      h_p_actual_cost = (TextView) itemView.findViewById(R.id.h_p_actual_cost);
      //      h_p_offer_cost = (TextView) itemView.findViewById(R.id.h_p_offer_cost);
          //  txt_unit = (TextView) itemView.findViewById(R.id.txt_unit);
            card_view = (CardView) itemView.findViewById(R.id.card_view);
            card_view.setOnClickListener(this);


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


