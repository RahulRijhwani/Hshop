package com.hshop.shopping;

import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.hshop.R;
import com.hshop.models.RegisterUser;
import com.hshop.rest.Config;
import com.hshop.rest.RestClient;
import com.hshop.utils.NetworkUtils;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class Register extends AppCompatActivity implements View.OnClickListener {

    Button btn_signup;
    RadioButton edt_mr,edt_mrs;
    String status,mem_contact;
    String edt_gender1;
    EditText edt_fname,edt_lname,edt_contact,edt_email,edt_password,edt_confirmpassword,edt_houseno,edt_society,edt_locality,edt_pincode;
    String edt_fname1,edt_lname1,edt_contact1,edt_email1,edt_password1,edt_confirmpassword1,edt_houseno1,edt_society1,edt_locality1,edt_pincode1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edt_fname = (EditText) findViewById(R.id.edt_fname);
        edt_lname = (EditText) findViewById(R.id.edt_lname);
        edt_contact = (EditText) findViewById(R.id.edt_contact);
        edt_email = (EditText) findViewById(R.id.edt_email);
        edt_password = (EditText) findViewById(R.id.edt_password);
        edt_confirmpassword = (EditText) findViewById(R.id.edt_confirmpassword);
        edt_houseno = (EditText) findViewById(R.id.edt_houseno);
        edt_society = (EditText) findViewById(R.id.edt_society);
        edt_locality = (EditText) findViewById(R.id.edt_locality);
        edt_pincode = (EditText) findViewById(R.id.edt_pincode);

        Intent i1 = getIntent();
        mem_contact = i1.getStringExtra("mem_contact");
        edt_contact.setText(mem_contact);
        edt_contact.setEnabled(false);

        View.OnClickListener radioListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioButton rb = (RadioButton) view;
                edt_gender1  = rb.getText().toString();
            }
        };

        edt_mr = (RadioButton) findViewById(R.id.edt_mr);
        edt_mr.setOnClickListener(radioListener);
        edt_mrs = (RadioButton) findViewById(R.id.edt_mrs);
        edt_mrs.setOnClickListener(radioListener);
        btn_signup = (Button) findViewById(R.id.btn_signup);
        btn_signup.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_signup:

                status = NetworkUtils.getConnectivityStatus(Register.this);
                if (status.equals("404")) {
                    Toast.makeText(this, "Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                } else {
                    if (validate()) {
                        setRegisterUser();
                    }
                }
                break;
        }
    }

    private void setRegisterUser() {
        /*String android_id = Settings.Secure.getString(Register.this.getContentResolver(),
                    Settings.Secure.ANDROID_ID);*/
        String token= FirebaseInstanceId.getInstance().getToken();
        String android_id = token;

        edt_fname1 = edt_fname.getText().toString();
        edt_lname1 = edt_lname.getText().toString();
        edt_contact1 = edt_contact.getText().toString();
        edt_email1 = edt_email.getText().toString();
        edt_password1 = edt_password.getText().toString();
        edt_confirmpassword1 = edt_confirmpassword.getText().toString();
        edt_houseno1 = edt_houseno.getText().toString();
        edt_society1 = edt_society.getText().toString();
        edt_locality1 = edt_locality.getText().toString();
        edt_pincode1 = edt_pincode.getText().toString();

        if (edt_password1.equals(edt_confirmpassword1))
        {
        final DilatingDotsProgressBar mDilatingDotsProgressBar = (DilatingDotsProgressBar) findViewById(R.id.progress);
            mDilatingDotsProgressBar.showNow();

            RestClient.GitApiInterface service = RestClient.getClient();
            Call<RegisterUser> call = service.getRegister(android_id,Config.mem_string,edt_gender1,edt_fname1,edt_lname1,mem_contact,edt_email1,edt_password1,edt_houseno1,edt_society1,edt_locality1,edt_pincode1);
            call.enqueue(new Callback<RegisterUser>() {
                public SharedPreferences.Editor f2467p2;
                public SharedPreferences.Editor f2467p1;
                public SharedPreferences.Editor f2467p;

                @Override
                public void onResponse(Response<RegisterUser> response) {
                    mDilatingDotsProgressBar.hideNow();
                    Log.d("SplashActivity", "Status Code = " + response.code());
                    if (response.isSuccess()) {
                        // request successful (status code 200, 201)
                        RegisterUser result = response.body();
                        if (result.getStatus().equals("success")) {
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


                                Intent i = new Intent(Register.this,Check.class);
                                startActivity(i);
                                finish();
                            }

                        } else {
                        }
                    } else {
                        // response received but request not successful (like 400,401,403 etc)
                        //Handle errors
                        Toast.makeText(Register.this, "Server Error", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    mDilatingDotsProgressBar.hideNow();
                }
            });
        }else
        {
            Toast.makeText(this, "Password Does Not Match", Toast.LENGTH_SHORT).show();
        }
        }
    public boolean validate() {
        boolean valid = true;

        edt_fname1 = edt_fname.getText().toString();
        edt_lname1 = edt_lname.getText().toString();
        edt_contact1 = edt_contact.getText().toString();
        edt_email1 = edt_email.getText().toString();
        edt_password1 = edt_password.getText().toString();
        edt_confirmpassword1 = edt_confirmpassword.getText().toString();
        edt_houseno1 = edt_houseno.getText().toString();
        edt_society1 = edt_society.getText().toString();
        edt_locality1 = edt_locality.getText().toString();
        edt_pincode1 = edt_pincode.getText().toString();

        if (edt_fname1.isEmpty()) {
            edt_fname.setError("Enter a firstname");
            edt_fname.requestFocus();
            valid = false;
        } else {
            edt_fname.setError(null);
        }
        if (edt_lname1.isEmpty()) {
            edt_lname.setError("Enter a lastname");
            edt_lname.requestFocus();
            valid = false;
        } else {
            edt_lname.setError(null);
        }
        if (edt_password1.isEmpty()) {
            edt_password.setError("Enter a Password");
            edt_password.requestFocus();
            valid = false;
        } else {
            edt_password.setError(null);
        }
        if (edt_confirmpassword1.isEmpty()) {
            edt_confirmpassword.setError("Enter a Confirmpassword");
            edt_confirmpassword.requestFocus();
            valid = false;
        } else {
            edt_confirmpassword.setError(null);
        }

        if (edt_houseno1.isEmpty()) {
            edt_houseno.setError("Enter a Flat/House/Office No");
            edt_houseno.requestFocus();
            valid = false;
        } else {
            edt_houseno.setError(null);
        }
        if (edt_society1.isEmpty()) {
            edt_society.setError("Enter a Street/Society/Office Name");
            edt_society.requestFocus();
            valid = false;
        } else {
            edt_society.setError(null);
        }
        if (edt_locality1.isEmpty()) {
            edt_locality.setError("Enter a Locality");
            edt_locality.requestFocus();
            valid = false;
        } else {
            edt_locality.setError(null);
        }
        if (edt_pincode1.isEmpty()) {
            edt_pincode.setError("Enter a Pincode");
            edt_pincode.requestFocus();
            valid = false;
        } else {
            edt_pincode.setError(null);
        }

        if (edt_gender1.length()==0) {
            Toast.makeText(this, "Select Gender", Toast.LENGTH_SHORT).show();
        }

        return valid;
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            // do something on back.
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
