package com.hshop.shopping;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.hshop.R;
import com.hshop.data.BaseItem;
import com.hshop.data.CustomDataProvider;
import com.hshop.models.AllCart;
import com.hshop.models.AllCartProduct;
import com.hshop.rest.Config;
import com.hshop.rest.RestClient;
import com.hshop.views.LevelBeamView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import pl.openrnd.multilevellistview.ItemInfo;
import pl.openrnd.multilevellistview.MultiLevelListAdapter;
import pl.openrnd.multilevellistview.MultiLevelListView;
import pl.openrnd.multilevellistview.OnItemClickListener;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class Master_Home extends AppCompatActivity
       {

    String user_info,user_address,user_id;
    private boolean doubleBackToExitPressedOnce = false;
    TextView h_name,h_contact,h_address,h_city;
    TextView textCartItemCount;
    List<?> list;
    public  View fview,fview1;
    public boolean viewchk,viewchk1;
    JSONObject resobj=null;
    public int color,color1;


           int mCartItemCount=0;
    int count=0;
    private MultiLevelListView multiLevelListView;
    List<AllCartProduct> getallCartProductLists = new ArrayList<>();

    UserSelectedItemCart returnCartItems = new UserSelectedItemCart();

           // int mCartItemCount=returnCartItems.callCartData();


   /* public Master_Home(int no) {
        textCartItemCount.setText( no + "" );
    }*/

   public Master_Home()
   {
       //resobj=dataadd();
   }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master__home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CustomDataProvider customDataProvider=new CustomDataProvider(this);
        C0456b.f2467p1 = getSharedPreferences(C0456b.f2907a1,0);
        C0456b.f2467p2 = getSharedPreferences(C0456b.f2907a2,0);
        C0456b.f2467p = getSharedPreferences(C0456b.f2907a,0);
        user_id = C0456b.f2467p.getString("user_id",null);

        user_info = C0456b.f2467p1.getString("user_info",null);
        user_address = C0456b.f2467p2.getString("user_address",null);





        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //navigationView.setNavigationItemSelectedListener(Master_Home.this);



        View hView =  navigationView.getRootView();
        h_name = (TextView) hView.findViewById(R.id.h_name);
        h_contact = (TextView) hView.findViewById(R.id.h_contact);
        h_address = (TextView) hView.findViewById(R.id.h_address);
        h_city = (TextView) hView.findViewById(R.id.h_city);

        try
        {
            String user_name[] = user_info.split(",");
            String user_location[] = user_address.split(",");

            h_name.setText(user_name[0]);
            h_contact.setText(user_name[1]);
            h_address.setText(user_location[0]+","+user_location[1]);
            h_city.setText(user_location[2]);
        }
        catch (Exception e)
        {

        }

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                selectedFragment = Home.newInstance();
                                break;
                           /* case R.id.navigation_category:
                                selectedFragment = Category.newInstance();
                                break;
                                 case R.id.navigation_search:
                                selectedFragment = Cart_details.newInstance();
                                break;
                                */

                            case R.id.navigation_list:
                                selectedFragment = Order.newInstance();
                                break;

                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.content, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content, Home.newInstance());
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce=false;
                }
            }, 2000);
        }
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
        confMenu();
        return true;
    }





 /* private void setupBadge() {
    int c= callCartData( user_id );
            if (c == 0) {
                if (textCartItemCount.getVisibility() != View.GONE) {
                    textCartItemCount.setVisibility(View.GONE);
                }
            } else {
                textCartItemCount.setText(String.valueOf(c));
                if (textCartItemCount.getVisibility() != View.VISIBLE) {
                    textCartItemCount.setVisibility(View.VISIBLE);
                }
            }


//Toast.makeText(getBaseContext(),returnCartItems.callCartData(),Toast.LENGTH_LONG ).show();

    }*/

 @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_cart) {
            Intent i1 = new Intent(
                    Master_Home.this,Cart.class);
            startActivity(i1);
        }
        if (id == R.id.action_search) {
            Intent i1 = new Intent(Master_Home.this,Searchactivity.class);
            startActivity(i1);
        }

        return super.onOptionsItemSelected(item);
    }

           private void confMenu() {
               multiLevelListView = (MultiLevelListView) findViewById(R.id.multiLevelMenu);

               // custom ListAdapter
               ListAdapter listAdapter;
               listAdapter = new ListAdapter();

               multiLevelListView.setAdapter(listAdapter);


               multiLevelListView.setOnItemClickListener(mOnItemClickListener);

               listAdapter.setDataItems(CustomDataProvider.getInitialItems());
           }

           JSONObject dataadd()
               {

                   //final List<?>[] l = new List<?>[1];
                   final JSONObject[] responce = new JSONObject[1];
                   final RequestQueue requestQueue = Volley.newRequestQueue(this);
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

                                           //jsonArray[0] =response.getJSONArray("category");
                                           responce[0] =response;
                     //                      l[0] =CustomDataProvider.getSubItems((BaseItem) object,Master_Home.this,response);
                                           /*for(int i=0;i<jsonArray.length();i++)
                                           {
                                               JSONObject obj=jsonArray.getJSONObject(i);
                                               listcat1.add(new Item(obj.getString("cat_name"),obj.getString("cat_id")));
                                           }*/
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
                   return responce[0];
               }




           private OnItemClickListener mOnItemClickListener = new OnItemClickListener() {

               private void showItemDescription(Object object, ItemInfo itemInfo) {
                  /* StringBuilder builder = new StringBuilder("\"");
                   builder.append(((BaseItem) object).getName());
                   builder.append("\" clicked!\n");
                   builder.append(getItemInfoDsc(itemInfo));*/
                   String select=((BaseItem) object).getName();
                   String id=((BaseItem)object).getId();
                   //Toast.makeText(Master_Home.this, builder.toString(), Toast.LENGTH_SHORT).show();

/*
                   if(viewchk==true)
                   {
                       //fview.setBackgroundColor(color);
                       viewchk=false;

                   }
                   color=view.getDrawingCacheBackgroundColor();
                   //view.setBackgroundColor(R.color.bg8);
                   viewchk=true;
                   fview=view;*/

                   if (select.equals("Home")) {
                       Fragment selectedFragment = null;
                       selectedFragment = Home.newInstance();
                       FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                       transaction.replace(R.id.content, selectedFragment);
                       transaction.commit();
                   } else if (select.equals("My Order")) {
                       Intent i1 = new Intent(Master_Home.this,Order_details.class);
                       startActivity(i1);
                   }
                   else if (select.equals("My Cart")) {
                       Intent i1 = new Intent(Master_Home.this,Cart.class);
                       startActivity(i1);
                   }else if (select.equals("My Profile")) {
                       Intent i1 = new Intent(Master_Home.this,Profile.class);
                       startActivity(i1);
                   }  else if (select.equals("Change Password")) {
                       Intent i1 = new Intent(Master_Home.this,Update_password.class);
                       startActivity(i1);
                   } else if (select.equals("Share")) {
                       startTest();
                   } else if (select.equals("Customer Service")) {
                       Intent i1 = new Intent(Master_Home.this,Query.class);
                       startActivity(i1);
                   }
                   else if (select.equals("About")) {
                       Intent i1 = new Intent(Master_Home.this,About.class);
                       startActivity(i1);
                   }
                   else if (select.equals("Logout")) {

                       AlertDialog.Builder builder1 = new AlertDialog.Builder(Master_Home.this);
                       builder1.setMessage("Do you want to Logout ?")
                               .setCancelable(false)
                               .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                   private SharedPreferences.Editor f2467p;

                                   @Override
                                   public void onClick(DialogInterface dialog, int which) {
                                       C0456b.f2467p = getSharedPreferences(C0456b.f2907a,0);

                                       String f2466o = C0456b.f2467p.getString("user_id", null);
                                       this.f2467p = C0456b.f2467p.edit();

                                       this.f2467p.putString("user_id",null);
                                       this.f2467p.commit();
                                       Intent i1 = new Intent(Master_Home.this,Check.class);
                                       startActivity(i1);
                                       Intent intent = new Intent(Intent.ACTION_MAIN);
                                       intent.addCategory(Intent.CATEGORY_HOME);
                                       intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                       startActivity(intent);
                                   }
                               })
                               .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                   @Override
                                   public void onClick(DialogInterface dialog, int which) {
                                       dialog.cancel();
                                   }
                               });
                       AlertDialog alert = builder1.create();
                       alert.setTitle("Logout");
                       alert.show();
                   }
                   else if(id.contains("cat"))
                   {
                       String id1=id.replace("cat","");
                       startActivity(new Intent(Master_Home.this, Product.class).putExtra("sub_cat_id", id1).putExtra("sub_cat_name", select));
                   }



               }

               @Override
               public void onItemClicked(MultiLevelListView parent, View view, Object item, ItemInfo itemInfo) {

                   if(viewchk==true)
                   {
                       fview.setBackgroundColor(color);
                       viewchk=false;

                   }
                   color=view.getDrawingCacheBackgroundColor();
                   view.setBackgroundColor(Color.parseColor("#FF58B358"));
                   viewchk=true;
                   fview=view;
                   showItemDescription(item, itemInfo);
               }

               @Override
               public void onGroupItemClicked(MultiLevelListView parent, View view, Object item, ItemInfo itemInfo) {

                   if(viewchk1==true)
                   {
                       fview1.setBackgroundColor(color);
                       viewchk1=false;

                   }
                   color1=view.getDrawingCacheBackgroundColor();
                   view.setBackgroundColor(Color.parseColor("#FF58B358"));
                   viewchk1=true;
                   fview1=view;
                   showItemDescription(item, itemInfo);
               }
           };

           private String getItemInfoDsc(ItemInfo itemInfo)
           {
               StringBuilder builder = new StringBuilder();

               builder.append(String.format("level[%d], idx in level[%d/%d]",
                       itemInfo.getLevel() + 1, /*Indexing starts from 0*/
                       itemInfo.getIdxInLevel() + 1 /*Indexing starts from 0*/,
                       itemInfo.getLevelSize()));

               if (itemInfo.isExpandable()) {
                   builder.append(String.format(", expanded[%b]", itemInfo.isExpanded()));
               }
               return builder.toString();
           }
           private class ListAdapter extends MultiLevelListAdapter {

               private class ViewHolder {
                   TextView nameView;
                   TextView infoView;
                   ImageView icon;
                   ImageView arrowView;
                   LevelBeamView levelBeamView;
               }

               @Override
               public List<?> getSubObjects(Object object) {

                   //Toast.makeText(Master_Home.this, , Toast.LENGTH_SHORT).show();
                   if(resobj==null)
                   {
                       //new Master_Home();
                       return CustomDataProvider.getSubItems((BaseItem) object,Master_Home.this,null);
                   }
                   // DIEKSEKUSI SAAT KLIK PADA GROUP-ITEM
                   return CustomDataProvider.getSubItems((BaseItem) object,Master_Home.this,resobj);

               }

               @Override
               public boolean isExpandable(Object object) {
                   return CustomDataProvider.isExpandable((BaseItem) object);
               }

               @Override
               public View getViewForObject(Object object, View convertView, ItemInfo itemInfo) {
                   ViewHolder viewHolder;
                   if (convertView == null) {
                       viewHolder = new ViewHolder();
                       convertView = LayoutInflater.from(Master_Home.this).inflate(R.layout.data_item, null);
                       //viewHolder.infoView = (TextView) convertView.findViewById(R.id.dataItemInfo);
                       viewHolder.nameView = (TextView) convertView.findViewById(R.id.dataItemName);
                       //viewHolder.nameView.setTextColor(R.color.black);
                       viewHolder.arrowView = (ImageView) convertView.findViewById(R.id.dataItemArrow);
                       viewHolder.icon= (ImageView) convertView.findViewById(R.id.icon);
                       //viewHolder.levelBeamView = (LevelBeamView) convertView.findViewById(R.id.dataItemLevelBeam);
                       convertView.setTag(viewHolder);
                   } else {
                       viewHolder = (ViewHolder) convertView.getTag();
                   }

                   viewHolder.nameView.setText(((BaseItem) object).getName());
                   viewHolder.nameView.setTextColor(Color.BLACK);
                   String select=((BaseItem) object).getName();
                   if(select.equals("Home")) {
                       //viewHolder.nameView.setTextColor(R.color.black);
                       viewHolder.icon.setImageResource(R.drawable.ic_navhome);
                   }else if(select.equals("Category")){
                       viewHolder.icon.setImageResource(R.drawable.ic_list);
                   }else if(select.equals("My Cart")){
                       viewHolder.icon.setImageResource(R.drawable.ic_cart);
                   }else if(select.equals("My Order")){
                       viewHolder.icon.setImageResource(R.drawable.ic_list);
                   }else if(select.equals("My Profile")){
                       viewHolder.icon.setImageResource(R.drawable.ic_user);
                   }else if(select.equals("Change Password")){
                       viewHolder.icon.setImageResource(R.drawable.ic_lock);
                   }else if(select.equals("Share")){
                       viewHolder.icon.setImageResource(R.drawable.ic_menu_share);
                   }else if(select.equals("About")){
                       viewHolder.icon.setImageResource(R.drawable.ic_about);
                   }else if(select.equals("Logout")){
                       viewHolder.icon.setImageResource(R.drawable.ic_logout);
                   }else if(select.equals("Customer Service")){
                       viewHolder.icon.setImageResource(R.drawable.ic_question);
                   }

                   //viewHolder.infoView.setText(getItemInfoDsc(itemInfo));

                   if (itemInfo.isExpandable()) {
                       viewHolder.arrowView.setVisibility(View.VISIBLE);
                       viewHolder.arrowView.setImageResource(itemInfo.isExpanded() ?
                               R.drawable.ic_expand_less : R.drawable.ic_expand_more);
                   } else {
                       viewHolder.arrowView.setVisibility(View.GONE);
                   }
                   if(itemInfo.getLevel()>0)
                   {
                       viewHolder.icon.setVisibility(View.GONE);
                   }
                   if(itemInfo.getLevel()==0)
                   {
                       viewHolder.icon.setVisibility(View.VISIBLE);
                   }
                   //viewHolder.levelBeamView.setLevel(itemInfo.getLevel());

                   return convertView;
               }
           }




           /* Hide By Rahul Rijhwani
        @SuppressWarnings("StatementWithEmptyBody")
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            // Handle navigation view item clicks here.
            int id = item.getItemId();

            if (id == R.id.nav_home) {
                Fragment selectedFragment = null;
                selectedFragment = Home.newInstance();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.content, selectedFragment);
                transaction.commit();
            } else if (id == R.id.nav_order) {
                Intent i1 = new Intent(Master_Home.this,Order_details.class);
                startActivity(i1);
            }
            else if (id == R.id.nav_cart) {
                Intent i1 = new Intent(Master_Home.this,Cart.class);
                startActivity(i1);
            }else if (id == R.id.nav_profile) {
                Intent i1 = new Intent(Master_Home.this,Profile.class);
                startActivity(i1);
            }  else if (id == R.id.nav_password) {
                Intent i1 = new Intent(Master_Home.this,Update_password.class);
                startActivity(i1);
            } else if (id == R.id.nav_share) {
                startTest();
            } else if (id == R.id.nav_service) {
                Intent i1 = new Intent(Master_Home.this,Query.class);
                startActivity(i1);
            }
            else if (id == R.id.nav_about) {
                Intent i1 = new Intent(Master_Home.this,About.class);
                startActivity(i1);
            }
            else if (id == R.id.nav_logout) {

                AlertDialog.Builder builder = new AlertDialog.Builder(Master_Home.this);
                builder.setMessage("Do you want to Logout ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            private SharedPreferences.Editor f2467p;

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                C0456b.f2467p = getSharedPreferences(C0456b.f2907a,0);

                               String f2466o = C0456b.f2467p.getString("user_id", null);
                                this.f2467p = C0456b.f2467p.edit();

                                this.f2467p.putString("user_id",null);
                                this.f2467p.commit();
                                Intent i1 = new Intent(Master_Home.this,Check.class);
                                startActivity(i1);
                                Intent intent = new Intent(Intent.ACTION_MAIN);
                                intent.addCategory(Intent.CATEGORY_HOME);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.setTitle("Logout");
                alert.show();
            }


            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        } Till here By Rahul*/
    private void startTest() {

        // set up an intent to share the image
        Intent share_intent = new Intent();
        share_intent.setAction(Intent.ACTION_SEND);
        share_intent.setType("text/plain");

        share_intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        share_intent.putExtra(Intent.EXTRA_TEXT,
                "https://play.google.com/store/apps/details?id=com.hshop&hl=en");

        // start the intent
        try {
            startActivity(Intent.createChooser(share_intent,
                    "ShareThroughChooser"));
        } catch (android.content.ActivityNotFoundException ex) {
            (new AlertDialog.Builder(Master_Home.this)
                    .setMessage("Share failed")
                    .setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int whichButton) {
                                }
                            }).create()).show();
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

