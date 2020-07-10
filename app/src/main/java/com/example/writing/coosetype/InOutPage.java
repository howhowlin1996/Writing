package com.example.writing.coosetype;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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
        int ID =v.getId();
        if(ID==R.id.rightup){
            Log.d("error","rightup");
            intent.putExtra("num",51);
        }
        else if(ID==R.id.rightdown){
            Log.d("error","rightdown");
            intent.putExtra("num",52);
        }
        else if(ID==R.id.rightmiddle){
            Log.d("error","rightmiddle");
            intent.putExtra("num",53);
        }
        else if(ID==R.id.middlemiddle){
            Log.d("error","middlemiddle");
            intent.putExtra("num",54);

        }
        else if(ID==R.id.middledown){
            Log.d("error","middledown");
            intent.putExtra("num",55);
        }
        else if(ID==R.id.leftdown){
            Log.d("error","leftdown");
            intent.putExtra("num",56);
        }
        startActivity(intent);

    }
}
