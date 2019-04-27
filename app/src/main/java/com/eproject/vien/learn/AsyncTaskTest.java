package com.eproject.vien.learn;

import android.graphics.Bitmap;
import android.os.AsyncTask;

/**
 * @author VienWang
 * @time 2018/12/9/009 22:49
 * @desc
 */

public class AsyncTaskTest extends AsyncTask<String,Integer,Bitmap>{

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        return null;
    }
}
