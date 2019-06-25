package com.hshop.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hshop.models.AllProductDetailsList;
import com.hshop.models.AllProductUnitDetailsList;
import com.hshop.models.UserAddProductCart;
import com.hshop.models.UserMinusProductCart;
import com.hshop.shopping.Cart;
import com.hshop.R;
import com.hshop.models.AllHomeProductList;
import com.hshop.models.UserAddCart;
import com.hshop.rest.Config;
import com.hshop.rest.RestClient;
import com.hshop.shopping.Master_Home;
import com.hshop.shopping.Product_details;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;


public class SelectProductDetailAdapter extends RecyclerView.Adapter<SelectProductDetailAdapter.GmailVH> {
    Context context;
    String user_id;
    List<AllProductDetailsList> getAllProductLists;
    List<AllProductUnitDetailsList> getAllProductUnitLists;
    ArrayList<String> list = new ArrayList<>();
    ArrayList<String> listItems = new ArrayList<>();
    ArrayAdapter<String> adapter1;
    String pro_id, pro_name;
    TextView[] myTextViews;
    int qty = 0, ich = 0;
    String pro_unit_id,unittextparse;

    public SelectProductDetailAdapter(Context context, List<AllProductDetailsList> getAllProductLists, List<AllProductUnitDetailsList> getAllProductUnitLists, String pro_id, String pro_name, String pro_unit_id,String unittextparse) {
        this.pro_unit_id = pro_unit_id;
        this.unittextparse=unittextparse;
        this.context = context;
        this.pro_id = pro_id;
        this.pro_name = pro_name;
        this.getAllProductLists = getAllProductLists;
        this.getAllProductUnitLists = getAllProductUnitLists;

    }

    String product_unit_id;

    @Override
    public GmailVH onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_home3, viewGroup, false);
        return new GmailVH(view);
    }

    @Override
    public void onBindViewHolder(final GmailVH gmailVH, final int i) {
        //  gmailVH.title.setText("Sample Test");

        final Resources res = context.getResources();
        //  String text2 = String.format(res.getString(R.string.txt_message22), "Examiner", "58 Posts", "Rs. 9300-34800/- grade Pay: Rs .4600/-", "Degree in law", "30 Years", "2016-03-31");
        String text1 = String.format(res.getString(R.string.txt_message223), getAllProductLists.get(i).getPro_id());
        String text3 = String.format(res.getString(R.string.txt_message223), getAllProductLists.get(i).getPro_offer());
        String text4 = String.format(res.getString(R.string.txt_message223), getAllProductLists.get(i).getPro_actualcost());
        String text5 = String.format(res.getString(R.string.txt_message223), getAllProductLists.get(i).getPro_offercost());
        String text6 = String.format(res.getString(R.string.txt_message223), getAllProductLists.get(i).getPro_name());
        String text7 = String.format(res.getString(R.string.txt_message223), getAllProductLists.get(i).getPro_unit());
        String text8 = String.format(res.getString(R.string.txt_message223), getAllProductLists.get(i).getPro_rating());
        String text9 = String.format(res.getString(R.string.txt_message223), getAllProductLists.get(i).getPro_description());
        String text10 = String.format(res.getString(R.string.txt_message223), getAllProductLists.get(i).getPro_features());
        String text11 = String.format(res.getString(R.string.txt_message223), getAllProductLists.get(i).getPro_disclaimer());
        String text12 = String.format(res.getString(R.string.txt_message223), getAllProductLists.get(i).getPro_store());
        String qtychk = String.format(res.getString(R.string.txt_message223), getAllProductLists.get(i).getQtyadd());
        gmailVH.qtycheck.setText(qtychk);

        final String cartval = String.format(res.getString(R.string.txt_message223), getAllProductLists.get(i).getCart_val());
        qty = Integer.parseInt(cartval);

        gmailVH.c_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String l = gmailVH.qtycheck.getText().toString();
                if (Integer.parseInt(l) > Integer.parseInt(String.format(res.getString(R.string.txt_message223), getAllProductLists.get(i).getCart_val()))) {
                    userproductadd(gmailVH, Config.mem_string, getAllProductLists.get(i).getUser_id(), getAllProductLists.get(i).getPro_id(), gmailVH.odetest.getText().toString(), gmailVH.tdptest.getText().toString());
                } else {
                    Toast.makeText(context, "Can not add quantity more than " + l, Toast.LENGTH_SHORT).show();
                }


            }
        });

        gmailVH.c_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userproductminus(gmailVH, Config.mem_string, getAllProductLists.get(i).getUser_id(), getAllProductLists.get(i).getPro_id(), gmailVH.odetest.getText().toString(), gmailVH.tdptest.getText().toString());
            }
        });


        gmailVH.cartadd_min.setVisibility(View.INVISIBLE);
        gmailVH.p_add.setVisibility(View.VISIBLE);
        if (Integer.parseInt(cartval) > 0) {
            gmailVH.cartadd_min.setVisibility(View.VISIBLE);
            gmailVH.p_add.setVisibility(View.INVISIBLE);
            gmailVH.c_quantity.setText(cartval);
        } else {
            gmailVH.cartadd_min.setVisibility(View.INVISIBLE);
            gmailVH.p_add.setVisibility(View.VISIBLE);
        }


        gmailVH.p_store.setText(text12);
        gmailVH.p_star.setText(text8);
        //  gmailVH.p_offer.setText(text3);
        gmailVH.p_name.setText(text6);
        //   gmailVH.p_actual_cost.setText(text4);
        //  gmailVH.p_offer_cost.setText(text5);
        //   gmailVH.p_unit.setText(text7);
        gmailVH.p_description.setText(Html.fromHtml(text9));
        gmailVH.p_features.setText(Html.fromHtml(text10));
        gmailVH.p_disclaimer.setText(Html.fromHtml(text11));
        try {
            Picasso.with(context).load(getAllProductLists.get(i).getPro_image()).into(gmailVH.p_image);

        } catch (Exception e) {

        }

        gmailVH.unitarrow.setImageResource(R.drawable.ic_arrow_down);
        gmailVH.unitlin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Integer.parseInt(gmailVH.txtich.getText().toString()) > 1) {
                    if (gmailVH.unitlinear.getVisibility() == View.VISIBLE) {
                        gmailVH.unitlinear.setVisibility(View.GONE);
                        gmailVH.unitarrow.setImageDrawable(null);
                        gmailVH.unitarrow.setImageResource(R.drawable.ic_arrow_down);

                    } else {
                        gmailVH.unitlinear.setVisibility(View.VISIBLE);
                        gmailVH.unitarrow.setImageDrawable(null);
                        gmailVH.unitarrow.setImageResource(R.drawable.ic_arrow_up);
                    }
                }
            }
        });

        /*adapter1= new ArrayAdapter<String>(context, R.layout.drop_down, R.id.txt, listItems);

        Log.d("fgh",adapter1.toString());
        GmailVH.spinner1.setAdapter(adapter1);

        GmailVH.spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                gmailVH.p_actual_cost.setText(getAllProductUnitLists.get(i).getTpd_actual_cost());
                gmailVH.p_offer_cost.setText(getAllProductUnitLists.get(i).getTpd_offer_cost());
                gmailVH.p_offer.setText(getAllProductUnitLists.get(i).getTpd_offer());

                product_unit_id  = getAllProductUnitLists.get(i).getTpd_id();
                // Toast.makeText(context, adapterView.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        */
        for (int j = 0; j < getAllProductUnitLists.size(); j++) {
            list.add(getAllProductUnitLists.get(j).getTpd_unit());
            gmailVH.p_actual_cost.setText(getAllProductUnitLists.get(j).getTpd_actual_cost());
            gmailVH.p_offer_cost.setText(getAllProductUnitLists.get(j).getTpd_offer_cost());
            gmailVH.p_offer.setText(getAllProductUnitLists.get(j).getTpd_offer());
            String tpdofr = getAllProductUnitLists.get(j).getTpd_offer();
            String txt8 = tpdofr.replace("SAVE ₹0", "0");
            if (txt8.equals("0")) {
                gmailVH.l2.setVisibility(View.INVISIBLE);
            }
            gmailVH.h_p_unit.setText(getAllProductUnitLists.get(j).getTpd_unit());
            if (getAllProductUnitLists.get(j).getTpd_image() != null) {
                try {
                    Picasso.with(context).load(getAllProductUnitLists.get(j).getTpd_image()).into(gmailVH.p_image);
                } catch (Exception e) {
                    try {
                        Picasso.with(context).load(getAllProductLists.get(i).getPro_image()).into(gmailVH.p_image);
                    } catch (Exception d) {

                    }
                }
            }


        }


        final int N = getAllProductUnitLists.size(); // total number of textviews to add
        gmailVH.unitlinear.removeAllViews();
        myTextViews = null;
        myTextViews = new TextView[N];
        ich = 0;
        gmailVH.txtich.setText(String.valueOf(ich));
        for (int j = 0; j < N; j++) {
//            list.add(pro_unit.get(j).getTpd_unit());
            final TextView unittext = new TextView(context);
            LinearLayout.LayoutParams textLayoutParams = new LinearLayout.LayoutParams(LinearLayoutCompat.LayoutParams.WRAP_CONTENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT);
            textLayoutParams.setMargins(20, 0, 0, 0);
            unittext.setLayoutParams(textLayoutParams);
            unittext.setId(Integer.parseInt(getAllProductUnitLists.get(j).getTpd_id()));
            unittext.setTextSize(15);
            ich = Integer.parseInt(gmailVH.txtich.getText().toString());
            ich++;
            gmailVH.txtich.setText(String.valueOf(ich));
            unittext.setText(getAllProductUnitLists.get(j).getTpd_unit());
            gmailVH.odetest.setText(getAllProductUnitLists.get(j).getOde_id());
            gmailVH.tdptest.setText(getAllProductUnitLists.get(j).getTpd_id());
            unittext.setBackgroundResource(R.drawable.edittext8);
            product_unit_id = getAllProductUnitLists.get(j).getTpd_id();
            myTextViews[j] = unittext;
            gmailVH.unitlinear.addView(unittext);

            unittext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    product_unit_id = String.valueOf(unittext.getId());
                    Intent i1 = new Intent(context, Product_details.class);
                    i1.putExtra("pro_id", String.format(res.getString(R.string.txt_message223), getAllProductLists.get(i).getPro_id()));
                    i1.putExtra("pro_name", String.format(res.getString(R.string.txt_message223), getAllProductLists.get(i).getPro_name()));
                    i1.putExtra("pro_unit_id", product_unit_id);
                    i1.putExtra("unittext", unittext.getText().toString());
                    i1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(i1);
                    ;


                    /*for(int p=0;p<getAllProductUnitLists.size();p++) {
                        if(getAllProductUnitLists.get(p).getTpd_id().equals(product_unit_id)) {
                            gmailVH.p_actual_cost.setText(getAllProductUnitLists.get(p).getTpd_actual_cost());
                            gmailVH.p_offer_cost.setText(getAllProductUnitLists.get(p).getTpd_offer_cost());
                            gmailVH.p_offer.setText(getAllProductUnitLists.get(p).getTpd_offer());
                            String tpdofr=getAllProductUnitLists.get(p).getTpd_offer();
                            String txt8=tpdofr.replace("SAVE₹0","0");
                            if(txt8.equals("0")){
                                gmailVH.l2.setVisibility(View.INVISIBLE);
                            }
                            gmailVH.h_p_unit.setText(unittext.getText().toString());
                            gmailVH.odetest.setText(getAllProductUnitLists.get(p).getOde_id());
                            gmailVH.tdptest.setText(getAllProductUnitLists.get(p).getTpd_id());
                            gmailVH.cartadd_min.setVisibility(View.INVISIBLE);
                            gmailVH.p_add.setVisibility(View.VISIBLE);
                            String cartval=getAllProductUnitLists.get(p).getCart_val();
                            if(Integer.parseInt(cartval)>0)
                            {
                                gmailVH.cartadd_min.setVisibility(View.VISIBLE);
                                gmailVH.p_add.setVisibility(View.INVISIBLE);
                                gmailVH.c_quantity.setText(cartval);
                            }
                            else
                            {
                                gmailVH.cartadd_min.setVisibility(View.INVISIBLE);
                                gmailVH.p_add.setVisibility(View.VISIBLE);
                            }
                            if(getAllProductUnitLists.get(p).getTpd_image()!=null) {
                                try {
                                    Picasso.with(context).load(getAllProductUnitLists.get(p).getTpd_image()).into(gmailVH.p_image);
                                } catch (Exception e) {
                                    try {
                                        Picasso.with(context).load(getAllProductLists.get(i).getPro_image()).into(gmailVH.p_image);
                                    } catch (Exception d) {

                                    }
                                }
                            }
                        }
                    }*/
                }
            });



        }
        if (Integer.parseInt(gmailVH.txtich.getText().toString()) < 2) {
            gmailVH.unitarrow.setVisibility(View.GONE);
        }

        gmailVH.p_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String l = gmailVH.qtycheck.getText().toString();
                if (Integer.parseInt(l) > Integer.parseInt(String.format(res.getString(R.string.txt_message223), getAllProductLists.get(i).getCart_val()))) {
                    addtocart(gmailVH, Config.mem_string, getAllProductLists.get(i).getUser_id(), getAllProductLists.get(i).getPro_id(), product_unit_id);
                } else {
                    Toast.makeText(context, "Can not add quantity more than " + l, Toast.LENGTH_SHORT).show();
                }
            }
        });
        if (true) {

            for (int p = 0; p < getAllProductUnitLists.size(); p++) {
                if (getAllProductUnitLists.get(p).getTpd_id().equals(pro_unit_id)) {
                    gmailVH.p_actual_cost.setText(getAllProductUnitLists.get(p).getTpd_actual_cost());
                    gmailVH.p_offer_cost.setText(getAllProductUnitLists.get(p).getTpd_offer_cost());
                    gmailVH.p_offer.setText(getAllProductUnitLists.get(p).getTpd_offer());
                    String tpdofr = getAllProductUnitLists.get(p).getTpd_offer();
                    String txt8 = tpdofr.replace("SAVE₹0", "0");
                    if (txt8.equals("0")) {
                        gmailVH.l2.setVisibility(View.INVISIBLE);
                    }
                    gmailVH.h_p_unit.setText(unittextparse);
                    gmailVH.odetest.setText(getAllProductUnitLists.get(p).getOde_id());
                    gmailVH.tdptest.setText(getAllProductUnitLists.get(p).getTpd_id());
                    gmailVH.cartadd_min.setVisibility(View.INVISIBLE);
                    gmailVH.p_add.setVisibility(View.VISIBLE);
                    String cartvall = getAllProductUnitLists.get(p).getCart_val();
                    if (Integer.parseInt(cartvall) > 0) {
                        gmailVH.cartadd_min.setVisibility(View.VISIBLE);
                        gmailVH.p_add.setVisibility(View.INVISIBLE);
                        gmailVH.c_quantity.setText(cartvall);
                    } else {
                        gmailVH.cartadd_min.setVisibility(View.INVISIBLE);
                        gmailVH.p_add.setVisibility(View.VISIBLE);
                    }
                    if (getAllProductUnitLists.get(p).getTpd_image() != null) {
                        try {
                            Picasso.with(context).load(getAllProductUnitLists.get(p).getTpd_image()).into(gmailVH.p_image);
                        } catch (Exception e) {
                            try {
                                Picasso.with(context).load(getAllProductLists.get(i).getPro_image()).into(gmailVH.p_image);
                            } catch (Exception d) {

                            }
                        }
                    }
                    product_unit_id=pro_unit_id;
                }
            }
        }

    }


    private void userproductadd(final GmailVH gmailVH, String mem_string, String user_id, String pro_id, String ode_id, String tpd_id) {
        if(ode_id.contains("success")){
            String sa[]=ode_id.split("=");
            ode_id=sa[1];
        }
        RestClient.GitApiInterface service = RestClient.getClient();
        Call<UserAddProductCart> call = service.getUserproductaddcart(mem_string, user_id, pro_id, ode_id, tpd_id);
        call.enqueue(new Callback<UserAddProductCart>() {
            @Override
            public void onResponse(Response<UserAddProductCart> response) {

                Log.d("SplashActivity", "Status Code = " + response.code());
                if (response.isSuccess()) {
                    // request successful (status code 200, 201)
                    UserAddProductCart result = response.body();
                    if (result.getStatus().equals("success")) {

                        String ttl = gmailVH.c_quantity.getText().toString();
                        gmailVH.c_quantity.setText(String.valueOf(Integer.parseInt(ttl) + 1));
                        TextView txtcart = ((Product_details) context).findViewById(R.id.cart_badge);
                        txtcart.setText(String.valueOf(Integer.parseInt(txtcart.getText().toString()) + 1));
                    } else {
                        Toast.makeText(context, result.getStatus().toString() + " proadp325", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // response received but request not successful (like 400,401,403 etc)
                    //Handle errors
                    Toast.makeText(context, "Server Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }


    private void userproductminus(final GmailVH gmailVH, String mem_string, String user_id, final String pro_id, String ode_id, String tpd_id) {
        if(ode_id.contains("success")){
            String sa[]=ode_id.split("=");
            ode_id=sa[1];
        }
        RestClient.GitApiInterface service = RestClient.getClient();
        Call<UserMinusProductCart> call = service.getUserproductminuscart(mem_string, user_id, pro_id, ode_id, tpd_id);
        call.enqueue(new Callback<UserMinusProductCart>() {
            @Override
            public void onResponse(Response<UserMinusProductCart> response) {

                Log.d("SplashActivity", "Status Code = " + response.code());
                if (response.isSuccess()) {
                    // request successful (status code 200, 201)
                    UserMinusProductCart result = response.body();
                    if (result.getStatus().equals("success")) {
                        //((Cart)context).callCartData();
                        String ttl = gmailVH.c_quantity.getText().toString();
                        gmailVH.c_quantity.setText(String.valueOf(Integer.parseInt(ttl) - 1));
                        TextView txtcart = ((Product_details) context).findViewById(R.id.cart_badge);
                        txtcart.setText(String.valueOf(Integer.parseInt(txtcart.getText().toString()) - 1));
                        if (gmailVH.c_quantity.getText().toString().equals("0")) {

                            gmailVH.p_add.setVisibility(View.VISIBLE);
                            gmailVH.cartadd_min.setVisibility(View.GONE);
                            /*Intent i1 = new Intent(context,Product_details.class);
                            i1.putExtra("pro_id",pro_id);
                            i1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            i1.putExtra("pro_name",pro_name);
                            context.startActivity(i1);*/
                        }
                    } else {
                        Toast.makeText(context, result.getStatus().toString() + "proadp388", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // response received but request not successful (like 400,401,403 etc)
                    //Handle errors
                    Toast.makeText(context, "Server Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }


    private void addtocart(final GmailVH gmailVH, String mem_string, String user_id, final String pro_id, String product_unit_id) {

        RestClient.GitApiInterface service = RestClient.getClient();
        Call<UserAddCart> call = service.getUserCartUnitAdd(mem_string, user_id, pro_id, product_unit_id);
        call.enqueue(new Callback<UserAddCart>() {

            @Override
            public void onResponse(Response<UserAddCart> response) {

                Log.d("SplashActivity", "Status Code = " + response.code());
                if (response.isSuccess()) {
                    // request successful (status code 200, 201)
                    UserAddCart result = response.body();
                    if (result.getStatus().contains("success")) {

                        String s1 = response.body().getStatus().toString();
                        s1 = s1.replace("success = ", "");
                        gmailVH.odetest.setText(s1);

                        gmailVH.p_add.setVisibility(View.GONE);
                        gmailVH.cartadd_min.setVisibility(View.VISIBLE);
                        gmailVH.c_quantity.setText("1");
                        TextView txtcart = ((Product_details) context).findViewById(R.id.cart_badge);
                        txtcart.setText(String.valueOf(Integer.parseInt(txtcart.getText().toString()) + 1));


                        /* Intent i1 = new Intent(context,Product_details.class);
                         i1.putExtra("pro_id",pro_id);
                        i1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                         i1.putExtra("pro_name",pro_name);
                         context.startActivity(i1);*/


                        //     Intent i1 = new Intent(context.getApplicationContext(),Master_Home.class);
                        //      context.startActivity(i1);
                        //      ((Master_Home)context).finish();
                    } else {
                        Toast.makeText(context, result.getStatus().toString() + " proadp433", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // response received but request not successful (like 400,401,403 etc)
                    //Handle errors
                    Toast.makeText(context, "Server Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }


    @Override
    public int getItemCount() {
        return getAllProductLists == null ? 0 : getAllProductLists.size();
    }

    static class GmailVH extends RecyclerView.ViewHolder {
        TextView p_store;
        TextView p_star;
        TextView p_offer;
        TextView p_name;
        TextView p_actual_cost;
        TextView p_offer_cost;
        //  TextView p_unit;

        TextView p_description;
        TextView p_features;
        TextView p_disclaimer;
        ImageView p_image;
        Button p_add;
        //static Spinner spinner1;
        TextView c_quantity;
        Button c_minus, c_plus;
        LinearLayout cartadd_min;
        RelativeLayout rel;
        RelativeLayout unitlin;
        ImageView unitarrow;
        LinearLayout unitlinear, l2;
        TextView h_p_unit, txtich, qtycheck, odetest, tdptest;


        public GmailVH(View itemView) {
            super(itemView);

            cartadd_min = (LinearLayout) itemView.findViewById(R.id.cartadd_min);
            c_minus = (Button) itemView.findViewById(R.id.c_minus);
            c_plus = (Button) itemView.findViewById(R.id.c_plus);
            c_quantity = (TextView) itemView.findViewById(R.id.c_quantity);
            p_store = (TextView) itemView.findViewById(R.id.p_store);
            p_image = (ImageView) itemView.findViewById(R.id.p_image);
            p_star = (TextView) itemView.findViewById(R.id.p_star);
            p_offer = (TextView) itemView.findViewById(R.id.p_offer);
            p_name = (TextView) itemView.findViewById(R.id.p_name);
            p_actual_cost = (TextView) itemView.findViewById(R.id.p_actual_cost);
            p_offer_cost = (TextView) itemView.findViewById(R.id.p_offer_cost);
            p_description = (TextView) itemView.findViewById(R.id.p_description);
            p_features = (TextView) itemView.findViewById(R.id.p_features);
            p_disclaimer = (TextView) itemView.findViewById(R.id.p_disclaimer);
            h_p_unit = (TextView) itemView.findViewById(R.id.h_p_unit);
            p_add = (Button) itemView.findViewById(R.id.p_add);
            odetest = (TextView) itemView.findViewById(R.id.odeidtest);
            tdptest = (TextView) itemView.findViewById(R.id.tdptest);
            rel = (RelativeLayout) itemView.findViewById(R.id.rel);
            unitlinear = (LinearLayout) itemView.findViewById(R.id.unitLinear);
            unitarrow = (ImageView) itemView.findViewById(R.id.unitarrow);
            unitlin = (RelativeLayout) itemView.findViewById(R.id.h_p_unitlin);
            //  spinner1 = (Spinner) itemView.findViewById(R.id.spinner1);
            txtich = (TextView) itemView.findViewById(R.id.ich);
            qtycheck = (TextView) itemView.findViewById(R.id.qtycheck);
            l2 = (LinearLayout) itemView.findViewById(R.id.l2);

        }
    }
}


