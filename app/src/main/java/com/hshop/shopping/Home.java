package com.hshop.shopping;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.hshop.R;
import com.hshop.adapter.MobileOS;
import com.hshop.adapter.ParentAdapter;
import com.hshop.adapter.Phone;
import com.hshop.adapter.RecyclerAdapter;
import com.hshop.adapter.SelectHomeDetailAdapter;
import com.hshop.adapter.SelectHomeDetailAdapter1;
import com.hshop.adapter.SelectLatestHomeDetailAdapter;
import com.hshop.models.AllCart;
import com.hshop.models.AllCartProduct;
import com.hshop.models.AllHome;
import com.hshop.models.AllHomeExpandCatList;
import com.hshop.models.AllHomeOffertList;
import com.hshop.models.AllHomeProductList;
import com.hshop.models.AllLatestHomeProductList;
import com.hshop.models.AllProductUnitDetailsList;
import com.hshop.models.pojo;
import com.hshop.rest.Config;
import com.hshop.rest.RestClient;
import com.hshop.utils.NetworkUtils;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

/**
 * Created by Nilay on 12-Apr-17.
 */

public class Home extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener{
    View view;
    RecyclerView recyclerView,recyclerView1,recyclerView2,recyclerView3;
    SelectHomeDetailAdapter adapter;
    SelectHomeDetailAdapter1 adapter1;
    SelectLatestHomeDetailAdapter adapter3;
    RecyclerAdapter adapter2;
    List<AllHomeProductList> getallHomeAllProductLists = new ArrayList<>();
    List<AllProductUnitDetailsList> getallHomeAllProductUnitLists = new ArrayList<>();
    List<AllLatestHomeProductList> getallLatestHomeAllProductLists = new ArrayList<>();
    List<AllHomeOffertList> getallHomeAllOfferLists = new ArrayList<>();
    List<AllHomeExpandCatList> getallHomeAllExpandLists = new ArrayList<>();
    TextView  empty_view;
    private ArrayList<MobileOS> mobileOSes = new ArrayList<>();;
    private OnFeedItemClickListener onFeedItemClickListener;
    LinearLayout pre_view_home;
    String user_id,status;
    LinearLayout linear_no_internet;
    TextView textCartItemCount;
    int mCartItemCount=0;
    List<AllCartProduct> getallCartProductLists = new ArrayList<>();
    int count = 0;
    SliderLayout sliderLayout;
    HashMap<String,String> Hash_file_maps ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_home, container, false);
        textCartItemCount= new TextView(getContext());

        Hash_file_maps = new HashMap<String, String>();
        sliderLayout = (SliderLayout)view.findViewById(R.id.slider);

        C0456b.f2467p3 = getActivity().getSharedPreferences(C0456b.f2907a3,0);
        String f00p3 = C0456b.f2467p3.getString("offerposter",null);
        C0456b.f2467p5 = getActivity().getSharedPreferences(C0456b.f2907a5,0);
        String f00p5 = C0456b.f2467p5.getString("offerpostername",null);

        C0456b.f2467p4 = getActivity().getSharedPreferences(C0456b.f2907a4,0);
        String f00p4 = C0456b.f2467p4.getString("offerposterid",null);
        Log.d("fgh",f00p4);

        String[] offerArray = f00p3.split(",");
        String[] offerArrayId = f00p4.split(",");

        for (int m=0;m<offerArray.length;m++)
        {
            Hash_file_maps.put(offerArrayId[m], offerArray[m]);
        }

        for(String name : Hash_file_maps.keySet()){

            TextSliderView textSliderView = new TextSliderView(getActivity());
            textSliderView
                    // .description("Abc")
                    .image(Hash_file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);
            sliderLayout.addSlider(textSliderView);
        }

        //sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setDuration(1500);
        sliderLayout.addOnPageChangeListener(this);

        recyclerView = (RecyclerView) view.findViewById(R.id.gmail_list);
        recyclerView1 = (RecyclerView) view.findViewById(R.id.gmail_list1);
        recyclerView2 = (RecyclerView) view.findViewById(R.id.gmail_list2);
        recyclerView3 = (RecyclerView) view.findViewById(R.id.gmail_list3);
        empty_view = (TextView) view.findViewById(R.id.empty_view);
        pre_view_home = (LinearLayout) view.findViewById(R.id.pre_view_home);
        linear_no_internet = (LinearLayout) view.findViewById(R.id.linear_no_internet);
        C0456b.f2467p = getActivity().getSharedPreferences(C0456b.f2907a,0);
        user_id = C0456b.f2467p.getString("user_id",null);
        // LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        // recyclerView2.setLayoutManager(layoutManager);
        // Badge();

       LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
       LinearLayoutManager horizontalLayoutManagaer1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
       LinearLayoutManager horizontalLayoutManagaer3 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);

        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setLayoutManager(horizontalLayoutManagaer);
        recyclerView1.setLayoutManager(horizontalLayoutManagaer1);
        recyclerView3.setLayoutManager(horizontalLayoutManagaer3);
        recyclerView2.setItemAnimator(new DefaultItemAnimator());

        if (adapter == null) {
            adapter = new SelectHomeDetailAdapter(getContext(),user_id,getallHomeAllProductLists);
            recyclerView.setAdapter(adapter);
        }
        if (adapter1 == null)
        {
            adapter1 = new SelectHomeDetailAdapter1(getContext(),getallHomeAllOfferLists);
            recyclerView1.setAdapter(adapter1);
        }
        if (adapter3 == null)
        {
            adapter3 = new SelectLatestHomeDetailAdapter(getContext(),user_id,getallLatestHomeAllProductLists);
            recyclerView3.setAdapter(adapter3);
        }

        adapter.SetOnItemClickListener(new SelectHomeDetailAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(getActivity(),Product_details.class).putExtra("pro_id",getallHomeAllProductLists.get(position).getPro_id()).putExtra("pro_name",getallHomeAllProductLists.get(position).getPro_name()));
            }
        });
        adapter1.SetOnItemClickListener(new SelectHomeDetailAdapter1.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(getActivity(),Product.class).putExtra("offer",getallHomeAllOfferLists.get(position).getOff_id()).putExtra("offer_name",getallHomeAllOfferLists.get(position).getOff_name()));
            }
        });
        adapter3.SetOnItemClickListener(new SelectLatestHomeDetailAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(getActivity(),Product_details.class).putExtra("pro_id",getallLatestHomeAllProductLists.get(position).getPro_id()).putExtra("pro_name",getallLatestHomeAllProductLists.get(position).getPro_name()));
            }
        });
        adapter.SetOnItemClickListener(new SelectHomeDetailAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(getActivity(),Product_details.class).putExtra("pro_id",getallHomeAllProductLists.get(position).getPro_id()).putExtra("pro_name",getallHomeAllProductLists.get(position).getPro_name()));
            }
        });

        status = NetworkUtils.getConnectivityStatus(getContext());
        if (status.equals("404")) {
            linear_no_internet.setVisibility(View.VISIBLE);
        }
        else {
            callEventData(Config.mem_string,user_id);
        }
        return view;
    }
    public static Fragment newInstance() {
        Home fragment = new Home();
        return fragment;
    }
    public void callEventData(String mem_string,String user_id) {
        final DilatingDotsProgressBar mDilatingDotsProgressBar = (DilatingDotsProgressBar) view.findViewById(R.id.progress);
        mDilatingDotsProgressBar.showNow();
        RestClient.GitApiInterface service = RestClient.getClient();

        Call<AllHome> call = service.getAllHome(mem_string,user_id);
        call.enqueue(new Callback<AllHome>() {
            @Override
            public void onResponse(Response<AllHome> response) {
                mDilatingDotsProgressBar.hideNow();
                Log.d("fgh", "Status Code = " + response.code());
                if (response.isSuccess()) {
                    // request successful (status code 200, 201)
                    AllHome result = response.body();

                    if (result.getStatus().equals("success")) {

                        if (result.getProduct() != null) {

                            if (result.getProduct().size() > 0) {

                                Log.d("fgh",result.getProduct().get(0).getPro_name());
                                getallHomeAllProductLists.clear();
                                getallHomeAllProductLists.addAll(result.getProduct());
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
                        if (result.getOffer() != null) {
                            if (result.getOffer().size() > 0) {
                                getallHomeAllOfferLists.clear();
                                getallHomeAllOfferLists.addAll(result.getOffer());
                                adapter1.notifyDataSetChanged();
                                if (getallHomeAllOfferLists.isEmpty()) {
                                    recyclerView1.setVisibility(View.GONE);
                                    empty_view.setVisibility(View.VISIBLE);
                                } else {
                                    recyclerView1.setVisibility(View.VISIBLE);
                                    empty_view.setVisibility(View.GONE);
                                }
                            } else {
                                recyclerView1.setVisibility(View.GONE);
                                empty_view.setVisibility(View.VISIBLE);
                            }
                        }

                        if (result.getExpandcat() != null) {
                            if (result.getExpandcat().size() > 0) {
                               getallHomeAllExpandLists.clear();
                              getallHomeAllExpandLists.addAll(result.getExpandcat());
                            //  adapter2.notifyDataSetChanged();



                                ArrayList<pojo> event=new ArrayList<pojo>();
                                for (int i =0 ;i<getallHomeAllExpandLists.size();i++){
                                    pojo p=new pojo();
                                    ArrayList<Phone> iphones = new ArrayList<>();
                                    String name = getallHomeAllExpandLists.get(i).getCat_name();
                                    String description = getallHomeAllExpandLists.get(i).getCat_description();
                                    String offer = getallHomeAllExpandLists.get(i).getCat_offer();
                                    String image = getallHomeAllExpandLists.get(i).getCat_image();
                                    p.setCatid(getallHomeAllExpandLists.get(i).getCat_id());
                                    p.setCatimg(image);
                                    p.setCatname(name);
                                    p.setOffer(offer);
                                    p.setDes(description);
                                    String[] subcat = new String[getallHomeAllExpandLists.get(i).getExpandsubcat().size()];
                                    for (int j=0;j<getallHomeAllExpandLists.get(i).getExpandsubcat().size();j++)
                                    {
                                        String subname = getallHomeAllExpandLists.get(i).getExpandsubcat().get(j).getSub_name();
                                        String subimage = getallHomeAllExpandLists.get(i).getExpandsubcat().get(j).getSub_image();
                                        String suboffer = getallHomeAllExpandLists.get(i).getExpandsubcat().get(j).getSub_offer();
                                        String subid = getallHomeAllExpandLists.get(i).getExpandsubcat().get(j).getSub_id();
                                        String subdetails = getallHomeAllExpandLists.get(i).getExpandsubcat().get(j).getSub_description();
                                        subcat[j]=subid+"!-"+subname+"!-"+subimage;
                                        iphones.add(new Phone(subname+"!-"+subimage+"!-"+suboffer+"!-"+subid+"!-"+subdetails));
                                    }
                                    if(subcat.length>0)
                                    {
                                        StringBuilder sc=new StringBuilder();
                                        for(int ijt=0;ijt<subcat.length;ijt++)
                                        {
                                            sc.append(subcat[ijt].toString()+",!,");
                                        }
                                        p.setSubcat(sc.toString());
                                    }
                                    mobileOSes.add(new MobileOS(name+"!-"+description+"!-"+offer+"!-"+image, iphones));
                                    event.add(p);
                                }
                                ParentAdapter prnt=new ParentAdapter(getContext()  ,event);
                                adapter2 = new RecyclerAdapter(getContext(),mobileOSes);
                                recyclerView2.setAdapter(prnt);
                                recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
                                GridLayoutManager glm = new GridLayoutManager(getContext(), 3);
                                glm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                                    @Override
                                    public int getSpanSize(int position) {
                                        switch(adapter2.getItemViewType(position)) {
                                            case 0:
                                                return 1;
                                            case 1:
                                                return 1;
                                            default:
                                                return 3;
                                        }
                                    }
                                });
                                recyclerView2.setLayoutManager(glm);


                                if (getallHomeAllExpandLists.isEmpty()) {
                                    recyclerView2.setVisibility(View.GONE);
                                    empty_view.setVisibility(View.VISIBLE);
                                } else {
                                    recyclerView2.setVisibility(View.VISIBLE);
                                    empty_view.setVisibility(View.GONE);
                                }
                            } else {
                                recyclerView2.setVisibility(View.GONE);
                                empty_view.setVisibility(View.VISIBLE);
                            }
                        }
                        if (result.getLatestproduct() != null) {

                            if (result.getLatestproduct().size() > 0) {

                                Log.d("fgh",result.getLatestproduct().get(0).getPro_name());
                                getallLatestHomeAllProductLists.clear();
                                getallLatestHomeAllProductLists.addAll(result.getLatestproduct());
                                adapter3.notifyDataSetChanged();

                                if (getallLatestHomeAllProductLists.isEmpty()) {
                                    recyclerView3.setVisibility(View.GONE);
                                    empty_view.setVisibility(View.VISIBLE);

                                } else {
                                    recyclerView3.setVisibility(View.VISIBLE);
                                    empty_view.setVisibility(View.GONE);
                                }
                            } else {
                                recyclerView3.setVisibility(View.GONE);
                                empty_view.setVisibility(View.VISIBLE);
                            }
                        }
                        pre_view_home.setVisibility(View.VISIBLE);
                    } else {
                        recyclerView1.setVisibility(View.GONE);
                        recyclerView2.setVisibility(View.GONE);
                        recyclerView3.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.GONE);
                        empty_view.setVisibility(View.VISIBLE);
                    }

                } else {
                    // response received but request not successful (like 400,401,403 etc)
                    recyclerView1.setVisibility(View.GONE);
                    recyclerView2.setVisibility(View.GONE);
                    recyclerView3.setVisibility(View.GONE);
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
    @Override
    public void onStop() {
        sliderLayout.stopAutoCycle();
        super.onStop();
    }
    @Override
    public void onSliderClick(BaseSliderView slider) {
        //Toast.makeText(getActivity(),slider.getBundle().get("extra") + "",Toast.LENGTH_SHORT).show();
       // startActivity(new Intent(getActivity(),Product.class).putExtra("offer",slider.getBundle().get("extra").toString()).putExtra("offer_name","Special Offer"));
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }
    @Override
    public void onPageScrollStateChanged(int state) {}

    private void setupBadge() {
       callCartData(user_id);


//Toast.makeText(getBaseContext(),returnCartItems.callCartData(),Toast.LENGTH_LONG ).show();

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



}
