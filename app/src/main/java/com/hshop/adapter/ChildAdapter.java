package com.hshop.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hshop.R;
import com.hshop.models.pojo;
import com.hshop.shopping.Product;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.RecyclerViewHolder> {

    LayoutInflater inflater;
    Context context;
    ArrayList<pojo> event;

    public ChildAdapter(Context context,ArrayList<pojo> event)
    {
        this.context=context;
        this.event=event;
        inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.adapter_home2, parent, false);
        RecyclerViewHolder holder=new RecyclerViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        final String id=event.get(position).getSubcatid();
        final String name=event.get(position).getSubcatname();
        String img=event.get(position).getSubcatimg();
        holder.h_e_s_name.setText(name);
        try
        {
            Picasso.with(context).load(img).into(holder.h_e_s_image);
            Log.d("fgh",img);
        }
        catch (Exception e)
        {
            Log.d("fgh","img"+img);
            Log.d("fgh",e.toString());
        }

        holder.card_view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(context, Product.class);
                i1.putExtra("sub_cat_id",id);
                i1.putExtra("sub_cat_name",name);
                context.startActivity(i1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return event.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView h_e_s_name;
        ImageView h_e_s_image;
        CardView card_view1;

        public RecyclerViewHolder(View itemView) {
            super(itemView);

            card_view1=(CardView)itemView.findViewById(R.id.card_view1);
            h_e_s_image=(ImageView)itemView.findViewById(R.id.h_e_s_image);
            h_e_s_name=(TextView)itemView.findViewById(R.id.h_e_s_name);

        }
    }
}
