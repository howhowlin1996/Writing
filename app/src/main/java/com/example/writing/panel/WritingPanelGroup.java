package com.example.writing.panel;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;
import android.view.WindowManager;

public class WritingPanelGroup extends ViewGroup {
    int width,height,type=1,answer_position=0;
    Context this_context;
    public WritingPanelGroup(Context context, AttributeSet attrs) {
        super(context,attrs);
        this_context=context;
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        width=dm.widthPixels;
        height=dm.heightPixels;
        this.setMeasuredDimension(width,height);
        //Log.d("here",new String(height+" "+width));

    }
    public void setType(int type_here,int answer_position_here){
        type=type_here; answer_position=answer_position_here;
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //Log.d("decision_group",new String(" "+type));
        //Log.d("here"," ");
        if (type==0){
            int button_width,button_block;
            int question_width,question_block;
            button_width=height/8;
            question_width=height/5;
            question_block=(3*height/4-2*height/5)/3;
           // button_block=(width-button_width*2)/3;
            Log.d("herehere",new String(button_width+" "+question_width));



            if (answer_position==1){

                int width_pos=0,height_pos=0;
                height_pos+=button_width;
                width_pos=width/4;
                for (int i=0;i<2;i++){

                    getChildAt(i).layout(width_pos-button_width/2,0,width_pos+button_width/2,height_pos);
                    width_pos+=width/2;
                }
                width_pos=width/4;
                for (int i=2;i<4;i++){

                    getChildAt(i).layout(width_pos-button_width/2,height-height_pos,width_pos+button_width/2,height);
                    width_pos+=width/2;

                }
                height_pos=button_width+question_block;
                for(int i=4;i<8;i+=2){
                    getChildAt(i).layout(width/2-question_width/2,height_pos,width/2+question_width/2,height_pos+question_width);
                    height_pos+=question_width+question_block;

                }
                height_pos=button_width+question_block;
                for (int i=5;i<8;i+=2){
                    getChildAt(i).layout(width/2+question_width/2,height_pos,width/2+question_width,height_pos+question_width);
                    height_pos+=question_width+question_block;

                }

            }
            else if(answer_position==0){
                int width_pos=0,height_pos=0;
                height_pos+=button_width;
                width_pos=width/4;
                for (int i=0;i<2;i++){

                    getChildAt(i).layout(width_pos-button_width/2,0,width_pos+button_width/2,height_pos);
                    width_pos+=width/2;
                }
                width_pos=width/4;
                for (int i=2;i<4;i++){

                    getChildAt(i).layout(width_pos-button_width/2,height-height_pos,width_pos+button_width/2,height);
                    width_pos+=width/2;

                }
                height_pos=button_width+question_block;
                for(int i=4;i<8;i+=2){
                    getChildAt(10-i).layout(width/2-question_width/2,height_pos,width/2+question_width/2,height_pos+question_width);
                    height_pos+=question_width+question_block;

                }
                height_pos=button_width+question_block;
                for (int i=5;i<8;i+=2){
                    getChildAt(12-i).layout(width/2+question_width/2,height_pos,width/2+question_width,height_pos+question_width);
                    height_pos+=question_width+question_block;

                }


            }

        }
        else {
            int button_width,button_block;
            int question_width,question_block;
            button_width=height/8;
            question_width=height/5;
            question_block=(3*height/4-3*height/5)/4;
            //button_block=(width-button_width*2)/3;
            Log.d("herehere",new String(button_width+" "+question_width+" "+height));

            if (answer_position==0||answer_position==10||answer_position==20||answer_position==210){
                int width_pos=0,height_pos=0;
                height_pos+=button_width;
                width_pos=width/4;
                for (int i=0;i<2;i++){

                    getChildAt(i).layout(width_pos-button_width/2,0,width_pos+button_width/2,height_pos);
                    width_pos+=width/2;
                }
                width_pos=width/4;
                for (int i=2;i<4;i++){

                    getChildAt(i).layout(width_pos-button_width/2,height-height_pos,width_pos+button_width/2,height);
                    width_pos+=width/2;

                }
                height_pos=button_width+question_block;
                for(int i=4;i<8;i+=2){
                    getChildAt(10-i).layout(width/2-question_width/2,height_pos,width/2+question_width/2,height_pos+question_width);       //turn up and down reverse change 4 to 6, 6 to 4
                    height_pos+=question_width+question_block;

                }
                getChildAt(8).layout(width/2-question_width/2,height_pos,width/2+question_width/2,height_pos+question_width);

                height_pos=button_width+question_block;
                for (int i=5;i<8;i+=2){
                    getChildAt(12-i).layout(width/2+question_width/2,height_pos,width/2+question_width,height_pos+question_width);
                    height_pos+=question_width+question_block;

                }
                getChildAt(9).layout(width/2+question_width/2,height_pos,width/2+question_width,height_pos+question_width);

            }

            else if(answer_position==1||answer_position==21){

                int width_pos=0,height_pos=0;
                height_pos+=button_width;
                width_pos=width/4;
                for (int i=0;i<2;i++){

                    getChildAt(i).layout(width_pos-button_width/2,0,width_pos+button_width/2,height_pos);
                    width_pos+=width/2;
                }
                width_pos=width/4;
                for (int i=2;i<4;i++){

                    getChildAt(i).layout(width_pos-button_width/2,height-height_pos,width_pos+button_width/2,height);
                    width_pos+=width/2;

                }
                height_pos=button_width+question_block;
                for(int i=4;i<8;i+=2){
                    getChildAt(i).layout(width/2-question_width/2,height_pos,width/2+question_width/2,height_pos+question_width);
                    height_pos+=question_width+question_block;


                }
                getChildAt(8).layout(width/2-question_width/2,height_pos,width/2+question_width/2,height_pos+question_width);
                height_pos=button_width+question_block;
                for (int i=5;i<8;i+=2){
                    getChildAt(i).layout(width/2+question_width/2,height_pos,width/2+question_width,height_pos+question_width);
                    height_pos+=question_width+question_block;

                }
                getChildAt(9).layout(width/2+question_width/2,height_pos,width/2+question_width,height_pos+question_width);


            }
            else if(answer_position==2){
                int width_pos=0,height_pos=0;
                height_pos+=button_width;
                width_pos=width/4;
                for (int i=0;i<2;i++){

                    getChildAt(i).layout(width_pos-button_width/2,0,width_pos+button_width/2,height_pos);
                    width_pos+=width/2;
                }
                width_pos=width/4;
                for (int i=2;i<4;i++){

                    getChildAt(i).layout(width_pos-button_width/2,height-height_pos,width_pos+button_width/2,height);
                    width_pos+=width/2;

                }
                height_pos=button_width+question_block;
                for(int i=4;i<8;i+=2){
                    getChildAt(i).layout(width/2-question_width/2,height_pos,width/2+question_width/2,height_pos+question_width);
                    height_pos+=(question_width+question_block)*2;


                }

                height_pos=button_width+question_block;
                for (int i=5;i<8;i+=2){
                    getChildAt(i).layout(width/2+question_width/2,height_pos,width/2+question_width,height_pos+question_width);
                    height_pos+=(question_width+question_block)*2;


                }
                height_pos=button_width+question_block*2+question_width;
                getChildAt(8).layout(width/2-question_width/2,height_pos,width/2+question_width/2,height_pos+question_width);
                getChildAt(9).layout(width/2+question_width/2,height_pos,width/2+question_width,height_pos+question_width);

            }

        }




    }
}
