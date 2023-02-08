package com.example.gowheely.payment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gowheely.R;
import com.razorpay.Checkout;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultListener;
import com.razorpay.PaymentResultWithDataListener;

import org.json.JSONObject;

public class PaymentActivity extends AppCompatActivity implements PaymentResultWithDataListener {

    private Button buttonConfirmOrder;
    private EditText editTextPayment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Checkout.preload(getApplicationContext());
        findViews();
        listeners();


    }
    public void findViews() {
        buttonConfirmOrder = (Button) findViewById(R.id.buttonConfirmOrder);
        editTextPayment = (EditText) findViewById(R.id.editTextPayment);
    }

    public void listeners() {


        buttonConfirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editTextPayment.getText().toString().equals(""))
                {
                    Toast.makeText(PaymentActivity.this, "Please fill payment", Toast.LENGTH_LONG).show();
                    return;
                }
                startPayment();
            }
        });
    }


    public void startPayment() {
        /**
         * You need to pass current activity in order to let Razorpay create CheckoutActivity
         */
        final Activity activity = this;

        final Checkout co = new Checkout();

        try {
            JSONObject options = new JSONObject();
            options.put("name", "Modern Swan Pvt Ltd");
            options.put("description", "Delivery Charges");
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAQwAAAC8CAMAAAC672BgAAAAkFBMVEX////NMwHLIADMKwDouq/JFADLIgD68u/vy8HYcVnosKfovLbWbVnov7borp/89vXXcV/67+n9+/jflITVYkfICADx0cfjppfy18/joZXWaE/biHvaemfXbFLXcFr14tvRTCjOOQDPQhrTWTjch3XbgmzntarPQRDagHHioZDRTzPSVj303tjVZUnWblvRTiw9OREAAAAEuklEQVR4nO3ca3eiMBAGYEwIXlbpQiu1rre1VrfWbf//v1urRLESNCwxE/o+39oD50zGAEmY4HkAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAGBQHMe2Q7BgwIIU2xJH/Xvbod3eU9DIx79hMoZclYxftkO7PX5GJmNkO7Sbi9vD4Xjc7U4mk587o6FMxsR2bARM05sIf7AdCQGRvE5mtiMhIJbJ+G07Egrk4ySwHQgF8zQZ4juOQb96Tq8TEdmOhICxTMbGdiQELNJksJbtSAiQsxXWsx0JAR2ZjKXtSAjwWfpo7diOhIAW+75z+DMDkSbjxXYkBEQyGWPbkRAQy2Q8246EAjlTm/d8A5ZT2+3TspJTNWZCcGe7fVqeVcuiVQgebTdPT9dkMlZN283T88dgMoJX263TdG8wGdy1RZJX1Xul/xc4N6oNzSWDu/Uo8Y4zteq51zG8xFgyGu6tJE6FoVQ42DEO79SqT4ZjY4xPkalcLGy3rITm/HLDSiXDvTvGdg6/NpMLFzuG5z0YGYJyt+bu0thEMribHcMbmUgGc/GO4R3fqVXJ2eXlRwMDDUb5juGPfiq9Vd8zSHeMe3Fe5ndW8FehgPAr/cjU9EOBdB2lwYlpLka4Y3hx2dcA5a4g3rbd4EJ3vR9l+JNS2RCUHyX/4W+JbNS22LjMGIQNbEdtSImeUduOsaxDx4h6aok8aJD+Q30DnZTIBbmKfF8UPC7TDVjxpOig8k9WQaxjRIVjLL7evfN7MjMQ40Pbrf9iWZyMfbgGpmefWHIhuFtL+gWzsoDvx8p/WNHcrewsjlzH8LzOe1fpJR0eNhfqY1LvW92GVjpqXnQdrS6n4Ngx1rbDNUy5FTaHqHfH0Foi5W+2gzUs0rhnEN+ZEN+pxJcO2OutatMxnn4HKqvdgMCfKQ9IPx9wfb8g3jFeC9Y++cf2gJaocOjF57bbW6joZ90lo9KCWNodozAZ4nPnUZXJ2GWXsLAvVNiujjlhygO0LyDm227uBXFTRR6x/ys6N9Uto167VgCrQ7O8qd4b/po6k5K6f1igp7X6U++OkWj1i8ac8h2jed8+mrTlbLLTvtKD3lowo7wrNmoEJ+tV6bb/SWFpQvkVLj6nXDL/8uWH3Y8Oe8Yqo0PbDS4y+/LL7itUzW0toXzH2E7RTuaewX50OGAsf3J6SF3xHFalT3xbXivMWMo6xGmYT37YbZb5X+d6IemOoUteP3Vfz72K/IjGfKqczBSxHX61DqNN9SRWrU97tU9bq1+y6Gv3JtvRMnGVuyRJWvqS/WmOlokrbJahX6JFUS907CMIl7XetsMPFrxoPh834+1JjA1r1S8W6QJfoFdyE6alLNy1vf9FRodlC6GzJPF4mOY4uv8qT6awReerO4PMlI+bi+62Ttb2Vtefl637cXKbYp6TjfCzq0/bZKe/Lm7mzfUr8wtrbIw4qRqrzVJwdrOeuP71YHZhxMUt7/kylSg6JUjZnsHrcpV4/vGpoFPmvDkmQ+uBTFu8TrsG16vTG8nrRBBf5NISzbYjSc7Fg95OqngoPk9js3pVtMVhd/a80G+TP/pYj/xarfcBAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAp/wArTlk7d2/EfQAAAABJRU5ErkJggg==");
            options.put("currency", "INR");

            String payment = editTextPayment.getText().toString();

            double total = Double.parseDouble(payment);
            total = total * 100;
            options.put("amount", total);
            JSONObject preFill = new JSONObject();
            preFill.put("email", "gitxxxxxxxxxxxx@gmail.com");
            preFill.put("contact", "978xxxxxxx8");
            options.put("prefill", preFill);
            co.open(activity, options);
        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }



    @Override
    public void onPaymentSuccess(String s, PaymentData paymentData) {

        Toast.makeText(this, "oid"+paymentData.getOrderId()+"pid"+paymentData.getPaymentId()+"user contact" +
                paymentData.getUserContact()+"user email"+paymentData.getUserEmail()  , Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onPaymentError(int i, String s, PaymentData paymentData) {

    }
}