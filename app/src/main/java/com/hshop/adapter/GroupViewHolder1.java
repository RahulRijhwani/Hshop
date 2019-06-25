package com.hshop.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.thoughtbot.expandablerecyclerview.listeners.OnGroupClickListener;

public class GroupViewHolder1  extends RecyclerView.ViewHolder implements View.OnClickListener {

    private OnGroupClickListener listener;

    public GroupViewHolder1(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            if (listener.onGroupClick(getAdapterPosition())) {
                expand();
            } else {
                collapse();
            }
        }
    }

    public void setOnGroupClickListener(OnGroupClickListener listener) {
        this.listener = listener;
    }

    public void expand() {}

    public void collapse() {}

}

