package com.example.writing.choosetype;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.writing.R;
import com.example.writing.panel.WritingPanel;

import java.util.Set;
import java.util.TreeSet;

public class ChooseResult extends AppCompatActivity implements View.OnClickListener {
    int split_code;
    Set<String> defaultSet=new TreeSet<String>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choosetype_result);
        getSupportActionBar().hide(); //隱藏標題
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN); //隱藏狀態
        TextView textView=findViewById(R.id.textView_result);
        Button confirm=findViewById(R.id.confirm_result);
        SharedPreferences num=getSharedPreferences("num", Context.MODE_PRIVATE);
        split_code=num.getInt("split_code",0);
        String key_name=getSharedPreferences("num",0).getStringSet("chartypenum",defaultSet).iterator().next();
        confirm.setOnClickListener(this);


        if (split_code==checkAnswer(key_name)){
            textView.setText("答對了");
            confirm.setText("開始寫字");
        }
        else {
            textView.setText("答錯了");
            confirm.setText("再試一次");
        }

    }

    @Override
    public void onClick(View v) {
        String key_name=getSharedPreferences("num",0).getStringSet("chartypenum",defaultSet).iterator().next();
        if (split_code==checkAnswer(key_name)){
            Intent intent =new Intent(getBaseContext(), WritingPanel.class);
            intent.putExtra("num",split_code);
            startActivity(intent);
        }
        else {
            Intent intent =new Intent(getBaseContext(), ChooseTypePage.class);
            startActivity(intent);
        }

    }



    public int checkAnswer(String type){

        int chartype=0;
        if(type.equals("Single")){
            chartype=11;
        }
        else if(type.equals("ThreeEle")){
            chartype=41;
        }
        else if (type.equals("UpDown")){
            chartype=21;
        }
        else if (type.equals("UpDown3")){
            chartype=22;
        }
        else if (type.equals("LeftRight2")){
            chartype=31;
        }
        else if (type.equals("LeftRight3")){
            chartype=32;
        }
        else if (type.equals("RightUp")){
            chartype=51;
        }
        else if (type.equals("RightDown")){
            chartype=52;
        }
        else if(type.equals("RightMiddle")){
            chartype=53;
        }
        else if (type.equals("MiddleMiddle")){
            chartype=54;
        }

        else if (type.equals("MiddleDown")){
            chartype=55;
        }
        else if (type=="LeftDown"){
            chartype=56;
        }

        return chartype;

    }
}
