package com.example.writing.panel;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.writing.R;

public class LookWriting extends AppCompatActivity implements View.OnClickListener {
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lookwriting);                                         //import layout.xml
        getSupportActionBar().hide(); //隱藏標題
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN); //隱藏狀態
        Button confirm=findViewById(R.id.confirm_lookwriting);
        Button delete=findViewById(R.id.delete_lookwriting);
        Panel panel=findViewById(R.id.rightDownA_lookwriting);
        panel.setBackground(getDrawable(R.drawable.white));
        confirm.setOnClickListener(this);
        delete.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.confirm_lookwriting)
        {
            Intent intent =new Intent(getBaseContext(), CopyWriting.class);
            startActivity(intent);
        }
        else{
            Panel panel=findViewById(R.id.rightDownA_lookwriting);
            panel.setBackground(getDrawable(R.drawable.white));

        }


    }
}
