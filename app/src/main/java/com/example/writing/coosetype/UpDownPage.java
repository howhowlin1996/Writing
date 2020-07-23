package com.example.writing.coosetype;
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
import com.example.writing.panel.CopyWriting;
import com.example.writing.panel.WritingPanel;
import com.example.writing.puzzle.Puzzle;

public class UpDownPage extends AppCompatActivity implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updownchoose);
        getSupportActionBar().hide(); //隱藏標題
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN); //隱藏狀態
        Button updown=findViewById(R.id.updown);
        Button leftright2=findViewById(R.id.leftright2_updown);
        Button leftright3=findViewById(R.id.leftright3_updown);
        Button updown3=findViewById(R.id.updown3);
        ImageView charLeft=findViewById(R.id.characterQleft_updown);
        ImageView charRight=findViewById(R.id.characterQright_updown);
        ImageView phoLeft=findViewById(R.id.phoneticQleft_updown);
        ImageView phoRight=findViewById(R.id.phoneticQright_updown);
        SharedPreferences storeinform=getSharedPreferences("num", Context.MODE_PRIVATE);
        String rightString=storeinform.getString("right",null);
        String leftString =storeinform.getString("left",null);
        String middleString=storeinform.getString("middle",null);
        int answer_position=storeinform.getInt("answer_position",0);
        Resources here_r=this.getResources();
        if(middleString.equals("0")){
            if(answer_position==0){
                charLeft.setImageResource(R.drawable.white);
                phoLeft.setImageResource(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()));
                charRight.setImageResource(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName()));
                phoRight.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));
            }
            else{

                charLeft.setImageResource(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()));
                phoLeft.setImageResource(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()));
                charRight.setImageResource(R.drawable.white);
                phoRight.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));

            }

        }
        else {

        }
        updown.setOnClickListener(this);
        updown3.setOnClickListener(this);
        leftright2.setOnClickListener(this);
        leftright3.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        int num=0;
        if(v.getId()==R.id.updown){
            num=21;
        }
        else if(v.getId()==R.id.updown3){
            num=22;
        }
        else if(v.getId()==R.id.leftright2_updown){
           num=31;
        }
        else if(v.getId()==R.id.leftright3_updown){
           num=32;
        }




        Intent intent =new Intent(getBaseContext(), ChooseResult.class);
        intent.putExtra("num",num);
        startActivity(intent);

    }
}
