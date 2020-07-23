package com.example.writing.badge;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.writing.R;
import com.example.writing.badgefactory.BadgeFactory;
import com.example.writing.coosetype.ChooseTypePage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import main.FirstScene;

public class Badge extends AppCompatActivity implements View.OnClickListener {
    FileInputStream photo;
    String photo_name;
    Bitmap bmp;
    Bitmap badge;
    Set<String> defaultSet=new TreeSet<String>();

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.badge);
        getSupportActionBar().hide(); //隱藏標題
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN); //隱藏狀態
        ImageView badge1=findViewById(R.id.badgeChooseView1);
        ImageView badge2=findViewById(R.id.badgeChooseView2);
        ImageView badge3=findViewById(R.id.badgeChooseView3);
        Button factory=findViewById(R.id.badgeFactory_badge);
        Button share=findViewById(R.id.share_badge);
        Button practice=findViewById(R.id.practice_badge);
        ReadImage();
        badge=BitmapFactory.decodeResource(this.getBaseContext().getResources(), R.drawable.badge);
        badge1.setOnClickListener(this);
        badge2.setOnClickListener(this);
        badge3.setOnClickListener(this);
        factory.setOnClickListener(this);
        share.setOnClickListener(this);
        practice.setOnClickListener(this);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {

        ImageView background=findViewById(R.id.badgeView);
        if(v.getId()==R.id.badgeChooseView1){
            background.setImageResource(R.drawable.badge);
           badge = BitmapFactory.decodeResource(this.getBaseContext().getResources(),
                    R.drawable.badge);
        }
        else if (v.getId()==R.id.badgeChooseView2){
            background.setImageResource(R.drawable.badge_2);
            badge = BitmapFactory.decodeResource(this.getBaseContext().getResources(),
                    R.drawable.badge_2);

        }
        else if(v.getId()==R.id.badgeChooseView3){
            background.setImageResource(R.drawable.badge_3);
            badge = BitmapFactory.decodeResource(this.getBaseContext().getResources(),
                    R.drawable.badge_3);

        }
        else if(v.getId()==R.id.badgeFactory_badge){
            Intent intent = new  Intent(this, BadgeFactory.class);
            startActivity(intent);

        }
        else if(v.getId()==R.id.share_badge){

            File appDir = new File(Environment.getExternalStorageDirectory(), "Writing");
            if (!appDir.exists()) {
                appDir.mkdir();
            }

            String fileName = "badge"+photo_name+ ".jpg";
            File file = new File(appDir, fileName);
            Log.d("photoname",fileName);
            try {
                Bitmap  bmpcombine=Bitmap.createBitmap(badge.getWidth(),badge.getHeight(),bmp.getConfig());
                Canvas canvas=new Canvas (bmpcombine);
                canvas.drawColor(Color.WHITE);
                int bmpWidth = bmp.getWidth();
                int bmpHeight=bmp.getHeight();
                float newWidth=badge.getWidth()/2;
                float newHeight=badge.getHeight()/2;
                Matrix matrix=new Matrix();
                matrix.postScale(newWidth/bmpWidth,newHeight/bmpHeight);
                bmp=Bitmap.createBitmap(bmp,0,0,bmpWidth,bmpHeight,matrix,true);
                canvas.drawBitmap(bmp,badge.getWidth()-bmp.getWidth()*6/5,badge.getHeight()/4,null);
                canvas.drawBitmap(badge,0,0,null);

                FileOutputStream fos = new FileOutputStream(file);
                bmpcombine.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                fos.flush();
                fos.close();

                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("image/jpeg");
                shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(file.toString()));
                startActivity(Intent.createChooser(shareIntent,"share"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }





        }
        else if(v.getId()==R.id.practice_badge){

            Intent intent = new  Intent(this, ChooseTypePage.class);
            startActivity(intent);

        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void ReadImage(){
        SharedPreferences storeinform=getSharedPreferences("num", Context.MODE_PRIVATE);
        photo_name="pic"+storeinform.getString("right",null).substring(0,storeinform.getString("right",null).length()-2);
        try{
            FileInputStream fin=openFileInput(photo_name+".jpg");
            photo=fin;
            ImageView characterbadge=findViewById(R.id.characterBadgeView);
            bmp = BitmapFactory.decodeStream(fin);
            characterbadge.setImageBitmap(bmp);
            fin.close();

        }
        catch(Exception e){
            e.printStackTrace();
            ImageView characterbadge=findViewById(R.id.characterBadgeView);
            characterbadge.setBackground(getDrawable(R.drawable.space));
        }




    }
}
