package com.example.writing.coosetype;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
