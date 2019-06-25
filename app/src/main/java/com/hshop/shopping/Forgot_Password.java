package com.hshop.shopping;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.hshop.R;
import com.hshop.models.UserForgotPassword;
import com.hshop.rest.Config;
import com.hshop.rest.RestClient;
import com.hshop.utils.NetworkUtils;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class Forgot_Password extends AppCompatActivity implements View.OnClickListener {

    Button btn_forgot;
    EditText edt_forcontact;
    String status,contact;
    LinearLayout linear_server;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot__password);
        edt_forcontact = (EditText) findViewById(R.id.edt_forcontact) ;
        btn_forgot = (Button) findViewById(R.id.btn_forgot);
        btn_forgot.setOnClickListener(this);
        linear_server = (LinearLayout) findViewById(R.id.linear_server);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_forgot:
                status = NetworkUtils.getConnectivityStatus(Forgot_Password.this);
                if (status.equals("404")) {
                    Toast.makeText(this, "Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                }
                else {
                    contact = edt_forcontact.getText().toString();
                    if (contact.length()==0)
                    {
                        edt_forcontact.setError("Enter Contact");
                    }
                    else if (!isPhoneValid(contact))
                    {
                        edt_forcontact.setError("Invalid Contact");
                    }
                    else
                    {
                       checkforgot(contact);
                    }
                }
                break;
        }
    }

    private void checkforgot(String contact) {
        final DilatingDotsProgressBar mDilatingDotsProgressBar = (DilatingDotsProgressBar) findViewById(R.id.progress);
        mDilatingDotsProgressBar.showNow();

        RestClient.GitApiInterface service = RestClient.getClient();
        Call<UserForgotPassword> call = service.getForgotpassword(Config.mem_string,contact);
        call.enqueue(new Callback<UserForgotPassword>() {

            @Override
            public void onResponse(Response<UserForgotPassword> response) {
                mDilatingDotsProgressBar.hideNow();
                Log.d("SplashActivity", "Status Code = " + response.code());
                if (response.isSuccess()) {
                    // request successful (status code 200, 201)
                    UserForgotPassword result = response.body();
                    if(result.getStatus().equals("success")) {
                        Toast.makeText(Forgot_Password.this, "Hshop Account Successfully Send on Registrated Contact Number", Toast.LENGTH_SHORT).show();
                        Intent i1 = new Intent(Forgot_Password.this,Login.class);
                        startActivity(i1);
                        finish();
                    } else if(result.getStatus().equals("notregister")){
                        Toast.makeText(Forgot_Password.this, "Contact Number not Register Yet.", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(Forgot_Password.this, "Plz TRY again Later", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // response received but request not successful (like 400,401,403 etc)
                    //Handle errors
                    linear_server.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Throwable t) {
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
