package com.example.writing.badge;

import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.writing.R;

public class Badge extends AppCompatActivity implements View.OnClickListener {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.badge);
        ImageView badge1=findViewById(R.id.badgeChooseView1);
        ImageView badge2=findViewById(R.id.badgeChooseView2);
        ImageView badge3=findViewById(R.id.badgeChooseView3);
        badge1.setOnClickListener(this);
        badge2.setOnClickListener(this);
        badge3.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        ImageView background=findViewById(R.id.badgeView);
        if(v.getId()==R.id.badgeChooseView1){
            background.setImageResource(R.drawable.badge);
        }
        else if (v.getId()==R.id.badgeChooseView2){
            background.setImageResource(R.drawable.badge_2);

        }
        else if(v.getId()==R.id.badgeChooseView3){
            background.setImageResource(R.drawable.badge_3);

        }

    }
}
