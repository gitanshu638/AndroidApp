package com.example.gowheely;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gowheely.payment.PaymentActivity;
import com.razorpay.Checkout;

public class CheckoutActivity extends AppCompatActivity {

    private Button buttonConfirmOrder;
    private TextView dataTextView;
    private String data_received;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        Checkout.preload(getApplicationContext());
        findViews();
        listeners();

        data_received = getIntent().getStringExtra("data_sent");
        dataTextView.setText(data_received);

    }

    public void findViews() {
        buttonConfirmOrder = (Button) findViewById(R.id.checkout_btn);
        dataTextView = (TextView) findViewById(R.id.dataText);

    }

    public void listeners() {


        buttonConfirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CheckoutActivity.this, PaymentActivity.class);
                startActivity(intent);
                Toast.makeText(CheckoutActivity.this, "Please fill payment", Toast.LENGTH_LONG).show();
                return;
            }
        });
    }
}


