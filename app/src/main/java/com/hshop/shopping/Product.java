package com.hshop.shopping;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.hshop.R;
import com.hshop.adapter.Phone;
import com.hshop.adapter.SelectSubCategoryDetailAdapter;
import com.hshop.adapter.SubCategoryAdapterList;
import com.hshop.models.AllCart;
import com.hshop.models.AllCartProduct;
import com.hshop.models.AllHome;
import com.hshop.models.AllHomeExpandCatList;
import com.hshop.models.AllProductSubCategoryDetailsList;
import com.hshop.models.AllProductUnitDetailsList;
import com.hshop.models.SubCategoryDetails;
import com.hshop.rest.Config;
import com.hshop.rest.RestClient;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class Product extends AppCompatActivity {

    String search;
    SubCategoryAdapterList catadb=null;
    RecyclerView recyclerView,subcat;
    SelectSubCategoryDetailAdapter adapter;

    SelectSubCategoryDetailAdapter adapter1;
    List<AllProductUnitDetailsList> getallHomeAllProductUnitLists = new ArrayList<>();

    List<AllProductSubCategoryDetailsList> getallHomeAllProductLists = new ArrayList<>();
    int eventposition=0;
    TextView empty_view;
    String user_id;
    TextView textCartItemCount;
    List<AllHomeExpandCatList> getallHomeAllExpandLists = new ArrayList<>();

    int mCartItemCount=0;
    List<AllCartProduct> getallCartProductLists = new ArrayList<>();
    ArrayList<SubCategoryDetails> event=new ArrayList<SubCategoryDetails>();
    RelativeLayout r1;
    String sub_cat_id,sub_cat_name,recid=null,filter=null,nexsub_cat_id,nexsub_cat_name,nexfilter=null,persub_cat_id,persub_cat_name,perfilter=null,offer=null;
    private OnFeedItemClickListener onFeedItemClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        r1=(RelativeLayout)findViewById(R.id.r1);

        Intent i1 = getIntent();
        recyclerView = (RecyclerView) findViewById(R.id.gmail_list);

        try
        {
            sub_cat_id = i1.getStringExtra("sub_cat_id");
            sub_cat_name = i1.getStringExtra("sub_cat_name");
            setTitle(sub_cat_name);
            filter=i1.getStringExtra("filter");
            recid=i1.getStringExtra("recid");


            try{
                persub_cat_id = i1.getStringExtra("persub_cat_id");
                persub_cat_name = i1.getStringExtra("persub_cat_name");
                perfilter=i1.getStringExtra("perfilter");
            }catch (Exception ex) {}

            try{
                nexsub_cat_id = i1.getStringExtra("nexsub_cat_id");
                nexsub_cat_name = i1.getStringExtra("nexsub_cat_name");
                nexfilter=i1.getStringExtra("nexfilter");
            }catch (Exception ex) {}

            if(!i1.getStringExtra("offer").equals("")) {
            offer = i1.getStringExtra("offer");
            setTitle(i1.getStringExtra("offer_name"));
            }
            if(sub_cat_id==null && filter==null)
            {
                search = i1.getStringExtra("search");
                setTitle(search);
            }
        }catch (Exception e) {
            try{
                search = i1.getStringExtra("search");
                setTitle(search);
            }catch (Exception e1)
            {
                try {
                    offer = i1.getStringExtra("offer");
                    setTitle(i1.getStringExtra("offer_name"));
                }
                catch (Exception eo)
                {

                   }
            }
            Log.d("error",e.toString());
        }


        subcat=(RecyclerView)findViewById( R.id.subcatrecycle );
       // SubCategoryDetails cat=new SubCategoryDetails();
       // cat.setSubCategoryName( "Mobiles" );
        //cat.setSubCategoryId( "1" );
        //SubCategoryDetails cat1=new SubCategoryDetails();
        //cat1.setSubCategoryName( "Mobiles" );
        //cat1.setSubCategoryId( "1" );
        //SubCategoryDetails cat2=new SubCategoryDetails();
       // cat2.setSubCategoryName( "Mobiles" );
       // cat2.setSubCategoryId( "1" );



        empty_view = (TextView) findViewById(R.id.empty_view);
        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        C0456b.f2467p = getSharedPreferences(C0456b.f2907a,0);
        user_id = C0456b.f2467p.getString("user_id",null);
       /* recyclerView.setOnTouchListener(new OnSwipeTouchListener(Product.this){
            public void onSwipeRight() {
                //Toast.makeText(Product.this, "right", Toast.LENGTH_SHORT).show();
                if(eventposition>0)
                {
                    eventposition=eventposition-1;
                    callEventData2(Config.mem_string, event.get(eventposition).getFilterid());
                    setTitle(event.get(eventposition).getSubCategoryName());
                }
            }
            public void onSwipeLeft() {
                //Toast.makeText(Product.this, "left", Toast.LENGTH_SHORT).show();
                if(eventposition<event.size())
                {
                    eventposition=eventposition+1;
                    callEventData2(Config.mem_string, event.get(eventposition).getFilterid());
                    setTitle(event.get(eventposition).getSubCategoryName());

                }



            }
        });*/

        //String pro_id=getallHomeAllProductLists.get(position).getPro_id();
       // callEventData1(Config.mem_string,user_id);


        // event.add( cat );
        // event.add( cat1 );
        // event.add( cat2 );


        if (adapter == null) {
            adapter = new SelectSubCategoryDetailAdapter(Product.this,getallHomeAllProductLists,sub_cat_id,sub_cat_name);
            recyclerView.setAdapter(adapter);
        }
        adapter.SetOnItemClickListener(new SelectSubCategoryDetailAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(Product.this,Product_details.class).putExtra("pro_id",getallHomeAllProductLists.get(position).getPro_id()).putExtra("pro_name",getallHomeAllProductLists.get(position).getPro_name()));
            }
        });

        if(search==null) {
            if (filter == null) {
                if (offer == null)
                {
                    setTitle(sub_cat_name);
                    callEventData(Config.mem_string, sub_cat_id);
                }
                else {
                    setTitle(i1.getStringExtra("offer_name"));
                    callEventData3(Config.mem_string, offer);
                }
            } else {
                callEventData2(Config.mem_string, filter);
            }

            callfilter(sub_cat_id);
        }
        else
        {
            callEventData2(Config.mem_string, search);
        }
        //adpset();
        if(filter!=null)
        {
            for(int ij=0;ij<event.size();ij++){

                if((event.get(ij).getFilterid()).equals(filter));
                {
                    setTitle(event.get(ij).getSubCategoryName());
                }
            }
        }
        /*recyclerView.setOnTouchListener(new OnSwipeTouchListener(Product.this){
            public void onSwipeRight() {
                Toast.makeText(Product.this, "right", Toast.LENGTH_SHORT).show();
                if(eventposition>0)
                {
                    eventposition=eventposition-1;
                    callEventData2(Config.mem_string, event.get(eventposition).getFilterid());
                    setTitle(event.get(eventposition).getSubCategoryName());
                }
            }
            public void onSwipeLeft() {
                Toast.makeText(Product.this, "left", Toast.LENGTH_SHORT).show();
                if(eventposition<event.size())
                {
                    eventposition=eventposition+1;
                    callEventData2(Config.mem_string, event.get(eventposition).getFilterid());
                    setTitle(event.get(eventposition).getSubCategoryName());

                }



            }
        });*/
    }
    void adpset()
    {
        if(catadb!=null) {
            subcat.smoothScrollToPosition(5);
            catadb.notifyDataSetChanged();
            //adapter.notifyDataSetChanged();
        }
        else{
            adpset();
        }

        if(sub_cat_name.equals(""))
        {

        }
        else{
            setTitle(sub_cat_name);
        }



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
                                //getallHomeAllProductUnitLists.addAll()
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
                                    mCartItemCount= a ;
                                    textCartItemCount.setText(a + "");
                                }
                            } else {
                                //recyclerView.setVisibility(View.GONE);
                                //empty_view.setVisibility(View.VISIBLE);


                            }
                        }

                    } else if (result.getStatus().equals("empty_cart")){

                    }
                    else
                    {

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
    @Override
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
            Intent i1 = new Intent(Product.this,Cart.class);
            startActivity(i1);
        }
        if (id == R.id.action_search) {
            Intent i1 = new Intent(Product.this,Searchactivity.class);
            startActivity(i1);
        }

        return super.onOptionsItemSelected(item);
    }

    public void callEventData(String mem_string,String sub_cat_id) {
        final DilatingDotsProgressBar mDilatingDotsProgressBar = (DilatingDotsProgressBar) findViewById(R.id.progress);
        mDilatingDotsProgressBar.showNow();
        RestClient.GitApiInterface service = RestClient.getClient();

        Call<SubCategoryDetails> call = service.getSubCategoryDetails(mem_string,sub_cat_id,user_id);
        call.enqueue(new Callback<SubCategoryDetails>() {
            @Override
            public void onResponse(Response<SubCategoryDetails> response) {
               mDilatingDotsProgressBar.hideNow();
                Log.d("fgh", "Status Code = " + response.code());
                if (response.isSuccess()) {
                    // request successful (status code 200, 201)
                    SubCategoryDetails result = response.body();

                    if (result.getStatus().equals("success")) {

                        if (result.getData() != null) {

                            if (result.getData().size() > 0) {

                                getallHomeAllProductLists.clear();
                                getallHomeAllProductLists.addAll(result.getData());
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

                    } else {

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

    public void callEventData2(String mem_string,String filter) {
        final DilatingDotsProgressBar mDilatingDotsProgressBar = (DilatingDotsProgressBar) findViewById(R.id.progress);
        mDilatingDotsProgressBar.showNow();
        RestClient.GitApiInterface service = RestClient.getClient();

        Call<SubCategoryDetails> call = service.getFilterDetails(mem_string,user_id,filter);
        call.enqueue(new Callback<SubCategoryDetails>() {
            @Override
            public void onResponse(Response<SubCategoryDetails> response) {
                mDilatingDotsProgressBar.hideNow();
                Log.d("fgh", "Status Code = " + response.code());
                if (response.isSuccess()) {
                    // request successful (status code 200, 201)
                    SubCategoryDetails result = response.body();

                    if (result.getStatus().equals("success")) {

                        if (result.getData() != null) {

                            if (result.getData().size() > 0) {

                                getallHomeAllProductLists.clear();
                                getallHomeAllProductLists.addAll(result.getData());
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

                    } else {

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

    public void callEventData3(String mem_string,String offer) {
        final DilatingDotsProgressBar mDilatingDotsProgressBar = (DilatingDotsProgressBar) findViewById(R.id.progress);
        mDilatingDotsProgressBar.showNow();
        RestClient.GitApiInterface service = RestClient.getClient();

        Call<SubCategoryDetails> call = service.getOfferDetails(mem_string,user_id,offer);
        call.enqueue(new Callback<SubCategoryDetails>() {
            @Override
            public void onResponse(Response<SubCategoryDetails> response) {
                mDilatingDotsProgressBar.hideNow();
                Log.d("fgh", "Status Code = " + response.code());
                if (response.isSuccess()) {
                    // request successful (status code 200, 201)
                    SubCategoryDetails result = response.body();

                    if (result.getStatus().equals("success")) {

                        if (result.getData() != null) {

                            if (result.getData().size() > 0) {
                                getallHomeAllProductLists.clear();
                                getallHomeAllProductLists.addAll(result.getData());
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

                    } else {

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

    public interface OnFeedItemClickListener {

        public void onAdapterItemClick(int position);

    }
    public void setOnFeedItemClickListener(OnFeedItemClickListener onFeedItemClickListener) {
        this.onFeedItemClickListener = onFeedItemClickListener;
    }



    public void callfilter(String sub_cat_id)
    {



        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest ( Request.Method.POST, "http://hshop.co.in/API/getkeyword.php?subcat_id="+sub_cat_id, null, new com.android.volley.Response.Listener<JSONObject> () {
            @Override
            public void onResponse(JSONObject response) {


                try {
                    boolean code = response.getBoolean ("status");
                    if (code == true) {

                        JSONArray array = response.getJSONArray ("data");

                        if (array.length () > 0) {
                            for (int n = 0; n < array.length (); n++) {
                                JSONObject obj = array.getJSONObject(n);
                                String name = obj.getString("keyword");
                                String subid = obj.getString("subcat_id");
                                String id = obj.getString("filter_id");
                                // adapCategoryProd = new categoryAdapter (this, categoryList);
                                //recyclerView.setAdapter (adapCategoryProd);
                                //recyclerView.setLayoutManager (new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));


                                SubCategoryDetails cat1 = new SubCategoryDetails();
                                cat1.setSubCategoryName(name);
                                cat1.setSubCategoryId(subid);
                                cat1.setFilterid(id);
                                event.add(cat1);

                                    if(id.equals(filter)){
                                        int size=event.size();
                                        eventposition=size-1;
                                        setTitle(name);
                                        subcat.smoothScrollToPosition(eventposition);
                                        //catadb.notifyDataSetChanged();
                                    }


                            }

                                catadb=new SubCategoryAdapterList( Product.this,  event);
                                subcat.setAdapter( catadb );

                                subcat.setLayoutManager( new LinearLayoutManager( Product.this,LinearLayoutManager.HORIZONTAL,false ) );
                                //subcat.getLayoutManager().scrollToPosition();
                                if(recid!=null)
                                {
                                    //subcat.smoothScrollToPosition(Integer.parseInt(recid));
                                    subcat.getLayoutManager().scrollToPosition(Integer.parseInt(recid));
                                }


                        }
                    }
                    else
                    {

                    }
                } catch (JSONException e) {
                    e.printStackTrace ();
                }


            }
        },
                new com.android.volley.Response.ErrorListener () {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(Product.this);
        requestQueue.add(jsonObjectRequest);
    }

    public void callEventData1(String mem_string,String user_id) {
       final DilatingDotsProgressBar mDilatingDotsProgressBar = (DilatingDotsProgressBar) findViewById(R.id.progress);
        mDilatingDotsProgressBar.showNow();
        RestClient.GitApiInterface service = RestClient.getClient();

        Call<AllHome> call = service.getAllHome(mem_string,user_id);
        call.enqueue(new Callback<AllHome>() {
            @Override
            public void onResponse(Response<AllHome> response) {
                //mDilatingDotsProgressBar.hideNow();
                Log.d("fgh", "Status Code = " + response.code());
                if (response.isSuccess()) {
                    // request successful (status code 200, 201)
                    AllHome result = response.body();

                    if (result.getStatus().equals("success")) {


                        if (result.getExpandcat() != null) {
                            if (result.getExpandcat().size() > 0) {
                              //  getallHomeAllExpandLists.clear();
                                getallHomeAllExpandLists.addAll(result.getExpandcat());
                                //  adapter2.notifyDataSetChanged();



                                for (int i =0 ;i<getallHomeAllExpandLists.size();i++){
                                    ArrayList<Phone> iphones = new ArrayList<>();
                                    String name = getallHomeAllExpandLists.get(i).getCat_name();
                                    String description = getallHomeAllExpandLists.get(i).getCat_description();
                                    String offer = getallHomeAllExpandLists.get(i).getCat_offer();
                                    String image = getallHomeAllExpandLists.get(i).getCat_image();

                                    for (int j=0;j<getallHomeAllExpandLists.get(i).getExpandsubcat().size();j++)
                                    {
                                        String subname = getallHomeAllExpandLists.get(i).getExpandsubcat().get(j).getSub_name();
                                        String subimage = getallHomeAllExpandLists.get(i).getExpandsubcat().get(j).getSub_image();
                                        String suboffer = getallHomeAllExpandLists.get(i).getExpandsubcat().get(j).getSub_offer();
                                        String subid = getallHomeAllExpandLists.get(i).getExpandsubcat().get(j).getSub_id();
                                        String subdetails = getallHomeAllExpandLists.get(i).getExpandsubcat().get(j).getSub_description();

                                        SubCategoryDetails cat=new SubCategoryDetails();
                                        cat.setSubCategoryName( subname );
                                        cat.setSubCategoryId( subid );
                                        event.add(cat);
                                        catadb=new SubCategoryAdapterList( Product.this,  event);
                                        subcat.setAdapter( catadb );
                                        subcat.setLayoutManager( new LinearLayoutManager( Product.this,LinearLayoutManager.HORIZONTAL,false ) );

                                     /*   adapter1.SetOnItemClickListener(new SelectHomeDetailAdapter1.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(View view, int position) {
                                                startActivity(new Intent(getBaseContext(),Product.class).putExtra("sub_cat_id",getallHomeAllExpandLists.get(position).getExpandsubcat().get(position).getSub_cat_id()).putExtra("sub_cat_name",getallHomeAllExpandLists.get(position).getExpandsubcat().get(position).getSub_name()));
                                            }
                                        });
*/
                                        // iphones.add(new Phone(subname+"!-"+subimage+"!-"+suboffer+"!-"+subid+"!-"+subdetails));
                                    }
                                  ///  mobileOSes.add(new MobileOS(name+"!-"+description+"!-"+offer+"!-"+image, iphones));
                                }
                               // adapter2 = new RecyclerAdapter(getContext(),mobileOSes);
                               // recyclerView2.setAdapter(adapter2);




                            } else {
                             //   recyclerView2.setVisibility(View.GONE);
                         //       empty_view.setVisibility(View.VISIBLE);
                            }
                        }

                    } else {
                       // recyclerView1.setVisibility(View.GONE);
                       // recyclerView2.setVisibility(View.GONE);
                       // recyclerView3.setVisibility(View.GONE);
                       // recyclerView.setVisibility(View.GONE);
                       // empty_view.setVisibility(View.VISIBLE);
                    }

                } else {
                    // response received but request not successful (like 400,401,403 etc)
                   // recyclerView1.setVisibility(View.GONE);
                   // recyclerView2.setVisibility(View.GONE);
                   // recyclerView3.setVisibility(View.GONE);
                   // recyclerView.setVisibility(View.GONE);
                   // empty_view.setVisibility(View.VISIBLE);
                }
                mDilatingDotsProgressBar.hideNow();
            }

            @Override
            public void onFailure(Throwable t) {
       //         mDilatingDotsProgressBar.hideNow();
            }
        });

    }
}
