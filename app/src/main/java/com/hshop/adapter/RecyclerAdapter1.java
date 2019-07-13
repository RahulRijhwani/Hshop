package com.hshop.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hshop.R;
import com.hshop.shopping.Product;
import com.hshop.shopping.Product_details;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Created by Nilay on 10/18/2017.
 */

public class RecyclerAdapter1 extends ExpandableRecyclerViewAdapter<OSViewHolder1, PhoneViewHolder1> {

    private Context activity;

    public RecyclerAdapter1(Context activity, List<? extends ExpandableGroup> groups) {
        super(groups);
        this.activity = activity;
    }

    @Override
    public OSViewHolder1 onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.adapter_order1, parent, false);

        return new OSViewHolder1(view);
    }

    @Override
    public PhoneViewHolder1 onCreateChildViewHolder(ViewGroup parent, final int viewType) {
       // LayoutInflater layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.adapter_order2, parent, false);

        return new PhoneViewHolder1(view);
    }

    @Override
    public void onBindChildViewHolder(PhoneViewHolder1 holder, int flatPosition, ExpandableGroup group, int childIndex) {
        final Phone1 phone = ((MobileOS1) group).getItems().get(childIndex);
        holder.onBind(phone,group);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String full_data = phone.getName();
                String[] seperatr_data = full_data.split("!-");
                Intent i1 = new Intent(activity,Product_details.class);
                i1.putExtra("pro_id",seperatr_data[8]);
                i1.putExtra("pro_name",seperatr_data[0]);
                i1.putExtra("type","proid");
                activity.startActivity(i1);;

            }
        });

    }

    @Override
    public void onBindGroupViewHolder(OSViewHolder1 holder, int flatPosition, ExpandableGroup group) {
        holder.setGroupName(group);
    }
}
