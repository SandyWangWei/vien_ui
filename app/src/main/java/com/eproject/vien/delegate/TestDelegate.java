package com.eproject.vien.delegate;

import android.util.Log;

import com.eproject.vien.vienhome.R;
import com.eproject.vien.widget.KaraokeSeekBar;

/**
 * Created by Administrator on 2018/7/8/008.
 */

public class TestDelegate extends AppDelegate {

    private KaraokeSeekBar seekBar;

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initWidget() {
//
//        seekBar = get(R.id.karaoke_seek_bar);
//        seekBar.setOnSeekBarChangeListener(new KaraokeSeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressBefore() {
//                Log.d("vienwang","onProgressBefore");
//            }
//
//            @Override
//            public void onProgressChanged(KaraokeSeekBar seekBar, double progress) {
//                Log.d("vienwang","onProgressChanged progress:" + progress);
//            }
//
//            @Override
//            public void onProgressAfter() {
//                Log.d("vienwang","onProgressAfter");
//            }
//        });
//        seekBar.requestFocus();

    }
}
