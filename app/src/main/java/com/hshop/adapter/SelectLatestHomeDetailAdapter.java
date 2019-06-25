package com.hshop.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.hshop.models.AllProductUnitDetailsList;
import com.hshop.models.UserAddProductCart;
import com.hshop.models.UserMinusProductCart;
import com.hshop.models.unitpojo;
import com.hshop.shopping.Cart;
import com.hshop.shopping.Master_Home;
import com.hshop.R;
import com.hshop.models.AllLatestHomeProductList;
import com.hshop.models.UserAddCart;
import com.hshop.rest.Config;
import com.hshop.rest.RestClient;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;


public class SelectLatestHomeDetailAdapter extends RecyclerView.Adapter<SelectLatestHomeDetailAdapter.GmailVH> {
    Context context;
    OnItemClickListener clickListener;
    List<AllLatestHomeProductList> getallLatestHomeAllProductLists;
    List<AllProductUnitDetailsList> getAllProductUnitLists;
    ArrayList<String> list = new ArrayList<>();
    ArrayList<String> listItems = new ArrayList<>();
    ArrayAdapter<String> adapter1;
    int TotalCount = 0;
    String product_unit_id,user_id;
    ArrayList<unitpojo> event=new ArrayList<unitpojo>();


    public SelectLatestHomeDetailAdapter(Context context,String user_id, List<AllLatestHomeProductList> getallLatestHomeAllProductLists) {
        this.context = context;
        this.user_id=user_id;
        this.getallLatestHomeAllProductLists = getallLatestHomeAllProductLists;
    }

    @Override
    public GmailVH onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_home_pre, viewGroup, false);
        return new GmailVH(view);
    }

    @Override
    public void onBindViewHolder(final GmailVH gmailVH, final int i) {
        //  gmailVH.title.setText("Sample Test");

        Resources res = context.getResources();
        //  String text2 = String.format(res.getString(R.string.txt_message22), "Examiner", "58 Posts", "Rs. 9300-34800/- grade Pay: Rs .4600/-", "Degree in law", "30 Years", "2016-03-31");
        String text1 = String.format(res.getString(R.string.txt_message223), getallLatestHomeAllProductLists.get(i).getPro_id());
     //   getunit(gmailVH,text1);
        String text2 = String.format(res.getString(R.string.txt_message223), getallLatestHomeAllProductLists.get(i).getPro_image());
        String text3 = String.format(res.getString(R.string.txt_message223), getallLatestHomeAllProductLists.get(i).getPro_offer());
        String text4 = String.format(res.getString(R.string.txt_message223), getallLatestHomeAllProductLists.get(i).getPro_actualcost());
        String text5 = String.format(res.getString(R.string.txt_message223), getallLatestHomeAllProductLists.get(i).getPro_offercost());
        String text6 = String.format(res.getString(R.string.txt_message223), getallLatestHomeAllProductLists.get(i).getPro_name());
        String text7 = String.format(res.getString(R.string.txt_message223), getallLatestHomeAllProductLists.get(i).getPro_unit());
        String qtyck = String.format(res.getString(R.string.txt_message223), getallLatestHomeAllProductLists.get(i).getQtyadd());
        final String ode_id = String.format(res.getString(R.string.txt_message223), getallLatestHomeAllProductLists.get(i).getOde_id());
        gmailVH.odetest.setText(ode_id);
        gmailVH.h_p_offer.setText(text3);
        gmailVH.qtycheck.setText(qtyck);
        gmailVH.h_p_name.setText(text6);
        gmailVH.h_p_unit.setText(text7);
        gmailVH.h_p_actual_cost.setText(text4);

        String cartval = String.format(res.getString(R.string.txt_message223), getallLatestHomeAllProductLists.get(i).getCart_val());


        gmailVH.c_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String l=gmailVH.qtycheck.getText().toString();
                if (Integer.parseInt(l) > Integer.parseInt(gmailVH.c_quantity.getText().toString())) {
                    userproductadd(gmailVH, Config.mem_string, getallLatestHomeAllProductLists.get(i).getUser_id(), getallLatestHomeAllProductLists.get(i).getPro_id(), gmailVH.odetest.getText().toString(), getallLatestHomeAllProductLists.get(i).getTpd_id());
                } else {
                    Toast.makeText(context, "Can not add quantity more than " + l, Toast.LENGTH_SHORT).show();
                }


            }
        });

        gmailVH.c_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userproductminus(gmailVH, Config.mem_string, getallLatestHomeAllProductLists.get(i).getUser_id(), getallLatestHomeAllProductLists.get(i).getPro_id(), gmailVH.odetest.getText().toString(), getallLatestHomeAllProductLists.get(i).getTpd_id());
            }
        });


        if (Integer.parseInt(cartval) > 0) {
            gmailVH.cartadd_min.setVisibility(View.VISIBLE);
            gmailVH.h_p_add.setVisibility(View.INVISIBLE);
            gmailVH.c_quantity.setText(cartval);
        } else {
            gmailVH.cartadd_min.setVisibility(View.INVISIBLE);
            gmailVH.h_p_add.setVisibility(View.VISIBLE);
        }


        gmailVH.h_p_offer_cost.setText(text5);
        try {
            Picasso.with(context).load(getallLatestHomeAllProductLists.get(i).getPro_image()).into(gmailVH.h_p_image);

        } catch (Exception e) {

        }
        //adapter1 = new ArrayAdapter<String>(context, R.layout.drop_down, R.id.txt, listItems);

//        Log.d("fgh", adapter1.toString());
      //  gmailVH.spinner1.setAdapter(adapter1);

        /*gmailVH.spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                gmailVH.h_p_actual_cost.setText(event.get(i).getH_p_actual_cost());
                gmailVH.h_p_offer_cost.setText(event.get(i).getH_p_offer_cost());
                gmailVH.h_p_offer.setText(event.get(i).getH_p_offer());
                product_unit_id = event.get(i).getTpd_id();
                String cartval1=event.get(i).getCart_val();
                if (Integer.parseInt(cartval1) > 0) {
                    gmailVH.cartadd_min.setVisibility(View.VISIBLE);
                    gmailVH.h_p_add.setVisibility(View.INVISIBLE);
                    gmailVH.c_quantity.setText(cartval1);
                } else {
                    gmailVH.cartadd_min.setVisibility(View.INVISIBLE);
                    gmailVH.h_p_add.setVisibility(View.VISIBLE);
                }
                // Toast.makeText(context, adapterView.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });*/

        gmailVH.h_p_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addtocart(gmailVH,Config.mem_string, getallLatestHomeAllProductLists.get(i).getUser_id(), getallLatestHomeAllProductLists.get(i).getPro_id(), getallLatestHomeAllProductLists.get(i).getTpd_id());
            }
        });


    }

    /*private void getunit(final GmailVH gmailVH, final String text1) {


        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = "http://hshop.co.in/API/productunit.php?proid=" + text1+"&uid=" + user_id;
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                url, null,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("RESPONSE", response.toString());
                        String status = null;
                        try {
                            status = response.getString("status");

                            if (status.contains("failed")) {
                                Log.d("search", status.toString());
                            } else if (status.contains("success")) {
                                JSONArray jsonArray=response.getJSONArray("productunit");
                                if(jsonArray.length()>0)
                                {

                                    event.clear();
                                    list.clear();
                                    listItems.clear();
                                    gmailVH.spinner1.setAdapter(null);
                                    for(int i=0;i<jsonArray.length();i++)
                                    {
                                        unitpojo pojo=new unitpojo();
                                        JSONObject obj = jsonArray.getJSONObject (i);
                                        pojo.setH_p_actual_cost(obj.getString("tpd_actual_cost"));
                                        pojo.setTdp_pro_id(obj.getString("tpd_pro_id"));
                                        pojo.setTpd_id(obj.getString("tpd_id"));
                                        pojo.setH_p_offer_cost(obj.getString("tpd_offer_cost"));
                                        pojo.setH_p_offer(obj.getString("tpd_offer"));
                                        pojo.setTdp_unit(obj.getString("tpd_unit"));
                                        pojo.setCart_val(obj.getString("cart_val"));
                                        list.add(obj.getString("tpd_unit"));
                                        event.add(pojo);
                                    }

                                    list.clear();
                                    listItems.clear();
                                    for (int j = 0; j < event.size(); j++) {

                                        if (event.get(j).getTdp_pro_id().equals(text1))
                                        {
                                            list.add(event.get(j).getTdp_unit());
                                        }
                                    }

                                    listItems.addAll(list);


                                    adapter1 = new ArrayAdapter<String>(context, R.layout.drop_down, R.id.txt, listItems);
                                    adapter1.notifyDataSetChanged();
                                    gmailVH.spinner1.setAdapter(adapter1);
                                    Log.d("fgh12", adapter1.toString());


                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("RESPONSE", "That didn't work!");
            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);
    }*/


    private void userproductadd(final GmailVH gmailVH, String mem_string, String user_id, String pro_id, String ode_id, String tpd_id) {
        RestClient.GitApiInterface service = RestClient.getClient();
        Call<UserAddProductCart> call = service.getUserproductaddcart(mem_string,user_id,pro_id,ode_id,tpd_id);
        call.enqueue(new Callback<UserAddProductCart>() {
            @Override
            public void onResponse(Response<UserAddProductCart> response) {

                Log.d("SplashActivity", "Status Code = " + response.code());
                if (response.isSuccess()) {
                    // request successful (status code 200, 201)
                    UserAddProductCart result = response.body();
                    if(result.getStatus().equals("success")) {

                        String ttl=gmailVH.c_quantity.getText().toString();
                        gmailVH.c_quantity.setText(String.valueOf(Integer.parseInt(ttl)+1));
                        TextView txtcart=((Master_Home)context).findViewById(R.id.cart_badge);
                        txtcart.setText(String.valueOf(Integer.parseInt(txtcart.getText().toString())+1));
                    }
                    else
                    {
                        Toast.makeText(context, result.getStatus().toString()+" homeletdet287", Toast.LENGTH_SHORT).show();
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



    private void userproductminus(final GmailVH gmailVH, String mem_string, String user_id, String pro_id, String ode_id, String tpd_id) {
        RestClient.GitApiInterface service = RestClient.getClient();
        Call<UserMinusProductCart> call = service.getUserproductminuscart(mem_string,user_id,pro_id,ode_id,tpd_id);
        call.enqueue(new Callback<UserMinusProductCart>() {
            @Override
            public void onResponse(Response<UserMinusProductCart> response) {

                Log.d("SplashActivity", "Status Code = " + response.code());
                if (response.isSuccess()) {
                    // request successful (status code 200, 201)
                    UserMinusProductCart result = response.body();
                    if(result.getStatus().equals("success")) {
                        //((Cart)context).callCartData();
                        String ttl=gmailVH.c_quantity.getText().toString();
                        gmailVH.c_quantity.setText(String.valueOf(Integer.parseInt(ttl)-1));
                        TextView txtcart=((Master_Home)context).findViewById(R.id.cart_badge);
                        txtcart.setText(String.valueOf(Integer.parseInt(txtcart.getText().toString())-1));
                        if(gmailVH.c_quantity.getText().toString().equals("0"))
                        {
                            gmailVH.h_p_add.setVisibility(View.VISIBLE);
                            gmailVH.cartadd_min.setVisibility(View.GONE);
                            /*Intent i1 = new Intent(context.getApplicationContext(),Master_Home.class);
                            context.startActivity(i1);
                            ((Master_Home)context).finish();*/

                        }
                    }
                    else
                    {
                        Toast.makeText(context, result.getStatus().toString()+" homeletdet334", Toast.LENGTH_SHORT).show();
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




    private void addtocart(final GmailVH gmailVH, String mem_string, String user_id, String pro_id, String tpd_id) {
        final ProgressDialog progressdialog = new ProgressDialog(context);
        progressdialog.setMessage("Please Wait....");
        progressdialog.show();
        RestClient.GitApiInterface service = RestClient.getClient();
        Call<UserAddCart> call = service.getUserCartAdd(mem_string,user_id,pro_id,tpd_id);
        call.enqueue(new Callback<UserAddCart>() {

            @Override
            public void onResponse(Response<UserAddCart> response) {
                progressdialog.dismiss();
                Log.d("SplashActivity", "Status Code = " + response.code());
                if (response.isSuccess()) {
                    // request successful (status code 200, 201)
                    UserAddCart result = response.body();
                    if(result.getStatus().contains("success")) {
                        /*Intent i1 = new Intent(context.getApplicationContext(),Master_Home.class);
                        context.startActivity(i1);
                        ((Master_Home)context).finish();*/
                        String s1=response.body().getStatus().toString();
                        s1=s1.replace("success = ","");
                        gmailVH.odetest.setText(s1);
                        gmailVH.h_p_add.setVisibility(View.GONE);
                        gmailVH.cartadd_min.setVisibility(View.VISIBLE);
                        gmailVH.c_quantity.setText("1");
                        TextView txtcart=((Master_Home)context).findViewById(R.id.cart_badge);
                        txtcart.setText(String.valueOf(Integer.parseInt(txtcart.getText().toString())+1));
                       // TextView txtcart=((Master_Home)context).findViewById(R.id.cart_badge);
                       // txtcart.setText(Integer.parseInt(txtcart.getText().toString())+1);

                      //  Intent i1 = new Intent(context,Master_Home.class);
                      //  context.startActivity(i1);
                    }
                    else
                    {
                        Toast.makeText(context, result.getStatus().toString()+" homeletdet388", Toast.LENGTH_SHORT).show();
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
        return getallLatestHomeAllProductLists == null ? 0 : getallLatestHomeAllProductLists.size();
    }


    class GmailVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView h_p_offer;
        TextView h_p_name;
        TextView h_p_unit;
        //Spinner spinner1;
        TextView h_p_actual_cost;
        TextView h_p_offer_cost;
        ImageView h_p_image;
        CardView card_view;
        Button h_p_add;
        LinearLayout cartadd_min;
        Button c_minus;
        Button c_plus;
        TextView c_quantity,odetest,qtycheck;


        public GmailVH(View itemView) {
            super(itemView);

            cartadd_min=(LinearLayout)itemView.findViewById(R.id.cartadd_min);
            c_minus=(Button)itemView.findViewById(R.id.c_minus);
            c_plus=(Button)itemView.findViewById(R.id.c_plus);
            c_quantity=(TextView)itemView.findViewById(R.id.c_quantity);
            h_p_offer = (TextView) itemView.findViewById(R.id.h_p_offer);
            h_p_image = (ImageView) itemView.findViewById(R.id.h_p_image);
            h_p_name = (TextView) itemView.findViewById(R.id.h_p_name);
            h_p_unit = (TextView) itemView.findViewById(R.id.h_p_unit);
            //spinner1 = (Spinner) itemView.findViewById(R.id.spinner1);
            h_p_actual_cost = (TextView) itemView.findViewById(R.id.h_p_actual_cost);
            h_p_offer_cost = (TextView) itemView.findViewById(R.id.h_p_offer_cost);
            card_view = (CardView) itemView.findViewById(R.id.card_view);
            h_p_add = (Button) itemView.findViewById(R.id.h_p_add);
            card_view.setOnClickListener(this);
            odetest = (TextView) itemView.findViewById(R.id.odeidtest);
            qtycheck = (TextView) itemView.findViewById(R.id.qtycheck);

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


