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
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.example.writing.R;
import com.example.writing.badgefactory.BadgeFactory;
import com.example.writing.choosetype.ChooseTypePage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.Set;
import java.util.TreeSet;


public class Badge extends AppCompatActivity implements View.OnClickListener {
    FileInputStream photo;
    String photo_name;
    Bitmap bmp;
    Bitmap badge;
    File combinefile;
    Set<String> defaultSet=new TreeSet<String>();

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.badge);
        getSupportActionBar().hide(); //隱藏標題
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN); //隱藏狀態
        Button factory=findViewById(R.id.badgeFactory_badge);
        Button share=findViewById(R.id.share_badge);
        Button practice=findViewById(R.id.practice_badge);
        ReadImage();
        factory.setOnClickListener(this);
        share.setOnClickListener(this);
        practice.setOnClickListener(this);

    }
    @Override
    protected void onResume() {
        super.onResume();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN); //隱藏狀態
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {

         if(v.getId()==R.id.badgeFactory_badge){
            Intent intent = new  Intent(this, BadgeFactory.class);
            startActivity(intent);

        }
        else if(v.getId()==R.id.share_badge){
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("image/jpeg");
           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
               Uri uri=FileProvider.getUriForFile(getBaseContext(),"com.example.writing.fileprovider",combinefile);
             shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
             shareIntent.putExtra(Intent.EXTRA_STREAM,uri);
             shareIntent.setType("image/jpeg");


             } else {

               shareIntent.putExtra(Intent.EXTRA_STREAM,Uri.fromFile(combinefile));
               shareIntent.setType("image/jpeg");
               shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
             }

             startActivity(Intent.createChooser(shareIntent,"share"));

        }
        else if(v.getId()==R.id.practice_badge){

            Intent intent = new  Intent(this, ChooseTypePage.class);
            startActivity(intent);

        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void ReadImage(){
        final ImageView badgeView=findViewById(R.id.badgeView);
        SharedPreferences storeinform=getSharedPreferences("num", Context.MODE_PRIVATE);
        photo_name="pic"+storeinform.getString("right",null).substring(0,storeinform.getString("right",null).length()-2);
        try{
            FileInputStream fin=openFileInput(photo_name+".jpg");
            photo=fin;
            bmp = BitmapFactory.decodeStream(fin);
            fin.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        File appDir = new File(Environment.getExternalStorageDirectory(), "Writing");
        if (!appDir.exists()) {
            Toast.makeText(this,appDir.toString(),Toast.LENGTH_SHORT);
            appDir.mkdir();
        }

        String fileName = "badge"+photo_name+ ".jpg";
        File file = new File(appDir, fileName);
        DBselect(file);
        Bitmap  bmpcombine=Bitmap.createBitmap(badge.getWidth(),badge.getHeight(),bmp.getConfig());
        try {
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

        } catch (FileNotFoundException e) {
            Log.d("hahaha","no file");
            e.printStackTrace();
        } catch (IOException e) {
            Log.d("hahaha","error");
            e.printStackTrace();
        }
        combinefile=file;

        badgeView.setImageBitmap(bmpcombine);






    }

    public  void DBselect(File file){
        DataHelper dbBadge=new DataHelper(this,"0000"+".db",null,1,"21");
        int time;
        time=dbBadge.practiceTime(file.toString());

        if (time==0){
            badge=BitmapFactory.decodeResource(this.getBaseContext().getResources(), R.drawable.badge);
            dbBadge.insert(file.toString(),1);
        }
        else if (time==1){
            badge=BitmapFactory.decodeResource(this.getBaseContext().getResources(), R.drawable.badge_2);
            dbBadge.update(file.toString(),2);
        }
        else{
            badge=BitmapFactory.decodeResource(this.getBaseContext().getResources(), R.drawable.badge_3);
            dbBadge.update(file.toString(),3);
        }
        dbBadge.close();

    }
}
