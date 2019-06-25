package com.hshop.shopping;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hshop.R;
import com.hshop.models.UserChangePassword;
import com.hshop.rest.Config;
import com.hshop.rest.RestClient;
import com.hshop.utils.NetworkUtils;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class Update_password extends AppCompatActivity implements View.OnClickListener {

    Button btn_updatepassword;
    String status,user_id;
    EditText up_currentpassword,up_newpassword,up_newconfirmpassword;
    String up_currentpassword1,up_newpassword1,up_newconfirmpassword1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);

        C0456b.f2467p = getSharedPreferences(C0456b.f2907a,0);
        user_id = C0456b.f2467p.getString("user_id",null);

        up_currentpassword = (EditText) findViewById(R.id.up_currentpassword) ;
        up_newpassword = (EditText) findViewById(R.id.up_newpassword) ;
        up_newconfirmpassword = (EditText) findViewById(R.id.up_newconfirmpassword) ;
        btn_updatepassword = (Button) findViewById(R.id.btn_updatepassword);
        btn_updatepassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_updatepassword:
                status = NetworkUtils.getConnectivityStatus(Update_password.this);
                if (status.equals("404")) {
                    Toast.makeText(this, "Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                }
                else {
                    up_currentpassword1 = up_currentpassword.getText().toString();
                    up_newpassword1 = up_newpassword.getText().toString();
                    up_newconfirmpassword1 = up_newconfirmpassword.getText().toString();
                    if (up_currentpassword1.length()==0)
                    {
                        up_currentpassword.setError("Enter Current Password");
                    }
                    else if (up_newpassword1.length()==0)
                    {
                        up_newpassword.setError("Enter New Password");
                    }
                    else if (up_newconfirmpassword1.length()==0) {
                        up_newconfirmpassword.setError("Enter Confirm Password");
                    }
                    else
                    {
                        if(up_newpassword1.equals(up_newconfirmpassword1))
                        {
                            updatepassword(user_id,up_currentpassword1,up_newpassword1);
                        }
                        else
                        {
                            Toast.makeText(this, "New Password and Confirm Password Does Not Match", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
                break;
        }
    }

    private void updatepassword(String user_id, String up_currentpassword1, String up_newpassword1) {

        final DilatingDotsProgressBar mDilatingDotsProgressBar = (DilatingDotsProgressBar) findViewById(R.id.progress);
        mDilatingDotsProgressBar.showNow();

        RestClient.GitApiInterface service = RestClient.getClient();
        Call<UserChangePassword> call = service.getUserChangePassword(Config.mem_string,user_id,up_currentpassword1,up_newpassword1);
        call.enqueue(new Callback<UserChangePassword>() {

            @Override
            public void onResponse(Response<UserChangePassword> response) {
                mDilatingDotsProgressBar.hideNow();
                Log.d("SplashActivity", "Status Code = " + response.code());
                if (response.isSuccess()) {
                    // request successful (status code 200, 201)
                    UserChangePassword result = response.body();
                    Log.d("SplashActivity", String.valueOf(result.getStatus()));
                    if(result.getStatus().equals("success")) {
                        Toast.makeText(Update_password.this, "Password Successfully Updated", Toast.LENGTH_SHORT).show();
                        Intent i1 = new Intent(Update_password.this,Master_Home.class);
                        startActivity(i1);
                        finish();
                    } else if(result.getStatus().equals("notmatch")){
                        Toast.makeText(Update_password.this, "Current Password Does Not Match", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(Update_password.this, "Plz TRY again Later", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // response received but request not successful (like 400,401,403 etc)
                    //Handle errors
                    Toast.makeText(Update_password.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                mDilatingDotsProgressBar.hideNow();
            }
        });
    }
}
