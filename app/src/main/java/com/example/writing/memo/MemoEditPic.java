package com.example.writing.memo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.util.Pair;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.writing.R;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class MemoEditPic extends AppCompatActivity implements View.OnClickListener{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memo_editpicture);
        Button  complete=findViewById(R.id.complete_editpic);
        Button return_but=findViewById(R.id.delete_editpic);
        ImageButton blackpen=findViewById(R.id.blackPen_memo);
        ImageButton bluepen=findViewById(R.id.bluePen_memo);
        ImageButton redpen=findViewById(R.id.redPen_memo);
        ImageButton greenpen=findViewById(R.id.greenPen_memo);
        ImageButton erase=findViewById(R.id.eraser_memo);
        EditText text =findViewById(R.id.editText_memo);
        complete.setOnClickListener(this);
        return_but.setOnClickListener(this);
        blackpen.setOnClickListener(this);
        bluepen.setOnClickListener(this);
        redpen.setOnClickListener(this);
        greenpen.setOnClickListener(this);
        erase.setOnClickListener(this);
        text.setSingleLine(false);
        text.setHorizontallyScrolling(false);
        ReadImage();


    }

    @Override
    public void onClick(View v) {
        M_Panel m_panel=findViewById(R.id.m_Panel);
        EditText text =findViewById(R.id.editText_memo);
        if(v.getId()==R.id.complete_editpic){

            m_panel.setText( text);
            Save(m_panel.vBitmap);
            Intent intent =new Intent(this,MemoLookUp.class);
            startActivity(intent);
        }
        else if(v.getId()==R.id.delete_editpic){
            m_panel.clearPanel();
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

    public void ReadImage(){
        try{
            FileInputStream fin=openFileInput("P1234.jpg");
            M_Panel m_panel=findViewById(R.id.m_Panel);
            Bitmap bitmap = BitmapFactory.decodeStream(fin);
            m_panel.setvBitmap(bitmap);
            //Toast.makeText(this,"hERE",Toast.LENGTH_LONG).show();
            fin.close();

        }
        catch(Exception e){
            e.printStackTrace();
            M_Panel m_panel=findViewById(R.id.m_Panel);
            m_panel.originSetvBitmap();
            //Toast.makeText(this,"hERE",Toast.LENGTH_LONG).show();
        }



    }


}
