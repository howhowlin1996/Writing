package com.example.writing.choosetype;

import java.io.File;
import java.io.FileOutputStream;

public class WritingCsvFile extends Thread {
    String file_name;
    String data[];
    String folder;
    StringBuilder sb;
    public  WritingCsvFile( String data[],String file_name,String folder){
        this.data=data;
        this.file_name=file_name;
        this.folder=folder;

    }
    @Override
    public void run() {
        super.run();
        File eFile = new File(folder + File.separator + file_name+".csv");
        if (!eFile.exists()) {

        }
        try {
            FileOutputStream os = new FileOutputStream(eFile, true);
            sb = new StringBuilder();
            String here="\n";
            for (int i = 0; i < data.length; i++) {
                if (data[i].equals(here)){
                    sb.append("\n");
                }
                else{
                    sb.append(data[i]).append(",");
                }

            }

            os.write(sb.toString().getBytes());
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
