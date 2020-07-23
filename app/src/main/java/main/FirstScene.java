package main;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.writing.R;
import com.example.writing.coosetype.ChooseTypePage;

import java.util.Set;
import java.util.TreeSet;

public class FirstScene extends AppCompatActivity implements View.OnClickListener {
    Set<String> defaultSet=new TreeSet<String>();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.firstscene);
        getSupportActionBar().hide(); //隱藏標題
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN); //隱藏狀態
        Button start =findViewById(R.id.beginButton_first);
        Button badge =findViewById(R.id.badge_first);
        Button setting =findViewById(R.id.setting_firstscene);
        start.setOnClickListener(this);
        badge.setOnClickListener(this);
        setting.setOnClickListener(this);
        Toast.makeText(this,new String(" "+getSharedPreferences("num",0).getStringSet("chartypenum",defaultSet)),Toast.LENGTH_SHORT).show();
        if (getSharedPreferences("num",0).getStringSet("chartypenum",defaultSet).size()==0){
            start.setVisibility(View.INVISIBLE);
        }

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
