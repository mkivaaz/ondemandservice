package kivaaz.com.on_demand_service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import kivaaz.com.ondemandserviceslibrary.Barcode.BarcodeScannerNGenerator;
import kivaaz.com.ondemandserviceslibrary.FirebaseChat.FirebaseConstants;

public class BarcodeScanner extends AppCompatActivity {
    TextView qrData;
    SurfaceView cameraView;
    CameraSource cameraSource;
    BarcodeDetector detector;

    Button generateBtn, scanBtn;

    final int REQUEST_CAMERA_PERMISSION_ID = 1001;
    static String qrCodeData = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_scanner);

        qrData = findViewById(R.id.qrDataTV);
        cameraView = findViewById(R.id.cameraView);
        generateBtn = findViewById(R.id.generateQRBtn);

        BarcodeScannerNGenerator barcodeScanne = new BarcodeScannerNGenerator(cameraView,cameraSource,this);

        barcodeScanne.setDetector();

    }

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            qrData.setText(intent.getExtras().getString(FirebaseConstants.BARCODE_DATA));
        }
    };

    @Override
    protected void onPause() {
        super.onPause();

        LocalBroadcastManager.getInstance(getBaseContext()).unregisterReceiver(receiver);
    }

    @Override
    protected void onResume() {
        super.onResume();

        LocalBroadcastManager.getInstance(getBaseContext()).registerReceiver(receiver, new IntentFilter(FirebaseConstants.BARCODE_INTENT));
    }

}
