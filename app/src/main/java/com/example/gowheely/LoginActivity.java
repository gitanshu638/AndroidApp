package com.example.gowheely;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {
    EditText mEmail,mPassword;
    Button mLoginBtn;
    TextView mCreateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmail = findViewById(R.id.Email);
        mPassword = findViewById(R.id.Password);
        mLoginBtn = findViewById(R.id.login_btn);
        mCreateBtn = findViewById(R.id.createText);


        mCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
            }
        });


        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    public void login(){
        String email = mEmail.getText().toString().trim();
        String pass = mPassword.getText().toString().trim();

        if(email.equals("abc@gmail.com") && pass.equals("12345")){
            Toast.makeText(this, "Email & Password Matched", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,CheckoutActivity.class));
        }
        else {
            Toast.makeText(this, "Email & Password not Matched", Toast.LENGTH_SHORT).show();
        }
    }
}