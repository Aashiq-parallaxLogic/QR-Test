package com.parallaxlogic.testjava;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.ByteArrayOutputStream;
import java.util.Base64;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;

public class MainActivity extends AppCompatActivity {

    private Bitmap bitmap;
    Button btn;
    ImageView iv;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        iv = findViewById(R.id.iv);



        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.i100);
        Bitmap bmpCompressed = Bitmap.createScaledBitmap(bitmap, 100, 100, true);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        // CompressFormat set up to JPG, you can change to PNG or whatever you want;
        bmpCompressed.compress(Bitmap.CompressFormat.JPEG, 70, bos);
        byte[] data = bos.toByteArray();



        String encoded = Base64.getEncoder().encodeToString(data);

        System.out.println("Data :: ");
        System.out.println(data);
        System.out.println(data.length);


        System.out.println("encoded :: " + encoded);
        System.out.println("encoded Length:: " + encoded.length());

        final int mid = encoded.length() / 2; //get the middle of the String
        String[] parts = {encoded.substring(0, mid),encoded.substring(mid)};
        System.out.println(parts[0].length()); //first part
        System.out.println(parts[1].length());



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                QRCodeWriter writer = new QRCodeWriter();
                try {
                    BitMatrix bitMatrix = writer.encode(encoded, BarcodeFormat.QR_CODE, 512, 512);
                    int width = bitMatrix.getWidth();
                    int height = bitMatrix.getHeight();
                    Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
                    for (int x = 0; x < width; x++) {
                        for (int y = 0; y < height; y++) {
                            bmp.setPixel(x, y, bitMatrix.get(x, y) ? BLACK : WHITE);
                        }
                    }
                    iv.setImageBitmap(bmp);
                    Toast.makeText(getApplicationContext(),"Convertet to QR code",Toast.LENGTH_SHORT).show();

                } catch (WriterException e) {
                    e.printStackTrace();
                }


            }
        });




    }











}