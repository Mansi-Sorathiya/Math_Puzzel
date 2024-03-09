package com.example.math_puzzel;

import static com.example.math_puzzel.MainActivity.lastLevel;
import static com.example.math_puzzel.MainActivity.preferences;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Puzzle_Adapter extends BaseAdapter {

    Puzzles_Activity puzzles_activity;
    int pagecount;
    private int levelno;

    public Puzzle_Adapter(Puzzles_Activity puzzles_activity, int pagecount) {
        this.puzzles_activity = puzzles_activity;
        this.pagecount = pagecount;
    }

    @Override
    public int getCount() {
        return 24;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(puzzles_activity).inflate(R.layout.puzzles_level_page_item, viewGroup, false);

        TextView textView;
        ImageView tick, lock;
        RelativeLayout relativeLayout;
        textView = view.findViewById(R.id.txtno);

        relativeLayout = view.findViewById(R.id.relativeLayout);
        tick = view.findViewById(R.id.tick);
        lock = view.findViewById(R.id.lock);

        if (pagecount == 1) {
            position = position + 24;
        }
        if (pagecount == 2) {
            position = position + 48;
        }
        if (pagecount == 3) {
            position = position + 72;
            if (position >= 75) {
                lock.setVisibility(View.INVISIBLE);
                textView.setVisibility(View.INVISIBLE);
            }

        }

        String levelStatus = preferences.getString("levelStatus" + position, "pending");
        levelno = preferences.getInt("lastLevel", -1);
        lastLevel = preferences.getInt("lastLevel", 0);

        Log.d("UUU", "position =" + position + " => " + preferences.getString("levelStatus " + position, "pending"));


        if (position == 0) {
            lock.setVisibility(View.INVISIBLE);
            textView.setText("" + (position + 1));
            textView.setVisibility(View.VISIBLE);
        }

        if (levelStatus.equals("win")) {
            lock.setVisibility(View.INVISIBLE);
            textView.setText("" + (position + 1));
            textView.setVisibility(View.VISIBLE);
            tick.setVisibility(View.VISIBLE);

        }
        if (levelStatus.equals("skip") || levelStatus.equals("win") || position == (levelno + 1)) {
            lock.setVisibility(View.INVISIBLE);
            textView.setText("" + (position + 1));
            textView.setVisibility(View.VISIBLE);

        }
        int finalPosition = position;
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(puzzles_activity, Continue_Activity.class);
                if (levelStatus.equals("win") || levelStatus.equals("skip") || finalPosition == (levelno + 1)) {

                    intent.putExtra("levelNo", finalPosition);
                    puzzles_activity.startActivity(intent);
                    puzzles_activity.finish();
                } else {
                    intent.putExtra("levelNo", finalPosition);

                }
            }

        });

        return view;
    }
}
