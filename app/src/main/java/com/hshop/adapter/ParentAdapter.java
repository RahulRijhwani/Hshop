package com.hshop.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.hshop.R;
import com.hshop.models.pojo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ParentAdapter extends RecyclerView.Adapter<ParentAdapter.RecylerViewHolder> {

    LayoutInflater inflater;
    Context context;
    ArrayList<pojo> event;
    public boolean ch=false;
    public RecylerViewHolder holder1;

    public ParentAdapter(Context context,ArrayList<pojo> event)
    {
        this.context=context;
        this.event=event;
        inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public RecylerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.adapter_home11, parent, false);
        RecylerViewHolder holder=new RecylerViewHolder(view);
        return holder;
    }

    public void slideUp(RecylerViewHolder holder){
        //view.setVisibility(View.VISIBLE);

        if(ch==true)
        {
            holder1.child.setVisibility(View.GONE);
            //slideDown1(holder1.child);
            holder1.h_e_arrow.setBackgroundResource(R.drawable.ic_arrow_down);
            holder1.card_view.setBackgroundResource(R.color.bg4);
            ch=false;
        }
        ch=true;
        holder1=holder;
        String sc=holder.subcat.getText().toString();
        String[] ary = sc.split(",!,");
        ArrayList<pojo> event1=new ArrayList<pojo>();
        int ij=0;
        for(int i=0;i<ary.length;i++)
        {

            if(!ary[i].toString().equals("")) {
                String[] ary1 = ary[i].split("!-");
                pojo p = new pojo();
                try {
                    p.setSubcatid(ary1[0]);
                    p.setSubcatname(ary1[1]);
                    p.setSubcatimg(ary1[2]);
                }catch (Exception ex)
                {

                }
                event1.add(p);
            }

        }

        ChildAdapter adp=new ChildAdapter(context,event1);
        holder.child.setAdapter(adp);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 3);
        holder.child.setLayoutManager(mLayoutManager);
        //holder.child.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));

        holder.child.setVisibility(View.VISIBLE);
        //slideUp1(holder.child);
        holder.h_e_arrow.setBackgroundResource(R.drawable.ic_arrow_up);
        holder.card_view.setBackgroundResource(R.color.bg9);



        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                holder.child.getHeight(),  // fromYDelta
                0);                // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        holder.child.startAnimation(animate);
    }

    // slide the view from its current position to below itself
    public void slideDown(RecylerViewHolder holder){



        ch=false;
        holder.child.setVisibility(View.GONE);
        //slideDown1(holder.child);
        holder.h_e_arrow.setBackgroundResource(R.drawable.ic_arrow_down);
        holder.card_view.setBackgroundResource(R.color.bg4);

        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                0,                 // fromYDelta
                holder.child.getHeight()); // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        holder.child.startAnimation(animate);
    }

    public void slideUp1(View view){
        view.setVisibility(View.VISIBLE);
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                 view.getHeight(),  // fromYDelta
                0);                // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
    }

    // slide the view from its current position to below itself
    public void slideDown1(View view){

        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                0,                 // fromYDelta
                view.getHeight()); // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);

    }


    @Override
    public void onBindViewHolder(final RecylerViewHolder holder, int position) {

        String name=event.get(position).getCatname();
        String id=event.get(position).getCatid();
        String img=event.get(position).getCatimg();
        String subcat=event.get(position).getSubcat();
        String dec=event.get(position).getDes();
        String offer=event.get(position).getOffer();
        holder.subcat.setText(subcat);
        holder.h_e_offer.setText(offer);
        holder.h_e_cname.setText(name);
        holder.child.setVisibility(View.GONE);
        try
        {
            Picasso.with(context).load(img).into(holder.h_e_image);
            Log.d("fgh",img);
        }
         catch (Exception e)
        {
            Log.d("fgh",img);
            Log.d("fgh",e.toString());
        }
        holder.h_e_description.setText(Html.fromHtml(dec));

        holder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(holder.child.getVisibility()==View.GONE)
                {
                    slideUp(holder);
                }
                else
                {
                    slideDown(holder);
                }

            }
        });




        /*holder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.child.getVisibility()==View.GONE) {
                    if(ch==true)
                    {
                        holder1.child.setVisibility(View.GONE);
                        holder1.h_e_arrow.setBackgroundResource(R.drawable.ic_arrow_down);
                        holder1.card_view.setBackgroundResource(R.color.bg4);
                        ch=false;
                    }
                    ch=true;
                    holder1=holder;
                    String sc=holder.subcat.getText().toString();
                    String[] ary = sc.split(",!,");
                    ArrayList<pojo> event1=new ArrayList<pojo>();
                    int ij=0;
                    for(int i=0;i<ary.length;i++)
                    {

                        if(!ary[i].toString().equals("")) {
                            String[] ary1 = ary[i].split("!-");
                            pojo p = new pojo();
                            try {
                                p.setSubcatid(ary1[0]);
                                p.setSubcatname(ary1[1]);
                                p.setSubcatimg(ary1[2]);
                            }catch (Exception ex)
                            {

                            }
                            event1.add(p);
                        }

                    }

                    ChildAdapter adp=new ChildAdapter(context,event1);
                    holder.child.setAdapter(adp);
                    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 3);
                    holder.child.setLayoutManager(mLayoutManager);
                    //holder.child.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));

                    holder.child.setVisibility(View.VISIBLE);
                    holder.h_e_arrow.setBackgroundResource(R.drawable.ic_arrow_up);
                    holder.card_view.setBackgroundResource(R.color.bg9);
                }
                else
                {
                    ch=false;
                    holder.child.setVisibility(View.GONE);
                    holder.h_e_arrow.setBackgroundResource(R.drawable.ic_arrow_down);
                    holder.card_view.setBackgroundResource(R.color.bg4);
                }
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return event.size();
    }

    public class RecylerViewHolder extends RecyclerView.ViewHolder {

        ImageView h_e_image,h_e_arrow;
        TextView h_e_offer,h_e_cname,subcat,h_e_description;
        RecyclerView child;
        CardView card_view;


        public RecylerViewHolder(View itemView) {
            super(itemView);
            card_view=(CardView)itemView.findViewById(R.id.card_view);
            h_e_image=(ImageView)itemView.findViewById(R.id.h_e_image);
            h_e_arrow=(ImageView)itemView.findViewById(R.id.h_e_arrow);
            h_e_offer=(TextView)itemView.findViewById(R.id.h_e_offer);
            h_e_description=(TextView)itemView.findViewById(R.id.h_e_description);
            h_e_cname=(TextView)itemView.findViewById(R.id.h_e_cname);
            subcat=(TextView)itemView.findViewById(R.id.subcat);
            child=(RecyclerView)itemView.findViewById(R.id.child);

        }
    }
}
