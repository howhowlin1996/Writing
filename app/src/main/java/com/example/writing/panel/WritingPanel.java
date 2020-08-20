package com.example.writing.panel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.writing.R;
import com.example.writing.badge.Badge;
import com.example.writing.memo.MemoEditPic;
import com.example.writing.puzzle.Puzzle;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;


public class WritingPanel extends AppCompatActivity implements View.OnClickListener {
    //Panel mPanel;
     long lastTime =0;
    Set<String> defaultSet=new TreeSet<String>();
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);                                         //import layout.xml
        WritingPanelGroup group=findViewById(R.id.group_writing);
        SharedPreferences storeinform=getSharedPreferences("num", Context.MODE_PRIVATE);
        int answer_position=storeinform.getInt("answer_position",0);
        getSupportActionBar().hide(); //隱藏標題
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN); //隱藏狀態
        final Button saveButton = findViewById(R.id.SaveButton);                 //get two button id in layout.xml saveButton and deleteButton
        final Button deleteButton= findViewById(R.id.DeleteButton);
        final Panel mPanel =findViewById(R.id.panel);                            //get the panel id in layout.xml
        final Button helpButton=findViewById(R.id.help_writing);
        final Button memo=findViewById(R.id.memo_writing);
        ImageView charimage=findViewById(R.id.characterQleft_writing);
        Panel charpanel=findViewById(R.id.panel);
        ImageView phoimage=findViewById(R.id.phoneticleftQ_writing);
        ImageView phopanel=findViewById(R.id.phoneticQright_writing);
        ImageView charlast=findViewById(R.id.characterQlast_writing);
        ImageView pholast=findViewById(R.id.phoneticQlast_writing);
        String rightString=storeinform.getString("right",null);
        String leftString =storeinform.getString("left",null);
        String middleString=storeinform.getString("middle",null);
        Resources here_r=this.getResources();
        if(middleString.equals("0")){
            group.setType(0,answer_position);
            group.invalidate();
            charlast.setVisibility(View.GONE);
            pholast.setVisibility(View.GONE);
            if(answer_position==0){
                charpanel.setBackground(getDrawable(R.drawable.block));
                phopanel.setImageResource(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()));
                charimage.setImageResource(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName()));
                phoimage.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));
            }
            else{

                charimage.setImageResource(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()));
                phoimage.setImageResource(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()));
                charpanel.setBackground(getDrawable(R.drawable.block));
                phopanel.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));

            }

        }
        else {
            group.setType(1,answer_position);
            group.invalidate();
            if (answer_position==0||answer_position==10||answer_position==20||answer_position==210){
                charpanel.setBackground(getDrawable(R.drawable.block));
                phopanel.setImageResource(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()));
                if (answer_position==10){
                    charimage.setBackground(getDrawable(R.drawable.white));
                    phoimage.setImageResource(here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName()));
                    charlast.setImageResource(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName()));
                    pholast.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));

                }
                else if(answer_position==20){
                    charimage.setImageResource(here_r.getIdentifier("cha"+middleString,"drawable",this.getPackageName()));
                    phoimage.setImageResource(here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName()));
                    charlast.setBackground(getDrawable(R.drawable.white));
                    pholast.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));

                }
                else if(answer_position==210){
                    charimage.setBackground(getDrawable(R.drawable.white));
                    phoimage.setImageResource(here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName()));
                    charlast.setBackground(getDrawable(R.drawable.white));
                    pholast.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));

                }
                else {
                    charimage.setImageResource(here_r.getIdentifier("cha"+middleString,"drawable",this.getPackageName()));
                    phoimage.setImageResource(here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName()));
                    charlast.setImageResource(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName()));
                    pholast.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));
                }

            }
            else if(answer_position==1||answer_position==21){
                if (answer_position==1){
                    charimage.setImageResource(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()));
                    phoimage.setImageResource(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()));
                    charlast.setImageResource(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName()));
                    pholast.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));

                }
                else {
                    charimage.setImageResource(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()));
                    phoimage.setImageResource(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()));
                    charlast.setBackground(getDrawable(R.drawable.white));
                    pholast.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));

                }
                charpanel.setBackground(getDrawable(R.drawable.block));
                phopanel.setImageResource(here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName()));

            }
            else if(answer_position==2){
                charimage.setImageResource(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()));
                phoimage.setImageResource(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()));
                charlast.setImageResource(here_r.getIdentifier("cha"+middleString,"drawable",this.getPackageName()));
                pholast.setImageResource(here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName()));
                charpanel.setBackground(getDrawable(R.drawable.block));
                phopanel.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));

            }

        }

        saveButton.setOnClickListener(this);                                     // if user click the button call function Onclick
        deleteButton.setOnClickListener(this);
        helpButton.setOnClickListener(this);
        memo.setOnClickListener(this);
        mPanel.setBackground(getDrawable(R.drawable.space));


    }
    @Override
    protected void onResume() {
        super.onResume();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN); //隱藏狀態
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        final Panel mPanel =findViewById(R.id.panel);
        long newTime = System.currentTimeMillis();                              //set time limit to avoid users hitting the button too many times in a short period
        if(v.getId()==R.id.help_writing){
            Intent intent = new  Intent(this, Puzzle.class);
            startActivity(intent);
            this.finish();

        }
        if(v.getId()==R.id.memo_writing){
            Intent intent = new  Intent(this, MemoEditPic.class);
            startActivity(intent);

        }

        if(newTime-lastTime>1000){

            if (v.getId()==R.id.SaveButton){                                    // distinct which the button hit by users
                /*if (mPanel.getBackground().getConstantState().equals(getDrawable(R.drawable.space).getConstantState())&&lastTime!=0){
                    mPanel.resetCanvas();
                    mPanel.setBackground(getDrawable(R.drawable.space));
                    return;
                }*/
                savePicture();
                Toast.makeText(WritingPanel.this,"儲存完畢",Toast.LENGTH_LONG).show();
                Intent intent = new  Intent(this, Badge.class);
                String key_name=getSharedPreferences("num",0).getStringSet("chartypenum",defaultSet).iterator().next();
                int num;
                num=getSharedPreferences("num",0).getInt(key_name,0);
                getSharedPreferences("num",0).edit().putInt(key_name,num-1).commit();
                startActivity(intent);

            }
            else if (v.getId()==R.id.DeleteButton){
                mPanel.resetCanvas();
                mPanel.setBackground(getDrawable(R.drawable.space));
            }
            lastTime=newTime;
        }
        else{
            Toast.makeText(WritingPanel.this,"停",Toast.LENGTH_LONG).show();
        }


    }


    public  void savePicture (){
        final Panel mPanel =findViewById(R.id.panel);
        SharedPreferences storeinform=getSharedPreferences("num", Context.MODE_PRIVATE);
        String photo_name="pic"+storeinform.getString("right",null).substring(0,storeinform.getString("right",null).length()-2);
        try {
            FileOutputStream fos = openFileOutput( photo_name+".jpg",Context.MODE_PRIVATE);
            mPanel.vBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}