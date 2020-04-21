package com.example.writing;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.app.PendingIntent.getActivity;
import static java.lang.StrictMath.abs;




class Panel extends View {
    List<PointF> points=new ArrayList<PointF>();
    Bitmap vBitmap;
    Canvas vBitmapCanvas;
    Paint mpaint = new Paint();

    public Panel(Context context,AttributeSet attrs) {
        super(context,attrs);
        mpaint.setColor(Color.BLACK);
        mpaint.setStyle(Paint.Style.STROKE);
        mpaint.setStrokeWidth(10);

        //取得手機解析度
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);

        //設定bitmap大小
        vBitmap = Bitmap.createBitmap(dm.widthPixels,dm.heightPixels, Bitmap.Config.RGB_565);
        vBitmapCanvas = new Canvas(vBitmap);
        vBitmapCanvas.drawColor(Color.WHITE);

    }

    @Override
    public void onDraw (Canvas canvas){
        super.onDraw(canvas);
        for (int i = 1; i < points.size(); i++) {
            PointF p1 = points.get(i - 1);
            PointF p2 = points.get(i);
            if(abs(p1.x-p2.x)<50 && abs(p1.y-p2.y)<50) {
                canvas.drawLine(p1.x, p1.y, p2.x, p2.y, mpaint);
                vBitmapCanvas.drawLine(p1.x, p1.y, p2.x, p2.y, mpaint);
            }
        }
    }



    @Override
    public boolean onTouchEvent (MotionEvent event){
        for (int i = 0; i < event.getHistorySize(); i++) {
            points.add(new PointF(event.getHistoricalX(i), event.getHistoricalY(i)));
        }
        Panel.this.invalidate();
        return true;
    }







    //Reset
    public void resetCanvas() {
        points.clear();
        Panel.this.invalidate();
        vBitmapCanvas.drawColor(Color.WHITE);
    }



    //save as picture
    public void savePicture (){
        ImageSave saveImageTask = new ImageSave( getContext());
        saveImageTask.execute(vBitmap);
    }
}
