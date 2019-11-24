package com.eproject.vien.vienhome;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.view.View;

import com.eproject.vien.widget.TestDialog;
import com.eproject.vien.widget.control.ControlLayout;
import com.eproject.vien.widget.control.ControlMenuAdapter;
import com.eproject.vien.widget.control.ControlMenuInfo;

import java.util.ArrayList;

public class ControlActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

        findViewById(R.id.btn_up).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                startActivity(intent);
            }
        });


        findViewById(R.id.btn_down).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testDialog();
            }
        });
    }

    private void testDialog(){
        TestDialog dialog = new TestDialog(this);
        dialog.show();
    }

    private void testControl(){
        final ControlLayout layout = findViewById(R.id.control_view);

        ControlMenuAdapter adapter = new ControlMenuAdapter();

        ArrayList<ControlMenuInfo> infos = new ArrayList<>();

        infos.add(new ControlMenuInfo().setTitle("test01"));
        infos.add(new ControlMenuInfo().setTitle("test02"));
        infos.add(new ControlMenuInfo().setTitle("test03"));
        infos.add(new ControlMenuInfo().setTitle("test04"));
        infos.add(new ControlMenuInfo().setTitle("test05"));

        adapter.setData(infos);

        layout.setAdapter(adapter);


        findViewById(R.id.btn_down).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.setSelectedPosition(layout.getSelectedPosition() + 1, true);
            }
        });

        findViewById(R.id.btn_up).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.setSelectedPosition(layout.getSelectedPosition() - 1, true);
            }
        });

        findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator anim = ObjectAnimator.ofFloat(layout.getChildAt(0),"translationY", 0, 300);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.setDuration(500);
                animatorSet.play(anim);
                animatorSet.start();

                ObjectAnimator anim1 = ObjectAnimator.ofFloat(layout.getChildAt(1),"translationY", 0, 300);
                AnimatorSet animatorSet1 = new AnimatorSet();
                animatorSet1.setDuration(500);
                animatorSet1.play(anim1);
                animatorSet1.start();

                ObjectAnimator anim2 = ObjectAnimator.ofFloat(layout.getChildAt(2),"translationY", 0, 300);
                AnimatorSet animatorSet2 = new AnimatorSet();
                animatorSet2.setDuration(500);
                animatorSet2.play(anim2);
                animatorSet2.start();

                ObjectAnimator anim3 = ObjectAnimator.ofFloat(layout.getChildAt(3),"translationY", 0, 300);
                AnimatorSet animatorSet3 = new AnimatorSet();
                animatorSet3.setDuration(500);
                animatorSet3.play(anim3);
                animatorSet3.start();

                ObjectAnimator anim4 = ObjectAnimator.ofFloat(layout.getChildAt(4),"translationY", 0, 300);
                AnimatorSet animatorSet4 = new AnimatorSet();
                animatorSet4.setDuration(500);
                animatorSet4.play(anim4);
                animatorSet4.start();
            }
        });

    }

}
