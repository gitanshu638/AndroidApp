package com.example.gowheely;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText mEmail,mPassword,fName,phoneNo;
    Button mRegisterBtn;
    TextView mCreateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mEmail = findViewById(R.id.eMail);
        mPassword = findViewById(R.id.PassNew);
        fName = findViewById(R.id.fullName);
        phoneNo = findViewById(R.id.PhoneNo);
        mRegisterBtn = findViewById(R.id.register);
        mCreateBtn = findViewById(R.id.login);

        mCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });


    }
    public void register(){

        String Name,Email,Pass,Phone;

        Name = fName.getText().toString();
        Email = mEmail.getText().toString();
        Pass = mPassword.getText().toString();
        Phone = phoneNo.getText().toString();

        if(Name.equals(""));{
            Toast.makeText(RegisterActivity.this, "Name cannot be blank", Toast.LENGTH_SHORT).show();
        }

        if(Email.equals(""));{
            Toast.makeText(RegisterActivity.this, "Email can not be blank", Toast.LENGTH_SHORT).show();
        }
        if(Pass.equals(""));{
            Toast.makeText(RegisterActivity.this, "Password can not be blank", Toast.LENGTH_SHORT).show();
        }
        if(Phone.equals(""));{
            Toast.makeText(RegisterActivity.this, "Phone no. can not be blank", Toast.LENGTH_SHORT).show();
        }

        Intent intent = new Intent(this,CheckoutActivity.class);

    }
}