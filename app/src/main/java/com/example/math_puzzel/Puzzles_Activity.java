package com.example.math_puzzel;

import static com.example.math_puzzel.MainActivity.editor;
import static com.example.math_puzzel.All_Data.pagecount;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

public class Puzzles_Activity extends AppCompatActivity {

    GridView gridview;
    ImageView next,back;
    Puzzle_Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzles);
        gridview=findViewById(R.id.gridview);
        next=findViewById(R.id.next);
        back=findViewById(R.id.back);

        if(pagecount==0)
        {
            adapter = new Puzzle_Adapter(Puzzles_Activity.this,pagecount);
            gridview.setAdapter(adapter);
        }

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pagecount++;
                if(pagecount==1)
                {
                    adapter = new Puzzle_Adapter(Puzzles_Activity.this, pagecount);
                    gridview.setAdapter(adapter);
                    back.setVisibility(View.VISIBLE);

                }
                if(pagecount==2)
                {
                    adapter = new Puzzle_Adapter(Puzzles_Activity.this, pagecount);
                    gridview.setAdapter(adapter);

                }
                if(pagecount==3)
                {
                    adapter = new Puzzle_Adapter(Puzzles_Activity.this,pagecount);
                    gridview.setAdapter(adapter);
                    next.setVisibility(View.INVISIBLE);
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pagecount--;
                if(pagecount==0){
                    adapter = new Puzzle_Adapter(Puzzles_Activity.this,pagecount);
                    gridview.setAdapter(adapter);
                    back.setVisibility(View.INVISIBLE);
                    next.setVisibility(View.VISIBLE);
                }
                if(pagecount==1){
                    adapter = new Puzzle_Adapter(Puzzles_Activity.this,pagecount);
                    gridview.setAdapter(adapter);
                    next.setVisibility(View.VISIBLE);

                }
                if(pagecount==2){
                    adapter = new Puzzle_Adapter(Puzzles_Activity.this,pagecount);
                    gridview.setAdapter(adapter);
                    next.setVisibility(View.VISIBLE);

                }
                if(pagecount==3){
                    adapter = new Puzzle_Adapter(Puzzles_Activity.this,pagecount);
                    gridview.setAdapter(adapter);
                    next.setVisibility(View.VISIBLE);

                }
            }
        });
    }
}