package main;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.writing.R;
import com.example.writing.badgefactory.BadgeFactory;
import com.example.writing.choosetype.ChooseTypePage;

import java.util.Set;
import java.util.TreeSet;

public class FirstScene extends AppCompatActivity implements View.OnClickListener {
    Set<String> defaultSet=new TreeSet<String>();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        int permission_check= ContextCompat.checkSelfPermission(this, Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS);
        if (permission_check!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new  String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.firstscene);
        getSupportActionBar().hide(); //隱藏標題
        ImageView shark=findViewById(R.id.setupshark);
        Button start =findViewById(R.id.beginButton_first);
        Button badge =findViewById(R.id.badge_first);
        Button setting =findViewById(R.id.setting_firstscene);
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);

        start.setOnClickListener(this);
        badge.setOnClickListener(this);
        setting.setOnClickListener(this);
        shark.getBackground().setAlpha(255);
        //Toast.makeText(this," "+getSharedPreferences("num", Context.MODE_PRIVATE).getStringSet("chartypenum",defaultSet)+" "+getSharedPreferences("num",Context.MODE_PRIVATE).getInt("writing_panel2",0),Toast.LENGTH_SHORT).show();


    }





    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.beginButton_first){
            if (getSharedPreferences("num",0).getStringSet("chartypenum",defaultSet).size()==0){
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("要先請老師出題喔~~");
                builder.setPositiveButton("好!!!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
                AlertDialog here =builder.create();
                here.show();
            }
            else{
                Intent intent =new Intent(getBaseContext(), ChooseTypePage.class);
                startActivity(intent);

            }


        }
        else if(v.getId()==R.id.badge_first){
            Intent intent =new Intent(getBaseContext(), BadgeFactory.class);
            startActivity(intent);
        }
        else if(v.getId()==R.id.setting_firstscene){
            Intent intent =new Intent(getBaseContext(), Setting.class);
            startActivity(intent);

        }

    }
}
