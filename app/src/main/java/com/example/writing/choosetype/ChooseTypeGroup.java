package com.example.writing.choosetype;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.RequiresApi;

public class ChooseTypeGroup extends ViewGroup {
    int width,height;
    int type_num=1,phonetic_num=1,question_num=1;
    public ChooseTypeGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        width=dm.widthPixels;
        height=dm.heightPixels;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        this.setMeasuredDimension(width,height);
        for (int i=0;i<getChildCount();i++){
            getChildAt(i).measure(getChildAt(i).getLayoutParams().width,getChildAt(i).getLayoutParams().height);
            getChildAt(i).invalidate();
        }

    }

    protected void getNum(int type,int phonetic,int question){
        type_num=type;
        phonetic_num=phonetic;
        question_num=question;

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int question_block=0;
        int phonetic_block=0;
        int type_block=0;
        question_block=height/(question_num*2+question_num+1);
        type_block=height/(type_num*2+type_num+1);
        phonetic_block=height/(phonetic_num*2+phonetic_num+1);
        int button_height=type_block;
        int question_height=question_block;
        int phonetic_height=phonetic_block;
        int questionwidth=0;

        for (int i=0;i<getChildCount();i++){
            String child_tag=getChildAt(i).getTag().toString();
            View child=getChildAt(i);

            if (child_tag.contains("type")){
                ChooseTypeGroup.LayoutParams params=new ChooseTypeGroup.LayoutParams(child.getLayoutParams());
                child.layout(3*width/4-params.width/2,button_height,3*width/4+params.width/2,button_height+params.height);
                button_height+=params.height;
                button_height+=type_block;
                Log.d("here",new String(params.height+" "+params.width+"i"));
            }
            else if (child_tag.contains("question")){
                ChooseTypeGroup.LayoutParams params=new ChooseTypeGroup.LayoutParams(child.getLayoutParams());
                child.layout(width/4-params.width/2,question_height,width/4+params.width/2,question_height+params.height);
                question_height+=params.height;
                question_height+=type_block;
                questionwidth=width/4+params.width/2;

                Log.d("here",new String(params.height+" "+params.width));

            }
            else{
                ChooseTypeGroup.LayoutParams params=new ChooseTypeGroup.LayoutParams(child.getLayoutParams());
                child.layout(questionwidth-params.width/6,phonetic_height,questionwidth+5*params.width/6,phonetic_height+params.height);
                phonetic_height+=params.height;
                phonetic_height+=type_block;

            }

        }
        invalidate();

    }
}
