package com.example.writing.coosetype;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.writing.R;
import com.example.writing.panel.CopyWriting;
import com.example.writing.panel.WritingPanel;
import com.example.writing.puzzle.Puzzle;

public class InOutPage extends AppCompatActivity implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inoutchoose);
        getSupportActionBar().hide(); //隱藏標題
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN); //隱藏狀態
        Button  rightup=findViewById(R.id.rightup);
        Button  rightdown=findViewById(R.id.rightdown);
        Button  rightmiddle=findViewById(R.id.rightmiddle);
        Button  middledown=findViewById(R.id.middledown);
        Button  middlemiddle=findViewById(R.id.middlemiddle);
        Button  leftdown=findViewById(R.id.leftdown);
        ImageView charLeft=findViewById(R.id.characterQleft_inout);
        ImageView charRight=findViewById(R.id.characterQright_inout);
        ImageView phoLeft=findViewById(R.id.phoneticQleft_inout);
        ImageView phoRight=findViewById(R.id.phoneticQright_inout);
        ImageView charMiddle=findViewById(R.id.characterQmiddle_inout);
        ImageView phoMiddle=findViewById(R.id.phoneticQmiddle_inout);
        SharedPreferences storeinform=getSharedPreferences("num", Context.MODE_PRIVATE);
        String rightString=storeinform.getString("right",null);
        String leftString =storeinform.getString("left",null);
        String middleString=storeinform.getString("middle",null);
        int answer_position=storeinform.getInt("answer_position",0);
        Resources here_r=this.getResources();
        if(middleString.equals("0")){
            charMiddle.setVisibility(View.GONE);
            phoMiddle.setVisibility(View.GONE);
            if(answer_position==0){
                //charLeft.setImageResource(R.drawable.white);
                phoLeft.setImageResource(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()));
                charRight.setImageResource(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName()));
                phoRight.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));
            }
            else{

                charLeft.setImageResource(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()));
                phoLeft.setImageResource(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()));
                //charRight.setImageResource(R.drawable.white);
                phoRight.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));

            }

        }
        else {
              if (answer_position/10==0){
                  if (answer_position==0){
                      //charLeft.setImageResource(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()));
                      charMiddle.setImageResource(here_r.getIdentifier("cha"+middleString,"drawable",this.getPackageName()));
                      charRight.setImageResource(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName()));
                      phoLeft.setImageResource(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()));
                      phoRight.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));
                      phoMiddle.setImageResource(here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName()));
                  }
                  else if (answer_position==1){
                      charLeft.setImageResource(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()));
                      //charMiddle.setImageResource(here_r.getIdentifier("cha"+middleString,"drawable",this.getPackageName()));
                      charRight.setImageResource(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName()));
                      phoLeft.setImageResource(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()));
                      phoRight.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));
                      phoMiddle.setImageResource(here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName()));

                  }
                  else{
                      charLeft.setImageResource(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()));
                      charMiddle.setImageResource(here_r.getIdentifier("cha"+middleString,"drawable",this.getPackageName()));
                      //charRight.setImageResource(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName()));
                      phoLeft.setImageResource(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()));
                      phoRight.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));
                      phoMiddle.setImageResource(here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName()));

                  }

              }
              else  if(answer_position/100==0){
                  if(answer_position==21){
                      charLeft.setImageResource(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()));
                      //charMiddle.setImageResource(here_r.getIdentifier("cha"+middleString,"drawable",this.getPackageName()));
                      //charRight.setImageResource(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName()));
                      phoLeft.setImageResource(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()));
                      phoRight.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));
                      phoMiddle.setImageResource(here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName()));

                  }
                  else  if(answer_position==20){
                      //charLeft.setImageResource(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()));
                      charMiddle.setImageResource(here_r.getIdentifier("cha"+middleString,"drawable",this.getPackageName()));
                      //charRight.setImageResource(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName()));
                      phoLeft.setImageResource(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()));
                      phoRight.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));
                      phoMiddle.setImageResource(here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName()));

                  }
                  else if(answer_position==10){
                      //charLeft.setImageResource(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()));
                      //charMiddle.setImageResource(here_r.getIdentifier("cha"+middleString,"drawable",this.getPackageName()));
                      charRight.setImageResource(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName()));
                      phoLeft.setImageResource(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()));
                      phoRight.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));
                      phoMiddle.setImageResource(here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName()));

                  }

              }
              else {
                  phoLeft.setImageResource(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()));
                  phoRight.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));
                  phoMiddle.setImageResource(here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName()));

              }



        }
        rightup.setOnClickListener(this);
        rightdown.setOnClickListener(this);
        rightmiddle.setOnClickListener(this);
        middledown.setOnClickListener(this);
        middlemiddle.setOnClickListener(this);
        leftdown.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        Intent intent =new Intent(getBaseContext(),ChooseResult .class);
        SharedPreferences num=getSharedPreferences("num", Context.MODE_PRIVATE);
        int ID =v.getId();
        if(ID==R.id.rightup){
            //Log.d("error","rightup");
            num.edit().putInt("split_code",51).commit();
        }
        else if(ID==R.id.rightdown){
            //Log.d("error","rightdown");
            num.edit().putInt("split_code",52).commit();
        }
        else if(ID==R.id.rightmiddle){
            //Log.d("error","rightmiddle");
            num.edit().putInt("split_code",53).commit();
        }
        else if(ID==R.id.middlemiddle){
            //Log.d("error","middlemiddle");
            num.edit().putInt("split_code",54).commit();

        }
        else if(ID==R.id.middledown){
            //Log.d("error","middledown");
            num.edit().putInt("split_code",55).commit();
        }
        else if(ID==R.id.leftdown){
            //Log.d("error","leftdown");
            num.edit().putInt("split_code",56).commit();
        }
        startActivity(intent);

    }
}
