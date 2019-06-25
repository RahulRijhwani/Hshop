package com.hshop.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.media.Image;
import android.support.v7.widget.CardView;
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
import com.hshop.models.AllProductSubCategoryDetailsList;
import com.hshop.models.AllProductSubCategoryUnitDetailsList;
import com.hshop.models.AllProductUnitDetailsList;
import com.hshop.models.ProductUnit;
import com.hshop.models.UserAddProductCart;
import com.hshop.models.UserMinusProductCart;
import com.hshop.shopping.Cart;
import com.hshop.shopping.Checkout;
import com.hshop.shopping.Home;
import com.hshop.R;
import com.hshop.models.AllHomeProductList;
import com.hshop.models.UserAddCart;
import com.hshop.rest.Config;
import com.hshop.rest.RestClient;
import com.hshop.shopping.Master_Home;
import com.hshop.shopping.Product;
import com.hshop.shopping.Product_details;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;


public class SelectSubCategoryDetailAdapter extends RecyclerView.Adapter<SelectSubCategoryDetailAdapter.GmailVH> {
    Context context;
    static OnItemClickListener clickListener;
    List<AllProductSubCategoryDetailsList> getAllProductLists;
    List<AllProductUnitDetailsList> getAllProductUnitLists;
    ArrayList<String> list = new ArrayList<>();
    ArrayList<String> listItems=new ArrayList<>();
    ArrayAdapter<String> adapter1;
    String id,name;
    String tpd_id=null;
    TextView[] myTextViews;
    int qty=0;
    String ode_id1;
    int ich=0;
    public boolean unitch=false;
    public LinearLayout unitlin;
    public ImageView unarrow;
    public int adpp;
    TextView txtcart;


    public SelectSubCategoryDetailAdapter(Context context,List<AllProductSubCategoryDetailsList> getAllProductLists,String id,String name) {
        this.context = context;
        this.getAllProductLists = getAllProductLists;
        this.id=id;
        this.txtcart=txtcart;
        this.name=name;
       // this.getAllProductUnitLists = getAllProductUnitLists;
    }

    @Override
    public GmailVH onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_home6, viewGroup, false);
        return new GmailVH(view);
    }

    @Override
    public void onBindViewHolder(final GmailVH gmailVH, final int i) {
        //  gmailVH.title.setText("Sample Test");

        Resources res = context.getResources();
      //  String text2 = String.format(res.getString(R.string.txt_message22), "Examiner", "58 Posts", "Rs. 9300-34800/- grade Pay: Rs .4600/-", "Degree in law", "30 Years", "2016-03-31");

        String text6 = String.format(res.getString(R.string.txt_message223), getAllProductLists.get(i).getPro_name());
        String text7 = String.format(res.getString(R.string.txt_message223), getAllProductLists.get(i).getPro_description());
        String text8 = String.format(res.getString(R.string.txt_message223), getAllProductLists.get(i).getPro_offer());
        String txt8=text8.replace("₹","");
        final List<ProductUnit> pro_unit=getAllProductLists.get(i).getPro_unit1();
        String text9 = String.format(res.getString(R.string.txt_message223), getAllProductLists.get(i).getPro_offercost());
        String text10 = String.format(res.getString(R.string.txt_message223), getAllProductLists.get(i).getPro_actualcost());
        String text11 = String.format(res.getString(R.string.txt_message223), getAllProductLists.get(i).getPro_unit());
        String qtychk = String.format(res.getString(R.string.txt_message223), getAllProductLists.get(i).getQtyadd());
        final String ode_id=String.format(res.getString(R.string.txt_message223), getAllProductLists.get(i).getOde_id());
        gmailVH.h_p_name.setText(text6);
        gmailVH.l2.setVisibility(View.VISIBLE);
        if(txt8.equals("0"))
        {
            gmailVH.l2.setVisibility(View.INVISIBLE);
        }
        gmailVH.qtycheck.setText(qtychk);
        gmailVH.txt_description.setText(Html.fromHtml(text7));
        gmailVH.h_p_actual_cost.setText(Html.fromHtml(text10));
        gmailVH.h_p_offer_cost.setText(Html.fromHtml(text9));
        gmailVH.h_p_offer.setText(Html.fromHtml(text8));
        gmailVH.h_p_unit.setText(Html.fromHtml(text11));
        gmailVH.unitarrow.setImageResource(R.drawable.ic_arrow_down);
        gmailVH.unitlinear.setVisibility(View.GONE);
        gmailVH.unitlin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(gmailVH.txtich.getText().toString())>1) {
                    if (gmailVH.unitlinear.getVisibility() == View.VISIBLE) {
                        unitch=false;
                        gmailVH.unitlinear.setVisibility(View.GONE);
                        gmailVH.unitarrow.setImageDrawable(null);
                        gmailVH.unitarrow.setImageResource(R.drawable.ic_arrow_down);
                    } else {
                        if(unitch==true)
                        {
                            unitlin.setVisibility(View.GONE);
                            unarrow.setImageResource(R.drawable.ic_arrow_down);
                        }
                        gmailVH.unitlinear.setVisibility(View.VISIBLE);
                        unitch=true;
                        adpp = gmailVH.getAdapterPosition();
                        unitlin=gmailVH.unitlinear;
                        unarrow=gmailVH.unitarrow;
                        gmailVH.unitarrow.setImageDrawable(null);
                        gmailVH.unitarrow.setImageResource(R.drawable.ic_arrow_up);
                    }
                }
            }
        });
        /*if(ich<2)
        {
            gmailVH.unitarrow.setVisibility(View.GONE);
        }

*/

        gmailVH.c_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // String l=context.getResources().getString(R.string.limit);
                String l=gmailVH.qtycheck.getText().toString();
                if (Integer.parseInt(l) > Integer.parseInt(gmailVH.c_quantity.getText().toString())) {
                    userproductadd(gmailVH,Config.mem_string,getAllProductLists.get(i).getUser_id(),getAllProductLists.get(i).getPro_id(),gmailVH.test1.getText().toString(),gmailVH.test.getText().toString());
                    //int intpls= Integer.parseInt(gmailVH.test2.getText().toString())+1 ;
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
                userproductminus(gmailVH,Config.mem_string,getAllProductLists.get(i).getUser_id(),getAllProductLists.get(i).getPro_id(),gmailVH.test1.getText().toString(),gmailVH.test.getText().toString());
                int intpls= Integer.parseInt(gmailVH.test2.getText().toString())-1;
                gmailVH.test2.setText(String.valueOf(intpls));
            }
        });






        try
        {
            Picasso.with(context).load(getAllProductLists.get(i).getPro_image()).into(gmailVH.h_p_image);
        }catch (Exception e)
        {

        }
       // adapter1= new ArrayAdapter<String>(context, R.layout.drop_down, R.id.txt, listItems);

        //Log.d("fgh",adapter1.toString());
        //SelectProductDetailAdapter.GmailVH.spinner1.setAdapter(adapter1);

        final int N = pro_unit.size(); // total number of textviews to add

        myTextViews=null;
        myTextViews = new TextView[N];
        gmailVH.unitlinear.removeAllViews();
        ich=0;
        gmailVH.txtich.setText(String.valueOf(ich));
        for (int j=0;j<N;j++)
        {
//            list.add(pro_unit.get(j).getTpd_unit());
            final TextView unittext=new TextView(context);
            LinearLayout.LayoutParams textLayoutParams = new LinearLayout.LayoutParams(LinearLayoutCompat.LayoutParams.WRAP_CONTENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT);
            textLayoutParams.setMargins(20, 0, 0, 0);
            unittext.setLayoutParams(textLayoutParams);
            unittext.setId(Integer.parseInt(pro_unit.get(j).getTpd_id()));
            unittext.setTextSize(15);
            ich=Integer.parseInt(gmailVH.txtich.getText().toString());
            ich++;
            gmailVH.txtich.setText(String.valueOf(ich));
            unittext.setText(pro_unit.get(j).getTpd_unit());
            unittext.setBackgroundResource(R.drawable.edittext8);
            gmailVH.h_p_actual_cost.setText(pro_unit.get(j).getTpd_actual_cost());
            gmailVH.h_p_offer.setText(pro_unit.get(j).getTpd_offer());
            gmailVH.h_p_offer_cost.setText(pro_unit.get(j).getTpd_offer_cost());
            ode_id1=pro_unit.get(j).getOde_id();
            gmailVH.test2.setText(pro_unit.get(j).getCart_val());
            gmailVH.test.setText(pro_unit.get(j).getTpd_id());
            gmailVH.test1.setText(pro_unit.get(j).getOde_id());
            String img=pro_unit.get(j).getTpd_image();
            if(img!=null) {
                try {
                    Picasso.with(context).load(pro_unit.get(j).getTpd_image()).into(gmailVH.h_p_image);
                } catch (Exception e) {
                    try {
                        Picasso.with(context).load(getAllProductLists.get(i).getPro_image()).into(gmailVH.h_p_image);
                    } catch (Exception d) {

                    }
                }
            }

            String cartval1=String.format(pro_unit.get(j).getCart_val());
            qty= Integer.parseInt(cartval1);
            if(Integer.parseInt(cartval1)>0)
            {
                gmailVH.cartadd_min.setVisibility(View.VISIBLE);
                gmailVH.h_p_add.setVisibility(View.INVISIBLE);
                gmailVH.c_quantity.setText(cartval1);
                ode_id1=pro_unit.get(j).getOde_id();
            }
            else
            {
                gmailVH.cartadd_min.setVisibility(View.INVISIBLE);
                gmailVH.h_p_add.setVisibility(View.VISIBLE);
            }

            tpd_id=null;
            tpd_id=pro_unit.get(j).getTpd_id();
            myTextViews[j] = unittext;
            unittext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tpd_id=null;
                    tpd_id= String.valueOf(unittext.getId());
                    gmailVH.test.setText(String.valueOf(unittext.getId()));

                    for(int p=0;p<pro_unit.size();p++) {
                     if(pro_unit.get(p).getTpd_id().equals(tpd_id)) {

                         gmailVH.test1.setText(pro_unit.get(p).getOde_id());
                         gmailVH.h_p_actual_cost.setText(pro_unit.get(p).getTpd_actual_cost());
                         gmailVH.h_p_offer_cost.setText(pro_unit.get(p).getTpd_offer_cost());
                         gmailVH.h_p_offer.setText(pro_unit.get(p).getTpd_offer());
                         gmailVH.h_p_unit.setText(unittext.getText().toString());
                         ode_id1=pro_unit.get(p).getOde_id();
                         gmailVH.test2.setText(pro_unit.get(p).getCart_val());
                         String cartval1=String.format(pro_unit.get(p).getCart_val());
                         qty= Integer.parseInt(cartval1);
                         if(Integer.parseInt(cartval1)>0)
                         {
                             gmailVH.cartadd_min.setVisibility(View.VISIBLE);
                             gmailVH.h_p_add.setVisibility(View.INVISIBLE);
                             gmailVH.c_quantity.setText(cartval1);
                             ode_id1=pro_unit.get(p).getOde_id();
                         }
                         else
                         {
                             gmailVH.cartadd_min.setVisibility(View.INVISIBLE);
                             gmailVH.h_p_add.setVisibility(View.VISIBLE);
                         }



                         String img=pro_unit.get(p).getTpd_image();
                         if(img!=null) {
                             try {
                                 Picasso.with(context).load(pro_unit.get(p).getTpd_image()).into(gmailVH.h_p_image);
                             } catch (Exception e) {
                                 try {
                                     Picasso.with(context).load(getAllProductLists.get(i).getPro_image()).into(gmailVH.h_p_image);
                                 } catch (Exception d) {

                                 }
                             }
                         }
                     }
                    }
                }
            });
            gmailVH.unitlinear.addView(unittext);
        }
        if(Integer.parseInt(gmailVH.txtich.getText().toString())<2)
        {
            gmailVH.unitarrow.setVisibility(View.GONE);
        }
        else
        {
            gmailVH.unitarrow.setVisibility(View.VISIBLE);
        }

        gmailVH.h_p_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(context, gmailVH.test.getText().toString(), Toast.LENGTH_SHORT).show();
                //Toast.makeText(context, gmailVH.test1.getText().toString(), Toast.LENGTH_SHORT).show();

                addtocart(gmailVH,Config.mem_string, getAllProductLists.get(i).getUser_id(),getAllProductLists.get(i).getPro_id(),gmailVH.test.getText().toString());

            }
        });


    }

    private void show(LinearLayout unitlinear) {
    }


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
                        txtcart=((Product)context).findViewById(R.id.cart_badge);
                        txtcart.setText(String.valueOf(Integer.parseInt(txtcart.getText().toString())+1));
                    }
                    else
                    {
                        Toast.makeText(context, result.getStatus().toString()+" subcatdet359", Toast.LENGTH_SHORT).show();
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
                        txtcart=((Product)context).findViewById(R.id.cart_badge);
                        txtcart.setText(String.valueOf(Integer.parseInt(txtcart.getText().toString())-1));
                        if (gmailVH.c_quantity.getText().toString().equals("0")) {
                            gmailVH.h_p_add.setVisibility(View.VISIBLE);
                            gmailVH.cartadd_min.setVisibility(View.GONE);
                        }
                    } else {
                        Toast.makeText(context, result.getStatus().toString()+" subcatdet399", Toast.LENGTH_SHORT).show();
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
        final Call<UserAddCart> call = service.getUserCartUnitAdd(mem_string,user_id,pro_id,tpd_id);
        call.enqueue(new Callback<UserAddCart>() {
            @Override
            public void onResponse(Response<UserAddCart> response) {
                progressdialog.dismiss();
                if (response.isSuccess()) {
                    // request successful (status code 200, 201)
                    UserAddCart result = response.body();
                    if(result.getStatus().contains("success")) {
                        String s1=response.body().getStatus().toString();
                        s1=s1.replace("success = ","");
                        gmailVH.test1.setText(s1);
                        /*Intent i1 = new Intent(context,Product.class);
                       i1.putExtra("sub_cat_id",id);
                        i1.putExtra("sub_cat_name",name);
                        i1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                       context.startActivity(i1);*/
                        gmailVH.cartadd_min.setVisibility(View.VISIBLE);
                        gmailVH.c_quantity.setText("1");
                        gmailVH.h_p_add.setVisibility(View.GONE);
                        int intpls= Integer.parseInt(gmailVH.test2.getText().toString())+1;
                        gmailVH.test2.setText(String.valueOf(intpls));
                        txtcart=((Product)context).findViewById(R.id.cart_badge);
                        txtcart.setText(String.valueOf(Integer.parseInt(txtcart.getText().toString())+1));
                       // gmailVH.test2.setText("1");

                       // Intent i1 = new Intent(context.getApplicationContext(),Master_Home.class);
                       // context.startActivity(i1);
                       // ((Master_Home)context).finish();
                    }
                    else
                    {
                        Toast.makeText(context, result.getStatus().toString()+" subcatdet449", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // response received but request not successful (like 400,401,403 etc)
                    //Handle errors
                    Toast.makeText(context, "Server Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                progressdialog.dismiss();
            }
        });
    }

    @Override
    public int getItemCount() {
        return getAllProductLists == null ? 0 : getAllProductLists.size();
    }


    static class GmailVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView h_p_offer;
        TextView h_p_name;
         TextView h_p_unit;
        TextView h_p_actual_cost;
        TextView h_p_offer_cost;
        TextView txt_description;
        ImageView h_p_image;
        CardView card_view;
        Button h_p_add;
        //Spinner spinner1;
        LinearLayout cartadd_min;
        Button c_minus,c_plus;
        TextView c_quantity;
        RelativeLayout rel;
        RelativeLayout unitlin;
        ImageView unitarrow;
        TextView test,test1;
        LinearLayout unitlinear;
        TextView test2,txtich,qtycheck;
        RelativeLayout l2;


        public GmailVH(View itemView) {
            super(itemView);

            cartadd_min=(LinearLayout)itemView.findViewById(R.id.cartadd_min);
            c_minus=(Button)itemView.findViewById(R.id.c_minus);
            c_plus=(Button)itemView.findViewById(R.id.c_plus);
            c_quantity=(TextView)itemView.findViewById(R.id.c_quantity);
            txt_description = (TextView) itemView.findViewById(R.id.txt_description);
            h_p_offer = (TextView) itemView.findViewById(R.id.h_p_offer);
            h_p_image = (ImageView) itemView.findViewById(R.id.h_p_image);
            h_p_name = (TextView) itemView.findViewById(R.id.h_p_name);
            h_p_unit = (TextView) itemView.findViewById(R.id.h_p_unit);
            h_p_actual_cost = (TextView) itemView.findViewById(R.id.h_p_actual_cost);
            h_p_offer_cost = (TextView) itemView.findViewById(R.id.h_p_offer_cost);
            card_view = (CardView) itemView.findViewById(R.id.card_view);
            h_p_add = (Button) itemView.findViewById(R.id.h_p_add);
            rel=(RelativeLayout)itemView.findViewById(R.id.rel);
            unitarrow=(ImageView)itemView.findViewById(R.id.unitarrow);
            unitlin=(RelativeLayout)itemView.findViewById(R.id.h_p_unitlin);
            unitlinear=(LinearLayout) itemView.findViewById(R.id.unitLinear);
            //spinner1 = (Spinner) itemView.findViewById(R.id.spinner1);
            h_p_image.setOnClickListener(this);
            rel.setOnClickListener(this);
            test=(TextView)itemView.findViewById(R.id.test);
            test1=(TextView)itemView.findViewById(R.id.test1);
            test2=(TextView)itemView.findViewById(R.id.test2);
            txtich = (TextView) itemView.findViewById(R.id.ich);
            qtycheck = (TextView) itemView.findViewById(R.id.qtycheck);
            l2=(RelativeLayout)itemView.findViewById(R.id.l2);

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


