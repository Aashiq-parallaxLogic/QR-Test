package com.parallaxlogic.testjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.ByteArrayOutputStream;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;

public class MainActivity extends AppCompatActivity {

    private Bitmap bitmap;
Button btn,btn2,btn3;
ImageView iv,iv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        iv = findViewById(R.id.iv);
        iv2 = findViewById(R.id.iv2);



        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img);
        // you can change the format of you image compressed for what do you want;
        // now it is set up to 640 x 480;
        Bitmap bmpCompressed = Bitmap.createScaledBitmap(bitmap, 640, 480, true);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        // CompressFormat set up to JPG, you can change to PNG or whatever you want;
        bmpCompressed.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        byte[] data = bos.toByteArray();


        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SecondActivity.class));
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                QRCodeWriter writer = new QRCodeWriter();
                try {
                    BitMatrix bitMatrix = writer.encode(String.valueOf(data), BarcodeFormat.QR_CODE, 512, 512);
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


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                iv2.setImageBitmap(bitmap);
                Toast.makeText(getApplicationContext(),"Decoded",Toast.LENGTH_SHORT).show();


            }
        });


    }




}