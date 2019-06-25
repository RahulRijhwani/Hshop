package com.hshop.shopping;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.hshop.R;
import com.hshop.adapter.SelectCartDetailAdapter;
import com.hshop.models.AllCart;
import com.hshop.models.AllCartProduct;
import com.hshop.models.UserCheckout;
import com.hshop.rest.Config;
import com.hshop.rest.RestClient;
import com.hshop.utils.NetworkUtils;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class Cart extends AppCompatActivity {

    RecyclerView recyclerView;
    SelectCartDetailAdapter adapter;
    List<AllCartProduct> getallCartProductLists = new ArrayList<>();
    TextView empty_view,c_subtotal,c_subtotal1,c_offerper,c_checktotalcost,txt_delivery_cost;
    String user_id,status;
    PopupWindow popupWindow;
    LinearLayout c_linear,final_order,linear_no_internet,emp_empty_bucket;
    String user_info,user_address;
    Button start_shopping;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        C0456b.f2467p1 = getSharedPreferences(C0456b.f2907a1,0);
        C0456b.f2467p2 = getSharedPreferences(C0456b.f2907a2,0);
        user_info = C0456b.f2467p1.getString("user_info",null);
        user_address = C0456b.f2467p2.getString("user_address",null);

        final String user_name[] = user_info.split(",");
        final String user_location[] = user_address.split(",");


        recyclerView = (RecyclerView) findViewById(R.id.gmail_list);
        empty_view = (TextView) findViewById(R.id.empty_view);
        c_subtotal = (TextView) findViewById(R.id.c_subtotal);
        c_subtotal1 = (TextView) findViewById(R.id.c_subtotal1);
        c_offerper = (TextView) findViewById(R.id.c_offerper);
        c_checktotalcost = (TextView) findViewById(R.id.c_checktotalcost);
        txt_delivery_cost = (TextView) findViewById(R.id.txt_delivery_cost);
        c_linear = (LinearLayout) findViewById(R.id.c_linear);
        final_order = (LinearLayout) findViewById(R.id.final_order);
        linear_no_internet = (LinearLayout) findViewById(R.id.linear_no_internet);
        emp_empty_bucket = (LinearLayout) findViewById(R.id.emp_empty_bucket);
        start_shopping = (Button) findViewById(R.id.start_shopping);
        start_shopping.setEnabled(false);
        start_shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(Cart.this,Master_Home.class);
                startActivity(i1);
                finish();
            }
        });
        C0456b.f2467p = getSharedPreferences(C0456b.f2907a,0);
        user_id = C0456b.f2467p.getString("user_id",null);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        if (adapter == null) {
            adapter = new SelectCartDetailAdapter(this,getallCartProductLists);
            recyclerView.setAdapter(adapter);
        }

        status = NetworkUtils.getConnectivityStatus(Cart.this);
        if (status.equals("404")) {
            linear_no_internet.setVisibility(View.VISIBLE);
        }
        else {
            callCartData();
        }
        final_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(Cart.this,Checkout.class);
                startActivity(i1);
             /*  LayoutInflater layoutInflater = (LayoutInflater)Cart.this.getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = layoutInflater.inflate(R.layout.popu, null);
                popupWindow = new PopupWindow(
                        popupView,
                        DrawerLayout.LayoutParams.MATCH_PARENT,
                        DrawerLayout.LayoutParams.WRAP_CONTENT);
                popupWindow.showAtLocation(popupView, Gravity.CENTER,Gravity.CENTER,250);
                popupWindow.setOutsideTouchable(false);
                popupWindow.setFocusable(true);
                popupWindow.update();
                final EditText cf_house = (EditText) popupView.findViewById(R.id.cf_house);
                final EditText cf_society = (EditText) popupView.findViewById(R.id.cf_society);
                final EditText cf_locality = (EditText) popupView.findViewById(R.id.cf_locality);
                final EditText cf_pincode = (EditText) popupView.findViewById(R.id.cf_pincode);

                cf_house.setText(user_location[0]);
                cf_society.setText(user_location[1]);
                cf_locality.setText(user_location[2]);
                cf_pincode.setText(user_location[3]);
                Button cf_cancel = (Button) popupView.findViewById(R.id.cf_cancel);
                Button cf_confirm = (Button) popupView.findViewById(R.id.cf_confirm);

                cf_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();

                    }
                });
                cf_confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final_order.setEnabled(false);
                        String o_number = cf_house.getText().toString();
                        String o_society = cf_society.getText().toString();
                        String o_location = cf_locality.getText().toString();
                        String o_pincode = cf_pincode.getText().toString();
                        checkout(Config.mem_string,user_id,o_number,o_society,o_location,o_pincode);
                    }
                });
                */
            }
        });
    }

    private void checkout(String mem_string, String user_id, String o_number, String o_society, String o_location, String o_pincode) {
        RestClient.GitApiInterface service = RestClient.getClient();
        Call<UserCheckout> call = service.getUsercheckout(mem_string,user_id,o_number,o_society,o_location,o_pincode);
        call.enqueue(new Callback<UserCheckout>() {
            @Override
            public void onResponse(Response<UserCheckout> response) {

                if (response.isSuccess()) {
                    // request successful (status code 200, 201)
                    UserCheckout result = response.body();
                    if(result.getStatus().equals("success")) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Cart.this);
                        builder.setMessage("Order Successfully Placed...!")
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Intent i1 = new Intent(Cart.this,Order_details.class);
                                        startActivity(i1);
                                        finish();
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();

                    }
                    else
                    {
                        Toast.makeText(Cart.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // response received but request not successful (like 400,401,403 etc)
                    //Handle errors
                    Toast.makeText(Cart.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    public void callCartData() {
        c_linear.setVisibility(View.INVISIBLE);
        final DilatingDotsProgressBar mDilatingDotsProgressBar = (DilatingDotsProgressBar) findViewById(R.id.progress);
        mDilatingDotsProgressBar.showNow();
        RestClient.GitApiInterface service = RestClient.getClient();

        Call<AllCart> call = service.getCartDetails(Config.mem_string,user_id);
        call.enqueue(new Callback<AllCart>() {
            @Override
            public void onResponse(Response<AllCart> response) {
                mDilatingDotsProgressBar.hideNow();
                if (response.isSuccess()) {
                    // request successful (status code 200, 201)
                    AllCart result = response.body();
                    if (result.getStatus().equals("success")) {
                        if (result.getProduct() != null) {
                            if (result.getProduct().size() > 0) {
                                getallCartProductLists.clear();
                                getallCartProductLists.addAll(result.getProduct());
                                adapter.notifyDataSetChanged();
                                if (getallCartProductLists.isEmpty()) {
                                    recyclerView.setVisibility(View.GONE);
                                    empty_view.setVisibility(View.VISIBLE);
                                } else {
                                    recyclerView.setVisibility(View.VISIBLE);
                                    empty_view.setVisibility(View.GONE);
                                }
                            } else {
                                recyclerView.setVisibility(View.GONE);
                                empty_view.setVisibility(View.VISIBLE);

                            }
                        }
                        c_subtotal.setText(result.getTotal());
                        c_subtotal1.setText(result.getSaving());
                        c_offerper.setText(result.getSavingoffer());
                        c_checktotalcost.setText(result.getTotal_shipping_cost());
                        txt_delivery_cost.setText(result.getShipping());
                        c_linear.setVisibility(View.VISIBLE);
                    } else if (result.getStatus().equals("empty_cart")){
                        recyclerView.setVisibility(View.INVISIBLE);
                        empty_view.setVisibility(View.INVISIBLE);
                        emp_empty_bucket.setVisibility(View.VISIBLE);
                        start_shopping.setEnabled(true);
                    }
                    else
                    {
                        recyclerView.setVisibility(View.GONE);
                        empty_view.setVisibility(View.VISIBLE);
                    }
                } else {
                    // response received but request not successful (like 400,401,403 etc)
                    recyclerView.setVisibility(View.GONE);
                    empty_view.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onFailure(Throwable t) {
                mDilatingDotsProgressBar.hideNow();
            }
        });
    }
}
