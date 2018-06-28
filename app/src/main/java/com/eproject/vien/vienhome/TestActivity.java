package com.eproject.vien.vienhome;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.eproject.vien.widget.KaraokeSeekBar;

/**
 * Created by Administrator on 2018/6/28/028.
 */

public class TestActivity extends Activity{

    private KaraokeSeekBar seekBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = findViewById(R.id.karaoke_seek_bar);
        seekBar.setOnSeekBarChangeListener(new KaraokeSeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressBefore() {
                Log.d("vienwang","onProgressBefore");
            }

            @Override
            public void onProgressChanged(KaraokeSeekBar seekBar, double progress) {
                Log.d("vienwang","onProgressChanged progress:" + progress);
            }

            @Override
            public void onProgressAfter() {
                Log.d("vienwang","onProgressAfter");
            }
        });
        seekBar.requestFocus();
    }
}
