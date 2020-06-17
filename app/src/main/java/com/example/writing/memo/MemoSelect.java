package com.example.writing.memo;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.writing.R;

import java.util.Date;

public class MemoSelect extends AppCompatActivity implements View.OnClickListener {
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memo_selection);
        final Button edit=findViewById(R.id.edit_memo);
        final Button lookup=findViewById(R.id.lookmemo_memo);
        final  Button return_but=findViewById(R.id.return_memo);
        edit.setOnClickListener(this);
        lookup.setOnClickListener(this);
        return_but.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.edit_memo){
            Intent intent =new Intent(this,MemoEditPic.class);
            startActivity(intent);
        }
        else if(v.getId()==R.id.lookmemo_memo){
            Intent intent =new Intent(this,MemoLookUp.class);
            startActivity(intent);

        }
        else if(v.getId()==R.id.return_memo){
            onBackPressed();
            this.finish();
        }


    }
}
