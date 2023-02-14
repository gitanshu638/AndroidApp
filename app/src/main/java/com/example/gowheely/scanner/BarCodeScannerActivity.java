package com.example.gowheely.scanner;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.gowheely.CartActivity;
import com.example.gowheely.DeviceControlActivity;
import com.example.gowheely.R;
import com.google.zxing.client.android.Intents;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class BarCodeScannerActivity extends AppCompatActivity {
    Button cartDetails;


    private final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(),
            result -> {
                if (result.getContents() == null) {
                    Intent originalIntent = result.getOriginalIntent();
                    if (originalIntent == null) {
                        Log.d("Barcode", "Cancelled scan");
                        Toast.makeText(BarCodeScannerActivity.this, "Cancelled", Toast.LENGTH_LONG).show();
                    } else if (originalIntent.hasExtra(Intents.Scan.MISSING_CAMERA_PERMISSION)) {
                        Log.d("barcode", "Cancelled scan due to missing camera permission");
                        Toast.makeText(BarCodeScannerActivity.this, "Cancelled due to missing camera permission", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Log.d("barcode", "Scanned");
                    Intent intent = new Intent(this, CartActivity.class);
                    intent.putExtra("UUID", result.getContents());
                    startActivity(intent);
                    finish();
                    // Toast.makeText(BarCodeScannerActivity.this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                }
            });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_code_scanner);

    }


    public void scanBarcode(View view) {
        barcodeLauncher.launch(new ScanOptions());
    }


}