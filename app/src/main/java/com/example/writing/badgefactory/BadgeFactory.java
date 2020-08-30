package com.example.writing.badgefactory;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ScrollView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.writing.R;

import java.io.File;
import java.io.FileInputStream;

public class BadgeFactory extends AppCompatActivity implements View.OnClickListener {
    String photo;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.badgefactory);
        getSupportActionBar().hide(); //隱藏標題
        final BadgeGroup group=findViewById(R.id.badgeGroup_badgefactory);
        final ScrollView scrollView=findViewById(R.id.ScrollView_badge_factory);
        int width;
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        width=dm.widthPixels;
        DataHelper dbBadge=new DataHelper(this,"0000"+".db",null,1,"21");
        int time;
        time =dbBadge.imageNum();
        dbBadge.close();
        group.height=(time/2+1)*width;


        Cursor file_position=dbBadge.getFileName();
        for (int i=0;i<time;i++){
            ImageView here=new ImageView(this);

            Bitmap bmp=null;
            photo=file_position.getString(0);
            File file=new File(photo);
            try{
                FileInputStream fin=new  FileInputStream(file);
                bmp = BitmapFactory.decodeStream(fin);
                int bmpWidth=bmp.getWidth();
                int bmpHeight=bmp.getHeight();
                Matrix matrix=new Matrix();
                matrix.setScale(0.5f,0.5f);
                bmp=Bitmap.createBitmap(bmp,0,0,bmpWidth,bmpHeight,matrix,true);

                fin.close();

            }
            catch(Exception e){
                Log.d("HERE","file_position.getString(0)");
                e.printStackTrace();
            }

            here.setImageBitmap(bmp);
            group.addView(here);
            file_position.moveToNext();

        }
        for (int i=0;i<time;i++){
            View here=group.getChildAt(i);
            here.setTag(i);
            BadgeGroup.LayoutParams params=new BadgeGroup.LayoutParams(here.getLayoutParams());
            params.height=width/2;
            params.width=width/2;
            here.setLayoutParams(params);
            here.setOnClickListener(this);
        }




    }


    @Override
    public void onClick(View v) {
        Intent intent = new  Intent(this, SingleBadge.class);
        intent.putExtra("background",(int)v.getTag());
        startActivity(intent);
    }
}
