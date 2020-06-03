package main;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.writing.R;
import com.example.writing.coosetype.ChooseTypePage;

public class FirstScene extends AppCompatActivity implements View.OnClickListener {
    int  charactertype  [] =new int [12];
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.firstscene);
        Button start =findViewById(R.id.beginButton_first);
        Button badge =findViewById(R.id.badge_first);
        Button setting =findViewById(R.id.setting_firstscene);
        start.setOnClickListener(this);
        badge.setOnClickListener(this);
        setting.setOnClickListener(this);

    }




    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.beginButton_first){
            Intent intent =new Intent(getBaseContext(), ChooseTypePage.class);
            startActivity(intent);

        }
        else if(v.getId()==R.id.badge_first){
            Intent intent =new Intent(getBaseContext(),ChooseTypePage.class);
            startActivity(intent);
        }
        else if(v.getId()==R.id.setting_firstscene){
            Intent intent =new Intent(getBaseContext(), Setting.class);
            startActivity(intent);

        }

    }
}
