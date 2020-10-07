package main;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.writing.R;
import com.example.writing.choosetype.ChooseTypePage;
import com.example.writing.panel.CopyWriting;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserEnter extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //隱藏標題
        setContentView(R.layout.userenterscene);
        Button confirm=findViewById(R.id.confirm_userenterscene);
        Button change=findViewById(R.id.change_userenterscene);
        change.setOnClickListener(this);
        confirm.setOnClickListener(this);




    }


    @Override
    public void onClick(View v) {
        final  EditText user_code=findViewById(R.id.userCodeEdit_enteruser);
        if (v.getId()==R.id.confirm_userenterscene){
           final String user_name=user_code.getText().toString();
           if (user_name.length()==0){
               return;
           }
            final SharedPreferences num=getSharedPreferences("num", Context.MODE_PRIVATE);
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            final Context context=this;
            builder.setMessage("確定代號為: "+user_name);
            builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    final AlertDialog.Builder here_builder = new AlertDialog.Builder(context) ;
                    here_builder.setView(R.layout.alert_skip_password);
                    here_builder.setPositiveButton("確定",null );
                    AlertDialog here =here_builder.create();
                    here.show();
                    final EditText password=here.findViewById(R.id.skip_password);
                    here.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener(){
                                                                                           @Override
                                                                                           public void onClick(View v) {
                                                                                               if (password.getText().toString().equals("0401")){
                                                                                                   num.edit().putString("userkey",user_name).commit();
                                                                                                   File appDir = new File(Environment.getExternalStorageDirectory(), "Writing/"+user_name);
                                                                                                   if (!appDir.exists()) {
                                                                                                       //Log.d("ERROR_FILE",appDir.toString());
                                                                                                       appDir.mkdir();
                                                                                                   }
                                                                                                   String nowDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
                                                                                                    appDir = new File(Environment.getExternalStorageDirectory(), "Writing/"+user_name+"/"+nowDate);
                                                                                                   if (!appDir.exists()) {
                                                                                                       //Log.d("ERROR_FILE",appDir.toString());
                                                                                                       appDir.mkdir();
                                                                                                   }
                                                                                                   Intent intent =new Intent(getBaseContext(), FirstScene.class);
                                                                                                   startActivity(intent);
                                                                                               }
                                                                                               else {
                                                                                                   password.getText().clear();
                                                                                                   Toast.makeText(context,"密碼不對喔",Toast.LENGTH_SHORT).show();

                                                                                               } }

                                                                                       }
                    );
                }
            });
            AlertDialog here =builder.create();
            here.show();

        }
        else{
            user_code.getText().clear();

        }



    }
}
