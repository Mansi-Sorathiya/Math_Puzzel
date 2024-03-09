package com.example.math_puzzel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView continuetxt,puzzlestxt;
    ImageView shareimg;

    public static int lastLevel;
    public static SharedPreferences preferences;
    public static SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        continuetxt=findViewById(R.id.Continuetxt);
        puzzlestxt=findViewById(R.id.Puzzlestxt);

        shareimg=findViewById(R.id.shareimg);

        preferences = getSharedPreferences("myPref",MODE_PRIVATE);
        editor = preferences.edit();

        lastLevel= preferences.getInt("lastLevel",-1);

        continuetxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Continue_Activity.class);
                lastLevel++;
                intent.putExtra("levelNo",lastLevel);
                startActivity(intent);
            }
        });

        puzzlestxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Puzzles_Activity.class);
                startActivity(intent);

            }
        });


        shareimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                String shareMessage= "\nTest your logical reasoning. Download and Enjoy!!!\n\n";
                shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=com.applabs.puzzle" + BuildConfig.APPLICATION_ID +"\n\n";
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                startActivity(Intent.createChooser(shareIntent, "choose one"));
            }
        });
    }
}