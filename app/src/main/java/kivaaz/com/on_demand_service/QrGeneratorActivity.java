package kivaaz.com.on_demand_service;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import kivaaz.com.ondemandserviceslibrary.Barcode.BarcodeScannerNGenerator;

public class QrGeneratorActivity extends AppCompatActivity {
    ImageView qrImg;
    EditText qrDataET;
    Button generateBtn,storeBtn;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_generator);
        qrImg = findViewById(R.id.qrImg);
        qrDataET = findViewById(R.id.qrDataET);
        generateBtn = findViewById(R.id.generateBtn);
        storeBtn = findViewById(R.id.storeBtn);
        final BarcodeScannerNGenerator generator = new BarcodeScannerNGenerator(this);

        generateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String qrData = qrDataET.getText().toString().trim();
                if(!qrData.isEmpty()){
                    bitmap = generator.generateQRCode(qrData);
                    if(bitmap != null){
                        qrImg.setImageBitmap(bitmap);
                        qrImg.setVisibility(View.VISIBLE);
                    }else{
                        Toast.makeText(getBaseContext(),"No Image Generated",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getBaseContext(),"Please Enter Some Data",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
