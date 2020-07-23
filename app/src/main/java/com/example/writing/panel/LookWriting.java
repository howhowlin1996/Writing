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
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN); //隱藏狀態
        LookWritingGroup group=findViewById(R.id.group_lookwriting);
        SharedPreferences storeinform=getSharedPreferences("num", Context.MODE_PRIVATE);
        int answer_position=storeinform.getInt("answer_position",0);
        group.setType(answer_position);
        group.invalidate();
        Button confirm=findViewById(R.id.confirm_lookwriting);
        Button delete=findViewById(R.id.delete_lookwriting);
        Panel panel=findViewById(R.id.rightDownA_lookwriting);
        panel.setBackground(getDrawable(R.drawable.white));

        ImageView leftup=findViewById(R.id.leftUpQ_lookwriting);
        ImageView leftdown=findViewById(R.id.leftDownQ_lookwriting);
        ImageView charimage=findViewById(R.id.rightUpA_lookwriting);
        String rightString=storeinform.getString("right",null);
        String leftString =storeinform.getString("left",null);
        String middleString=storeinform.getString("middle",null);
        Resources here_r=this.getResources();
        if(middleString.equals("0")){
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

        }

        confirm.setOnClickListener(this);
        delete.setOnClickListener(this);
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
            panel.setBackground(getDrawable(R.drawable.white));

        }


    }
}
