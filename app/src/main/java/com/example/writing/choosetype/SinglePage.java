package com.example.writing.choosetype;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.writing.R;

public class SinglePage extends AppCompatActivity implements View.OnClickListener {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singlechoose);
        getSupportActionBar().hide(); //隱藏標題
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN); //隱藏狀態
        Button single=findViewById(R.id.single);
        SharedPreferences storeinform=getSharedPreferences("num", Context.MODE_PRIVATE);
        ImageView charLeft=findViewById(R.id.characterQleft_single);
        ImageView charRight=findViewById(R.id.characterQright_single);
        ImageView phoLeft=findViewById(R.id.phoneticQleft_single);
        ImageView phoRight=findViewById(R.id.phoneticQright_single);
        ImageView charMiddle=findViewById(R.id.characterQmiddle_single);
        ImageView phoMiddle=findViewById(R.id.phoneticQmiddle_single);
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

        single.setOnClickListener(this);
        }





    @Override
    public void onClick(View v) {

        SharedPreferences num=getSharedPreferences("num", Context.MODE_PRIVATE);
       if(v.getId()==R.id.single){
           num.edit().putInt("split_code",11).commit();
       }

        Intent intent =new Intent(getBaseContext(), ChooseResult.class);
        startActivity(intent);

    }
}