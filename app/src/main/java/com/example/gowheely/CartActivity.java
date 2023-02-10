package com.example.gowheely;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gowheely.Adapter.CartAdapter;
import com.example.gowheely.Adapter.OrderAdapter;
import com.example.gowheely.Model.CartModel;
import com.example.gowheely.Model.OrderModel;
import com.example.gowheely.Model.ProductModel_request;
import com.example.gowheely.Model.ProductModel_response;
import com.example.gowheely.scanner.BarCodeScannerActivity;
import com.example.gowheely.service.API_client;
import com.example.gowheely.service.API_interface;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity {

    private String uuid;
    private BluetoothSocket socket;
    private BluetoothDevice bt_device = null;
    final byte delimiter = 35;
    int readBufferPosition = 0;

    private static final int REQUEST_ENABLE_BT = 0;
    private static final int REQUEST_DISCOVERABLE_BT = 0;

    private BluetoothAdapter bluetoothAdapter;

    private Button turnOn;
    private Button turnOff;
    private Button send, find;
    private TextView status;

    private Set<BluetoothDevice> pairedDevices;
    private List<BluetoothDevice> PairedDevices;
    private ListView listView;
    private ArrayAdapter<String> BTArrayAdapter;
    final Handler handler = new Handler();

    @SuppressLint("MissingPermission")

    Button checkOut, cartDetails;
    API_interface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        checkOut = findViewById(R.id.next_page);

        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),CheckoutActivity.class));
            }
        });

                    final InputStream inputStream;
        try {
            inputStream = socket.getInputStream();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        int bytesAvailable;
        try {
            bytesAvailable = inputStream.available();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        if (bytesAvailable > 0) {

                        byte[] packetBytes = new byte[bytesAvailable];
                        Log.e("Bytes received from", "Raspberry Pi");
                        byte[] readBuffer = new byte[1024];
            try {
                inputStream.read(packetBytes);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            boolean workDone = false;
            for (int i = 0; i < bytesAvailable; i++) {
                            byte b = packetBytes[i];
                            if (b == delimiter) {
                                byte[] encodedBytes = new byte[readBufferPosition];
                                System.arraycopy(readBuffer, 0, encodedBytes, 0, encodedBytes.length);
                                final String data;
                                try {
                                    data = new String(encodedBytes, "US-ASCII");
                                } catch (UnsupportedEncodingException ex) {
                                    throw new RuntimeException(ex);
                                }
                                readBufferPosition = 0;

                                handler.post(new Runnable() {
                                    public void run() {
                                        status.setText(data);
                                    }
                                });

                                workDone = true;
                                break;
                            } else {
                                readBuffer[readBufferPosition++] = b;
                            }
                        }

                        if (workDone == true) {
                            try {
                                socket.close();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            break;
                        }

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }
    }


    public void sendMessage(String send_message) {

        UUID uuid = UUID.fromString(send_message);

        try {

            socket = bt_device.createRfcommSocketToServiceRecord(uuid);
            if (!socket.isConnected()) {
                socket.connect();
            }

            String message = send_message;

            OutputStream btOutputStream = socket.getOutputStream();
            btOutputStream.write(message.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//        call productAPI
        apiInterface = API_client.getClient().create(API_interface.class);
        /**         GET List Resources         **/
        ProductModel_request request = new ProductModel_request();
        request.setProductName("Soyabean Oil");
        request.setCategoryId(1);
        request.setProductCode("110635");
        request.setWeight(500.0);
        request.setCost(200.0);
        request.setQuantity(10);


        Call<ProductModel_response> call = apiInterface.callProducts(request);
        call.enqueue(new Callback<ProductModel_response>() {
            @Override
            public void onResponse(Call<ProductModel_response> call, Response<ProductModel_response> response) {
                Log.d("TAG", response.code() + "");
                String displayResponse = "";
                ProductModel_response resource = response.body();

            }
            // responseText.setText(displayResponse);            }            @Override            public void onFailure(Call<CategoryModel> call, Throwable t) {                call.cancel();            }        });    }}

            @Override
            public void onFailure(Call<ProductModel_response> call, Throwable t) {

            }

        });


}
