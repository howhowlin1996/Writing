package com.example.writing.puzzle;


import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.writing.R;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Puzzle extends AppCompatActivity implements View.OnTouchListener {
    private float begin_x,begin_y;
    private int move_x,move_y;
    private long begin_time=0,final_time=0;
    private int[]begin_l=new int [11];
    private int[]begin_t=new int[11];
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
        AnswerBoard answerBoard=findViewById(R.id.answerBoard);
        PuzzlePanelGroup group=findViewById(R.id.Group);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {         //set panel background for copying the character

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

        for (int i=0;i<11;i++){
            idMap.put(group.getChildAt(i).getId(),i);
        }



    }



    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int idnum=v.getId();

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
        PuzzlePanelGroup group=findViewById(R.id.Group);
        for (int i=0;i<11;i++){
            begin_l[i]=group.begin_l[i];
            begin_t[i]=group.begin_t[i];
        }
    }



    public void backToStart(int id){                                                                 //The work of this function is to set the puzzles to the begin position
        PuzzlePanelGroup group=findViewById(R.id.Group);
        int idnum=idMap.get(id);
        View child=group.getChildAt(idnum);
        child.layout(begin_l[idnum],begin_t[idnum],begin_l[idnum]+child.getWidth(),begin_t[idnum]+child.getHeight());
    }


}
