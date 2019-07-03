package com.hshop.shopping;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.hshop.R;
import com.hshop.models.UserLogin;
import com.hshop.rest.Config;
import com.hshop.rest.RestClient;
import com.hshop.utils.NetworkUtils;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class Login extends AppCompatActivity implements View.OnClickListener {

    Button btn_login;
    TextView txt_forgot,txt_signup,txt_terms;
    EditText edt_lcontact,edt_lpassword;
    String status,contact,password;
    LinearLayout linear_server;
    String token="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edt_lcontact = (EditText) findViewById(R.id.edt_lcontact);
        edt_lpassword = (EditText) findViewById(R.id.edt_lpassword);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
        txt_forgot = (TextView) findViewById(R.id.txt_forgot);
        txt_forgot.setOnClickListener(this);
        txt_signup = (TextView) findViewById(R.id.txt_signup);
        txt_terms = (TextView) findViewById(R.id.txt_terms);
        txt_signup.setOnClickListener(this);
        txt_terms.setOnClickListener(this);
        linear_server = (LinearLayout) findViewById(R.id.linear_server);
        //token= FirebaseInstanceId.getInstance().getToken();
        Log.e("newToken",token);
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener( Login.this,  new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                token = instanceIdResult.getToken();
               // Log.d("newToken",token);
                //Toast.makeText(Login.this, token, Toast.LENGTH_SHORT).show();
            }

        }
        ).addOnFailureListener(Login.this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //Log.e("token error",e.toString());
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                status = NetworkUtils.getConnectivityStatus(Login.this);
                if (status.equals("404")) {
                    Toast.makeText(this, "Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                } else {
                    contact = edt_lcontact.getText().toString();
                    password = edt_lpassword.getText().toString();
                    if (contact.length()==0)
                    {
                        edt_lcontact.setError("Enter Contact");
                    }
                    else if (!isPhoneValid(contact))
                    {
                        edt_lcontact.setError("Invalid Contact");
                    }
                    else if(password.length()==0)
                    {
                        edt_lpassword.setError("Enter Password");
                    }
                    else
                    {
                        checklogin(contact,password,token);
                    }
                }
                break;
            case R.id.txt_forgot:
                Intent i2 = new Intent(Login.this,Forgot_Password.class);
                startActivity(i2);
                break;
            case R.id.txt_signup:
                Intent i3 = new Intent(Login.this,Generate_otp.class);
                startActivity(i3);
                break;
            case R.id.txt_terms:
                String url = "http://hshop.co.in/index.php?file=terms-Conditions";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                break;
        }
    }

    private void checklogin(String contact, String password,String token) {

        final DilatingDotsProgressBar mDilatingDotsProgressBar = (DilatingDotsProgressBar) findViewById(R.id.progress);
        mDilatingDotsProgressBar.showNow();

        RestClient.GitApiInterface service = RestClient.getClient();
        Call<UserLogin> call = service.getUserLogin(Config.mem_string,contact,password,token);
        call.enqueue(new Callback<UserLogin>() {
            public SharedPreferences.Editor f2467p2;
            public SharedPreferences.Editor f2467p1;
            public SharedPreferences.Editor f2467p;
            @Override
            public void onResponse(Response<UserLogin> response) {
                mDilatingDotsProgressBar.hideNow();
                Log.d("SplashActivity", "Status Code = " + response.code());
                if (response.isSuccess()) {
                    // request successful (status code 200, 201)
                    UserLogin result = response.body();
                    Log.d("fgh",result.getStatus());
                    if(result.getStatus().equals("success")) {
                        if (result.getData() != null) {
                            String user_id1 = String.valueOf(result.getData());
                            String user_name1 = String.valueOf(result.getUser_fname());
                            String user_contact1 = String.valueOf(result.getUser_contact());
                            String user_address1 = String.valueOf(result.getUser_address());

                            C0456b.f2467p = getSharedPreferences(C0456b.f2907a,0);
                            C0456b.f2467p1 = getSharedPreferences(C0456b.f2907a1,0);
                            C0456b.f2467p2 = getSharedPreferences(C0456b.f2907a2,0);

                            String f00p = C0456b.f2467p.getString("user_id",null);
                            String f00p1 = C0456b.f2467p1.getString("user_info",null);
                            String f00p2= C0456b.f2467p2.getString("user_address",null);
                            this.f2467p = C0456b.f2467p.edit();
                            this.f2467p1 = C0456b.f2467p1.edit();
                            this.f2467p2 = C0456b.f2467p2.edit();
                            this.f2467p.putString("user_id",user_id1);
                            this.f2467p1.putString("user_info",user_name1+","+user_contact1);
                            this.f2467p2.putString("user_address",user_address1);
                            this.f2467p.commit();
                            this.f2467p1.commit();
                            this.f2467p2.commit();

                            Intent i = new Intent(Login.this,Check.class);
                            startActivity(i);
                            finish();
                        }
                        else {
                            Toast.makeText(Login.this, "Invalid Contact OR Password", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(Login.this, "Invalid Contact OR Password", Toast.LENGTH_SHORT).show();
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
