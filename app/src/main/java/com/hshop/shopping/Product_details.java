package com.hshop.shopping;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.hshop.R;
import com.hshop.adapter.SelectProductDetailAdapter;
import com.hshop.models.AllCart;
import com.hshop.models.AllCartProduct;
import com.hshop.models.AllProductDetailsList;
import com.hshop.models.AllProductUnitDetailsList;
import com.hshop.models.ProductDetails;
import com.hshop.rest.Config;
import com.hshop.rest.RestClient;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class Product_details extends AppCompatActivity {

    String user_id,pro_id,pro_name;
    String pro_unit_id="",unittext="";
    TextView  empty_view;
    RecyclerView recyclerView;
    TextView textCartItemCount;
    int mCartItemCount=0;
    List<AllCartProduct> getallCartProductLists = new ArrayList<>();
    SelectProductDetailAdapter adapter;
    List<AllProductDetailsList> getallHomeAllProductLists = new ArrayList<>();
    List<AllProductUnitDetailsList> getallHomeAllProductUnitLists = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        Intent i1 = getIntent();
        pro_id = i1.getStringExtra("pro_id");
        pro_name = i1.getStringExtra("pro_name");

        try{
            pro_unit_id= i1.getStringExtra("pro_unit_id");
            unittext= i1.getStringExtra("unittext");
        }catch (Exception e){
            Log.d("ex",""+e);
        }
        setTitle(pro_name);

        C0456b.f2467p = getSharedPreferences(C0456b.f2907a,0);
        user_id = C0456b.f2467p.getString("user_id",null);


        recyclerView = (RecyclerView) findViewById(R.id.gmail_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        empty_view = (TextView) findViewById(R.id.empty_view);

        if (adapter == null) {
            adapter = new SelectProductDetailAdapter(Product_details.this,getallHomeAllProductLists,getallHomeAllProductUnitLists,pro_id,pro_name,pro_unit_id,unittext);
            recyclerView.setAdapter(adapter);
        }

        fetchproduct(pro_id,user_id);



    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.master__home, menu);

        final MenuItem menuItem = menu.findItem(R.id.action_cart);

        View actionView = MenuItemCompat.getActionView(menuItem);
        LayoutInflater inflater= LayoutInflater.from( getBaseContext() );
        View countq =menu.findItem( R.id.action_cart ).getActionView();
        textCartItemCount = (TextView)countq.findViewById( R.id.cart_badge );
        callCartData( user_id );
        // textCartItemCount.setText( "400" );
        View view = inflater.inflate(R.layout.custon_action_item_layout,null);



        // setupBadge();
        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(menuItem);
            }
        });

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_cart) {
            Intent i1 = new Intent(
                    Product_details.this,Cart.class);
            startActivity(i1);
        }
        if (id == R.id.action_search) {
            Intent i1 = new Intent(Product_details.this,Searchactivity.class);
            startActivity(i1);
        }

        return super.onOptionsItemSelected(item);
    }


    public int callCartData(String user_id) {


        RestClient.GitApiInterface service = RestClient.getClient();

        Call<AllCart> call = service.getCartDetails( Config.mem_string,user_id);
        call.enqueue(new Callback<AllCart>() {
            @Override


            public void onResponse(Response<AllCart> response) {
                if (response.isSuccess()) {
                    // request successful (status code 200, 201)
                    AllCart result = response.body();
                    if (result.getStatus().equals("success")) {
                        if (result.getProduct() != null) {
                            if (result.getProduct().size() > 0) {
                                getallCartProductLists.clear();
                                getallCartProductLists.addAll(result.getProduct());
                                // adapter.notifyDataSetChanged();

                                if (getallCartProductLists.isEmpty()) {
                                    //     recyclerView.setVisibility(View.GONE);
                                    //    empty_view.setVisibility(View.VISIBLE);
                                    mCartItemCount= 0;
                                    textCartItemCount.setText(mCartItemCount);
                                } else {
                                    //  recyclerView.setVisibility(View.VISIBLE);
                                    //  empty_view.setVisibility(View.GONE);
                                    int qty=0;
                                    for(int i=0;i<result.getProduct().size();i++) {
                                        AllCartProduct jo = result.getProduct().get(i);
                                        qty= qty+Integer.parseInt(jo.getOde_quantity());
                                    }
                                        int a = qty;
                                        mCartItemCount = a;
                                        textCartItemCount.setText(a + "");

                                }
                            } else {
                                //recyclerView.setVisibility(View.GONE);
                                //empty_view.setVisibility(View.VISIBLE);
                            }
                        }
                    } else if (result.getStatus().equals("empty_cart")){
                    }
                    else{
                    }
                } else {
                    // response received but request not successful (like 400,401,403 etc)
                }
            }
            @Override
            public void onFailure(Throwable t) {
            }
        });
        return mCartItemCount;
    }

    public void fetchproduct(String pro_id,String user_id) {
        final DilatingDotsProgressBar mDilatingDotsProgressBar = (DilatingDotsProgressBar) findViewById(R.id.progress);
        mDilatingDotsProgressBar.showNow();
        RestClient.GitApiInterface service = RestClient.getClient();

        Call<ProductDetails> call = service.getProductDetails(Config.mem_string,pro_id,user_id);
        call.enqueue(new Callback<ProductDetails>() {
            @Override
            public void onResponse(Response<ProductDetails> response) {
                mDilatingDotsProgressBar.hideNow();
                Log.d("fgh", "Status Code = " + response.code());
                if (response.isSuccess()) {
                    // request successful (status code 200, 201)
                    ProductDetails result = response.body();

                    if (result.getStatus().equals("success")) {

                        if (result.getData() != null) {

                            if (result.getData().size() > 0) {
                                getallHomeAllProductLists.clear();
                                getallHomeAllProductLists.addAll(result.getData());
                                getallHomeAllProductUnitLists.clear();
                                getallHomeAllProductUnitLists.addAll(result.getProduct_data());
                                adapter.notifyDataSetChanged();

                                if (getallHomeAllProductLists.isEmpty()) {
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
