package com.example.writing.memo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.writing.R;
import com.example.writing.panel.ImageSave;

import java.util.ArrayList;
import java.util.List;

import static java.lang.StrictMath.abs;


class M_Panel extends View {
    List<PointF> points=new ArrayList<PointF>();
    List<Integer>colors=new ArrayList<Integer>();
    Bitmap vBitmap,changemap=null;
    private Rect mSrcRect, mDestRect;
    int paintcolor;
    Canvas vBitmapCanvas;
    Paint mainpaint = new Paint();


    public M_Panel(Context context, AttributeSet attrs) {
        super(context,attrs);
        paintcolor=Color.BLACK;
        mainpaint.setStyle(Paint.Style.STROKE);
        mainpaint.setStrokeWidth(10);
        //取得手機解析度
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);

        //設定bitmap大小
        int mar_bot=110* (int)getResources().getDisplayMetrics().scaledDensity;
        vBitmap = Bitmap.createBitmap(dm.widthPixels,dm.heightPixels-mar_bot, Bitmap.Config.RGB_565);


    }

    public void originSetvBitmap(){
        vBitmapCanvas = new Canvas(vBitmap);
        vBitmapCanvas.drawColor(Color.WHITE);
    }

    public  void setvBitmap( Bitmap bitmap){
         changemap=bitmap;
        mSrcRect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        mDestRect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        vBitmapCanvas = new Canvas(vBitmap);
        vBitmapCanvas .drawBitmap(bitmap, mSrcRect, mDestRect, mainpaint);
        M_Panel.this.invalidate();


    }



    public  void changeColor(int color){
        paintcolor=color;
    }

    public void clearPanel(){
        points.clear();
        colors.clear();
        changemap=null;
        this.resetCanvas();
        this.invalidate();


    }

    public void setText(EditText text){

        int spSize = 20;
        float scaledSizeInPixels = spSize * getResources().getDisplayMetrics().scaledDensity;
        float x_val=0;
        float y_val=scaledSizeInPixels;
        String textsample=text.getText().toString();
        mainpaint.setColor(Color.BLACK);
        for (int i=0;i<textsample.length();i++){
            mainpaint.setTextSize(scaledSizeInPixels);
            mainpaint.setStrokeWidth(2);
            vBitmapCanvas.drawText(String.valueOf(textsample.charAt(i)),x_val,y_val,mainpaint);
            vBitmapCanvas.save();
            vBitmapCanvas.restore();
            x_val+=scaledSizeInPixels;
            if(textsample.charAt(i)=='\n'){
                x_val=0;
                y_val+=scaledSizeInPixels;
            }
        }

    }

    @Override
    public void onDraw (Canvas canvas){
        super.onDraw(canvas);
        if (changemap!=null){
            mSrcRect = new Rect(0, 0, changemap.getWidth(),changemap.getHeight());
            mDestRect = new Rect(0, 0, changemap.getWidth(),changemap.getHeight());
            canvas.drawBitmap(changemap, mSrcRect, mDestRect, mainpaint);
        }
        for (int i = 1; i < points.size(); i++) {
            PointF p1 = points.get(i - 1);
            PointF p2 = points.get(i);
            if(abs(p1.x-p2.x)<50 && abs(p1.y-p2.y)<50) {
                mainpaint.setColor(colors.get(i));
                if(colors.get(i)==Color.WHITE){
                    mainpaint.setStrokeWidth(30);
                }
                else{
                    mainpaint.setStrokeWidth(10);
                }
                canvas.drawLine(p1.x, p1.y, p2.x, p2.y, mainpaint);
                vBitmapCanvas.drawLine(p1.x, p1.y, p2.x, p2.y, mainpaint);
            }
        }

    }



    @Override
    public boolean onTouchEvent (MotionEvent event){
        for (int i = 0; i < event.getHistorySize(); i++) {
            points.add(new PointF(event.getHistoricalX(i), event.getHistoricalY(i)));
            colors.add(paintcolor);
        }
        M_Panel.this.invalidate();
        return true;
    }







    //Reset
    public void resetCanvas() {
        points.clear();
        M_Panel.this.invalidate();
        vBitmapCanvas.drawColor(Color.WHITE);
    }






}
