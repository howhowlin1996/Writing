package com.example.writing.coosetype;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.writing.R;
import com.example.writing.panel.WritingPanel;

public class ChooseResult extends AppCompatActivity implements View.OnClickListener {
    int split_code;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choosetype_result);
        TextView textView=findViewById(R.id.textView_result);
        Button confirm=findViewById(R.id.confirm_result);
        split_code=getIntent().getExtras().getInt("num");
        confirm.setOnClickListener(this);

        if (split_code==31){
            textView.setText("答對了");
            confirm.setText("開始寫字");
        }
        else {
            textView.setText("答錯了");
            confirm.setText("再試一次");
        }

    }

    @Override
    public void onClick(View v) {

        if (split_code==31){
            Intent intent =new Intent(getBaseContext(), WritingPanel.class);
            intent.putExtra("num",split_code);
            startActivity(intent);
        }
        else {
            Intent intent =new Intent(getBaseContext(), ChooseTypePage.class);
            startActivity(intent);
        }

    }
}
