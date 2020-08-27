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

public class LookWriting extends AppCompatActivity implements View.OnClickListener {
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lookwriting);                                         //import layout.xml
        getSupportActionBar().hide(); //隱藏標題
        LookWritingGroup group=findViewById(R.id.group_lookwriting);
        SharedPreferences storeinform=getSharedPreferences("num", Context.MODE_PRIVATE);
        int answer_position=storeinform.getInt("answer_position",0);
        Button confirm=findViewById(R.id.confirm_lookwriting);
        Button delete=findViewById(R.id.delete_lookwriting);
        Panel panel=findViewById(R.id.rightDownA_lookwriting);
        panel.setBackground(getDrawable(R.drawable.white));

        ImageView leftup=findViewById(R.id.leftUpQ_lookwriting);
        ImageView leftdown=findViewById(R.id.leftDownQ_lookwriting);
        ImageView charimage=findViewById(R.id.rightUpA_lookwriting);
        ImageView lastleft=findViewById(R.id.leftLast_lookwriting);
        ImageView lastright =findViewById(R.id.rightLast_lookwriting);
        String rightString=storeinform.getString("right",null);
        String leftString =storeinform.getString("left",null);
        String middleString=storeinform.getString("middle",null);
        Resources here_r=this.getResources();
        if(middleString.equals("0")){
            group.setType(0,answer_position);
            group.invalidate();
            if(answer_position==0){
                leftup.setImageResource(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()));
                leftdown.setImageResource(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName()));
                charimage.setImageResource(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName()));

            }
            else{
                leftup.setImageResource(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()));
                leftdown.setImageResource(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName()));
                charimage.setImageResource(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()));
            }

        }
        else {
            group.setType(1, answer_position);
            if (answer_position == 0 || answer_position == 10 || answer_position == 20 || answer_position == 210) {
                leftup.setImageResource(here_r.getIdentifier("cha" + leftString, "drawable", this.getPackageName()));
                leftdown.setImageResource(here_r.getIdentifier("cha" + middleString, "drawable", this.getPackageName()));
                //charimage.setImageResource(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName()));
                if (answer_position == 10) {
                    charimage.setBackground(getDrawable(R.drawable.white));
                    lastleft.setImageResource(here_r.getIdentifier("cha" + rightString, "drawable", this.getPackageName()));
                    lastright.setImageResource(here_r.getIdentifier("cha" + rightString, "drawable", this.getPackageName()));
                } else if (answer_position == 20) {
                    charimage.setImageResource(here_r.getIdentifier("cha" + middleString, "drawable", this.getPackageName()));
                    lastleft.setImageResource(here_r.getIdentifier("cha" + rightString, "drawable", this.getPackageName()));
                    lastright.setBackground(getDrawable(R.drawable.white));
                } else if (answer_position == 210) {
                    charimage.setBackground(getDrawable(R.drawable.white));
                    ;
                    lastleft.setImageResource(here_r.getIdentifier("cha" + rightString, "drawable", this.getPackageName()));
                    lastright.setBackground(getDrawable(R.drawable.white));

                } else {
                    charimage.setImageResource(here_r.getIdentifier("cha" + middleString, "drawable", this.getPackageName()));
                    lastleft.setImageResource(here_r.getIdentifier("cha" + rightString, "drawable", this.getPackageName()));
                    lastright.setImageResource(here_r.getIdentifier("cha" + rightString, "drawable", this.getPackageName()));

                }


            }
            else if (answer_position == 1 || answer_position == 21) {
                leftup.setImageResource(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()));
                leftdown.setImageResource(here_r.getIdentifier("cha"+middleString,"drawable",this.getPackageName()));
                charimage.setImageResource(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()));
                if (answer_position == 1) {
                    lastleft.setImageResource(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName()));
                    lastright.setImageResource(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName()));

                }
                else {
                    lastleft.setBackground(getDrawable(R.drawable.white));
                    lastright.setBackground(getDrawable(R.drawable.white));


                }


            }
            else if(answer_position==2){
                leftup.setImageResource(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()));
                leftdown.setImageResource(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName()));
                charimage.setImageResource(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()));
                lastleft.setImageResource(here_r.getIdentifier("cha"+middleString,"drawable",this.getPackageName()));
                lastright.setImageResource(here_r.getIdentifier("cha"+middleString,"drawable",this.getPackageName()));


            }
        }



        confirm.setOnClickListener(this);
        delete.setOnClickListener(this);
    }
    @Override
    protected void onResume() {
        super.onResume();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN); //隱藏狀態
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.confirm_lookwriting)
        {
            Intent intent =new Intent(getBaseContext(), CopyWriting.class);
            startActivity(intent);
        }
        else{
            Panel panel=findViewById(R.id.rightDownA_lookwriting);
            panel.resetCanvas();

        }


    }
}
