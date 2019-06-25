package com.hshop.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hshop.R;
import com.hshop.models.AllCancel;
import com.hshop.rest.Config;
import com.hshop.rest.RestClient;
import com.hshop.shopping.Master_Home;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

/**
 * Created by Nilay on 10/18/2017.
 */

public class OSViewHolder1 extends GroupViewHolder {

    private TextView or_date;
    private TextView or_status;
    private TextView or_total;
    private TextView or_quntity;
    private TextView or_cancel;
    private ImageView h_e_arrow;
    private CardView h_e_card;
 
    Context context;
    public OSViewHolder1(View itemView) {
        super(itemView);

        or_date = (TextView) itemView.findViewById(R.id.or_date);
        or_status = (TextView) itemView.findViewById(R.id.or_status);
        or_total = (TextView) itemView.findViewById(R.id.or_total);
        or_quntity = (TextView) itemView.findViewById(R.id.or_quntity);
        or_cancel = (TextView) itemView.findViewById(R.id.or_cancel);
        h_e_arrow = (ImageView) itemView.findViewById(R.id.h_e_arrow);
        h_e_card = (CardView) itemView.findViewById(R.id.card_view);
    }
    @Override
    public void expand() {
        h_e_arrow.setBackgroundResource(R.drawable.ic_arrow_down);
        h_e_card.setBackgroundResource(R.color.bg4);
        // h_e_arrow.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_down_arrow, 0);
        Log.i("Adapter", "expand");
    }
    @Override
    public void collapse() {
        Log.i("Adapter", "collapse");
        h_e_arrow.setBackgroundResource(R.drawable.ic_arrow_up);
        h_e_card.setBackgroundResource(R.color.bg9);
       // h_e_cname.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_up_arrow, 0);
    }
    public void setGroupName(ExpandableGroup group) {
        String full_data = group.getTitle();
        Log.d("fgh",full_data);
        final String[] seperatr_data = full_data.split("!-");
        or_date.setText(seperatr_data[0]);
        or_status.setText(seperatr_data[1]);
        or_total.setText(seperatr_data[2]);
        or_quntity.setText(seperatr_data[3]);
        if (seperatr_data[1].equals("Cancel"))
        {
            or_status.setTextColor(Color.RED);
        }
        if (seperatr_data[1].equals("Pending"))
        {
            or_cancel.setVisibility(View.VISIBLE);
        }
        or_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("fgh","Cancel");
                // ((Cart)context).callCartData();
                // itemView.getContext().startActivity(new Intent("com.hshop.shopping.Master_Home"));
                AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
                builder.setMessage("Do you want to Cancel Order ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Toast.makeText(itemView.getContext(), seperatr_data[4], Toast.LENGTH_SHORT).show();
                                ordercancel(seperatr_data[4]);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.setTitle("Cancel Order");
                alert.show();

            }
        });

    }
    private void ordercancel(String order_id) {
        RestClient.GitApiInterface service = RestClient.getClient();
        Call<AllCancel> call = service.getCancelOrder(Config.mem_string,order_id);
        call.enqueue(new Callback<AllCancel>() {
            @Override
            public void onResponse(Response<AllCancel> response) {
                if (response.isSuccess()) {
                    // request successful (status code 200, 201)
                    AllCancel result = response.body();
                    if (result.getStatus().equals("success")) {
                        Toast.makeText(itemView.getContext(), "Order Successfully Cancel", Toast.LENGTH_SHORT).show();
                        itemView.getContext().startActivity(new Intent(itemView.getContext(), Master_Home.class));
                        }
                }
            }
            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(itemView.getContext(), "Please try again is something wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
