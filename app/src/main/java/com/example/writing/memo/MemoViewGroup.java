package com.example.writing.memo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;

public class MemoViewGroup extends ViewGroup {
    int imageheight=0,imagewidth=0,textheight=0,textwidth=0,textmove=0;
    public MemoViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected  void getImageSize(int imgheight,int imgwidth){
        imageheight=imgheight;
        imagewidth=imgwidth;
    }
    protected  void getTextSize(int txtheight,int txtwidth){
      textheight=txtheight;
      textwidth=txtwidth;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.d("test_changed"," "+changed);
        getChildAt(0).layout(0,0,imagewidth,imageheight);



    }
}
