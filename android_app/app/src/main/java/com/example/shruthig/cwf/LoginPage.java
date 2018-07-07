package com.example.shruthig.cwf;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
    }


    public void OpenLogin(View view) {
    }



    public void OpenRegister(View view) {
        // Switch to login page
        Intent i = new Intent(LoginPage.this,RegisterPage.class);
        startActivity(i);
    }

    public void ForgotPass(View view) {
        //Switch to the ForgotPassword page
        Intent j = new Intent(LoginPage.this,ForgotPasswordPage.class);
        startActivity(j);
    }
}
