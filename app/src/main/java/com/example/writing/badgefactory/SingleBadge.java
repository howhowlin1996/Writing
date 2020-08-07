package com.example.writing.badgefactory;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
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

public class SingleBadge extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singlebadge);
        ImageView badgeBackground=findViewById(R.id.badge_singleBadge);
        Button returnButton=findViewById(R.id.returnButton_singleBadge);
        Button shareButton=findViewById(R.id.shareButton_singleBadge);
        String photot_here=this.getIntent().getExtras().getString("background");
        Bitmap bmp=null;
        File file=new File(photot_here);
        try{
            FileInputStream fin=new  FileInputStream(file);
            //Log.d("HERE",file_position.getString(0));
            bmp = BitmapFactory.decodeStream(fin);
            int bmpWidth=bmp.getWidth();
            int bmpHeight=bmp.getHeight();
            Matrix matrix=new Matrix();
            matrix.setScale(0.5f,0.5f);
            bmp= Bitmap.createBitmap(bmp,0,0,bmpWidth,bmpHeight,matrix,true);

            fin.close();

        }
        catch(Exception e){
            Log.d("HERE","file_position.getString(0)");
            e.printStackTrace();
        }
        badgeBackground.setImageBitmap(bmp);
        returnButton.setOnClickListener(this);
        shareButton.setOnClickListener(this);
        //badgeBackground.setBackground(backgroundNum);
    }

    @Override
    public void onClick(View v) {

        if (v.getId()==R.id.returnButton_singleBadge){
            onBackPressed();
        }
        else{

        }
    }
}
