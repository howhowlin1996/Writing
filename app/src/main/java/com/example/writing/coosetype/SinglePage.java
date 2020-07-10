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

public class SinglePage extends AppCompatActivity implements View.OnClickListener {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singlechoose);
        getSupportActionBar().hide(); //隱藏標題
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN); //隱藏狀態
        Button single=findViewById(R.id.single);
        Button threeele=findViewById(R.id.threeele_single);
        single.setOnClickListener(this);
        threeele.setOnClickListener(this);
        }





    @Override
    public void onClick(View v) {
        Intent intent =new Intent(getBaseContext(), ChooseResult.class);
       if(v.getId()==R.id.single){
           intent.putExtra("num",11);
       }
       else if(v.getId()==R.id.threeele_single){
           intent.putExtra("num",41);
       }

        startActivity(intent);

    }
}
