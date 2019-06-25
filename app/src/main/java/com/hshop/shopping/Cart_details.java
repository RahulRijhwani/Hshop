package com.hshop.shopping;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.hshop.R;
import com.hshop.adapter.MobileOS1;
import com.hshop.adapter.Phone1;
import com.hshop.adapter.RecyclerAdapter1;
import com.hshop.adapter.SelectCartDetailAdapter;
import com.hshop.models.AllCart;
import com.hshop.models.AllCartProduct;
import com.hshop.models.AllExpandOrderList;
import com.hshop.models.AllOrder;
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

/**
 * Created by Nilay on 12-Apr-17.
 */

public class Cart_details extends Fragment {
    View view;
    RecyclerView recyclerView;
    SelectCartDetailAdapter adapter;
    List<AllCartProduct> getallCartProductLists = new ArrayList<>();
    TextView empty_view,c_subtotal,c_subtotal1,c_offerper,c_checktotalcost;
    String user_id,status;
    PopupWindow popupWindow;
    LinearLayout c_linear,final_order,linear_no_internet,emp_empty_bucket;
    String user_info,user_address;
    Button start_shopping;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.frag_cart_details, container, false);

        C0456b.f2467p1 = getActivity().getSharedPreferences(C0456b.f2907a1, 0);
        C0456b.f2467p2 = getActivity().getSharedPreferences(C0456b.f2907a2, 0);
        user_info = C0456b.f2467p1.getString("user_info", null);
        user_address = C0456b.f2467p2.getString("user_address", null);

        final String user_name[] = user_info.split(",");
        final String user_location[] = user_address.split(",");


        recyclerView = (RecyclerView) view.findViewById(R.id.gmail_list);
        empty_view = (TextView) view.findViewById(R.id.empty_view);
        c_subtotal = (TextView) view.findViewById(R.id.c_subtotal);
        c_subtotal1 = (TextView) view.findViewById(R.id.c_subtotal1);
        c_offerper = (TextView) view.findViewById(R.id.c_offerper);
        c_checktotalcost = (TextView) view.findViewById(R.id.c_checktotalcost);
        c_linear = (LinearLayout) view.findViewById(R.id.c_linear);
        final_order = (LinearLayout) view.findViewById(R.id.final_order);
        linear_no_internet = (LinearLayout) view.findViewById(R.id.linear_no_internet);
        emp_empty_bucket = (LinearLayout) view.findViewById(R.id.emp_empty_bucket);
        start_shopping = (Button) view.findViewById(R.id.start_shopping);
        start_shopping.setEnabled(false);
        start_shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(getActivity(), Master_Home.class);
                startActivity(i1);
                getActivity().finish();
            }
        });
        C0456b.f2467p = getActivity().getSharedPreferences(C0456b.f2907a, 0);
        user_id = C0456b.f2467p.getString("user_id", null);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        if (adapter == null) {
            adapter = new SelectCartDetailAdapter(getContext(), getallCartProductLists);
            recyclerView.setAdapter(adapter);
        }

        status = NetworkUtils.getConnectivityStatus(getActivity());
        if (status.equals("404")) {
            linear_no_internet.setVisibility(View.VISIBLE);
        } else {
            callCartData();
        }
        final_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(getContext(), Checkout.class);
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
        return view;
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
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setMessage("Order Successfully Placed...!")
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Intent i1 = new Intent(getActivity(),Order_details.class);
                                        startActivity(i1);
                                        getActivity().finish();
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();

                    }
                    else
                    {
                        Toast.makeText(getActivity(), "Plz TRY again Later", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // response received but request not successful (like 400,401,403 etc)
                    //Handle errors
                    Toast.makeText(getActivity(), "Server Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    public void callCartData() {
        c_linear.setVisibility(View.INVISIBLE);
        final DilatingDotsProgressBar mDilatingDotsProgressBar = (DilatingDotsProgressBar) view.findViewById(R.id.progress);
        mDilatingDotsProgressBar.showNow();
        RestClient.GitApiInterface service = RestClient.getClient();

        Call<AllCart> call = service.getCartDetails(Config.mem_string,user_id);
        call.enqueue(new Callback<AllCart>() {
            @Override
            public void onResponse(Response<AllCart> response) {
                mDilatingDotsProgressBar.hideNow();
                Log.d("fgh", "Status Code = " + response.code());
                if (response.isSuccess()) {
                    // request successful (status code 200, 201)
                    AllCart result = response.body();
                    if (result.getStatus().equals("success")) {
                        if (result.getProduct() != null) {
                            if (result.getProduct().size() > 0) {
                                Log.d("fgh",result.getProduct().get(0).getPro_name());
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
                        c_checktotalcost.setText(result.getTotal());
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


    public static Fragment newInstance() {
        Cart_details fragment = new Cart_details();
        return fragment;
    }
}