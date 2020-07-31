package com.example.writing.badgefactory;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TableLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.writing.R;

public class BadgeFactory extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.badgefactory);
        getSupportActionBar().hide(); //隱藏標題
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN); //隱藏狀態
        final BadgeGroup group=findViewById(R.id.badgeGroup_badgefactory);
        final ScrollView scrollView=findViewById(R.id.ScrollView_badge_factory);
        int width;
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        width=dm.widthPixels;


        for (int i=0;i<10;i++){
            ImageView here=new ImageView(this);
            here.setImageResource(R.drawable.cha31001301);
            group.addView(here);
        }
        for (int i=0;i<10;i++){
            View here=group.getChildAt(i);
            BadgeGroup.LayoutParams params=new BadgeGroup.LayoutParams(here.getLayoutParams());
            params.height=width/2;
            params.width=width/2;
            here.setLayoutParams(params);
            here.setOnClickListener(this);
        }



    }

    @Override
    public void onClick(View v) {
        onBackPressed();
    }
}
