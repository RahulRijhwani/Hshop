package com.hshop.shopping;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.hshop.R;
import com.hshop.models.UserChangeProfile;
import com.hshop.models.UserProfile;
import com.hshop.rest.Config;
import com.hshop.rest.RestClient;
import com.hshop.utils.NetworkUtils;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class Profile extends AppCompatActivity implements View.OnClickListener  {

    String user_id,status,gender;
    Button btn_updateprofile;
    LinearLayout linear_pre;
    RadioButton edt_mr,edt_mrs;
    EditText edt_fname,edt_lname,edt_contact,edt_email,edt_houseno,edt_society,edt_locality,edt_pincode;
    String edt_fname1,edt_lname1,edt_contact1,edt_email1,edt_houseno1,edt_society1,edt_locality1,edt_pincode1,edt_gender1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        C0456b.f2467p = getSharedPreferences(C0456b.f2907a,0);
        user_id = C0456b.f2467p.getString("user_id",null);
        status = NetworkUtils.getConnectivityStatus(Profile.this);
        if (status.equals("404")) {
            Toast.makeText(this, "Check Your Internet Connection", Toast.LENGTH_SHORT).show();
        }
        else {
            getprofile(user_id);
        }

        edt_fname = (EditText) findViewById(R.id.edt_fname);
        edt_lname = (EditText) findViewById(R.id.edt_lname);
        edt_contact = (EditText) findViewById(R.id.edt_contact);
        edt_email = (EditText) findViewById(R.id.edt_email);
        edt_houseno = (EditText) findViewById(R.id.edt_houseno);
        edt_society = (EditText) findViewById(R.id.edt_society);
        edt_locality = (EditText) findViewById(R.id.edt_locality);
        edt_pincode = (EditText) findViewById(R.id.edt_pincode);
        linear_pre = (LinearLayout) findViewById(R.id.linear_pre);
        btn_updateprofile = (Button) findViewById(R.id.btn_updateprofile);
        btn_updateprofile.setOnClickListener(this);


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

    }
    private void getprofile(String user_id) {

        final DilatingDotsProgressBar mDilatingDotsProgressBar = (DilatingDotsProgressBar) findViewById(R.id.progress);
        mDilatingDotsProgressBar.showNow();
        RestClient.GitApiInterface service = RestClient.getClient();
        Call<UserProfile> call = service.getUserProfile(Config.mem_string,user_id);
        call.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Response<UserProfile> response) {
                mDilatingDotsProgressBar.hideNow();
                if (response.isSuccess()) {
                    // request successful (status code 200, 201)
                    UserProfile result = response.body();

                    if (result.getStatus().equals("success")) {

                        if (result.getData() != null) {
                            if (result.getData().size() > 0) {
                                Log.i("SplashActivity", "result.getData()" + result.getData().get(0).getFirstname());

                                String fname = result.getData().get(0).getFirstname();
                                String lname = result.getData().get(0).getLastname();
                                edt_gender1 = result.getData().get(0).getGender();
                                String contact = result.getData().get(0).getContact();
                                String email = result.getData().get(0).getEmail();
                                String houseno = result.getData().get(0).getHouseno();
                                String society = result.getData().get(0).getSociety();
                                String locality = result.getData().get(0).getLocality();
                                String pincode = result.getData().get(0).getPincode();

                                edt_fname.setText(fname);
                                edt_lname.setText(lname);
                                edt_contact.setText(contact);
                                edt_contact.setEnabled(false);
                                edt_email.setText(email);
                                edt_houseno.setText(houseno);
                                edt_society.setText(society);
                                edt_locality.setText(locality);
                                edt_pincode.setText(pincode);

                                if (edt_gender1.equals("Male"))
                                {
                                    edt_mr.setChecked(true);
                                }
                                else if(edt_gender1.equals("Female"))
                                {
                                    edt_mrs.setChecked(true);
                                }
                                linear_pre.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                mDilatingDotsProgressBar.hideNow();
                Toast.makeText(Profile.this, "Please try again is something wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_updateprofile:
                if (status.equals("404")) {
                    Toast.makeText(this, "Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                } else {
                    if (validate()) {
                        setUpdateProfile();
                    }
                }
                break;
        }
    }

    private void setUpdateProfile() {
        edt_fname1 = edt_fname.getText().toString();
        edt_lname1 = edt_lname.getText().toString();
        edt_email1 = edt_email.getText().toString();
        edt_houseno1 = edt_houseno.getText().toString();
        edt_society1 = edt_society.getText().toString();
        edt_locality1 = edt_locality.getText().toString();
        edt_pincode1 = edt_pincode.getText().toString();

        final DilatingDotsProgressBar mDilatingDotsProgressBar = (DilatingDotsProgressBar) findViewById(R.id.progress);
        mDilatingDotsProgressBar.showNow();

        RestClient.GitApiInterface service = RestClient.getClient();

        Call<UserChangeProfile> call = service.getUserChangeProfile(Config.mem_string,edt_gender1,edt_fname1,edt_lname1,user_id,edt_email1,edt_houseno1,edt_society1,edt_locality1,edt_pincode1);
        call.enqueue(new Callback<UserChangeProfile>() {
            public SharedPreferences.Editor f2467p2;
            public SharedPreferences.Editor f2467p1;

            @Override
            public void onResponse(Response<UserChangeProfile> response) {
                mDilatingDotsProgressBar.hideNow();
                Log.d("SplashActivity", "Status Code = " + response.code());
                if (response.isSuccess()) {
                    // request successful (status code 200, 201)
                    UserChangeProfile result = response.body();
                    if (result.getStatus().equals("success")) {

                        String user_name1 = String.valueOf(result.getUser_fname());
                        String user_contact1 = String.valueOf(result.getUser_contact());
                        String user_address1 = String.valueOf(result.getUser_address());

                        Log.d("fgh",user_name1);
                        Log.d("fgh",user_contact1);
                        Log.d("fgh",user_address1);

                        C0456b.f2467p1 = getSharedPreferences(C0456b.f2907a1,0);
                        C0456b.f2467p2 = getSharedPreferences(C0456b.f2907a2,0);
                        String f00p1 = C0456b.f2467p1.getString("user_info",null);
                        String f00p2= C0456b.f2467p2.getString("user_address",null);
                        this.f2467p1 = C0456b.f2467p1.edit();
                        this.f2467p2 = C0456b.f2467p2.edit();
                        this.f2467p1.putString("user_info",user_name1+","+user_contact1);
                        this.f2467p2.putString("user_address",user_address1);
                        this.f2467p1.commit();
                        this.f2467p2.commit();
                        Toast.makeText(Profile.this, "Profile Successfully Updated", Toast.LENGTH_SHORT).show();
                        Intent i1 = new Intent(Profile.this,Master_Home.class);
                        startActivity(i1);
                        finish();

                    } else {
                    }
                } else {
                    // response received but request not successful (like 400,401,403 etc)
                    //Handle errors
                    Toast.makeText(Profile.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                mDilatingDotsProgressBar.hideNow();
            }
        });
    }

    public boolean validate() {
        boolean valid = true;

        edt_fname1 = edt_fname.getText().toString();
        edt_lname1 = edt_lname.getText().toString();
        edt_contact1 = edt_contact.getText().toString();
        edt_email1 = edt_email.getText().toString();
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
       /* if (edt_email1.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(edt_email1).matches()) {
            edt_email.setError("Enter a valid email address");
            edt_email.requestFocus();
            valid = false;
        } else {
            edt_email.setError(null);
        }  */

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
}
