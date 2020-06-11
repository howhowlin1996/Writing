package com.example.writing.memo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.writing.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class MemoEditPic extends AppCompatActivity implements View.OnClickListener {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memo_editpicture);
        Button  complete=findViewById(R.id.complete_editpic);
        Button return_but=findViewById(R.id.return_editpic);
        ImageButton blackpen=findViewById(R.id.blackPen_memo);
        ImageButton bluepen=findViewById(R.id.bluePen_memo);
        ImageButton redpen=findViewById(R.id.redPen_memo);
        ImageButton greenpen=findViewById(R.id.greenPen_memo);
        ImageButton erase=findViewById(R.id.eraser_memo);
        complete.setOnClickListener(this);
        return_but.setOnClickListener(this);
        blackpen.setOnClickListener(this);
        bluepen.setOnClickListener(this);
        redpen.setOnClickListener(this);
        greenpen.setOnClickListener(this);
        erase.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        M_Panel m_panel=findViewById(R.id.m_Panel);
        if(v.getId()==R.id.complete_editpic){
            Save(m_panel.vBitmap);
            Intent intent =new Intent(this,MemoEditText.class);
            startActivity(intent);
        }
        else if(v.getId()==R.id.return_editpic){
            onBackPressed();
        }
        else if(v.getId()==R.id.blackPen_memo){
            m_panel.changeColor(Color.BLACK);
        }
        else if(v.getId()==R.id.bluePen_memo){
            m_panel.changeColor(Color.BLUE);
        }
        else if(v.getId()==R.id.redPen_memo){
            m_panel.changeColor(Color.RED);
        }
        else if(v.getId()==R.id.greenPen_memo){
            m_panel.changeColor(Color.GREEN);
        }
        else if(v.getId()==R.id.eraser_memo){
            m_panel.changeColor(Color.WHITE);
        }

    }

    public void Save ( Bitmap bmp) {

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
