package com.example.writing.memo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.writing.R;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MemoPosition extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener {
    private int width=0,height=0,move_x=0,move_y=0,picture_height=0,textheight=0,textwidth=0;
    private  String   keyin_word;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.memoposition);
        getSupportActionBar().hide(); //隱藏標題
        Button confirm=findViewById(R.id.confirmbutton_memopos);
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        width=dm.widthPixels;
        height=dm.heightPixels;
        MemoViewGroup viewgroup_here=findViewById(R.id.memoposition_group);
        ReadImage();
        keyin_word=getIntent().getExtras().get("text").toString();
        TextView textView=new  TextView(this);
        textView.setText(keyin_word);
        textView.setTextSize(30);
        textView.setTypeface(Typeface.DEFAULT_BOLD,Typeface.BOLD);
        textView.setBackground(getDrawable(R.drawable.block));                     //remember the version problem
        Log.d("test_change"," "+keyin_word.length()+" "+textView.getTextSize());
        textView.layout(0,0,changeSp2Dp(30)*(keyin_word.length()+1),changeSp2Dp(30)*2);
        textwidth=changeSp2Dp(30)*(keyin_word.length());
        textheight=changeSp2Dp(30)*2;
        viewgroup_here.getTextSize(changeSp2Dp(30)*2,changeSp2Dp(30)*(keyin_word.length()+1));
        viewgroup_here.addView(textView);

        textView.setOnTouchListener(this);
        confirm.setOnClickListener(this);






    }

    public int changeSp2Dp(float spsize){
        float fontScale = getBaseContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) ((int) (spsize-0.5f)*fontScale);
    }



    public void ReadImage(){

        try{
            FileInputStream fin=openFileInput("P1234.jpg");
            ImageView img=new ImageView(this);
            Bitmap bitmap = BitmapFactory.decodeStream(fin);
            img.setImageBitmap(bitmap);
            fin.close();
            MemoViewGroup viewgroup_here=findViewById(R.id.memoposition_group);
            viewgroup_here.getImageSize(bitmap.getHeight(),bitmap.getWidth());
            picture_height=bitmap.getHeight();
            viewgroup_here.addView(img);

        }
        catch(Exception e){
            e.printStackTrace();
            //Toast.makeText(this,"hERE",Toast.LENGTH_LONG).show();
        }



    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        float begin_x=0,begin_y=0;
        MemoViewGroup viewgroup_here=findViewById(R.id.memoposition_group);
        viewgroup_here.textmove=1;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                begin_x = event.getX();
                begin_y = event.getY();

            case MotionEvent.ACTION_MOVE:
                move_x = (int) (event.getRawX() - begin_x);
                move_y = (int) (event.getRawY() - begin_y);
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
        if(move_y+v.getHeight()>picture_height){
            move_y=picture_height-v.getHeight();
        }

        v.layout((int)move_x,(int)move_y,(int)(move_x+v.getWidth()),(int)(move_y+v.getHeight()));


        return true;
    }

    @Override
    public void onClick(View v) {
        setTextCanvas();
        Intent intent =new Intent(this,MemoEditPic.class);
        startActivity(intent);

    }

    public  void setTextCanvas(){
        Log.d("test_changed","here");
        FileInputStream fin= null;
        try {
            fin = openFileInput("P1234.jpg");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(fin).copy(Bitmap.Config.ARGB_4444, true);
        Canvas canvas=new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        paint.setTextSize(changeSp2Dp(30));
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText(keyin_word,move_x,move_y+textheight/2,paint);
        Save(bitmap);
    }

    public void Save ( Bitmap bmp) {
        Log.d("test_changed","here");
        try {
            FileOutputStream fos = openFileOutput( "P1234"+ ".jpg",Context.MODE_PRIVATE);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
