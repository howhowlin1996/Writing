package com.example.writing.coosetype;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.writing.R;
import com.example.writing.panel.WritingPanel;
import com.example.writing.puzzle.Puzzle;

public class LeftRightPage extends AppCompatActivity implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leftrightchoose);
        Button leftright2=findViewById(R.id.leftright2);
        Button leftright3=findViewById(R.id.leftright3);
        leftright2.setOnClickListener(this);
        leftright3.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {


        Intent intent =new Intent(getBaseContext(), Puzzle.class);
        startActivity(intent);
    }
}
