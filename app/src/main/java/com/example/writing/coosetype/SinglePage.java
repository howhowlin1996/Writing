package com.example.writing.coosetype;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.writing.R;
import com.example.writing.panel.WritingPanel;
import com.example.writing.puzzle.Puzzle;

public class SinglePage extends AppCompatActivity implements View.OnClickListener {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singlechoose);
        Button single=findViewById(R.id.single);
        single.setOnClickListener(this);

        }





    @Override
    public void onClick(View v) {




        Intent intent =new Intent(getBaseContext(), Puzzle.class);
        startActivity(intent);

    }
}
