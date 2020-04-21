package com.example.writing;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.format.DateFormat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;


public  class ImageSave extends AsyncTask<Bitmap,Integer, File> {
    final static String TAG = "MyActivity";

    private Context context;

    public  ImageSave(Context context){
        this.context=context;
    }
    public void Save (Context context, Bitmap bmp) {
        File appDir = new File(Environment.getExternalStorageDirectory(), "Writing");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        Date day =new Date();
        CharSequence time = DateFormat.format("MMddyy hh:mm:ss", day.getTime());
        String fileName = "writing "+time + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 其次把檔案插入到系統圖庫
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 最後通知相簿更新
        //context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + "/storage/emulated/0/Pictures/Writing")));




    }



    @Override
    protected File doInBackground(Bitmap... bitmaps) {
        Save(context,bitmaps[0]);
        return null;
    }





}
