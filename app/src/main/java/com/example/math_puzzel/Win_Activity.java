package com.example.math_puzzel;

import static com.example.math_puzzel.All_Data.shareimage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

public class Win_Activity extends AppCompatActivity {

    TextView textwin;

    ImageView shareimg;
    private int levelNo;
    Button btnwincontinue,btnwinmainmenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        textwin=findViewById(R.id.textwin);
        btnwincontinue=findViewById(R.id.btnwincontinue);
        btnwinmainmenu=findViewById(R.id.btnwinmainmenu);
        shareimg=findViewById(R.id.shareimg);

        levelNo = getIntent().getIntExtra("levelNo",levelNo);

//        int levelNo=getIntent().getIntExtra("levelNo",0);
        textwin.setText("Puzzle "+(levelNo+1)+" Completed");

        btnwincontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Win_Activity.this,Continue_Activity.class);
                intent.putExtra("levelNo",levelNo+1);
                startActivity(intent);
                finish();
            }
        });

        btnwinmainmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Win_Activity.this,MainActivity.class);
                intent.putExtra("levelNo",levelNo);
                startActivity(intent);
                finish();
            }
        });

        shareimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bitmap b = BitmapFactory.decodeResource(getResources(), shareimage[levelNo]);
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("image/jpeg");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                b.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                String path = MediaStore.Images.Media.insertImage(getContentResolver(), b, "Title", null);
                Uri imageUri =  Uri.parse(path);
                share.putExtra(Intent.EXTRA_STREAM, imageUri);
                startActivity(Intent.createChooser(share, "Select"));

            }
        });

    }
}