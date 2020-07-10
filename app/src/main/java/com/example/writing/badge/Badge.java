package com.example.writing.badge;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.writing.R;
import com.example.writing.badgefactory.BadgeFactory;
import com.example.writing.coosetype.ChooseTypePage;
import com.example.writing.panel.WritingPanel;

public class Badge extends AppCompatActivity implements View.OnClickListener {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.badge);
        getSupportActionBar().hide(); //隱藏標題
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN); //隱藏狀態
        ImageView badge1=findViewById(R.id.badgeChooseView1);
        ImageView badge2=findViewById(R.id.badgeChooseView2);
        ImageView badge3=findViewById(R.id.badgeChooseView3);
        Button factory=findViewById(R.id.badgeFactory_badge);
        Button share=findViewById(R.id.share_badge);
        Button practice=findViewById(R.id.practice_badge);
        badge1.setOnClickListener(this);
        badge2.setOnClickListener(this);
        badge3.setOnClickListener(this);
        factory.setOnClickListener(this);
        share.setOnClickListener(this);
        practice.setOnClickListener(this);
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
        else if(v.getId()==R.id.badgeFactory_badge){
            Intent intent = new  Intent(this, BadgeFactory.class);
            startActivity(intent);

        }
        else if(v.getId()==R.id.share_badge){


        }
        else if(v.getId()==R.id.practice_badge){
            Intent intent = new  Intent(this, ChooseTypePage.class);
            //intent.putExtra("num",0);
            startActivity(intent);

        }

    }
}
