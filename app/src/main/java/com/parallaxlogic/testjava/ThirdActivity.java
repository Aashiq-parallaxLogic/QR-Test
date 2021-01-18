package com.parallaxlogic.testjava;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class ThirdActivity extends AppCompatActivity {


    ImageView image;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        String value = getIntent().getStringExtra("data");

        String inputString = "Hello World!";
        Charset charset = StandardCharsets.UTF_16;

        byte[] data = inputString.getBytes(charset);

        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
        image.setImageBitmap(bitmap);
        Toast.makeText(getApplicationContext(),"Decoded",Toast.LENGTH_SHORT).show();



    }
}