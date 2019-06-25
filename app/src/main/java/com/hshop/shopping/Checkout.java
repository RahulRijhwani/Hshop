package com.hshop.shopping;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hshop.R;
import com.hshop.models.UserCheckout;
import com.hshop.rest.Config;
import com.hshop.rest.RestClient;
import com.hshop.utils.NetworkUtils;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class Checkout extends AppCompatActivity {
    TextView txt_houseno,txt_society,txt_locality,txt_pincode;
    EditText edt_houseno,edt_society,edt_locality,edt_pincode;
    String user_info,user_address,user_id;
    Button final_checkout;
    String status;
    LinearLayout new_address,new_address_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        txt_houseno = (TextView) findViewById(R.id.txt_houseno);
        edt_houseno = (EditText) findViewById(R.id.edt_houseno);
        txt_society = (TextView) findViewById(R.id.txt_society);
        edt_society = (EditText) findViewById(R.id.edt_society);
        txt_locality = (TextView) findViewById(R.id.txt_locality);
       // edt_locality = (EditText) findViewById(R.id.edt_locality);
        txt_pincode = (TextView) findViewById(R.id.txt_pincode);
        edt_pincode = (EditText) findViewById(R.id.edt_pincode);
        final_checkout  = (Button) findViewById(R.id.final_checkout);
        new_address  = (LinearLayout) findViewById(R.id.new_address);
        new_address_add  = (LinearLayout) findViewById(R.id.new_address_add);
        C0456b.f2467p1 = getSharedPreferences(C0456b.f2907a1,0);
        C0456b.f2467p2 = getSharedPreferences(C0456b.f2907a2,0);
        user_info = C0456b.f2467p1.getString("user_info",null);
        user_address = C0456b.f2467p2.getString("user_address",null);

        final String user_name[] = user_info.split(",");
        final String user_location[] = user_address.split(",");

        C0456b.f2467p = getSharedPreferences(C0456b.f2907a,0);
        user_id = C0456b.f2467p.getString("user_id",null);

        try
        {
            txt_houseno.setText(user_location[0]);
            txt_society.setText(user_location[1]);
          //  txt_locality.setText(user_location[2]);
            txt_pincode.setText(user_location[3]);

            edt_houseno.setText(user_location[0]);
            edt_society.setText(user_location[1]);
            edt_locality.setText(user_location[2]);
            edt_pincode.setText(user_location[3]);
        }
        catch (Exception e)
        {

        }
        new_address_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new_address_add.setEnabled(false);
                new_address.setVisibility(View.VISIBLE);
            }
        });
        final_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String o_number = edt_houseno.getText().toString();
                String o_society = edt_society.getText().toString();
             //   String o_location = edt_locality.getText().toString();
                String o_pincode = edt_pincode.getText().toString();
                status = NetworkUtils.getConnectivityStatus(Checkout.this);
                if (status.equals("404")) {
                    Toast.makeText(Checkout.this, "Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(o_number.length()==0)
                    {
                        edt_houseno.setError("Enter Flat/House/Office Number");
                    }else if(o_number.length()==0)
                    {
                        edt_society.setError("Enter Street/Society/Office Name");
                    }
               /*     else if(o_number.length()==0)
                    {
                        edt_locality.setError("Enter Locality");
                    }*/
                    else if(o_number.length()==0)
                    {
                        edt_pincode.setError("Enter Pincode");
                    }
                    else
                    {
                        checkout(Config.mem_string,user_id,o_number,o_society,"",o_pincode);
                    }
                }
            }
        });
    }
    private void checkout(String mem_string, String user_id, String o_number, String o_society, String o_location, String o_pincode) {
        final ProgressDialog progressdialog = new ProgressDialog(Checkout.this);
        progressdialog.setMessage("Please Wait....");
        progressdialog.show();
        final_checkout.setEnabled(false);
        RestClient.GitApiInterface service = RestClient.getClient();
        Call<UserCheckout> call = service.getUsercheckout(mem_string,user_id,o_number,o_society,o_location,o_pincode);
        call.enqueue(new Callback<UserCheckout>() {
            @Override
            public void onResponse(Response<UserCheckout> response) {
                progressdialog.dismiss();
                if (response.isSuccess()) {
                    // request successful (status code 200, 201)
                    UserCheckout result = response.body();
                    if(result.getStatus().equals("success")) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Checkout.this);
                        builder.setMessage("Order Successfully Placed...!")
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Intent i1 = new Intent(Checkout.this,Order_details.class);
                                        startActivity(i1);
                                        finish();
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                    else
                    {
                        final_checkout.setEnabled(true);
                        Toast.makeText(Checkout.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    final_checkout.setEnabled(true);
                    // response received but request not successful (like 400,401,403 etc)
                    //Handle errors
                    Toast.makeText(Checkout.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                progressdialog.dismiss();
            }
        });
    }
}
