package com.example.writing.coosetype;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.writing.R;
import com.example.writing.panel.WritingPanel;
import com.example.writing.puzzle.Puzzle;

public class InOutPage extends AppCompatActivity implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inoutchoose);
        Button  rightup=findViewById(R.id.rightup);
        Button  rightdown=findViewById(R.id.rightdown);
        Button  rightmiddle=findViewById(R.id.rightmiddle);
        Button  middledown=findViewById(R.id.middledown);
        Button  middlemiddle=findViewById(R.id.middlemiddle);
        Button  leftdown=findViewById(R.id.leftdown);
        rightup.setOnClickListener(this);
        rightdown.setOnClickListener(this);
        rightdown.setOnClickListener(this);
        middledown.setOnClickListener(this);
        middlemiddle.setOnClickListener(this);
        leftdown.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {



        Intent intent =new Intent(getBaseContext(), Puzzle.class);
        startActivity(intent);

    }
}
