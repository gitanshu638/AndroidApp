package com.example.gowheely;

import static android.Manifest.permission.BLUETOOTH_CONNECT;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothSocket;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.ParcelUuid;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.gowheely.Model.ProductModel_request;
import com.example.gowheely.Model.ProductModel_response;
import com.example.gowheely.bleservice.BluetoothLEService;
import com.example.gowheely.bleservice.BluetoothUtils;
import com.example.gowheely.bleservice.Constants;
import com.example.gowheely.bleservice.SampleGattAttributes;
import com.example.gowheely.service.API_client;
import com.example.gowheely.service.API_interface;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity {

    private String uuid;
    ProgressBar progressBar;
    private BluetoothSocket socket;
    private BluetoothDevice bt_device = null;
    final byte delimiter = 35;
    int readBufferPosition = 0;
    private BluetoothDevice bluetoothDevice;
    private boolean mScanning = false;
    private BluetoothLeScanner bluetoothLeScanner;
    private BluetoothAdapter mBluetoothAdapter;

    private static final int REQUEST_ENABLE_BT = 0;
    private static final int REQUEST_DISCOVERABLE_BT = 0;
    private BluetoothGattCharacteristic mNotifyCharacteristic;
    private boolean mConnected = false;

    private BluetoothLEService mBluetoothLEService;

    private BluetoothAdapter bluetoothAdapter;

    private Button turnOn;
    private Button turnOff, connect, connect_ser;
    private Button send, find;
    private TextView statusTv;

    private Set<BluetoothDevice> pairedDevices;
    private List<BluetoothDevice> PairedDevices;
    private ListView listView;
    private ArrayAdapter<String> BTArrayAdapter;
    final Handler handler = new Handler();

    @SuppressLint("MissingPermission")

    Button checkOut;

    BroadcastReceiver bReceiver, getmGattUpdateReceiver;


    @SuppressLint({"MissingPermission", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        checkOut = findViewById(R.id.next_page);
        turnOn = (Button) findViewById(R.id.turn_on);
        turnOff = (Button) findViewById(R.id.turn_off);
        send = (Button) findViewById(R.id.send_data);
        find = (Button) findViewById(R.id.Find);
        statusTv = (TextView) findViewById(R.id.status);
        listView = (ListView) findViewById(R.id.listView1);
        //   connect = (Button) findViewById(R.id.connect);
        //  connect_ser = (Button) findViewById(R.id.connect_service);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        //get uuid from scan
        uuid = getIntent().getStringExtra("UUID");
        statusTv.setText("List of Devices");

        bluetoothAdapter = BluetoothAdapter.
                getDefaultAdapter();
        if (bluetoothAdapter == null) {
            //  out.append("device not supported");
        }
        mBluetoothAdapter = BluetoothUtils.getBluetoothAdapter(CartActivity.this);

        pairedDevices = bluetoothAdapter.getBondedDevices();
        PairedDevices = new ArrayList<>(pairedDevices);

        for (int i = 0; i < PairedDevices.size(); i++) {

            String name = PairedDevices.get(i).getName();
            //The name in quotes must match your device
            if (name.equals("Cart_Info")) {
                bt_device = PairedDevices.get(i);
                break;
            }
        }

        turnOn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            public void onClick(View v) {
                if (!bluetoothAdapter.isEnabled()) {
                    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
                }
            }
        });
        find.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View arg0) {

                startScanning(true);
            }
        });
        turnOff.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View arg0) {
                bluetoothAdapter.disable();
                Context context = getApplicationContext();
                CharSequence text = "TURNING_OFF BLUETOOTH";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // list(view);
                String selectedItem = (String) parent.getItemAtPosition(position);
                Toast.makeText(CartActivity.this, selectedItem, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CartActivity.this, DeviceControlActivity.class);
                intent.putExtra(DeviceControlActivity.EXTRAS_DEVICE_NAME, selectedItem);
                intent.putExtra(DeviceControlActivity.EXTRAS_DEVICE_ADDRESS, selectedItem);
                startActivity(intent);
            }

        });


        // send data
        send.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                (new Thread(new workerThread("send_data"))).start();

            }
        });

        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CheckoutActivity.class));
            }
        });


//        connect.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (bluetoothDevice != null) {
//                    progressBar.setVisibility(View.VISIBLE);
//                    Intent gattServiceIntent = new Intent(CartActivity.this, BluetoothLEService.class);
//                    bindService(gattServiceIntent, mServiceConnection, BIND_AUTO_CREATE);
//                }
//            }
//        });
//
//        connect_ser.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mNotifyCharacteristic != null) {
//                    final int charaProp = mNotifyCharacteristic.getProperties();
//                    if ((charaProp | BluetoothGattCharacteristic.PROPERTY_READ) > 0) {
//                        mBluetoothLEService.readCharacteristic(mNotifyCharacteristic);
//                    }
//                    if ((charaProp | BluetoothGattCharacteristic.PROPERTY_NOTIFY) > 0) {
//                        mBluetoothLEService.setCharacteristicNotification(mNotifyCharacteristic, true);
//                    }
//                }
//            }
//        });


        // create the arrayAdapter that contains the BTDevices, and set it to the ListView
        BTArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(BTArrayAdapter);

        bReceiver = new BroadcastReceiver() {
            @SuppressLint("MissingPermission")
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                // When discovery finds a device
                if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                    // Get the BluetoothDevice object from the Intent
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    // add the name and the MAC address of the object to the arrayAdapter
                    BTArrayAdapter.add(device.getName() + "\n" + device.getAddress());
                    BTArrayAdapter.notifyDataSetChanged();
                }
            }
        };


    }


    @SuppressLint("MissingPermission")
    public void list(View view) {
        // get paired devices
        pairedDevices = bluetoothAdapter.getBondedDevices();

        // put it's one to the adapter
        for (BluetoothDevice device : pairedDevices)
            BTArrayAdapter.add(device.getName() + "\n" + device.getAddress());

        Toast.makeText(getApplicationContext(), "Show Paired Devices",
                Toast.LENGTH_SHORT).show();

    }


    @Override
    protected void onResume() {
        super.onResume();

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
        } else {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    Constants.REQUEST_LOCATION_ENABLE_CODE);
        }

        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Toast.makeText(this, "Your devices that don't support BLE", Toast.LENGTH_LONG).show();
            finish();
        }
        if (!mBluetoothAdapter.enable()) {
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, Constants.REQUEST_BLUETOOTH_ENABLE_CODE);
        }

        if (ContextCompat.checkSelfPermission(CartActivity.this, BLUETOOTH_CONNECT) == PackageManager.PERMISSION_DENIED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                ActivityCompat.requestPermissions(CartActivity.this, new String[]{BLUETOOTH_CONNECT}, 2);
                return;
            }
        }
        registerReceiver(mGattUpdateReceiver, GattUpdateIntentFilter());
        if (mBluetoothLEService != null) {
            final boolean result = mBluetoothLEService.connect(bluetoothDevice.getAddress());
            Log.d("BluetoothGatt", "Connect request result=" + result);
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mGattUpdateReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mServiceConnection);
        mBluetoothLEService = null;
        unregisterReceiver(bReceiver);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.REQUEST_BLUETOOTH_ENABLE_CODE && resultCode == RESULT_CANCELED) {
            finish();
        }
    }

    @SuppressLint("MissingPermission")
    private void startScanning(final boolean enable) {
        bluetoothLeScanner = mBluetoothAdapter.getBluetoothLeScanner();
        Handler mHandler = new Handler();
        if (enable) {
            List<ScanFilter> scanFilters = new ArrayList<>();
            final ScanSettings settings = new ScanSettings.Builder().build();
            //  ScanFilter scanFilter = new ScanFilter.Builder().setServiceUuid(ParcelUuid.fromString(SampleGattAttributes.UUID_CART_INFO)).build();
            //  scanFilters.add(scanFilter);
            mHandler.postDelayed(new Runnable() {
                @SuppressLint("MissingPermission")
                @Override
                public void run() {
                    mScanning = false;
                    progressBar.setVisibility(View.INVISIBLE);
                    bluetoothLeScanner.stopScan(scanCallback);
                }
            }, Constants.SCAN_PERIOD);
            mScanning = true;
            //  bluetoothLeScanner.startScan(scanFilters, settings, scanCallback);
            bluetoothLeScanner.startScan(scanCallback);
        } else {
            mScanning = false;
            Toast.makeText(this, "Not scanned any device", Toast.LENGTH_LONG).show();
            bluetoothLeScanner.stopScan(scanCallback);
        }
    }


    private void updateConnectionState(final String status) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                statusTv.setText(status);
                Toast.makeText(CartActivity.this, "connected", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void displayData(String data) {
        if (data != null) {
            // batteryLevel.setText(data);
            Intent intent = new Intent(this, CheckoutActivity.class);
            intent.putExtra("data_sent", data);
            startActivity(intent);
        }
    }

    private void displayGattServices(List<BluetoothGattService> gattServices) {
        if (gattServices == null)
            return;
        String uuid = null;
        String serviceString = "unknown service";
        String charaString = "unknown characteristic";

        for (BluetoothGattService gattService : gattServices) {

            uuid = gattService.getUuid().toString();

            serviceString = SampleGattAttributes.lookup(uuid, serviceString);

            if (serviceString != null) {
                List<BluetoothGattCharacteristic> gattCharacteristics =
                        gattService.getCharacteristics();

                for (BluetoothGattCharacteristic gattCharacteristic : gattCharacteristics) {
                    HashMap<String, String> currentCharaData = new HashMap<String, String>();
                    uuid = gattCharacteristic.getUuid().toString();
                    charaString = SampleGattAttributes.lookup(uuid, charaString);
                    if (charaString != null) {
                        statusTv.setText(charaString);
                    }
                    mNotifyCharacteristic = gattCharacteristic;
                    return;
                }
            }
        }
    }

    final class workerThread implements Runnable {

        private String message;

        public workerThread(String msg) {
            message = msg;
        }

        public void run() {

            sendMessage(message);
            while (!Thread.currentThread().isInterrupted()) {

                int bytesAvailable;
                boolean workDone = false;

                send.setOnClickListener(view -> CartActivity.super.onBackPressed());
                try {

                    final InputStream inputStream;
                    inputStream = socket.getInputStream();
                    bytesAvailable = inputStream.available();
                    if (bytesAvailable > 0) {

                        byte[] packetBytes = new byte[bytesAvailable];
                        Log.e("Bytes received from", "Cart_Info - Aka Raspberry Pi");
                        byte[] readBuffer = new byte[1024];
                        inputStream.read(packetBytes);

                        for (int i = 0; i < bytesAvailable; i++) {
                            byte b = packetBytes[i];
                            if (b == delimiter) {
                                byte[] encodedBytes = new byte[readBufferPosition];
                                System.arraycopy(readBuffer, 0, encodedBytes, 0, encodedBytes.length);
                                final String data = new String(encodedBytes, StandardCharsets.US_ASCII);
                                readBufferPosition = 0;

                                handler.post(new Runnable() {
                                    public void run() {
                                        statusTv.setText("Received from pi" + data);
                                        Intent intent = new Intent(CartActivity.this, CheckoutActivity.class);
                                        intent.putExtra("data_sent", data);
                                    }
                                });

                                workDone = true;
                                break;
                            } else {
                                readBuffer[readBufferPosition++] = b;
                            }
                        }

                        if (workDone == true) {
                            socket.close();
                            break;
                        }

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }

    }

    private final ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            mBluetoothLEService = ((BluetoothLEService.LocalBinder) service).getService();
            if (!mBluetoothLEService.initialize()) {
                Log.e("BluetoothGatt", "Unable to initialize Bluetooth");
                Toast.makeText(CartActivity.this, "Unable to initialize BLE", Toast.LENGTH_LONG).show();
                finish();
            }
            mBluetoothLEService.connect(bluetoothDevice.getAddress());
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBluetoothLEService = null;
        }
    };

    // Handles various events fired by the Service.
    // ACTION_GATT_CONNECTED: connected to a GATT server.
    // ACTION_GATT_DISCONNECTED: disconnected from a GATT server.
    // ACTION_GATT_SERVICES_DISCOVERED: discovered GATT services.
    // ACTION_DATA_AVAILABLE: received data from the device.  This can be a result of read
    //                        or notification operations.
    private final BroadcastReceiver mGattUpdateReceiver = new BroadcastReceiver() {
        @SuppressLint("MissingPermission")
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (BluetoothLEService.ACTION_GATT_CONNECTED.equals(action)) {
                mConnected = true;
                updateConnectionState("connected");
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // add the name and the MAC address of the object to the arrayAdapter
                BTArrayAdapter.add(device.getName() + "\n" + device.getAddress());
                BTArrayAdapter.notifyDataSetChanged();
                // invalidateOptionsMenu();
            } else if (BluetoothLEService.ACTION_GATT_DISCONNECTED.equals(action)) {
                mConnected = false;
                updateConnectionState("disconnected");
                //clearUI();
            } else if (BluetoothLEService.ACTION_GATT_SERVICES_DISCOVERED.equals(action)) {
                displayGattServices(mBluetoothLEService.getSupportedGattServices());
            } else if (BluetoothLEService.ACTION_DATA_AVAILABLE.equals(action)) {
                displayData(intent.getStringExtra(BluetoothLEService.EXTRA_DATA));
            } else if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Get the BluetoothDevice object from the Intent
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // add the name and the MAC address of the object to the arrayAdapter
                BTArrayAdapter.add(device.getName() + "\n" + device.getAddress());
                BTArrayAdapter.notifyDataSetChanged();
            }

        }
    };

    private ScanCallback scanCallback = new ScanCallback() {
        @SuppressLint("MissingPermission")
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            super.onScanResult(callbackType, result);
            bluetoothDevice = result.getDevice();
            // add the name and the MAC address of the object to the arrayAdapter
            BTArrayAdapter.add(bluetoothDevice.getName() + "\n" + bluetoothDevice.getAddress());
            BTArrayAdapter.notifyDataSetChanged();
            // Toast.makeText(CartActivity.this, "device added in list", Toast.LENGTH_LONG).show();
            progressBar.setVisibility(View.INVISIBLE);
        }

        @Override
        public void onBatchScanResults(List<ScanResult> results) {
            super.onBatchScanResults(results);
        }

        @Override
        public void onScanFailed(int errorCode) {
            super.onScanFailed(errorCode);
            Log.d("BluetoothGatt", "Scanning Failed " + errorCode);
            Toast.makeText(CartActivity.this, "Scan failed", Toast.LENGTH_LONG).show();
            progressBar.setVisibility(View.INVISIBLE);
        }
    };

    private static IntentFilter GattUpdateIntentFilter() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothLEService.ACTION_GATT_CONNECTED);
        intentFilter.addAction(BluetoothLEService.ACTION_GATT_DISCONNECTED);
        intentFilter.addAction(BluetoothLEService.ACTION_GATT_SERVICES_DISCOVERED);
        intentFilter.addAction(BluetoothLEService.ACTION_DATA_AVAILABLE);
        return intentFilter;
    }

    @SuppressLint("MissingPermission")
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


}

