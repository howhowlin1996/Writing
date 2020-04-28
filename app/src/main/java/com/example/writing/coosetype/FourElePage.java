package com.example.writing.coosetype;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.writing.R;
import com.example.writing.puzzle.Puzzle;

public class FourElePage extends AppCompatActivity implements View.OnClickListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fourelechoose);
       final TextView textView =findViewById(R.id.textView);
       textView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {


        Intent intent =new Intent(getBaseContext(), Puzzle.class);
        startActivity(intent);

    }
}
