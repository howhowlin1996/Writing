package com.example.writing.panel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.writing.R;

public class CopyWriting extends AppCompatActivity implements View.OnClickListener {
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.copywriting);
        getSupportActionBar().hide(); //隱藏標題
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN); //隱藏狀態
        CopyWritingGroup group=findViewById(R.id.group_copywriting);
        SharedPreferences storeinform=getSharedPreferences("num", Context.MODE_PRIVATE);
        int answer_position=storeinform.getInt("answer_position",0);

        Panel mypanel=findViewById(R.id.panel_copy);
        Button confirm=findViewById(R.id.SaveButton_copy);
        Button delete=findViewById(R.id.DeleteButton_copy);


        ImageView charleft=findViewById(R.id.characterQleft_copywriting);
        ImageView pholeft=findViewById(R.id.phoneticQleft_copywriting);
        ImageView phoright=findViewById(R.id.phoneticQright_copywriting);
        ImageView charmiddle=findViewById(R.id.characterQmiddle_copywriting);
        ImageView phomiddle=findViewById(R.id.phoneticQmiddle_copywriting);
        String rightString=storeinform.getString("right",null);
        String leftString =storeinform.getString("left",null);
        String middleString=storeinform.getString("middle",null);
        Resources here_r=this.getResources();
        if(middleString.equals("0")){
            group.setType(0,answer_position);
            group.invalidate();
            if(answer_position==0){
                mypanel.setBackground(getDrawable(R.drawable.block));
                pholeft.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));
                charleft.setImageResource(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName()));
                phoright.setImageResource(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()));
            }
            else{

                charleft.setImageResource(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()));
                pholeft.setImageResource(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()));
                mypanel.setBackground(getDrawable(R.drawable.block));
                phoright.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));

            }

        }
        else {
            group.setType(1,answer_position);
            group.invalidate();
            if (answer_position==0||answer_position==10||answer_position==20||answer_position==210){
                mypanel.setBackground(getDrawable(R.drawable.block));
                phoright.setImageResource(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()));
                if (answer_position==10){
                    charleft.setBackground(getDrawable(R.drawable.white));
                    pholeft.setImageResource(here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName()));
                    charmiddle.setImageResource(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName()));
                    phomiddle.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));

                }
                else if(answer_position==20){
                    charleft.setImageResource(here_r.getIdentifier("cha"+middleString,"drawable",this.getPackageName()));
                    pholeft.setImageResource(here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName()));
                    charmiddle.setBackground(getDrawable(R.drawable.white));
                    phomiddle.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));

                }
                else if(answer_position==210){
                    charleft.setBackground(getDrawable(R.drawable.white));
                    pholeft.setImageResource(here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName()));
                    charmiddle.setBackground(getDrawable(R.drawable.white));
                    phomiddle.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));

                }
                else {
                    charleft.setImageResource(here_r.getIdentifier("cha"+middleString,"drawable",this.getPackageName()));
                    pholeft.setImageResource(here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName()));
                    charmiddle.setImageResource(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName()));
                    phomiddle.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));
                }

            }
            else if(answer_position==1||answer_position==21){
                if (answer_position==1){
                    charleft.setImageResource(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()));
                    pholeft.setImageResource(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()));
                    charmiddle.setImageResource(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName()));
                    phomiddle.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));

                }
                else {
                    charleft.setImageResource(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()));
                    pholeft.setImageResource(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()));
                    charmiddle.setBackground(getDrawable(R.drawable.white));
                    phomiddle.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));

                }
                mypanel.setBackground(getDrawable(R.drawable.block));
                phoright.setImageResource(here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName()));

            }
            else if(answer_position==2){
                charleft.setImageResource(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()));
                pholeft.setImageResource(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()));
                charmiddle.setImageResource(here_r.getIdentifier("cha"+middleString,"drawable",this.getPackageName()));
                phomiddle.setImageResource(here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName()));
                mypanel.setBackground(getDrawable(R.drawable.block));
                phoright.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));

            }







        }
        confirm.setOnClickListener(this);
        delete.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        Panel mypanel=findViewById(R.id.panel_copy);
        if(v.getId()==R.id.SaveButton_copy){
            Intent intent =new Intent(this,WritingPanel.class);
            intent.putExtra("num",0);
            startActivity(intent);

        }
        else if(v.getId()==R.id.DeleteButton_copy){
            mypanel.resetCanvas();
            mypanel.setBackground(getDrawable(R.drawable.white));
        }

    }
}
