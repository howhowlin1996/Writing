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

import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.writing.R;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MemoEditPic extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener, View.OnFocusChangeListener, View.OnKeyListener {
    private float begin_y,move_y,down_y,height;
    private long final_time=0;
    private int decision=0,top,bottom,old_top,old_bottom;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memo_editpicture);
        getSupportActionBar().hide(); //隱藏標題
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
        text.setOnTouchListener(this);
        text.setBackground(getDrawable(R.drawable.block));
        text.setOnFocusChangeListener(this);
        text.setOnKeyListener(this);
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
            height=m_panel.vBitmapCanvas.getHeight();
            fin.close();

        }
        catch(Exception e){
            e.printStackTrace();
            M_Panel m_panel=findViewById(R.id.m_Panel);
            m_panel.originSetvBitmap();
            height=m_panel.vBitmapCanvas.getHeight();
        }



    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        LinearLayout here_bound=findViewById(R.id.layout_memo);
        long begin_time=System.currentTimeMillis();
        InputMethodManager imm = ((InputMethodManager)getSystemService(INPUT_METHOD_SERVICE));
        if (final_time==0){
            final_time=begin_time;
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                begin_y = event.getY();
                down_y=event.getRawY()-begin_y-25;
                //Log.d("HEREBEGINhere",new String(" "+" "+begin_y+" "+down_y+" "+v.getHeight()));
            case MotionEvent.ACTION_MOVE:
                move_y = event.getRawY() -begin_y-25;
               // Log.d("HEREBEGIN",new String(" "+" "+begin_y+" "+move_y ));

        }
         top=v.getTop()+(int)(move_y-down_y);
         bottom=v.getBottom()+(int)(move_y-down_y);


        if(abs(move_y-down_y)>30){
            final_time=begin_time;
            if(top<0){
                top=0;
                bottom=v.getHeight();

            }
            if (bottom>here_bound.getTop()){
                bottom=here_bound.getTop();
                top=bottom-v.getHeight();
            }
            v.layout(0,top,v.getWidth(),bottom);
            down_y=move_y;
            decision=0;
        }
        else{
            if(begin_time-final_time>500){
                v.requestFocus();
                imm.showSoftInput(v,0);
                v.layout(0,top,v.getWidth(),bottom);
                Log.d("TOUC",new String(" "+top+" "+bottom));
                decision=1;
                old_top=top;
                old_bottom=bottom;
            }


        }
        //Log.d("TOUC",new String(" "+final_time+" "+begin_time));
        return false;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus){
            Log.d("TOUC",new String(" "+hasFocus));
        }
        else {
            Log.d("TOUC",new String(" "+hasFocus));
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
            //隐藏键盘
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm.isActive()) {
                imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
            }
            EditText_memo here=findViewById(R.id.editText_memo);
            if (decision==1&&here.getText().toString().length()!=0){
                Log.d("HEREBEGINhere","hahhah");
                v.layout(0,old_top,v.getWidth(),old_bottom);
            }
            return true;
        }






        return false;
    }
}
