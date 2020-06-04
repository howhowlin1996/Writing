package com.example.writing.coosetype;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.writing.R;


public class ChooseTypePage extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choosepage);
        final Button singleButton = findViewById(R.id.SingleButton);                 //get character type id from choosepage.xml
        final Button updownButton = findViewById(R.id.UpDownButton);
        final Button inoutButton = findViewById(R.id.InOutButton);

        singleButton.setOnClickListener(this);
        updownButton.setOnClickListener(this);
        inoutButton.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        Integer idnum=v.getId();
        if (idnum==R.id.SingleButton){
            Intent intent = new  Intent(ChooseTypePage.this, SinglePage.class);             //change activity to the activity that every button matches
            startActivity(intent);
        }
        else if (idnum==R.id.UpDownButton){
            Intent intent = new  Intent(ChooseTypePage.this, UpDownPage.class);
            startActivity(intent);
        }
        else  if (idnum==R.id.InOutButton){
            Intent intent = new  Intent(ChooseTypePage.this, InOutPage.class);
            startActivity(intent);
        }



    }
}
