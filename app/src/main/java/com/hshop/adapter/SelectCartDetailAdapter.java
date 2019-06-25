package com.hshop.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.hshop.shopping.Cart;
import com.hshop.R;
import com.hshop.models.AllCartProduct;
import com.hshop.models.UserAddProductCart;
import com.hshop.models.UserMinusProductCart;
import com.hshop.rest.Config;
import com.hshop.rest.RestClient;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;


public class SelectCartDetailAdapter extends RecyclerView.Adapter<SelectCartDetailAdapter.GmailVH> {
    Context context;
    OnItemClickListener clickListener;
    List<AllCartProduct> getallCartProductLists;


    public SelectCartDetailAdapter(Context context, List<AllCartProduct> getallCartProductLists) {
        this.context = context;

        this.getallCartProductLists = getallCartProductLists;
    }

    @Override
    public GmailVH onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_cart, viewGroup, false);
        return new GmailVH(view);
    }

    @Override
    public void onBindViewHolder(final GmailVH gmailVH, final int i) {
        //  gmailVH.title.setText("Sample Test");
        final Resources res = context.getResources();
      //  String text2 = String.format(res.getString(R.string.txt_message22), "Examiner", "58 Posts", "Rs. 9300-34800/- grade Pay: Rs .4600/-", "Degree in law", "30 Years", "2016-03-31");
        String text1 = String.format(res.getString(R.string.txt_message223), getallCartProductLists.get(i).getOde_offer());
        String text2 = String.format(res.getString(R.string.txt_message223), getallCartProductLists.get(i).getPro_name());
        String text3 = String.format(res.getString(R.string.txt_message223), getallCartProductLists.get(i).getPro_unit());
        String text4 = String.format(res.getString(R.string.txt_message223), getallCartProductLists.get(i).getOde_actual_cost());
        String text5 = String.format(res.getString(R.string.txt_message223), getallCartProductLists.get(i).getOde_offer_cost());
        String text6 = String.format(res.getString(R.string.txt_message223), getallCartProductLists.get(i).getOde_quantity());
        String txt8=text1.replace("SAVE ₹0","0");
        if(txt8.equals("0")){
            gmailVH.l2.setVisibility(View.INVISIBLE);
        }
        gmailVH.c_offer.setText(text1);
        gmailVH.c_name.setText(text2);
        gmailVH.c_unit.setText(text3);
        gmailVH.c_actual_cost.setText(text4);
        gmailVH.c_offer_cost.setText(text5);
        gmailVH.c_quantity.setText(text6);
        gmailVH.cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(getallCartProductLists.get(i).getOde_id());
            }
        });
        try
        {
            Picasso.with(context).load(getallCartProductLists.get(i).getPro_image()).into(gmailVH.c_image);
        }catch (Exception e)
        {

        }
        gmailVH.c_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String l=String.format(res.getString(R.string.txt_message223), getallCartProductLists.get(i).getQtyadd());
                String txtqty=gmailVH.c_quantity.getText().toString();
                if(Integer.parseInt(l)>Integer.parseInt(txtqty)) {
                    userproductadd(Config.mem_string, getallCartProductLists.get(i).getUser_id(), getallCartProductLists.get(i).getOde_pro_id(), getallCartProductLists.get(i).getOde_id(), getallCartProductLists.get(i).getOde_tpd_id());
                }
                else
                {
                    Toast.makeText(context, "Can not add quantity more than "+l, Toast.LENGTH_SHORT).show();
                }
            }
        });

        gmailVH.c_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userproductminus(Config.mem_string,getallCartProductLists.get(i).getUser_id(),getallCartProductLists.get(i).getOde_pro_id(),getallCartProductLists.get(i).getOde_id(),getallCartProductLists.get(i).getOde_tpd_id());
            }
        });
    }

    private void delete(String ode_id) {

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url="http://hshop.co.in/API/cartremove.php?ode_id="+ode_id;
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                url,null,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("RESPONSE", response.toString());
                        String status= null;
                        try {
                            status = response.getString("status");

                            if(status.contains("failed"))
                            {
                                Toast.makeText(context, "Somthing Went Wrong", Toast.LENGTH_SHORT).show();
                            }
                            else if(status.contains("success")){
                                ((Cart)context).callCartData();
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
    }

    private void userproductadd(String mem_string, String user_id, String pro_id,String ode_id,String tpd_id) {
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
                        ((Cart)context).callCartData();
                    }
                    else
                    {
                        Toast.makeText(context, "Plz TRY again Later", Toast.LENGTH_SHORT).show();
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

    private void userproductminus(String mem_string, String user_id, String pro_id,String ode_id,String tpd_id) {
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
                        ((Cart)context).callCartData();
                    }
                    else
                    {
                        Toast.makeText(context, "Plz TRY again Later", Toast.LENGTH_SHORT).show();
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
        return getallCartProductLists == null ? 0 : getallCartProductLists.size();
    }


    class GmailVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView c_offer;
        TextView c_name;
        TextView c_unit;
        TextView c_actual_cost;
        TextView c_offer_cost;
        TextView c_quantity;
        ImageView c_image;
        Button c_minus;
        Button c_plus,cart;
        LinearLayout l2;


        public GmailVH(View itemView) {
            super(itemView);

            c_offer = (TextView) itemView.findViewById(R.id.c_offer);
            c_image = (ImageView) itemView.findViewById(R.id.c_image);
            c_name = (TextView) itemView.findViewById(R.id.c_name);
            c_unit = (TextView) itemView.findViewById(R.id.c_unit);
            c_actual_cost = (TextView) itemView.findViewById(R.id.c_actual_cost);
            c_offer_cost = (TextView) itemView.findViewById(R.id.c_offer_cost);
            c_quantity = (TextView) itemView.findViewById(R.id.c_quantity);
            c_minus = (Button) itemView.findViewById(R.id.c_minus);
            c_plus = (Button) itemView.findViewById(R.id.c_plus);
            cart=(Button)itemView.findViewById(R.id.cart);
            l2=(LinearLayout)itemView.findViewById(R.id.l2);

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


