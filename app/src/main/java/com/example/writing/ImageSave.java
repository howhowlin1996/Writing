package com.example.writing;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public  class ImageSave extends AsyncTask<Bitmap,Integer, File> {
    final static String TAG = "MyActivity";

    private Context context;

    public  ImageSave(Context context){
        this.context=context;
    }
    public void Save (Context context, Bitmap bmp) {
        File appDir = new File(Environment.getExternalStorageDirectory(), "Canvas_Painter");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
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
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + "/storage/emulated/0/Pictures/Canvas_Painter")));




    }



    @Override
    protected File doInBackground(Bitmap... bitmaps) {
        Save(context,bitmaps[0]);
        return null;
    }





}
