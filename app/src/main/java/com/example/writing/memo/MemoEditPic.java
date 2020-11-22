package com.example.writing.memo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import static java.lang.StrictMath.abs;

import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Constraints;

import com.example.writing.R;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MemoEditPic extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener{
    private float begin_y,move_y,down_y,height;
    private long final_time=0;
    private int decision=0,top,bottom,old_top,old_bottom;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memo_editpicture);
        getSupportActionBar().hide(); //隱藏標題
        final Button  complete=findViewById(R.id.complete_editpic);
        final Button delete_but=findViewById(R.id.delete_editpic);
        final ImageView blackpen=findViewById(R.id.blackpen_memo);
        final ImageView bluepen=findViewById(R.id.bluepen_memo);
        final ImageView redpen=findViewById(R.id.redpen_memo);
        final ImageView erase=findViewById(R.id.eraser_memo);
        final ImageView pinin =findViewById(R.id.pinin_memo);
        final ImageView yes=findViewById(R.id.yes_memo);
        final ImageView return_but=findViewById(R.id.return_memo);
        final EditText text =findViewById(R.id.editText_memo);
        complete.setVisibility(View.GONE);
        delete_but.setVisibility(View.GONE);
        text.setVisibility(View.GONE);
        complete.setOnClickListener(this);
        delete_but.setOnClickListener(this);
        blackpen.setOnClickListener(this);
        bluepen.setOnClickListener(this);
        redpen.setOnClickListener(this);
        erase.setOnClickListener(this);
        pinin.setOnClickListener(this);
        text.setOnClickListener(this);
        yes.setOnClickListener(this);
        return_but.setOnClickListener(this);
        ReadImage();





    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        final M_Panel m_panel=findViewById(R.id.m_Panel_memo);
        final EditText text =findViewById(R.id.editText_memo);
        final Button  complete=findViewById(R.id.complete_editpic);
        final Button delete_but=findViewById(R.id.delete_editpic);
        final ImageView yes=findViewById(R.id.yes_memo);
        final ImageView return_but=findViewById(R.id.return_memo);
        if(v.getId()==R.id.yes_memo){
            Save(m_panel.vBitmap);
            Intent intent =new Intent(this,MemoLookUp.class);
            startActivity(intent);
        }
        else if(v.getId()==R.id.return_memo){
            m_panel.clearPanel();
        }
        else if(v.getId()==R.id.blackpen_memo){
            m_panel.changeColor(Color.BLACK);
        }
        else if(v.getId()==R.id.bluepen_memo){
            m_panel.changeColor(Color.BLUE);
        }
        else if(v.getId()==R.id.redpen_memo){
            m_panel.changeColor(Color.RED);
        }

        else if(v.getId()==R.id.eraser_memo){
            m_panel.changeColor(Color.WHITE);
        }
        else if (v.getId()==R.id.pinin_memo){
              text.setVisibility(View.VISIBLE);
              text.requestFocus();
              InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
              imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
              complete.setVisibility(View.VISIBLE);
              delete_but.setVisibility(View.VISIBLE);
              yes.setVisibility(View.GONE);
              return_but.setVisibility(View.GONE);
        }
        else if (v.getId()==R.id.complete_editpic){
            Save(m_panel.vBitmap);
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(0, 0);
            Intent intent =new Intent(this,MemoPosition.class);
            intent.putExtra("text",text.getText());
            startActivity(intent);

        }
        else if (v.getId()==R.id.delete_editpic){
            text.requestFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

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
            M_Panel m_panel=findViewById(R.id.m_Panel_memo);
            Bitmap bitmap = BitmapFactory.decodeStream(fin);
            m_panel.setvBitmap(bitmap);
            height=m_panel.vBitmapCanvas.getHeight();
            fin.close();
        }
        catch(Exception e){
            e.printStackTrace();
            M_Panel m_panel=findViewById(R.id.m_Panel_memo);
            m_panel.originSetvBitmap();
            height=m_panel.vBitmapCanvas.getHeight();
        }

    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
            return true;
    }


}
