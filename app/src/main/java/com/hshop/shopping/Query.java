package com.hshop.shopping;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
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
import com.hshop.models.UserSuggestion;
import com.hshop.rest.Config;
import com.hshop.rest.RestClient;
import com.hshop.utils.NetworkUtils;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class Query extends AppCompatActivity implements View.OnClickListener{

    EditText edt_message;
    String edt_message1;
    String status,user_id;
    Button btn_updatepassword;
    LinearLayout call_query;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);

        edt_message = (EditText) findViewById(R.id.edt_message);
        call_query = (LinearLayout) findViewById(R.id.call_query);
        call_query.setOnClickListener(this);
        btn_updatepassword = (Button) findViewById(R.id.btn_updatepassword);
        btn_updatepassword.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_updatepassword:

                status = NetworkUtils.getConnectivityStatus(Query.this);
                if (status.equals("404")) {
                    Toast.makeText(this, "Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                } else {
                    if (validate()) {
                        setRegisterUser();
                    }
                }
                break;
            case R.id.call_query:
                try
                {
                    Intent i1 = new Intent(Intent.ACTION_CALL, Uri.parse("tel:7096364414"));
                    startActivity(i1);
                }
                catch (Exception e)
                {
                    isStoragePermissionGranted();
                }

                break;
        }
    }
    private void setRegisterUser() {

        edt_message1 = edt_message.getText().toString();


        C0456b.f2467p = getSharedPreferences(C0456b.f2907a,0);
        user_id = C0456b.f2467p.getString("user_id",null);

        final DilatingDotsProgressBar mDilatingDotsProgressBar = (DilatingDotsProgressBar) findViewById(R.id.progress);
        mDilatingDotsProgressBar.showNow();
        RestClient.GitApiInterface service = RestClient.getClient();
        Call<UserSuggestion> call = service.getUserSuggestion(Config.mem_string,user_id,edt_message1);
        call.enqueue(new Callback<UserSuggestion>() {
            @Override
            public void onResponse(Response<UserSuggestion> response) {
                mDilatingDotsProgressBar.hideNow();
                if (response.isSuccess()) {
                    // request successful (status code 200, 201)
                    UserSuggestion result = response.body();
                    if (result.getStatus().equals("success")) {
                        Toast.makeText(Query.this, "Query Successfully Submitted", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Query.this,Master_Home.class);
                        startActivity(i);
                        finish();

                    }
                    else
                    {
                        Toast.makeText(Query.this, "Plz TRY again Later", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // response received but request not successful (like 400,401,403 etc)
                    //Handle errors
                    Toast.makeText(Query.this, "Server Error", Toast.LENGTH_SHORT).show();
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

        edt_message1 = edt_message.getText().toString();

        if (edt_message1.isEmpty()) {
            edt_message.setError("Enter a Message");
            edt_message.requestFocus();
            valid = false;
        } else {
            edt_message.setError(null);
        }



        return valid;
    }
    public boolean isStoragePermissionGranted() {
        String TAG = "cxvx";
        if (Build.VERSION.SDK_INT >= 23) {
            if ( checkSelfPermission(Manifest.permission.CALL_PHONE)
                    == PackageManager.PERMISSION_GRANTED ) {
                Log.v(TAG, "Permission is granted");
                return true;
            } else {
                Log.v(TAG, "Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG, "Permission is granted");
            return true;
        }
    }

}