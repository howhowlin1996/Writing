package com.example.writing.puzzle;


import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.example.writing.R;
import com.example.writing.panel.WritingPanel;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Puzzle extends AppCompatActivity implements View.OnTouchListener,View.OnClickListener  {
    private float begin_x,begin_y;
    private int move_x,move_y,width,height,hit_l,hit_t,hit_r,hit_b,hit_puzzle_id,split_code;
    private long begin_time=0,final_time=0,hit_begin=0,hit_final=0;
    private int[]begin_l=new int [15];
    private int[]begin_t=new int[15];
    private Map<Integer,Integer>idMap=new HashMap<>();
    private Queue<Integer>puzzlequeue=new LinkedList<Integer>();
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
        AnswerBoard answerBoard=findViewById(R.id.answerBoard1);
        PuzzlePanelGroup group=findViewById(R.id.Group);
        split_code=getIntent().getExtras().getInt("num");
        group.splitNum(split_code);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {         //set panel background for copying the character

            qu1.setBackground(getDrawable(R.drawable.pic_0000));
            qu2.setBackground(getDrawable(R.drawable.white));
            up1.setBackground(getDrawable(R.drawable.p5973));
            up2.setBackground(getDrawable(R.drawable.p5bf8));
            up3.setBackground(getDrawable(R.drawable.p571f));
            //up4.setBackground(getDrawable(R.drawable.space));
            down1.setBackground(getDrawable(R.drawable.p5b50));
            down2.setBackground(getDrawable(R.drawable.p65a4));
            down3.setBackground(getDrawable(R.drawable.p6728));
            //down4.setBackground(getDrawable(R.drawable.space));
            //answerBoard.setBackground(getDrawable(R.drawable.space));
        }
        DisplayMetrics dm = new DisplayMetrics();

        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        width=dm.widthPixels;
        height=dm.heightPixels;


        up1.setOnTouchListener(this);
        up2.setOnTouchListener(this);
        up3.setOnTouchListener(this);
        up4.setOnTouchListener(this);
        down1.setOnTouchListener(this);
        down2.setOnTouchListener(this);
        down3.setOnTouchListener(this);
        down4.setOnTouchListener(this);
        answerBoard.setOnClickListener((View.OnClickListener) this);

        for (int i=0;i<15;i++){
            idMap.put(group.getChildAt(i).getId(),i);
        }



    }



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int idnum=v.getId();
        int middle_w,middle_h;

        if (final_time==0){
            saveInfoPos();
        }
        if (puzzlequeue.size()!=0) {
            if (!puzzlequeue.contains(idnum)){
                puzzlequeue.add(idnum);
            }
        }
        else{
            puzzlequeue.add(idnum);
        }
        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:
                begin_x=event.getX();
                begin_y=event.getY();

            case MotionEvent.ACTION_MOVE:
                move_x=(int)(event.getRawX()-begin_x);                                              /*2*begin_y??*/
                move_y=(int)(event.getRawY()-2*begin_y);
                middle_w=move_x+v.getWidth()/2;
                middle_h=move_y+v.getHeight()/2;
                if(middle_w>hit_l&&middle_w<hit_r&&middle_h>hit_t&&middle_h<hit_b){                 //if the radical puzzles hit the answerboard more than 1sec then change its background amd set the puzzle back to the start
                    if(hit_final==0){
                        hit_puzzle_id=idnum;                                                          //to judge which puzzle hit the answerboard
                        hit_begin=System.currentTimeMillis();
                        hit_final=System.currentTimeMillis();
                    }
                    else{
                        if(idnum==hit_puzzle_id){
                            hit_final=System.currentTimeMillis();
                        }
                        else {
                            hit_final=0;
                        }

                    }
                    if(hit_final-hit_begin>1000){
                        AnswerBoard answerBoard1=findViewById(R.id.answerBoard1);
                        AnswerBoard answerBoard2=findViewById(R.id.answerBoard2);
                        AnswerBoard answerBoard3=findViewById(R.id.answerBoard3);
                        backToStart(v.getId());
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//set panel background for copying the character
                            if(middle_w>answerBoard1.getLeft()&&middle_w<answerBoard1.getRight()&&middle_h>answerBoard1.getTop()&&middle_h<answerBoard1.getBottom()){
                                answerBoard1.setBackground(v.getBackground());
                            }
                            if(middle_w>answerBoard2.getLeft()&&middle_w<answerBoard2.getRight()&&middle_h>answerBoard2.getTop()&&middle_h<answerBoard2.getBottom()){
                                answerBoard2.setBackground(v.getBackground());
                            }
                            if(middle_w>answerBoard3.getLeft()&&middle_w<answerBoard3.getRight()&&middle_h>answerBoard3.getTop()&&middle_h<answerBoard3.getBottom()){
                                answerBoard3.setBackground(v.getBackground());
                            }

                        }
                        break;
                    }
                }
                if (move_x<0){
                    move_x=0;
                }
                if(move_y<0){
                    move_y=0;
                }
                if(move_x+v.getWidth()>width){
                    move_x=width-v.getWidth();
                }
                if(move_y+v.getHeight()>height-v.getHeight()*3/4){
                    move_y=height-v.getHeight()*7/4;
                }

                v.layout(move_x,move_y,move_x+v.getWidth(),move_y+v.getHeight());

        }

        final_time=System.currentTimeMillis();

        if (final_time-begin_time>500&&begin_time!=0&&puzzlequeue.size()!=0){                       // the system will set the puzzles to the start position every 500ms
            int size=puzzlequeue.size();
            for (int i=0;i<size;i++){
                if (puzzlequeue.element()!=idnum){
                    backToStart(puzzlequeue.element());
                    puzzlequeue.remove();
                }
            }
        }

        if (begin_time==0){
            begin_time=System.currentTimeMillis();
        }

        return true;
    }



    public void saveInfoPos(){                                                                      //The work of this function is to remember the begin position of every views by using array
        int width=0;
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        width=dm.widthPixels/2;
        PuzzlePanelGroup group=findViewById(R.id.Group);
        for (int i=0;i<15;i++){
            begin_l[i]=group.begin_l[i];
            begin_t[i]=group.begin_t[i];
        }
        hit_l=begin_l[0];
        hit_t=begin_t[0];
        hit_r=hit_l+width;
        hit_b=hit_t+width;
    }



    public void backToStart(int id){                                                                 //The work of this function is to set the puzzles to the begin position
        PuzzlePanelGroup group=findViewById(R.id.Group);
        int idnum=idMap.get(id);
        View child=group.getChildAt(idnum);
        child.layout(begin_l[idnum],begin_t[idnum],begin_l[idnum]+child.getWidth(),begin_t[idnum]+child.getHeight());
    }


    @Override
    public void onClick(View v) {
        Intent intent =new Intent(getBaseContext(), WritingPanel.class);
        startActivity(intent);
    }
}
