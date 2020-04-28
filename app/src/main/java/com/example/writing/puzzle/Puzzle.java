package com.example.writing.puzzle;


import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.writing.R;

public class Puzzle extends AppCompatActivity implements View.OnTouchListener {
    private float begin_x,begin_y;
    private int move_x,move_y;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.puzzle);
        PuzzlePanel qu1=findViewById(R.id.questionPanelLeft);
        PuzzlePanel qu2=findViewById(R.id.questionPanelRIght);
        PuzzlePanel up1=findViewById(R.id.charUp1);
        PuzzlePanel up2=findViewById(R.id.charUp2);
        PuzzlePanel up3=findViewById(R.id.charUp3);
        PuzzlePanel up4=findViewById(R.id.charUp4);
        PuzzlePanel down1=findViewById(R.id.charDown1);
        PuzzlePanel down2=findViewById(R.id.charDown2);
        PuzzlePanel down3=findViewById(R.id.charDown3);
        PuzzlePanel down4=findViewById(R.id.charDown4);
        AnswerBoard answerBoard=findViewById(R.id.answerBoard);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {            //set panel background for copying the character
            qu1.setBackground(getDrawable(R.drawable.no));
            qu2.setBackground(getDrawable(R.drawable.longlong));
            up1.setBackground(getDrawable(R.drawable.longlong_copy));
            up2.setBackground(getDrawable(R.drawable.longlong_copy));
            up3.setBackground(getDrawable(R.drawable.longlong_copy));
            up4.setBackground(getDrawable(R.drawable.longlong_copy));
            down1.setBackground(getDrawable(R.drawable.longlong_copy));
            down2.setBackground(getDrawable(R.drawable.longlong_copy));
            down3.setBackground(getDrawable(R.drawable.longlong_copy));
            down4.setBackground(getDrawable(R.drawable.longlong_copy));
            answerBoard.setBackground(getDrawable(R.drawable.space));
        }

        up1.setOnTouchListener(this);
        up2.setOnTouchListener(this);
        up3.setOnTouchListener(this);
        up4.setOnTouchListener(this);
        down1.setOnTouchListener(this);
        down2.setOnTouchListener(this);
        down3.setOnTouchListener(this);
        down4.setOnTouchListener(this);

    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {


        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:
                begin_x=event.getX();
                begin_y=event.getY();

            case MotionEvent.ACTION_MOVE:
                move_x=(int)(event.getRawX()-begin_x);                                              /*2*begin_y??*/
                move_y=(int)(event.getRawY()-2*begin_y);
                v.layout(move_x,move_y,move_x+v.getWidth(),move_y+v.getHeight());
            case MotionEvent.ACTION_UP:

                break;
        }


        return true;
    }




}
