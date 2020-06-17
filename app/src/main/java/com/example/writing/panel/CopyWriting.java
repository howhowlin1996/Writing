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

public class CopyWriting extends AppCompatActivity implements View.OnClickListener {
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.copywriting);
        Panel mypanel=findViewById(R.id.panel_copy);
        Button confirm=findViewById(R.id.SaveButton_copy);
        Button delete=findViewById(R.id.DeleteButton_copy);
        mypanel.setBackground(getDrawable(R.drawable.pic_0001_copy));
        confirm.setOnClickListener(this);
        delete.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        Panel mypanel=findViewById(R.id.panel_copy);
        if(v.getId()==R.id.SaveButton_copy){
            Intent intent =new Intent(this,WritingPanel.class);
            intent.putExtra("num",0);
            startActivity(intent);

        }
        else if(v.getId()==R.id.DeleteButton_copy){
            mypanel.resetCanvas();
            mypanel.setBackground(getDrawable(R.drawable.pic_0001_copy));
        }

    }
}
