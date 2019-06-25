package com.hshop.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hshop.shopping.Product;
import com.hshop.R;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/*
 *
 * Created by Nilay on 10/18/2017.
 */

public class RecyclerAdapter extends ExpandableRecyclerViewAdapter<OSViewHolder, PhoneViewHolder> {

    private Context activity;
    int in=0;
    public boolean ok;
    public View viewp;

    public RecyclerAdapter(Context activity, List<? extends ExpandableGroup> groups) {
        super(groups);
        this.activity = activity;
    }

    @Override
    public OSViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.adapter_home1, parent, false);
        viewp=view;
        return new OSViewHolder(view);
    }

    @Override
    public PhoneViewHolder onCreateChildViewHolder(ViewGroup parent, final int viewType) {
       // LayoutInflater layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.adapter_home2, parent, false);
        return new PhoneViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(PhoneViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {

        final Phone phone = ((MobileOS) group).getItems().get(childIndex);
        in=flatPosition;
        holder.onBind(phone,group);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String full_data = phone.getName();
                String[] seperatr_data = full_data.split("!-");
                Intent i1 = new Intent(activity, Product.class);
                i1.putExtra("sub_cat_id",seperatr_data[3]);
                i1.putExtra("sub_cat_name",seperatr_data[0]);
                activity.startActivity(i1);

            }
        });


    }

    @Override
    public void onBindGroupViewHolder(OSViewHolder holder, int flatPosition, ExpandableGroup group) {

           /* holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OSViewHolder osv=new OSViewHolder(viewp);
                    if(in!=0)
                    {
                        osv.expand();
                        in=0;
                    }
                    osv.collapse();
                }
            });*/

        holder.setGroupName(group);

    }

}
