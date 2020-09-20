package com.example.writing.memo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.writing.R;

import java.io.FileInputStream;

public class MemoPosition extends AppCompatActivity implements View.OnTouchListener {
    int width=0,height=0;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        String keyin_word;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memoposition);
        getSupportActionBar().hide(); //隱藏標題
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
        viewgroup_here.getTextSize(changeSp2Dp(30)*2,changeSp2Dp(30)*(keyin_word.length()+1));
        viewgroup_here.addView(textView);

        textView.setOnTouchListener(this);






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
            viewgroup_here.addView(img);

        }
        catch(Exception e){
            e.printStackTrace();
            //Toast.makeText(this,"hERE",Toast.LENGTH_LONG).show();
        }



    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        float begin_x=0,begin_y=0,move_x=0,move_y=0;
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
        if(move_y+v.getHeight()>height){
            move_y=height-v.getHeight();
        }

        v.layout((int)move_x,(int)move_y,(int)(move_x+v.getWidth()),(int)(move_y+v.getHeight()));

        return true;
    }
}
