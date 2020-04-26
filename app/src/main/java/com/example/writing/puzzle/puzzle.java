package com.example.writing.puzzle;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.writing.R;

public class puzzle extends AppCompatActivity implements View.OnTouchListener {
    private float begin_x,begin_y;
    private int move_x,move_y;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.puzzle);
        ImageView img=findViewById(R.id.imageView4);
        img.setOnTouchListener(this);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:
                begin_x=event.getX();
                begin_y=event.getY();
            case MotionEvent.ACTION_MOVE:
                    move_x=(int)(event.getRawX()-begin_x);
                    move_y=(int)(event.getRawY()-begin_y);
                    v.layout(move_x,move_y,move_x+v.getWidth(),move_y+v.getHeight());


            case MotionEvent.ACTION_UP:
                break;

        }


        return true;
    }
}
