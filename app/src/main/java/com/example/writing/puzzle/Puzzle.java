package com.example.writing.puzzle;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.example.writing.R;
import com.example.writing.panel.CopyWriting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Random;


public class Puzzle extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener {
    private float begin_x,begin_y;
    private int move_x,move_y,width,height,hit_l,hit_t,hit_r,hit_b,hit_puzzle_id,split_code,answer1_change=0,answer2_change=0,answer1_name=-1,answer2_name=-1,up_answer=0,down_answer=4,checkRightAnswer_time=0,checkWrongAnswer_time=0,puzzle_change=0,try_time=1,confirmClick_time=0;
    private long begin_time=0,final_time=0,hit_begin=0,hit_final=0,beginpassword_time=0;
    private View board1_view=null,board2_view=null;
    private String puzzlename[]=new String[8];
    private  String partone,parttwo;
    private int[]begin_l=new int [16];
    private int[]begin_t=new int[16];
    private int[]up_part_question=new int[4];
    private int[]down_part_question=new int[4];
    private Map<Integer,Integer>idMap=new HashMap<>();
    private Queue<Integer>puzzlequeue=new LinkedList<Integer>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.puzzle);
        getSupportActionBar().hide(); //隱藏標題

        PuzzlePanel up1=findViewById(R.id.charUp1);
        PuzzlePanel up2=findViewById(R.id.charUp2);
        PuzzlePanel up3=findViewById(R.id.charUp3);
        PuzzlePanel up4=findViewById(R.id.charUp4);
        PuzzlePanel down1=findViewById(R.id.charDown1);
        PuzzlePanel down2=findViewById(R.id.charDown2);
        PuzzlePanel down3=findViewById(R.id.charDown3);
        PuzzlePanel down4=findViewById(R.id.charDown4);


        PuzzlePanelGroup group=findViewById(R.id.Group);

        for(int i=0;i<4;i++){
            up_part_question[i]=-1;
            down_part_question[i]=-1;
        }
        int sdk = android.os.Build.VERSION.SDK_INT;
        if (sdk< Build.VERSION_CODES.LOLLIPOP){
            prepareViewOld(0);
        }
        else{
            prepareView(0);
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



        for (int i=0;i<15;i++){
            idMap.put(group.getChildAt(i).getId(),i);
        }



    }



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected  void prepareView(int flag){
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
        PuzzlePanelGroup group=findViewById(R.id.Group);


        SharedPreferences storeinform=getSharedPreferences("num", Context.MODE_PRIVATE);
        split_code=storeinform.getInt("split_code",0);
        int part_num=storeinform.getInt("part_num",0);
        if (split_code/10==5){
            PuzzlePanel newpart=new PuzzlePanel(this,null);
            group.addView(newpart);

        }
        group.splitNum(split_code);
        int answer_position=storeinform.getInt("answer_position",0);
        String rightString=storeinform.getString("right",null);
        String leftString =storeinform.getString("left",null);
        String middleString=storeinform.getString("middle",null);
        partone="part"+rightString.substring(0,rightString.length()-2)+"0";
        parttwo="part"+rightString.substring(0,rightString.length()-2)+"1";
        Resources here_r=this.getResources();
        setquestionPuzzle(split_code);

        if (flag==0){
            if (part_num==1){
                up2.setVisibility(View.GONE);
                down2.setVisibility(View.GONE);
                up3.setVisibility(View.GONE);
                down3.setVisibility(View.GONE);
                up4.setVisibility(View.GONE);
                down4.setVisibility(View.GONE);
                upRandomPart(1);
                downRandomPart(1);

            }
            else if(part_num==2){
                up3.setVisibility(View.GONE);
                down3.setVisibility(View.GONE);
                up4.setVisibility(View.GONE);
                down4.setVisibility(View.GONE);
                upRandomPart(2);
                downRandomPart(2);


            }
            else if (part_num==3){
                up4.setVisibility(View.GONE);
                down4.setVisibility(View.GONE);
                upRandomPart(3);
                downRandomPart(3);

            }
            else{

                upRandomPart(4);
                downRandomPart(4);
            }



        }

        else {
            if (part_num==1){
                up2.setVisibility(View.GONE);
                down2.setVisibility(View.GONE);
                up3.setVisibility(View.GONE);
                down3.setVisibility(View.GONE);
                up4.setVisibility(View.GONE);
                down4.setVisibility(View.GONE);
            }
            else if(part_num==2){
                up3.setVisibility(View.GONE);
                down3.setVisibility(View.GONE);
                up4.setVisibility(View.GONE);
                down4.setVisibility(View.GONE);
            }
            else if (part_num==3){
                up4.setVisibility(View.GONE);
                down4.setVisibility(View.GONE);

            }

        }


        up1.setTag(0);
        up2.setTag(1);
        up3.setTag(2);
        up4.setTag(3);
        down1.setTag(4);
        down2.setTag(5);
        down3.setTag(6);
        down4.setTag(7);



        if(middleString.equals("0")){
            group.setType(0,answer_position);
            group.invalidate();
            if(answer_position==0){
                qu1.setBackground(getDrawable(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName())));
                up1.setBackground(getDrawable(here_r.getIdentifier(partone+up_part_question[0],"drawable",this.getPackageName())));
                up2.setBackground(getDrawable(here_r.getIdentifier(partone+up_part_question[1],"drawable",this.getPackageName())));
                up3.setBackground(getDrawable(here_r.getIdentifier(partone+up_part_question[2],"drawable",this.getPackageName())));
                up4.setBackground(getDrawable(here_r.getIdentifier(partone+up_part_question[3],"drawable",this.getPackageName())));
                down1.setBackground(getDrawable(here_r.getIdentifier(parttwo+down_part_question[0],"drawable",this.getPackageName())));
                down2.setBackground(getDrawable(here_r.getIdentifier(parttwo+down_part_question[1],"drawable",this.getPackageName())));
                down3.setBackground(getDrawable(here_r.getIdentifier(parttwo+down_part_question[2],"drawable",this.getPackageName())));
                down4.setBackground(getDrawable(here_r.getIdentifier(parttwo+down_part_question[3],"drawable",this.getPackageName())));
            }
            else{
                qu1.setBackground(getDrawable(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName())));
                up1.setBackground(getDrawable(here_r.getIdentifier(partone+up_part_question[0],"drawable",this.getPackageName())));
                up2.setBackground(getDrawable(here_r.getIdentifier(partone+up_part_question[1],"drawable",this.getPackageName())));
                up3.setBackground(getDrawable(here_r.getIdentifier(partone+up_part_question[2],"drawable",this.getPackageName())));
                up4.setBackground(getDrawable(here_r.getIdentifier(partone+up_part_question[3],"drawable",this.getPackageName())));
                down1.setBackground(getDrawable(here_r.getIdentifier(parttwo+down_part_question[0],"drawable",this.getPackageName())));
                down2.setBackground(getDrawable(here_r.getIdentifier(parttwo+down_part_question[1],"drawable",this.getPackageName())));
                down3.setBackground(getDrawable(here_r.getIdentifier(parttwo+down_part_question[2],"drawable",this.getPackageName())));
                down4.setBackground(getDrawable(here_r.getIdentifier(parttwo+down_part_question[3],"drawable",this.getPackageName())));

            }

        }
        else {
            group.setType(1,answer_position);
            group.invalidate();
            up1.setBackground(getDrawable(here_r.getIdentifier(partone+up_part_question[0],"drawable",this.getPackageName())));
            up2.setBackground(getDrawable(here_r.getIdentifier(partone+up_part_question[1],"drawable",this.getPackageName())));
            up3.setBackground(getDrawable(here_r.getIdentifier(partone+up_part_question[2],"drawable",this.getPackageName())));
            up4.setBackground(getDrawable(here_r.getIdentifier(partone+up_part_question[3],"drawable",this.getPackageName())));
            down1.setBackground(getDrawable(here_r.getIdentifier(parttwo+down_part_question[0],"drawable",this.getPackageName())));
            down2.setBackground(getDrawable(here_r.getIdentifier(parttwo+down_part_question[1],"drawable",this.getPackageName())));
            down3.setBackground(getDrawable(here_r.getIdentifier(parttwo+down_part_question[2],"drawable",this.getPackageName())));
            down4.setBackground(getDrawable(here_r.getIdentifier(parttwo+down_part_question[3],"drawable",this.getPackageName())));
            if (answer_position==0||answer_position==10||answer_position==20||answer_position==210){
                if (answer_position==210){
                    qu1.setBackground(getDrawable(R.drawable.white));
                    qu2.setBackground(getDrawable(R.drawable.white));
                }
                else if (answer_position==20){
                    qu1.setBackground(getDrawable(here_r.getIdentifier("cha"+middleString,"drawable",this.getPackageName())));
                    qu2.setBackground(getDrawable(R.drawable.white));
                }
                else if (answer_position==10){
                    qu1.setBackground(getDrawable(R.drawable.white));
                    qu2.setBackground(getDrawable(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName())));
                }
                else {
                    qu1.setBackground(getDrawable(here_r.getIdentifier("cha"+middleString,"drawable",this.getPackageName())));
                    qu2.setBackground(getDrawable(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName())));
                }


            }

            else if(answer_position==1||answer_position==21){
                if (answer_position==21){
                    qu1.setBackground(getDrawable(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName())));
                    qu2.setBackground(getDrawable(R.drawable.white));

                }
                else{
                    qu1.setBackground(getDrawable(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName())));
                    qu2.setBackground(getDrawable(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName())));
                }



            }
            else if(answer_position==2){
                qu1.setBackground(getDrawable(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName())));
                qu2.setBackground(getDrawable(here_r.getIdentifier("cha"+middleString,"drawable",this.getPackageName())));

            }







        }
    }
    protected  void prepareViewOld(int flag){
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
        PuzzlePanelGroup group=findViewById(R.id.Group);


        SharedPreferences storeinform=getSharedPreferences("num", Context.MODE_PRIVATE);
        split_code=storeinform.getInt("split_code",0);
        int part_num=storeinform.getInt("part_num",0);
        group.splitNum(split_code);
        if (split_code/10==5){
            PuzzlePanel newpart=new PuzzlePanel(this,null);
            group.addView(newpart);

        }
        setquestionPuzzle(split_code);
        int answer_position=storeinform.getInt("answer_position",0);
        String rightString=storeinform.getString("right",null);
        String leftString =storeinform.getString("left",null);
        String middleString=storeinform.getString("middle",null);

        /*if (split_code/10==5){
            partone="part"+rightString.substring(0,rightString.length()-2)+"1";
            parttwo="part"+rightString.substring(0,rightString.length()-2)+"0";
        }*/
        //else{
            partone="part"+rightString.substring(0,rightString.length()-2)+"0";
            parttwo="part"+rightString.substring(0,rightString.length()-2)+"1";
        //}

        Resources here_r=this.getResources();
        if (flag==0){
            if (part_num==1){
                up2.setVisibility(View.GONE);
                down2.setVisibility(View.GONE);
                up3.setVisibility(View.GONE);
                down3.setVisibility(View.GONE);
                up4.setVisibility(View.GONE);
                down4.setVisibility(View.GONE);
                upRandomPart(1);
                downRandomPart(1);

            }
            else if(part_num==2){
                up3.setVisibility(View.GONE);
                down3.setVisibility(View.GONE);
                up4.setVisibility(View.GONE);
                down4.setVisibility(View.GONE);
                upRandomPart(2);
                downRandomPart(2);


            }
            else if (part_num==3){
                up4.setVisibility(View.GONE);
                down4.setVisibility(View.GONE);
                upRandomPart(3);
                downRandomPart(3);

            }
            else{

                upRandomPart(4);
                downRandomPart(4);
            }



        }

        else {
            if (part_num==1){
                up2.setVisibility(View.GONE);
                down2.setVisibility(View.GONE);
                up3.setVisibility(View.GONE);
                down3.setVisibility(View.GONE);
                up4.setVisibility(View.GONE);
                down4.setVisibility(View.GONE);
            }
            else if(part_num==2){
                up3.setVisibility(View.GONE);
                down3.setVisibility(View.GONE);
                up4.setVisibility(View.GONE);
                down4.setVisibility(View.GONE);
            }
            else if (part_num==3){
                up4.setVisibility(View.GONE);
                down4.setVisibility(View.GONE);

            }

        }


        up1.setTag(0);
        up2.setTag(1);
        up3.setTag(2);
        up4.setTag(3);
        down1.setTag(4);
        down2.setTag(5);
        down3.setTag(6);
        down4.setTag(7);
        if(middleString.equals("0")){
            group.setType(0,answer_position);
            group.invalidate();
            if(answer_position==0){
                qu1.setBackground(getResources().getDrawable(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName())));
                up1.setBackground(getResources().getDrawable(here_r.getIdentifier(partone+up_part_question[0],"drawable",this.getPackageName())));
                up2.setBackground(getResources().getDrawable(here_r.getIdentifier(partone+up_part_question[1],"drawable",this.getPackageName())));
                up3.setBackground(getResources().getDrawable(here_r.getIdentifier(partone+up_part_question[2],"drawable",this.getPackageName())));
                up4.setBackground(getResources().getDrawable(here_r.getIdentifier(partone+up_part_question[3],"drawable",this.getPackageName())));
                down1.setBackground(getResources().getDrawable(here_r.getIdentifier(parttwo+down_part_question[0],"drawable",this.getPackageName())));
                down2.setBackground(getResources().getDrawable(here_r.getIdentifier(parttwo+down_part_question[1],"drawable",this.getPackageName())));
                down3.setBackground(getResources().getDrawable(here_r.getIdentifier(parttwo+down_part_question[2],"drawable",this.getPackageName())));
                down4.setBackground(getResources().getDrawable(here_r.getIdentifier(parttwo+down_part_question[3],"drawable",this.getPackageName())));
            }
            else{
                qu1.setBackground(getResources().getDrawable(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName())));
                up1.setBackground(getResources().getDrawable(here_r.getIdentifier(partone+up_part_question[0],"drawable",this.getPackageName())));
                up2.setBackground(getResources().getDrawable(here_r.getIdentifier(partone+up_part_question[1],"drawable",this.getPackageName())));
                up3.setBackground(getResources().getDrawable(here_r.getIdentifier(partone+up_part_question[2],"drawable",this.getPackageName())));
                up4.setBackground(getResources().getDrawable(here_r.getIdentifier(partone+up_part_question[3],"drawable",this.getPackageName())));
                down1.setBackground(getResources().getDrawable(here_r.getIdentifier(parttwo+down_part_question[0],"drawable",this.getPackageName())));
                down2.setBackground(getResources().getDrawable(here_r.getIdentifier(parttwo+down_part_question[1],"drawable",this.getPackageName())));
                down3.setBackground(getResources().getDrawable(here_r.getIdentifier(parttwo+down_part_question[2],"drawable",this.getPackageName())));
                down4.setBackground(getResources().getDrawable(here_r.getIdentifier(parttwo+down_part_question[3],"drawable",this.getPackageName())));

            }

        }
        else {
            group.setType(1,answer_position);
            group.invalidate();
            up1.setBackground(getResources().getDrawable(here_r.getIdentifier(partone+up_part_question[0],"drawable",this.getPackageName())));
            up2.setBackground(getResources().getDrawable(here_r.getIdentifier(partone+up_part_question[1],"drawable",this.getPackageName())));
            up3.setBackground(getResources().getDrawable(here_r.getIdentifier(partone+up_part_question[2],"drawable",this.getPackageName())));
            up4.setBackground(getResources().getDrawable(here_r.getIdentifier(partone+up_part_question[3],"drawable",this.getPackageName())));
            down1.setBackground(getResources().getDrawable(here_r.getIdentifier(parttwo+down_part_question[0],"drawable",this.getPackageName())));
            down2.setBackground(getResources().getDrawable(here_r.getIdentifier(parttwo+down_part_question[1],"drawable",this.getPackageName())));
            down3.setBackground(getResources().getDrawable(here_r.getIdentifier(parttwo+down_part_question[2],"drawable",this.getPackageName())));
            down4.setBackground(getResources().getDrawable(here_r.getIdentifier(parttwo+down_part_question[3],"drawable",this.getPackageName())));
            if (answer_position==0||answer_position==10||answer_position==20||answer_position==210){
                if (answer_position==210){
                    qu1.setBackground(getResources().getDrawable(R.drawable.white));
                    qu2.setBackground(getResources().getDrawable(R.drawable.white));
                }
                else if (answer_position==20){
                    qu1.setBackground(getResources().getDrawable(here_r.getIdentifier("cha"+middleString,"drawable",this.getPackageName())));
                    qu2.setBackground(getResources().getDrawable(R.drawable.white));
                }
                else if (answer_position==10){
                    qu1.setBackground(getResources().getDrawable(R.drawable.white));
                    qu2.setBackground(getResources().getDrawable(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName())));
                }
                else {
                    qu1.setBackground(getResources().getDrawable(here_r.getIdentifier("cha"+middleString,"drawable",this.getPackageName())));
                    qu2.setBackground(getResources().getDrawable(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName())));
                }


            }

            else if(answer_position==1||answer_position==21){
                if (answer_position==21){
                    qu1.setBackground(getResources().getDrawable(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName())));
                    qu2.setBackground(getResources().getDrawable(R.drawable.white));

                }
                else{
                    qu1.setBackground(getResources().getDrawable(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName())));
                    qu2.setBackground(getResources().getDrawable(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName())));
                }



            }
            else if(answer_position==2){
                qu1.setBackground(getResources().getDrawable(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName())));
                qu2.setBackground(getResources().getDrawable(here_r.getIdentifier("cha"+middleString,"drawable",this.getPackageName())));


            }







        }
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        final AnswerBoard answerBoard1=findViewById(R.id.answerBoard1);
        AnswerBoard answerBoard2=findViewById(R.id.answerBoard2);
        AnswerBoard answerBoard3=findViewById(R.id.answerBoard3);
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
                move_x=(int)(event.getRawX()-begin_x);
                move_y=(int)(event.getRawY()-begin_y);
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
                    if(hit_final-hit_begin>100){

                        puzzle_change=1;
                        backToStart(v.getId());
                       // if (split_code/10!=5){
                        Resources here_r=this.getResources();
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//set panel background for copying the character
                                if(v.getTag().hashCode()<4&&checkHit(middle_w,middle_h,answerBoard1)){
                                    answerBoard1.setBackground(getDrawable(here_r.getIdentifier("answer"+partone+up_part_question[v.getTag().hashCode()],"drawable",this.getPackageName())));
                                    v.setVisibility(View.GONE);
                                    if (board1_view!=v&&board1_view!=null){
                                        board1_view.setVisibility(View.VISIBLE);
                                    }
                                    board1_view=v;
                                    answer1_name=v.getTag().hashCode();
                                    answer1_change=1;
                                    backToStart(puzzlequeue.element());
                                    puzzlequeue.remove();

                                }
                                final PuzzlePanelGroup group=findViewById(R.id.Group);
                                View checkboard;
                                if (split_code/10==5){
                                    checkboard=group.getChildAt(group.getChildCount()-1);
                                }
                                else {
                                    checkboard=answerBoard2;
                                }
                                if(v.getTag().hashCode()>=4&&(checkHit(middle_w,middle_h,checkboard))){

                                    if (split_code/10==5){
                                        answerBoard2.setVisibility(View.VISIBLE);
                                        group.getChildAt(group.getChildCount()-1).setVisibility(View.INVISIBLE);
                                    }
                                    answerBoard2.setBackground(getDrawable(here_r.getIdentifier("answer"+parttwo+down_part_question[v.getTag().hashCode()-4],"drawable",this.getPackageName())));

                                    v.setVisibility(View.GONE);
                                    if (board2_view!=v&&board2_view!=null){
                                        board2_view.setVisibility(View.VISIBLE);
                                    }
                                    board2_view=v;
                                    answer2_name=v.getTag().hashCode();
                                    answer2_change=1;
                                    backToStart(puzzlequeue.element());
                                    puzzlequeue.remove();

                                }
                                if((checkHit(middle_w,middle_h,answerBoard3))){
                                    answerBoard3.setBackground(v.getBackground());

                                }

                            }
                            else {
                                if(v.getTag().hashCode()<4&&checkHit(middle_w,middle_h,answerBoard1)){
                                    answerBoard1.setBackground(getResources().getDrawable(here_r.getIdentifier("answer"+partone+up_part_question[v.getTag().hashCode()],"drawable",this.getPackageName())));
                                    v.setVisibility(View.GONE);
                                    if (board1_view!=v&&board1_view!=null){
                                        board1_view.setVisibility(View.VISIBLE);
                                    }
                                    board1_view=v;
                                    answer1_name=v.getTag().hashCode();
                                    answer1_change=1;
                                    backToStart(puzzlequeue.element());
                                    puzzlequeue.remove();

                                }
                                final PuzzlePanelGroup group=findViewById(R.id.Group);
                                View checkboard;
                                if (split_code/10==5){
                                    checkboard=group.getChildAt(group.getChildCount()-1);
                                }
                                else {
                                    checkboard=answerBoard2;
                                }
                                if(v.getTag().hashCode()>=4&&(checkHit(middle_w,middle_h,checkboard))){
                                    answerBoard2.setBackground(getResources().getDrawable(here_r.getIdentifier("answer"+parttwo+down_part_question[v.getTag().hashCode()-4],"drawable",this.getPackageName())));
                                    v.setVisibility(View.GONE);
                                    if (split_code/10==5){
                                        answerBoard2.setVisibility(View.VISIBLE);
                                        group.getChildAt(group.getChildCount()-1).setVisibility(View.INVISIBLE);
                                    }
                                    if (board2_view!=v&&board2_view!=null){
                                        board2_view.setVisibility(View.VISIBLE);
                                    }
                                    board2_view=v;
                                    answer2_name=v.getTag().hashCode();
                                    answer2_change=1;
                                    backToStart(puzzlequeue.element());
                                    puzzlequeue.remove();

                                }
                                if((checkHit(middle_w,middle_h,answerBoard3))){
                                    answerBoard3.setBackground(v.getBackground());

                                }
                            }



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
                if(move_y+v.getHeight()>height){
                    move_y=height-v.getHeight();
                }

                v.layout(move_x,move_y,move_x+v.getWidth(),move_y+v.getHeight());

        }

        final_time=System.currentTimeMillis();

        if (final_time-begin_time>500&&begin_time!=0&&puzzlequeue.size()!=0){                        // the system will set the puzzles to the start position every 500ms
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


        if (answer1_change==1&&answer2_change==1&&puzzle_change==1){


            if (answer1_name==up_answer&&answer2_name==down_answer){
                answer1_change=-1;
                answer2_change=-1;
                answer1_name=-1;
                answer2_name=-1;
                Runnable check =new Runnable(){

                    @Override
                    public void run() {
                        if (checkRightAnswer_time==0){
                            checkRightAnswer_time++;
                            answerBoard1.postDelayed(this,1500);
                        }
                        else{
                            changeContext(0);
                        }

                    }
                };
                this.runOnUiThread(check);
            }
            else {
                puzzle_change=0;
                answer1_change=-1;
                answer2_change=-1;
                answer1_name=-1;
                answer2_name=-1;
                //Toast.makeText(this,"不對喔,再試試看",Toast.LENGTH_SHORT).show();
                Runnable check =new Runnable(){

                    @Override
                    public void run() {
                        if (checkWrongAnswer_time==0){
                            checkWrongAnswer_time++;
                            answerBoard1.postDelayed(this,1500);
                        }
                        else{
                             checkWrongAnswer_time=0;
                            changeContext(1);
                        }

                    }
                };
                this.runOnUiThread(check);



            }

        }



        return true;
    }



    protected  Boolean checkHit(int middle_w,int middle_h,View board){
        if (middle_w>board.getLeft()&&middle_w<board.getRight()&&middle_h>board.getTop()&&middle_h<board.getBottom()){
            return true;
        }
        return  false;
    }


    public   void changeContext( int flag){
        final AnswerBoard answerBoard1=findViewById(R.id.answerBoard1);
        AnswerBoard answerBoard2=findViewById(R.id.answerBoard2);
        AnswerBoard answerBoard3=findViewById(R.id.answerBoard3);
        SharedPreferences storeinform=getSharedPreferences("num", Context.MODE_PRIVATE);
        final String photo_name="puzzle"+storeinform.getString("right",null).substring(0,storeinform.getString("right",null).length()-2);

        if (flag==0){
            PuzzleCombine puzzleCombine =new PuzzleCombine(split_code,answerBoard1.getBackground(),null,answerBoard2.getBackground());
            final SharedPreferences num=getSharedPreferences("num", Context.MODE_PRIVATE);
            puzzleCombine.user_name=num.getString("userkey",null);
            puzzleCombine.photo_name=photo_name+"correct"+try_time;
            puzzleCombine.Combine();
            setContentView(R.layout.choosetype_result);
            ImageView result=findViewById(R.id.result_choosetyperesult);
            result.setBackground(getResources().getDrawable(R.drawable.correct));
            Button confirm_button=findViewById(R.id.confirm_result);
            confirm_button.setOnClickListener(this);

        }
        else{
            PuzzleCombine puzzleCombine =new PuzzleCombine(split_code,answerBoard1.getBackground(),null,answerBoard2.getBackground());
            final SharedPreferences num=getSharedPreferences("num", Context.MODE_PRIVATE);
            puzzleCombine.user_name=num.getString("userkey",null);
            puzzleCombine.photo_name=photo_name+"wrong"+try_time;
            puzzleCombine.Combine();
            setContentView(R.layout.choosetype_result);
            ImageView result=findViewById(R.id.result_choosetyperesult);
            result.setBackground(getResources().getDrawable(R.drawable.wrong));
            Button confirm_button=findViewById(R.id.confirm_result);
            confirm_button.setOnClickListener(this);

        }
        try_time++;





    }



    public void saveInfoPos(){                                                                      //The work of this function is to remember the begin position of every views by using array
        int width=0;
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        width=dm.widthPixels*70/100;
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





    private  void upRandomPart(int num ){
        ArrayList<Integer>numlist=new ArrayList<Integer>();

        for (int i=1;i<4;i++){
            numlist.add(i);
        }
        Random rd=new Random();
        int answer_position= rd.nextInt(num);
        up_part_question[answer_position]=0;
        up_answer=answer_position;
        int bound=3;
        for (int i=0;i<4;i++){
            if(i!=answer_position){
                int here= rd.nextInt(bound);
                up_part_question[i]=numlist.get(here);
                numlist.remove(here);
                bound--;
            }

        }


    }



    private  void downRandomPart(int num ){
        ArrayList<Integer>numlist=new ArrayList<Integer>();
        for (int i=1;i<4;i++){
            numlist.add(i);
        }
        Random rd=new Random();
        int answer_position= rd.nextInt(num);
        down_part_question[answer_position]=0;
        down_answer=answer_position+4;
        int bound=3;
        for (int i=0;i<4;i++){
            if(i!=answer_position){
                int here= rd.nextInt(bound);
                down_part_question[i]=numlist.get(here);
                numlist.remove(here);
                bound--;
            }



        }

    }


    @Override
    public void onClick(View v) {

        if (checkRightAnswer_time>0){
            int flag=checkPassword();
            if (flag==1){

                Intent intent =new Intent(getBaseContext(), CopyWriting.class);
                startActivity(intent);
            }
        }
        else{
            setContentView(R.layout.puzzle);
            PuzzlePanel up1=findViewById(R.id.charUp1);
            PuzzlePanel up2=findViewById(R.id.charUp2);
            PuzzlePanel up3=findViewById(R.id.charUp3);
            PuzzlePanel up4=findViewById(R.id.charUp4);
            PuzzlePanel down1=findViewById(R.id.charDown1);
            PuzzlePanel down2=findViewById(R.id.charDown2);
            PuzzlePanel down3=findViewById(R.id.charDown3);
            PuzzlePanel down4=findViewById(R.id.charDown4);
            int sdk = android.os.Build.VERSION.SDK_INT;
            if (sdk< Build.VERSION_CODES.LOLLIPOP){
                prepareViewOld(1);
            }
            else{
                prepareView(1);
            }
            up1.setOnTouchListener(this);
            up2.setOnTouchListener(this);
            up3.setOnTouchListener(this);
            up4.setOnTouchListener(this);
            down1.setOnTouchListener(this);
            down2.setOnTouchListener(this);
            down3.setOnTouchListener(this);
            down4.setOnTouchListener(this);
            answer1_change=-1;
            answer2_change=-1;
            answer1_name=-1;
            answer2_name=-1;
        }


    }

    private int checkPassword(){

        if (confirmClick_time==0){
            beginpassword_time=System.currentTimeMillis();
        }

        if (System.currentTimeMillis()-beginpassword_time>1000){

            if (confirmClick_time==2){
                confirmClick_time=0;
                return 1;

            }
            else{
                confirmClick_time=0;
                //Toast.makeText(this,"不要亂按",Toast.LENGTH_SHORT).show();
            }

        }
        else{
            confirmClick_time++;
            Toast.makeText(this,"按了"+confirmClick_time+"次",Toast.LENGTH_SHORT).show();
            return 0;
        }



        return 0;
    }

    private void setquestionPuzzle(int type){
        PuzzlePanelGroup group=findViewById(R.id.Group);
        if (type/10==3){
            group.getChildAt(0).setBackground(getResources().getDrawable(R.drawable.leftelement));
            group.getChildAt(1).setBackground(getResources().getDrawable(R.drawable.rightelement));
        }
        else if (type/10==5){
                group.getChildAt(1).setVisibility(View.INVISIBLE);
                group.getChildAt(group.getChildCount()-1).setBackground(getResources().getDrawable(R.drawable.inoutelement));
                group.getChildAt(0).setBackground(getResources().getDrawable(R.drawable.single));
        }
    }






}
