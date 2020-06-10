package com.example.writing.memo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.writing.R;

import java.io.FileOutputStream;

public class MemoEditText extends AppCompatActivity implements View.OnClickListener {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memo_edittext);
        Button complete=findViewById(R.id.complete_edit);
        Button return_but =findViewById(R.id.return_edit);
        EditText editText=findViewById(R.id.editText_edit);
        editText.setSingleLine(false);
        editText.setHorizontallyScrolling(false);
        complete.setOnClickListener(this);
        return_but.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.complete_edit){
            EditText editText=findViewById(R.id.editText_edit);
            WriteFile(editText.getText().toString());
            Intent intent=new Intent(this,MemoLookUp.class);
            startActivity(intent);
        }
        else if(v.getId()==R.id.return_edit){
            onBackPressed();
        }

    }


    public void WriteFile(String context){
        try {
            FileOutputStream fout=openFileOutput("P1234.txt", Context.MODE_PRIVATE);
            fout.write(context.getBytes());
            fout.close();

        }
        catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this,"hahhahh",Toast.LENGTH_LONG).show();
        }

    }
}
