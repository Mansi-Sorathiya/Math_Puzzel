package com.example.math_puzzel;

import static com.example.math_puzzel.MainActivity.editor;
import static com.example.math_puzzel.MainActivity.lastLevel;
import static com.example.math_puzzel.MainActivity.preferences;
import static com.example.math_puzzel.All_Data.imgArr;
import static com.example.math_puzzel.All_Data.ansArray;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class Continue_Activity extends AppCompatActivity implements View.OnClickListener {
    TextView anstxt, levelText;
    ImageView deletetxt, skip, hint;
    ImageView levelImg;
    String temp;
    Button submitbutton;
    TextView[] b = new TextView[10];

    int levelNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continue);

        anstxt = findViewById(R.id.anstxt);
        levelImg = findViewById(R.id.level_image);
        levelText = findViewById(R.id.levelText);

        if (getIntent().getExtras() != null) {
            levelNo = getIntent().getIntExtra("levelNo", 0);
        }

        levelImg.setImageResource(imgArr[levelNo]);

        levelText.setText("Level: " + (levelNo + 1));

        lastLevel = preferences.getInt("lastLevel", 0);

        deletetxt = findViewById(R.id.delete);
        deletetxt.setOnClickListener(this);

        skip = findViewById(R.id.skip);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Dialog dialog = new Dialog(Continue_Activity.this);
                dialog.setContentView(R.layout.skip_dialog);
                TextView msg = dialog.findViewById(R.id.textSkip);
                Button okBtn = dialog.findViewById(R.id.btnOk);
                Button cancelBtn = dialog.findViewById(R.id.btnCancel);

                //okBtn.setEnabled(false);

                CountDownTimer timer = new CountDownTimer(2000, 1000) {
                    @Override
                    public void onTick(long l) {
                        msg.setText("Time: " + (l / 1000) + " remains to unlock the "+"\n"+ "next level");

                    }
                    @Override
                    public void onFinish() {

                        okBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                Intent intent = new Intent(Continue_Activity.this, Continue_Activity.class);
                                if (levelNo > lastLevel) // 5>=4
                                {
                                    intent.putExtra("levelNo", (levelNo + 1));
                                    editor.putInt("lastLevel", levelNo);
                                    editor.putString("levelStatus" + levelNo, "skip");
                                    editor.commit();
                                } else {
                                    intent.putExtra("levelNo", (levelNo + 1));
                                }

                                startActivity(intent);
                                finish();

                            }
                        });

                    }
                }.start();
                dialog.show();

//                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(Continew_Second_Page.this);
//                builder.setTitle("Skip");
//
//
//                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {

//                    }
//
//                });
//
//                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        builder.setCancelable(true);
//                    }
//                });
//                builder.show();

            }
        });


        hint=findViewById(R.id.hint);
        hint.setOnClickListener(this);

        submitbutton=findViewById(R.id.submitbutton);
        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(anstxt.getText().toString().equals(ansArray[levelNo]))
                {
                    Intent intent=new Intent(Continue_Activity.this,Win_Activity.class);

                    if (levelNo>lastLevel) {

                        intent.putExtra("levelNo", levelNo);
                        editor.putInt("lastLevel", levelNo);
                        editor.putString("levelStatus" + levelNo, "win");
                        editor.commit();

                    }else {
                        intent.putExtra("levelNo", levelNo);
                        editor.putString("levelStatus" + levelNo, "win");
                        editor.commit();

                    }
                    startActivity(intent);
                    finish();

                }
                else {
                    Toast.makeText(Continue_Activity.this, " Wrong !!!", Toast.LENGTH_LONG).show();
                    anstxt.setText("");
                }
            }
        });


        for (int i=0;i<b.length;i++)
        {
            int id=getResources().getIdentifier("b"+i,"id",getPackageName());
            b[i] =findViewById(id);
            b[i].setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View view)
    {
        try {

            if (view.getId() == b[1].getId()) {
                temp = anstxt.getText().toString();
                anstxt.setText("" + temp + "1");
            }
            if (view.getId() == b[2].getId()) {
                temp = anstxt.getText().toString();
                anstxt.setText("" + temp + "2");
            }
            if (view.getId() == b[3].getId()) {
                temp = anstxt.getText().toString();
                anstxt.setText("" + temp + "3");
            }
            if (view.getId() == b[4].getId()) {
                temp = anstxt.getText().toString();
                anstxt.setText("" + temp + "4");
            }
            if (view.getId() == b[5].getId()) {
                temp = anstxt.getText().toString();
                anstxt.setText("" + temp + "5");
            }
            if (view.getId() == b[6].getId()) {
                temp = anstxt.getText().toString();
                anstxt.setText("" + temp + "6");
            }
            if (view.getId() == b[7].getId()) {
                temp = anstxt.getText().toString();
                anstxt.setText("" + temp + "7");
            }
            if (view.getId() == b[8].getId()) {
                temp = anstxt.getText().toString();
                anstxt.setText("" + temp + "8");
            }
            if (view.getId() == b[9].getId()) {
                temp = anstxt.getText().toString();
                anstxt.setText("" + temp + "9");
            }
            if (view.getId() == b[0].getId()) {
                temp = anstxt.getText().toString();
                anstxt.setText("" + temp + "0");
            }
            if (view.getId() == deletetxt.getId()) {

                temp= temp.substring(0,anstxt.getText().toString().length()-1);
                anstxt.setText(""+temp);
            }
        }catch (Exception e)
        {
            Toast.makeText(this, "Wrong!!!", Toast.LENGTH_SHORT).show();
        }
    }

}