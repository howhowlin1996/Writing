package com.example.writing;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //Panel mPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        final Button saveButton = findViewById(R.id.SaveButton);
        final Button deleteButton= findViewById(R.id.DeleteButton);

       // final ImageView writingPanel = findViewById(R.id.WritingPanel);

        saveButton.setOnClickListener(this);

        deleteButton.setOnClickListener(this);


        //畫布
       //mPanel = new Panel(this);
        //setContentView(mPanel);

    }

    @Override
    public void onClick(View v) {
        final Panel mPanel =findViewById(R.id.panel);
        mPanel.savePicture();
        if (v.getId()==R.id.SaveButton){
            mPanel.savePicture();
            Toast.makeText(MainActivity.this,"儲存完畢",Toast.LENGTH_LONG).show();
        }
        else if (v.getId()==R.id.DeleteButton){
            mPanel.resetCanvas();
        }
    }


    /*@SuppressLint("ResourceType")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.xml.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_about:
                mPanel.resetCanvas();
                break;
            case R.id.menu_save:
                mPanel.savePicture();

                break;
            case R.id.menu_quit:
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }*/
}