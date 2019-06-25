package com.hshop.shopping;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.hshop.R;
import com.hshop.models.AllContactCheck;
import com.hshop.rest.Config;
import com.hshop.rest.RestClient;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import java.util.Random;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class Generate_otp extends AppCompatActivity implements View.OnClickListener {

    Button btn_generateotp;
    EditText edt_contact;
    String contact,finalotp;
    StringBuffer otp;
    LinearLayout linear_server;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_otp);

        edt_contact = (EditText) findViewById(R.id.edt_contact);
        btn_generateotp = (Button) findViewById(R.id.btn_generateotp);
        btn_generateotp.setOnClickListener(this);
        linear_server = (LinearLayout) findViewById(R.id.linear_server);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_generateotp:
                    sendotp();
                break;
        }
    }

    private void sendotp() {
        contact = edt_contact.getText().toString();
        if (contact.length()==0)
        {
            edt_contact.setError("Enter Contact");
        }
        else if (!isPhoneValid(contact))
        {
            edt_contact.setError("Invalid Contact");
        }
        else
        {
            Random rnd = new Random();
            otp = new StringBuffer("" + rnd.nextInt(999999));
            if (otp.length() <= 5) {
                for (int i = otp.length(); i <= 5; i++) {
                    otp.insert(0, "0");
                }
            }
            finalotp= otp.toString();
            callCheckContactData();
        }
    }
    public void callCheckContactData() {
        final DilatingDotsProgressBar mDilatingDotsProgressBar = (DilatingDotsProgressBar) findViewById(R.id.progress);
        mDilatingDotsProgressBar.showNow();
        RestClient.GitApiInterface service = RestClient.getClient();
        Call<AllContactCheck> call = service.getAllContactCheck(Config.mem_string,contact,finalotp);
        call.enqueue(new Callback<AllContactCheck>() {
            @Override
            public void onResponse(Response<AllContactCheck> response) {
                mDilatingDotsProgressBar.hideNow();
                Log.d("test", "Status Code = " + response.code());
                if (response.isSuccess()) {
                    // request successful (status code 200, 201)
                    AllContactCheck result = response.body();
                    Log.d("fgh", result.getStatus());
                    if (result.getStatus().equals("success")) {

                       //  Toast.makeText(Generate_otp.this,finalotp, Toast.LENGTH_SHORT).show();
                        Intent i1 = new Intent(Generate_otp.this,Verify_otp.class);
                        i1.putExtra("mem_contact",contact);
                        i1.putExtra("mem_otp",finalotp);
                        startActivity(i1);
                        finish();
                    } else if (result.getStatus().equals("already")){
                        Toast.makeText(Generate_otp.this, "Contact Number Already Register With us", Toast.LENGTH_SHORT).show();
                    }
                    else {

                    }
                } else {
                    // response received but request not successful (like 400,401,403 etc)
                    linear_server.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onFailure(Throwable t) {
                Log.d("fgh", String.valueOf(t));
                mDilatingDotsProgressBar.hideNow();
            }
        });

    }
    public static boolean isPhoneValid(String phone) {
        boolean retval = false;
        String phoneNumberPattern =
                "(\\d{10})?";
        retval = phone.matches(phoneNumberPattern);
        String msg = "NO MATCH: pattern:" + phone
                + "\r\n regex: " + phoneNumberPattern;
        if (retval) {
            msg = " MATCH: pattern:" + phone
                    + "\r\n regex: " + phoneNumberPattern;
        }
        System.out.println(msg + "\r\n");
        return retval;
    }

}
