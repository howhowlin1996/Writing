package com.example.writing;

import android.app.AppComponentFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


public class ChoosePage extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choosepage);
        final Button singleButton = findViewById(R.id.SingleButton);
        final Button leftrightButton = findViewById(R.id.LeftRightButton);
        final Button updownButton = findViewById(R.id.UpDownButton);
        final Button inoutButton = findViewById(R.id.InOutButton);
        final Button foureleButton = findViewById(R.id.FoutEleButton);
        singleButton.setOnClickListener(this);
        leftrightButton.setOnClickListener(this);
        updownButton.setOnClickListener(this);
        inoutButton.setOnClickListener(this);
        foureleButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Integer idnum=v.getId();
        if (idnum==R.id.SingleButton){

        }
        else if(idnum==R.id.LeftRightButton){

        }
        else if (idnum==R.id.UpDownButton){

        }
        else  if (idnum==R.id.InOutButton){

        }
        else if (idnum==R.id.FoutEleButton){

        }

    }
}
