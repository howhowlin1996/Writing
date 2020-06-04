package com.example.writing.coosetype;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import com.example.writing.R;
import com.example.writing.panel.WritingPanel;
import com.example.writing.puzzle.Puzzle;

public class UpDownPage extends AppCompatActivity implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updownchoose);
        Button updown=findViewById(R.id.updown);

        updown.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {




        Intent intent =new Intent(getBaseContext(), Puzzle.class);
        intent.putExtra("num",21);
        startActivity(intent);

    }
}
