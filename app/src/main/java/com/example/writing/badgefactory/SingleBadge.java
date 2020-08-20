package com.example.writing.badgefactory;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.writing.R;

import java.io.File;
import java.io.FileInputStream;

import main.FirstScene;

public class SingleBadge extends AppCompatActivity implements View.OnClickListener {
    File combinefile;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singlebadge);
        getSupportActionBar().hide(); //隱藏標題
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN); //隱藏狀態
        ImageView badgeBackground=findViewById(R.id.badge_singleBadge);
        Button returnButton=findViewById(R.id.returnButton_singleBadge);
        Button shareButton=findViewById(R.id.shareButton_singleBadge);
        int time=this.getIntent().getExtras().getInt("background");

        DataHelper dbBadge=new DataHelper(this,"0000"+".db",null,1,"21");
        Cursor file_position=dbBadge.getFileName();
        Bitmap bmp=null;
        String photo;
        for (int i=0;i<time;i++){

            file_position.moveToNext();

        }
        photo=file_position.getString(0);
        File file=new File(photo);
        combinefile=file;
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
            e.printStackTrace();
        }

        badgeBackground.setImageBitmap(bmp);
        returnButton.setOnClickListener(this);
        shareButton.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN); //隱藏狀態
    }

    @Override
    public void onClick(View v) {

        if (v.getId()==R.id.returnButton_singleBadge){
           Intent intent=new Intent(this, FirstScene.class);
           startActivity(intent);
        }
        else{
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("image/jpeg");
            shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(combinefile.toString()));
            startActivity(Intent.createChooser(shareIntent,"share"));




        }
    }
}
