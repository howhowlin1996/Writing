package com.example.writing.badgefactory;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TableLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.writing.R;
import com.example.writing.badgefactory.DataHelper;

import java.io.File;
import java.io.FileInputStream;

public class BadgeFactory extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.badgefactory);
        getSupportActionBar().hide(); //隱藏標題
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN); //隱藏狀態
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
        Log.d("TIMEHERE",new String(time+" "));
        dbBadge.close();


        Cursor file_position=dbBadge.getFileName();
        for (int i=0;i<time;i++){
            ImageView here=new ImageView(this);

            Bitmap bmp=null;
            Log.d("HERE",file_position.getString(0));
            File file=new File(file_position.getString(0));
            try{
                FileInputStream fin=new  FileInputStream(file);
                Log.d("HERE",file_position.getString(0));
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
            BadgeGroup.LayoutParams params=new BadgeGroup.LayoutParams(here.getLayoutParams());
            params.height=width/2;
            params.width=width/2;
            here.setLayoutParams(params);
            here.setOnClickListener(this);
        }



    }

    @Override
    public void onClick(View v) {
        onBackPressed();
    }
}
