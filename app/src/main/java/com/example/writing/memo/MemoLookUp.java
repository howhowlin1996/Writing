package com.example.writing.memo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.writing.R;
import com.example.writing.panel.WritingPanel;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

public class MemoLookUp extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memo_lookup);
            ReadImage();
        final Button keeppractice=findViewById(R.id.keepPractice_lookup);
        final Button edit=findViewById(R.id.editMemo_lookup);

        keeppractice.setOnClickListener(this);
        edit.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.keepPractice_lookup){
            Intent intent =new Intent(this, WritingPanel.class);
            intent.putExtra("num",54);
            startActivity(intent);
        }
        else if(v.getId()==R.id.editMemo_lookup){
            Intent intent =new Intent(this, MemoEditPic.class);
            startActivity(intent);
        }

    }


    public void ReadImage(){
        try{
            FileInputStream fin=openFileInput("P1234.jpg");
            ImageView img=findViewById(R.id.image_lookup);
            Bitmap bitmap = BitmapFactory.decodeStream(fin);
            img.setImageBitmap(bitmap);
            fin.close();

        }
        catch(Exception e){
            e.printStackTrace();
            //Toast.makeText(this,"hERE",Toast.LENGTH_LONG).show();
        }



    }
}
