package kivaaz.com.ondemandserviceslibrary.Barcode;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Vibrator;
import android.support.v4.content.LocalBroadcastManager;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.IOException;

import kivaaz.com.ondemandserviceslibrary.FirebaseChat.FirebaseConstants;

/**
 * Created by Muguntan on 2/6/2018.
 */

public class BarcodeScannerNGenerator {
    SurfaceView cameraView;
    CameraSource cameraSource;
    BarcodeDetector detector;
    Context context;
    static String qrCodeData = "";
    public BarcodeScannerNGenerator(SurfaceView cameraView, CameraSource cameraSource, Context context) {
        this.cameraView = cameraView;
        this.cameraSource = cameraSource;
        this.context = context;
    }

    public BarcodeScannerNGenerator(Context context) {
        this.context = context;
    }

    public void setDetector(){

        detector = new BarcodeDetector.Builder(context)
                .setBarcodeFormats(Barcode.ALL_FORMATS)
                .build();

        cameraSource = new CameraSource.Builder(context,detector)
                .setRequestedPreviewSize(640,480)
                .build();

        cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @SuppressLint("MissingPermission")
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {

                try {
                    cameraSource.start(cameraView.getHolder());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                cameraSource.stop();
            }
        });
        detector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> qrCodes = detections.getDetectedItems();

                if(qrCodes.size() != 0){
                    Vibrator vibrator = (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);
                    if(!qrCodes.valueAt(0).displayValue.equals(qrCodeData)){
                        vibrator.vibrate(1000);
                        qrCodeData = qrCodes.valueAt(0).displayValue;
                        Intent intent = new Intent(FirebaseConstants.BARCODE_SCANNER_INTENT);
                        intent.putExtra(FirebaseConstants.BARCODE_DATA, qrCodeData);
                        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                    }
                }
            }
        });
    }



    public Bitmap generateQRCode(String qrData){
        MultiFormatWriter writer = new MultiFormatWriter();
        try {
            BitMatrix matrix = writer.encode(qrData, BarcodeFormat.QR_CODE,1000,1000);
            BarcodeEncoder encoder = new BarcodeEncoder();
            Bitmap bitmap = encoder.createBitmap(matrix);
            return bitmap;
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }
}
