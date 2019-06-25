package com.hshop.data;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by awidiyadew on 15/09/16.
 */
public class CustomDataProvider {

    private static final int MAX_LEVELS = 3;
    List<BaseItem> listcat = new ArrayList<>();
    List<BaseItem> listsubcat = new ArrayList<>();
    private static final int LEVEL_1 = 1;
    private static final int LEVEL_2 = 2;
    private static final int LEVEL_3 = 3;
    String user_id;
    Context context;
    List<BaseItem> listcat1 = new ArrayList<>();

    public CustomDataProvider(Context context){
        this.context=context;
        //dataadd();
    }
    //SharedPreferences prefs = home.getSharedPreferences("user_id",Context.MODE_PRIVATE);
    //C0456b.f2467p = prefs.getSharedPreferences(C0456b.f2907a, 0);

    // user_id = C0456b.f2467p.getString("user_id", null);
    private static List<BaseItem> mMenu = new ArrayList<>();

    void dataadd() {
        {

            final RequestQueue requestQueue = Volley.newRequestQueue(context);
            String url="http://hshop.co.in/API/get_category.php";
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
                                    Log.d("search",status.toString());
                                }
                                else if(status.contains("success")){

                                    JSONArray jsonArray=response.getJSONArray("category");
                                    for(int i=0;i<jsonArray.length();i++)
                                    {
                                        JSONObject obj=jsonArray.getJSONObject(i);
                                        listcat1.add(new Item(obj.getString("cat_name"),obj.getString("cat_id")));
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
        }


    }


    public static List<BaseItem> getInitialItems() {
        //return getSubItems(new GroupItem("root"));

        List<BaseItem> rootMenu = new ArrayList<>();

        /*Master_Home home =new Master_Home();
        Context context1=home.getcontext
        Context context=new Master_Home().getApplicationContext();
        C0456b.f2467p = context.getSharedPreferences(C0456b.f2907a,0);
       // C0456b.f2467p1 = getSharedPreferences(C0456b.f2907a1,0);
        //C0456b.f2467p2 = getSharedPreferences(C0456b.f2907a2,0);

        String f00p = C0456b.f2467p.getString("user_id",null);*/
        /*

        * ITEM = TANPA CHILD
        * GROUPITEM = DENGAN CHILD
        * */
        rootMenu.add(new Item("Home","0"));
        rootMenu.add(new GroupItem("Category","10"));
        rootMenu.add(new Item("My Cart","1"));
        //rootMenu.add(new GroupItem("KATEGORI","101"));
        rootMenu.add(new Item("My Order","2"));
        rootMenu.add(new Item("My Profile","3"));
        rootMenu.add(new Item("Change Password","4"));
        rootMenu.add(new Item("Share","5"));
        rootMenu.add(new Item("Customer Service","6"));
        //rootMenu.add(new Item("About","7"));
        rootMenu.add(new Item("Logout","8"));

        return rootMenu;
    }

    public static List<BaseItem> getSubItems(BaseItem baseItem,Context context,JSONObject jsonobj) {


        List<BaseItem> result = new ArrayList<>();
        int level = ((GroupItem) baseItem).getLevel() + 1;
        String menuItem = baseItem.getName();

        if (!(baseItem instanceof GroupItem)) {
            throw new IllegalArgumentException("GroupItem required");
        }

        GroupItem groupItem = (GroupItem)baseItem;
        if(groupItem.getLevel() >= MAX_LEVELS){
            return null;
        }

        /*
        * HANYA UNTUK GROUP-ITEM
        * */
        switch (level){
            case LEVEL_1 :
                switch (menuItem){
                    case "KATEGORI" :
                        result = getListKategori();
                        break;
                    case "Category" :
                        //result = Maincat(context,jsonobj);
                        result=getListCat(context);
                        break;
                }
                break;

            case LEVEL_2 :
                switch (menuItem){
                 //   case "GROUP 1" :
               //         result = getListGroup1();
              //          break;
                    case "GROUP 1" :
                        result = getListGroupX();
                        break;
                    case "Personal care" :
                        result = Personal_care(context);
                        break;
                    case "Baby care" :
                        result = Baby_care();
                        break;
                    case "Grocery" :
                        result = Grocery();
                        break;
                    case "Household Needs" :
                        result = Household_Needs();
                        break;
                    case "Packages food" :
                        result = Packages_food();
                        break;
                    case "Beverages" :
                        result =Beverages();
                        break;
                }
                break;
            case LEVEL_3 :
                switch (menuItem){
                    default:
                        //result = getListKategori();

                        break;
                }
                break;

        }

        return result;
    }




    private static List<BaseItem> Maincat(Context context,JSONObject jsonobj) {

        List<BaseItem> list = new ArrayList<>();
        List<BaseItem > list1=new ArrayList<>();


            try {
                JSONArray jsonArray=jsonobj.getJSONArray("category");
                for(int i=0;i<jsonArray.length();i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    GroupItem groupItem = new GroupItem(obj.getString("cat_name"), "main" + obj.get("cat_id"));
                    groupItem.setLevel(groupItem.getLevel() + 1);
                    list.add(groupItem);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }




    return list;

    }


    private static List<BaseItem> Personal_care(Context context) {

        List<BaseItem> list = new ArrayList<>();
        list.add(new Item("Perfumes & Deos","cat1"));
        list.add(new Item("Hair care","cat4"));
        list.add(new Item("Skin care","cat6"));
        list.add(new Item("Oral care","cat7"));
        list.add(new Item("Personal Hygiene","cat8"));
        list.add(new Item("Sanitary napkins","cat9"));
        list.add(new Item("Shaving needs","cat10"));
        list.add(new Item("Health & Wellness","cat25"));
        return list;
    }

    private static List<BaseItem> getListGroupX(){
        List<BaseItem> list = new ArrayList<>();
        list.add(new Item("CHILD OF GX-A","0"));
        list.add(new Item("CHILD OF GX-B12","7"));

        return list;
    }

    private static List<BaseItem> Beverages() {
        List<BaseItem> list = new ArrayList<>();
        list.add(new Item("Softdrinks","cat30"));
        list.add(new Item("Tea & Coffee","cat31"));
        list.add(new Item("Juice & Energy Drinks","cat20"));
        list.add(new Item("Healthdrinks","cat40"));
        return list;
    }

    private static List<BaseItem> Packages_food() {
        List<BaseItem> list = new ArrayList<>();
        list.add(new Item("Biscuits & Cookies","cat23"));
        list.add(new Item("Breakfast,Jam & Spread","cat24"));
        list.add(new Item("Chocolates","cat26"));
        list.add(new Item("Ketchup & Noodles","cat27"));
        list.add(new Item("Farsan & Sweets","cat28"));
        list.add(new Item("Soup & Health food","cat29"));
        list.add(new Item("Instant Mix","cat39"));

        return list;
    }

    private static List<BaseItem> Household_Needs() {
        List<BaseItem> list = new ArrayList<>();
        list.add(new Item("Detergents","cat14"));
        list.add(new Item("Cleaners","cat19"));
        list.add(new Item("Air freshener","cat10"));
        list.add(new Item("Repellent","cat21"));
        list.add(new Item("Other need","cat22"));

        return list;
    }

    private static List<BaseItem> Grocery() {
        List<BaseItem> list = new ArrayList<>();
        list.add(new Item("Ghee & Oil","cat15"));
        list.add(new Item("Masala","cat16"));
        list.add(new Item("Atta & Besan","cat17"));
        list.add(new Item("Rice & Rice products","cat34"));
        list.add(new Item("Salt,Sugar &Jaggery","cat35"));
        list.add(new Item("Dals & Pulse","cat36"));




        return list;
    }

    private static List<BaseItem> Baby_care() {
        List<BaseItem> list = new ArrayList<>();
        list.add(new Item("Diapers & Wipes","cat11"));
        list.add(new Item("Oil,Powder,& Shops","cat12"));
        list.add(new Item("Lotion,Cream & Shampoo","cat13"));
        return list;
    }

    public static boolean isExpandable(BaseItem baseItem) {
        return baseItem instanceof GroupItem;
    }

    private static List<BaseItem> getListKategori(){

        List<BaseItem> list = new ArrayList<>();

        // Setiap membuat groupItem harus di set levelnya
        GroupItem groupItem = new GroupItem("GROUP 1","00");
        groupItem.setLevel(groupItem.getLevel() + 1);

        list.add(new Item("ITEM 1","01"));
        list.add(new Item("ITEM 2","02"));
        list.add(groupItem);

        return list;
    }

    private static List<BaseItem> getListCat(Context context){



        List<BaseItem> list = new ArrayList<>();

        GroupItem groupItem = new GroupItem("Personal care","3");
        GroupItem groupItem1 = new GroupItem("Baby care","8");
        GroupItem groupItem2 = new GroupItem("Grocery","10");
        GroupItem groupItem3 = new GroupItem("Household Needs","10");
        GroupItem groupItem4 = new GroupItem("Packages food","11");
        GroupItem groupItem5 = new GroupItem("Beverages","12");
        groupItem.setLevel(groupItem.getLevel() + 1);
        groupItem1.setLevel(groupItem1.getLevel() + 1);
        groupItem2.setLevel(groupItem2.getLevel() + 1);
        groupItem3.setLevel(groupItem3.getLevel() + 1);
        groupItem4.setLevel(groupItem4.getLevel() + 1);
        groupItem5.setLevel(groupItem5.getLevel() + 1);
        list.add(groupItem);
        list.add(groupItem1);
        list.add(groupItem2);
        list.add(groupItem3);
        list.add(groupItem4);
        list.add(groupItem5);
        return list;
    }



  /*  public void callEventData(String mem_string,String user_id) {

        RestClient.GitApiInterface service = RestClient.getClient();

        Call<AllHome> call = service.getAllHome(mem_string,user_id);
        call.enqueue(new Callback<AllHome>() {
            @Override
            public void onResponse(Response<AllHome> response) {
                Log.d("fgh", "Status Code = " + response.code());
                if (response.isSuccess()) {
                    // request successful (status code 200, 201)
                    AllHome result = response.body();

                    if (result.getStatus().equals("success")) {

                        if (result.getExpandcat() != null) {
                            if (result.getExpandcat().size() > 0) {
                                getallHomeAllExpandLists.clear();
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

                                        iphones.add(new Phone(subname+"!-"+subimage+"!-"+suboffer+"!-"+subid+"!-"+subdetails));
                                    }
                                    mobileOSes.add(new MobileOS(name+"!-"+description+"!-"+offer+"!-"+image, iphones));
                                }
                                adapter2 = new RecyclerAdapter(getContext(),mobileOSes);
                                recyclerView2.setAdapter(adapter2);
                                GridLayoutManager glm = new GridLayoutManager(getContext(), 2);
                                glm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                                    @Override
                                    public int getSpanSize(int position) {
                                        switch(adapter2.getItemViewType(position)) {
                                            case 0:
                                                return 1;
                                            case 1:
                                                return 1;
                                            default:
                                                return 2;
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

    }   */

    /*private static List<BaseItem> getListGroup1(){
        List<BaseItem> list = new ArrayList<>();
        list.add(new Item("CHILD OF G1-A"));
        list.add(new Item("CHILD OF G1-B"));

        return list;
    }*/





}
