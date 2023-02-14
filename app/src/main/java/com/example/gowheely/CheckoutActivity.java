package com.example.gowheely;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gowheely.payment.PaymentActivity;
import com.razorpay.Checkout;

public class CheckoutActivity extends AppCompatActivity {

    private Button buttonConfirmOrder;
    private TextView dataTextView;
    private String data_received;

    private TextView mPrice;

    ListView simpleList;
    ArrayAdapter arrayAdapter;
    String groceriesList[] = {"Soyabean Oil, weight: 500gm :         Price = 50 Rs", "Baby Soap, weight: 80gm:         Price = 30 Rs", "Rin Soap, weight: 200gm:      Price = 60 Rs"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        Checkout.preload(getApplicationContext());

//        simpleList = (ListView)findViewById(R.id.simpleListView);
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_cart, R.id.listView1, groceriesList);
//        simpleList.setAdapter(arrayAdapter);
        findViews();
        listeners();
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, groceriesList);
        simpleList.setAdapter(arrayAdapter);

//        data_received = getIntent().getStringExtra("da");
//        dataTextView.setText(data_received);

    }

    public void findViews() {
        buttonConfirmOrder = (Button) findViewById(R.id.checkout_btn);
        mPrice = (TextView) findViewById(R.id.price);
        simpleList = (ListView)findViewById(R.id.simpleListView);
//        dataTextView = (TextView) findViewById(R.id.dataText);

    }

    public void listeners() {


        buttonConfirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CheckoutActivity.this, PaymentActivity.class);
                intent.putExtra("Price","140");
                startActivity(intent);
                Toast.makeText(CheckoutActivity.this, "Please fill payment", Toast.LENGTH_LONG).show();
                return;
            }
        });
    }
}


