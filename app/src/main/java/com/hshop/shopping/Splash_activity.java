package com.hshop.shopping;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hshop.R;
import com.hshop.models.UserActiveInfo;
import com.hshop.rest.Config;
import com.hshop.rest.RestClient;
import com.hshop.utils.NetworkUtils;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class Splash_activity extends AppCompatActivity {

    String user_id,status;
    ImageView sp_logo;
    LinearLayout linear_no_internet,linear_server;
    TextView companytxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_activity);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        companytxt=(TextView)findViewById(R.id.companytxt);
        sp_logo = (ImageView) findViewById(R.id.sp_logo);
        linear_no_internet = (LinearLayout) findViewById(R.id.linear_no_internet);
        linear_server = (LinearLayout) findViewById(R.id.linear_server);

        C0456b.f2467p = getSharedPreferences(C0456b.f2907a,0);
        user_id = C0456b.f2467p.getString("user_id",null);

        status = NetworkUtils.getConnectivityStatus(Splash_activity.this);
        if (status.equals("404")) {
            linear_no_internet.setVisibility(View.VISIBLE);
        }
        else {
            companytxt.setVisibility(View.VISIBLE);
            sp_logo.setVisibility(View.VISIBLE);
            fetchactiveuser(Config.mem_string,user_id);
        }
    }
    private void fetchactiveuser(String mem_string, String user_id) {
       // final DilatingDotsProgressBar mDilatingDotsProgressBar = (DilatingDotsProgressBar) findViewById(R.id.progress);
        // mDilatingDotsProgressBar.showNow();
        RestClient.GitApiInterface service = RestClient.getClient();
        Call<UserActiveInfo> call = service.getUseractiveinfo(mem_string,user_id);
        call.enqueue(new Callback<UserActiveInfo>() {
            public SharedPreferences.Editor f2467p4;
            public SharedPreferences.Editor f2467p3;
            public SharedPreferences.Editor f2467p5;

            @Override
            public void onResponse(Response<UserActiveInfo> response) {
               // mDilatingDotsProgressBar.hideNow();
                if (response.isSuccess()) {
                    // request successful (status code 200, 201)
                    UserActiveInfo result = response.body();
                    if (result.getStatus().equals("success")) {

                        Log.d("fgh",result.getOfferposter());
                        Log.d("fgh",result.getOfferposterid());


                        C0456b.f2467p3 = getSharedPreferences(C0456b.f2907a3,0);
                        String f00p3 = C0456b.f2467p3.getString("offerposter",null);
                        this.f2467p3 = C0456b.f2467p3.edit();
                        this.f2467p3.putString("offerposter",String.valueOf(result.getOfferposter()));
                        this.f2467p3.commit();


                        C0456b.f2467p5 = getSharedPreferences(C0456b.f2907a5,0);
                        String f00p5 = C0456b.f2467p5.getString("offerpostername",null);
                        this.f2467p5 = C0456b.f2467p5.edit();
                        this.f2467p5.putString("offerpostername",String.valueOf(result.getOfferpostername()));
                        this.f2467p5.commit();

                        C0456b.f2467p4 = getSharedPreferences(C0456b.f2907a4,0);
                        String f00p4 = C0456b.f2467p4.getString("offerposterid",null);
                        this.f2467p4 = C0456b.f2467p4.edit();
                        this.f2467p4.putString("offerposterid",String.valueOf(result.getOfferposterid()));
                        this.f2467p4.commit();

                        Intent i1 = new Intent(Splash_activity.this,Master_Home.class);
                        startActivity(i1);
                        finish();
                    }
                    else if(result.getStatus().equals("servererror")){
                        AlertDialog.Builder builder = new AlertDialog.Builder(Splash_activity.this);
                        builder.setMessage(result.getMessage()).setTitle("Response")
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                        finish();
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                    else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(Splash_activity.this);
                        builder.setMessage("Some Reason Through Inactive By Admin.").setTitle("Response")
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                        finish();
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                }
                else
                {
                    linear_server.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onFailure(Throwable t) {
              //   mDilatingDotsProgressBar.hideNow();
                Toast.makeText(Splash_activity.this, "Please try again is something wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
