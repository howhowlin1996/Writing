package com.example.writing.coosetype;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.writing.R;
import com.example.writing.panel.WritingPanel;

public class LeftRightPage extends AppCompatActivity implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leftrightchoose);

    }


    @Override
    public void onClick(View v) {


        Intent intent =new Intent(getBaseContext(), WritingPanel.class);
        startActivity(intent);
    }
}
