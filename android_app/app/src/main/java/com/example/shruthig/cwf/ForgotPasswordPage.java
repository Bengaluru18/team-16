package com.example.shruthig.cwf;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ForgotPasswordPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_page);
    }

    public void GotoLogin(View view) {
        // switch to login page
        Intent i = new Intent(ForgotPasswordPage.this,LoginPage.class);
        startActivity(i);
    }
}
