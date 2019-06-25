package com.hshop.shopping;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hshop.R;

public class Check extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        C0456b.f2467p = getSharedPreferences(C0456b.f2907a,0);
        String contact= C0456b.f2467p.getString("user_id", null);

        if (contact != null)
        {
            Intent i1 = new Intent(Check.this,Splash_activity.class);
            startActivity(i1);
            finish();
        }
        else {
            Intent i1 = new Intent(Check.this,Login.class);
            startActivity(i1);
            finish();
        }
    }
}
