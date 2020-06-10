package com.example.writing.memo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

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
        complete.setOnClickListener(this);
        return_but.setOnClickListener(this);
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
