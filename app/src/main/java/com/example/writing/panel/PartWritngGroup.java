package com.example.writing.panel;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

public class PartWritngGroup extends ViewGroup {
    int width,height,type=1,answer_position=0,button_width,writing2_width,writing3_width,button_height,character_type=0,child_time=0;
    Context this_context;
    public  PartWritngGroup(Context context, AttributeSet attrs) {
        super(context,attrs);
        this_context=context;
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        width=dm.widthPixels;
        height=dm.heightPixels;
        this.setMeasuredDimension(width,height);

    }

    public void setType(int type_here,int answer_position_here){
        type=type_here;
        answer_position=answer_position_here;

    }
    public  void setDimension(int button,int writing_panel2,int writing_panel3){

        button_height=button;
        button_width=2*button_height;
        writing2_width=writing_panel2;
        writing3_width=writing_panel3;
    }
    public void setCharacter_type(int type,int time){
        character_type=type;
        child_time=time;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    protected void partSet(int left,int top,int partwidth,int partheight){
        int childnum=getChildCount()-1;

        if(character_type/10==1){

            getChildAt(childnum).layout(left,top,left+partwidth,top+partheight);
        }
        else if(character_type/10==2){
            if(character_type%10==1){

                /*answer1.measure(answerboard_width,answerboard_width/2);
                answer2.measure(answerboard_width,answerboard_width/2);
                answer3.measure(0,0);*/
            }
            else if(character_type%10==2){

                /*answer1.measure(answerboard_width,answerboard_width/3);
                answer2.measure(answerboard_width,answerboard_width/3);
                answer3.measure(answerboard_width,answerboard_width/3);*/
            }

        }
        else if(character_type/10==3){
            if(character_type%10==1){
                if (child_time==0){
                    getChildAt(4).layout(left, top, left+partwidth/2, top+partheight);
                    getChildAt(childnum).layout(left+partwidth/2,top,left+partwidth,top+partheight);
                }
                else{
                    getChildAt(childnum).layout(left, top, left+partwidth/2, top+partheight);
                    getChildAt(4).layout(left+partwidth/2,top,left+partwidth,top+partheight);
                }

                /*answer1.measure(answerboard_width/2,answerboard_width);
                answer2.measure(answerboard_width/2,answerboard_width);
                answer3.measure(0,0);*/
            }
            else if(character_type%10==2){
                /*answer1.measure(answerboard_width/3,answerboard_width);
                answer2.measure(answerboard_width/3,answerboard_width);
                answer3.measure(answerboard_width/3,answerboard_width);*/
            }
        }
        else if(character_type/10==4){
            /*answer1.measure(answerboard_width/2,answerboard_width/2);
            answer2.measure(answerboard_width/2,answerboard_width/2);
            answer3.measure(answerboard_width/2,answerboard_width/2);*/
        }
        else if(character_type/10==5){
            if (character_type%10==1){
                if (child_time==0){
                    getChildAt(childnum).layout(left, top, left+partwidth, top+partheight);
                    getChildAt(4).layout(left+partwidth/3,top,left+partwidth,top+partheight*2/3);
                }
                else{
                    getChildAt(4).layout(left, top, left+partwidth, top+partheight);
                    getChildAt(childnum).layout(left+partwidth/3,top,left+partwidth,top+partheight*2/3);
                }

            }
            /*answer1.measure(answerboard_width,answerboard_width);
            answer2.measure(answerboard_width/2,answerboard_width/2);
            answer3.measure(0,0);*/
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int question_width,question_block;

        if (type==0) {                                                                            //question_type->0 means 2 words(without middle string),1 means 3 words (contain middle string)
            question_width=writing2_width;
            question_block=(height-button_width-2*question_width)/3;
            if (question_block<0) {
                question_block=0;
            }

        }
        else {
            question_width=writing3_width;
            question_block=(height-button_width-3*question_width)/4;
            if (question_block<0) {
                question_block=0;
            }

        }
        if (type==0){

            if (answer_position==1){
                int width_pos=width/4;
                for (int i=0;i<2;i++){

                    getChildAt(i).layout(width_pos-button_width/2,height-button_height,width_pos+button_width/2,height);
                    width_pos=width*3/4;

                }
                int height_pos=question_block;
                for(int i=2;i<6;i+=2){
                    getChildAt(i).layout(width/2-question_width/2,height_pos,width/2+question_width/2,height_pos+question_width);
                    height_pos+=question_width+question_block;

                }
                height_pos=question_block;
                for (int i=3;i<6;i+=2){
                    getChildAt(i).layout(width/2+question_width/2,height_pos,width,height_pos+question_width);
                    height_pos+=question_width+question_block;

                }

            }
            else if(answer_position==0){

                int width_pos=width/4;
                for (int i=0;i<2;i++){

                    getChildAt(i).layout(width_pos-button_width/2,height-button_height,width_pos+button_width/2,height);
                    width_pos=width*3/4;

                }
                int height_pos=question_block;
                for(int i=2;i<6;i+=2){
                    getChildAt(6-i).layout(width/2-question_width/2,height_pos,width/2+question_width/2,height_pos+question_width);
                    height_pos+=question_width+question_block;

                }
                height_pos=question_block;
                for (int i=3;i<6;i+=2){
                    getChildAt(8-i).layout(width/2+question_width/2,height_pos,width,height_pos+question_width);
                    height_pos+=question_width+question_block;

                }
            }

        }
        else{

            if (answer_position==0||answer_position==10||answer_position==20||answer_position==210){

                int width_pos=width/4;
                for (int i=0;i<2;i++){

                    getChildAt(i).layout(width_pos-button_width/2,height-button_height,width_pos+button_width/2,height);
                    width_pos=width*3/4;

                }
                int height_pos=question_block;
                for(int i=2;i<6;i+=2){
                    getChildAt(6-i).layout(width/2-question_width/2,height_pos,width/2+question_width/2,height_pos+question_width);
                    height_pos+=question_width+question_block;

                }
                height_pos=question_block;
                for (int i=3;i<6;i+=2){
                    getChildAt(8-i).layout(width/2+question_width/2,height_pos,width,height_pos+question_width);
                    height_pos+=question_width+question_block;

                }
                getChildAt(6).layout(width/2-question_width/2,height_pos,width/2+question_width/2,height_pos+question_width);
                getChildAt(7).layout(width/2+question_width/2,height_pos,width,height_pos+question_width);


            }

            else if(answer_position==1||answer_position==21){

                int width_pos=width/4;
                for (int i=0;i<2;i++){

                    getChildAt(i).layout(width_pos-button_width/2,height-button_height,width_pos+button_width/2,height);
                    width_pos=width*3/4;

                }
                int height_pos=question_block;
                for(int i=2;i<6;i+=2){
                    getChildAt(i).layout(width/2-question_width/2,height_pos,width/2+question_width/2,height_pos+question_width);
                    height_pos+=question_width+question_block;

                }
                getChildAt(6).layout(width/2-question_width/2,height_pos,width/2+question_width/2,height_pos+question_width);
                height_pos=question_block;
                for (int i=3;i<6;i+=2){
                    getChildAt(i).layout(width/2+question_width/2,height_pos,width,height_pos+question_width);
                    height_pos+=question_width+question_block;

                }
                getChildAt(7).layout(width/2+question_width/2,height_pos,width,height_pos+question_width);



            }
            else if(answer_position==2){

                int width_pos=width/4;
                for (int i=0;i<2;i++){

                    getChildAt(i).layout(width_pos-button_width/2,height-button_height,width_pos+button_width/2,height);
                    width_pos=width*3/4;

                }
                int height_pos=question_block;
                for(int i=2;i<6;i+=2){
                    getChildAt(6-i).layout(width/2-question_width/2,height_pos,width/2+question_width/2,height_pos+question_width);
                    height_pos+=2*(question_width+question_block);

                }
                height_pos=question_block;
                for (int i=3;i<6;i+=2){
                    getChildAt(8-i).layout(width/2+question_width/2,height_pos,width,height_pos+question_width);
                    height_pos+=2*(question_width+question_block);

                }
                height_pos=question_width+question_block;
                getChildAt(6).layout(width/2-question_width/2,height_pos,width/2+question_width/2,height_pos+question_width);
                getChildAt(7).layout(width/2+question_width/2,height_pos,width,height_pos+question_width);


            }


        }


        View writingView=getChildAt(4);
        partSet(writingView.getLeft(),writingView.getTop(),question_width,question_width);
    }
}
