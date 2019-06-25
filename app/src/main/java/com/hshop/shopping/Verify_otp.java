package com.hshop.shopping;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hshop.R;

public class Verify_otp extends AppCompatActivity implements View.OnClickListener {

    Button btn_verifyotp;
    EditText edt_verifyotp;
    String mem_contact,mem_otp;
    TextView edt_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);
        Intent i1 = getIntent();
        mem_contact = i1.getStringExtra("mem_contact");
        mem_otp = i1.getStringExtra("mem_otp");
        setTitle("Verify +91 - "+mem_contact);
        edt_message = (TextView) findViewById(R.id.edt_message) ;
        edt_message.setText(mem_contact);
        edt_verifyotp = (EditText) findViewById(R.id.edt_verifyotp) ;
        btn_verifyotp = (Button) findViewById(R.id.btn_verifyotp);
        btn_verifyotp.setOnClickListener(this);

        /*SmsReceiver.bindListener(new SmsListener() {
            @Override
            public void messageReceived(String messageText) {
                String s2 = messageText.substring(0,6);

                edt_verifyotp.setText(s2);
            }
        });*/
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_verifyotp:
                String edotp = edt_verifyotp.getText().toString();
                if (edotp.equals(mem_otp))
                {
                    Intent i1 = new Intent(Verify_otp.this,Register.class);
                    i1.putExtra("mem_contact",mem_contact);
                    startActivity(i1);
                }
                else
                {
                    Toast.makeText(Verify_otp.this, "Invalid OTP", Toast.LENGTH_SHORT).show();
                }
                break;
        }
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
