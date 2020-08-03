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
        Log.d("decision_group",new String(" "+type));
        Log.d("here"," ");
        if (type==0){

            if (answer_position==1){

                int width_pos=0,height_pos=0;
                height_pos+=2*width/8;
                for (int i=0;i<2;i++){
                    width_pos+=width/8;
                    getChildAt(i).layout(width_pos,0,width_pos+2*width/8,height_pos);
                    width_pos+=3*width/8;
                }
                width_pos=0;
                for (int i=2;i<4;i++){
                    width_pos+=width/8;
                    getChildAt(i).layout(width_pos,height-height_pos,width_pos+2*width/8,height);
                    width_pos+=3*width/8;

                }
                height_pos=height/2-width*7/10;
                for(int i=4;i<8;i+=2){
                    getChildAt(i).layout(width*15/100,height_pos,width*85/100,height_pos+width*70/100);
                    height_pos+=+width*70/100;

                }
                height_pos=height/2-width*7/10;
                for (int i=5;i<8;i+=2){
                    getChildAt(i).layout(width*85/100,height_pos,width*95/100,height_pos+width*70/100);
                    height_pos+=+width*70/100;

                }

            }
            else if(answer_position==0){
                int width_pos=0,height_pos=0;
                height_pos+=2*width/8;
                for (int i=0;i<2;i++){
                    width_pos+=width/8;
                    getChildAt(i).layout(width_pos,0,width_pos+2*width/8,height_pos);
                    width_pos+=3*width/8;
                }
                width_pos=0;
                for (int i=2;i<4;i++){
                    width_pos+=width/8;
                    getChildAt(i).layout(width_pos,height-height_pos,width_pos+2*width/8,height);
                    width_pos+=3*width/8;

                }
                height_pos=height/2-width*7/10;
                for(int i=4;i<8;i+=2){
                    getChildAt(10-i).layout(width*15/100,height_pos,width*85/100,height_pos+width*70/100);         //turn up and down reverse change 4 to 6, 6 to 4
                    height_pos+=+width*70/100;

                }
                height_pos=height/2-width*7/10;
                for (int i=5;i<8;i+=2){
                    getChildAt(12-i).layout(width*85/100,height_pos,width*95/100,height_pos+width*70/100);
                    height_pos+=+width*70/100;

                }

            }

        }
        else {
            if (answer_position==0||answer_position==10||answer_position==20||answer_position==210){
                int width_pos=0,height_pos=0;
                height_pos+=2*width/8;
                for (int i=0;i<2;i++){
                    width_pos+=width/8;
                    getChildAt(i).layout(width_pos,0,width_pos+2*width/8,height_pos);
                    width_pos+=3*width/8;
                }
                width_pos=0;
                for (int i=2;i<4;i++){
                    width_pos+=width/8;
                    getChildAt(i).layout(width_pos,height-height_pos,width_pos+2*width/8,height);
                    width_pos+=3*width/8;

                }
                height_pos=width/7;
                for(int i=4;i<8;i+=2){
                    getChildAt(10-i).layout(width*20/100,height_pos,width*80/100,height_pos+width*60/100);         //turn up and down reverse change 4 to 6, 6 to 4
                    height_pos+=width*60/100;

                }
                getChildAt(8).layout(width*20/100,height_pos,width*80/100,height_pos+width*60/100);

                height_pos=width/7;
                for (int i=5;i<8;i+=2){
                    getChildAt(12-i).layout(width*80/100,height_pos,width*90/100,height_pos+width*60/100);
                    height_pos+=width*60/100;

                }
                getChildAt(9).layout(width*80/100,height_pos,width*90/100,height_pos+width*60/100);

            }

            else if(answer_position==1||answer_position==21){

                int width_pos=0,height_pos=0;
                height_pos+=2*width/8;
                for (int i=0;i<2;i++){
                    width_pos+=width/8;
                    getChildAt(i).layout(width_pos,0,width_pos+2*width/8,height_pos);
                    width_pos+=3*width/8;
                }
                width_pos=0;
                for (int i=2;i<4;i++){
                    width_pos+=width/8;
                    getChildAt(i).layout(width_pos,height-height_pos,width_pos+2*width/8,height);
                    width_pos+=3*width/8;

                }
                height_pos=width/7;
                for(int i=4;i<8;i+=2){
                    getChildAt(i).layout(width*20/100,height_pos,width*80/100,height_pos+width*60/100);
                    height_pos+=+width*60/100;

                }
                getChildAt(8).layout(width*20/100,height_pos,width*80/100,height_pos+width*60/100);
                height_pos=width/7;
                for (int i=5;i<8;i+=2){
                    getChildAt(i).layout(width*80/100,height_pos,width*90/100,height_pos+width*60/100);
                    height_pos+=+width*60/100;

                }
                getChildAt(9).layout(width*80/100,height_pos,width*90/100,height_pos+width*60/100);


            }
            else if(answer_position==2){
                int width_pos=0,height_pos=0;
                height_pos+=2*width/8;
                for (int i=0;i<2;i++){
                    width_pos+=width/8;
                    getChildAt(i).layout(width_pos,0,width_pos+2*width/8,height_pos);
                    width_pos+=3*width/8;
                }
                width_pos=0;
                for (int i=2;i<4;i++){
                    width_pos+=width/8;
                    getChildAt(i).layout(width_pos,height-height_pos,width_pos+2*width/8,height);
                    width_pos+=3*width/8;

                }
                height_pos=width/7;
                for(int i=4;i<8;i+=2){
                    getChildAt(i).layout(width*20/100,height_pos,width*80/100,height_pos+width*60/100);
                    height_pos+=width*120/100;

                }

                height_pos=width/7;
                for (int i=5;i<8;i+=2){
                    getChildAt(i).layout(width*80/100,height_pos,width*90/100,height_pos+width*60/100);
                    height_pos+=width*120/100;

                }
                height_pos=width_pos+width*60/100;
                getChildAt(8).layout(width*20/100,height_pos,width*80/100,height_pos+width*60/100);
                getChildAt(9).layout(width*80/100,height_pos,width*90/100,height_pos+width*60/100);

            }

        }




    }
}
