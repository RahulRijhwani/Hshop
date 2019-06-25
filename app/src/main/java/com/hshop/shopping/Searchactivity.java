    package com.hshop.shopping;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.hshop.R;
import com.hshop.adapter.SelectSearchDetailAdapter;
import com.hshop.models.AllSearch;
import com.hshop.models.AllSearchProduct;
import com.hshop.rest.Config;
import com.hshop.rest.RestClient;
import com.hshop.utils.NetworkUtils;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class Searchactivity extends AppCompatActivity{

    RecyclerView recyclerView;
    SelectSearchDetailAdapter adapter;
    List<AllSearchProduct> getallSearchProduct = new ArrayList<>();
    TextView empty_view;
    LinearLayout linear_no_internet;
    String user_id,status;
    private EditText searchBox;
    AllSearch result;
    ImageView img_search;
    String edt_serach_text;
    List<AllSearchProduct> getallSearchProduct1 = new ArrayList<>();
    List<AllSearchProduct> getallSearchProduct4 = new ArrayList<>();
    List<AllSearchProduct> gad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchactivity);

        recyclerView = (RecyclerView) findViewById(R.id.gmail_list);
        empty_view = (TextView) findViewById(R.id.empty_view);
        linear_no_internet = (LinearLayout) findViewById(R.id.linear_no_internet);
        C0456b.f2467p = getSharedPreferences(C0456b.f2907a, 0);
        user_id = C0456b.f2467p.getString("user_id", null);
        searchBox = (EditText)findViewById(R.id.search_box);
        img_search = (ImageView) findViewById(R.id.img_search);

 /*       searchBox.requestFocus();
        InputMethodManager imm = (InputMethodManager)getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(searchBox, 0);*/
         edt_serach_text = "alldata";
        callEventData(Config.mem_string,user_id,edt_serach_text);

       // LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(Searchactivity.this, LinearLayoutManager.HORIZONTAL, false);
      //  recyclerView.setLayoutManager(horizontalLayoutManagaer);
        recyclerView.setLayoutManager(new LinearLayoutManager(Searchactivity.this));
        if (adapter == null) {
            adapter = new SelectSearchDetailAdapter(Searchactivity.this, getallSearchProduct);
            recyclerView.setAdapter(adapter);
        }

            /*searchBox.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //performSearch();




                    edt_serach_text = searchBox.getText().toString();

                    status = NetworkUtils.getConnectivityStatus(Searchactivity.this);
                    if (status.equals("404")) {
                        linear_no_internet.setVisibility(View.VISIBLE);
                    } else {
                        if (edt_serach_text.length()>0)
                        {
                            dataadd(edt_serach_text,user_id);
                            callEventData(Config.mem_string,user_id,edt_serach_text);
                            Intent i1 = new Intent(Searchactivity.this,Product.class);
                            i1.putExtra("search",edt_serach_text);
                            i1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i1);
                        }
                        else
                        {
                            Toast.makeText(Searchactivity.this, "Enter Words", Toast.LENGTH_SHORT).show();
                        }
                    }

                    return true;

                    }


                return false;
            }
        });*/

        adapter.SetOnItemClickListener(new SelectSearchDetailAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                dataadd(getallSearchProduct.get(position).getPro_name(),user_id);

                Intent i1 = new Intent(Searchactivity.this,Product.class);
                i1.putExtra("search",getallSearchProduct.get(position).getPro_name());
                i1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i1);

                //startActivity(new Intent(Searchactivity.this, Product_details.class).putExtra("pro_id", getallSearchProduct.get(position).getPro_id()).putExtra("pro_name", getallSearchProduct.get(position).getPro_name()));
            }
        });




        searchBox.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    edt_serach_text = searchBox.getText().toString();

                    status = NetworkUtils.getConnectivityStatus(Searchactivity.this);
                    if (status.equals("404")) {
                        linear_no_internet.setVisibility(View.VISIBLE);
                    } else {
                        if (edt_serach_text.length()>0)
                        {
                            dataadd(edt_serach_text,user_id);
                            callEventData(Config.mem_string,user_id,edt_serach_text);
                            Intent i1 = new Intent(Searchactivity.this,Product.class);
                            i1.putExtra("search",edt_serach_text);
                            i1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i1);
                        }
                        else
                        {
                            Toast.makeText(Searchactivity.this, "Input Text", Toast.LENGTH_SHORT).show();
                        }
                    }
                    return true;
                }
                return false;
            }
        });

        searchBox.addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {




                edt_serach_text = searchBox.getText().toString();
                Boolean bln=true;
  /*              if(edt_serach_text.length()<3)
                {
                    bln=true;
                }
                else
                {
                    bln=false;
                }
*/
                //final DilatingDotsProgressBar mDilatingDotsProgressBar = (DilatingDotsProgressBar) findViewById(R.id.progress);
                //mDilatingDotsProgressBar.showNow();
                if(edt_serach_text.equals(""))
                {
                    callEventData(Config.mem_string,user_id,"alldata");

                   /* getallSearchProduct4.clear();
                    getallSearchProduct4.addAll(gad);*/
                    if(getallSearchProduct4.size()>0) {
                        getallSearchProduct = getallSearchProduct4;
                    }
                }
                else if(bln)
                {
                    callEventData(Config.mem_string,user_id,edt_serach_text);
                }
                else if(getallSearchProduct1.size()>0)
                {
                    List<AllSearchProduct> getallSearchProduct3 = new ArrayList<>();
                    //getallSearchProduct.clear();
                    for(int i=0;i<getallSearchProduct1.size();i++)
                    {
                        if((getallSearchProduct1.get(i).getPro_name().toString().toLowerCase()).contains(edt_serach_text.toLowerCase())) {

                            String s1=getallSearchProduct1.get(i).getPro_name().toString();
                            String[] ary=s1.split(" ");

                            for(int ij=0;ary.length>ij;ij++)
                            {
                                if(ary[ij].toString().toLowerCase().equals(edt_serach_text.toLowerCase()))
                                {
                                AllSearchProduct apdl=new AllSearchProduct();
                                int cj=0;
                                for(int c=0;c<getallSearchProduct3.size();c++) {

                                    if((getallSearchProduct3.get(c).getPro_name().toLowerCase()).equals(ary[ij].toString().toLowerCase()))
                                    {
                                        cj++;
                                    }

                                }
                                    if(cj==0) {
                                        apdl.setPro_name(ary[ij].toString());
                                        getallSearchProduct3.add(apdl);
                                    }
                                    //getallSearchProduct.set(j, getallSearchProduct3.).setPro_name(""));
                                    //j++;

                                }
                            }
                          //  getallSearchProduct.addAll(getallSearchProduct3);
                            //j=getallSearchProduct3.size()-1;
                        }
                    }

                    for(int i=0;i<getallSearchProduct1.size();i++)
                    {
                        if((getallSearchProduct1.get(i).getPro_name().toString().toLowerCase()).contains(edt_serach_text.toLowerCase())) {
                            //getallSearchProduct.set(j, getallSearchProduct1.get(i));
                            //j++;


                            AllSearchProduct apdl=new AllSearchProduct();
                            int cj=0;
                            for(int c=0;c<getallSearchProduct3.size();c++) {

                                if((getallSearchProduct3.get(c).getPro_name().toLowerCase()).equals((getallSearchProduct1.get(i).getPro_name().toString().toLowerCase())))
                                {
                                    cj++;
                                }

                            }
                            if(cj==0) {
                                apdl.setPro_name(getallSearchProduct1.get(i).getPro_name().toString());
                                getallSearchProduct3.add(apdl);
                            }


                        }
                    }
                    //getallSearchProduct1.clear();
                    //getallSearchProduct.addAll(getallSearchProduct3);
                    for(int i=0;i<getallSearchProduct3.size();i++)
                    {
                        getallSearchProduct.add(i,getallSearchProduct3.get(i));
                    }

                }

                if (getallSearchProduct.isEmpty()) {
                    recyclerView.setVisibility(View.GONE);
                    empty_view.setVisibility(View.VISIBLE);
                } else {
                    recyclerView.setVisibility(View.VISIBLE);
                    empty_view.setVisibility(View.GONE);
                }
                //mDilatingDotsProgressBar.hideNow();
                adapter.notifyDataSetChanged();

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        } );



        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 edt_serach_text = searchBox.getText().toString();

                status = NetworkUtils.getConnectivityStatus(Searchactivity.this);
                if (status.equals("404")) {
                    linear_no_internet.setVisibility(View.VISIBLE);
                } else {
                    if (edt_serach_text.length()>0)
                    {
                        dataadd(edt_serach_text,user_id);
                        callEventData(Config.mem_string,user_id,edt_serach_text);
                        Intent i1 = new Intent(Searchactivity.this,Product.class);
                        i1.putExtra("search",edt_serach_text);
                        i1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i1);
                    }
                    else
                    {
                        Toast.makeText(Searchactivity.this, "Input Text", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }





    private void dataadd(String pro_name, String user_id) {
        {

            pro_name=pro_name.replace(" ","%20");
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            String url="http://hshop.co.in/API/searchdata.php?search="+pro_name+"&uid="+user_id;
            final JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                    url,null,
                    new com.android.volley.Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d("RESPONSE", response.toString());
                            String status= null;
                            try {
                                status = response.getString("status");

                                if(status.contains("error"))
                                {
                                    Log.d("search",status.toString());
                                }
                                else if(status.contains("ok")){
                                    Log.d("search",status.toString());
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


    }

    public void callEventData(String mem_string,String user_id,String edt_serach_text) {
        //final DilatingDotsProgressBar mDilatingDotsProgressBar = (DilatingDotsProgressBar) findViewById(R.id.progress);
        //mDilatingDotsProgressBar.showNow();
        RestClient.GitApiInterface service = RestClient.getClient();

        Call<AllSearch> call = service.getallsearch(mem_string,user_id,edt_serach_text);
        call.enqueue(new Callback<AllSearch>() {
            @Override
            public void onResponse(Response<AllSearch> response) {
                //mDilatingDotsProgressBar.hideNow();
                Log.d("fgh", "Status Code = " + response.code());
                if (response.isSuccess()) {
                    // request successful (status code 200, 201)
                   result  = response.body();
                    if (result.getStatus().equals("success")) {
                        if (result.getData() != null) {

                            if (result.getData().size() > 0) {
                                getallSearchProduct.clear();

                                 gad= result.getData();
                                getallSearchProduct.addAll(result.getData());
                                    adapter.notifyDataSetChanged();
                                    getallSearchProduct1=getallSearchProduct;
                                    getallSearchProduct4=getallSearchProduct;

                                if (getallSearchProduct.isEmpty()) {
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
               // mDilatingDotsProgressBar.hideNow();
            }
        });

    }
}