package com.example.writing.memo;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.writing.R;

import java.util.Date;

public class Memo extends AppCompatActivity implements View.OnClickListener {
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memo);
        TextView date=findViewById(R.id.memodate);
        Date day =new Date();
        CharSequence time = DateFormat.format("yyyy/MM/dd", day.getTime());
        date.setText(time+"日記");
        final Button confirm=findViewById(R.id.save_memo);
        final M_Panel mypanel=findViewById(R.id.picture_memo);
        confirm.setOnClickListener(this);
       mypanel.setBackground(getDrawable(R.drawable.block));
    }

    @Override
    public void onClick(View v) {
        onBackPressed();
        this.finish();

    }
}
