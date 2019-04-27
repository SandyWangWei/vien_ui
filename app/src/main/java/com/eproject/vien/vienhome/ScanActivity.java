package com.eproject.vien.vienhome;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.eproject.vien.Util.QRCodeUtil;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

/**
 * @author VienWang
 * @time 2018/8/18/018 9:54
 * @desc
 */

public class ScanActivity extends AppCompatActivity {

    private TextView mQRcodeText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQRcodeText = (TextView) findViewById(R.id.text_qr_code);

        findViewById(R.id.button_scan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new IntentIntegrator(ScanActivity.this)
                        .setOrientationLocked(false)
                        .setCaptureActivity(ScanCodeActivity.class) // 设置自定义的activity是ScanActivity
                        .initiateScan(); // 初始化扫描
            }
        });

        ((ImageView)findViewById(R.id.image_qr_code)).setImageBitmap(QRCodeUtil.createQRCodeBitmap("This is a test",500,500));
    }

    @Override
// 通过 onActivityResult的方法获取扫描回来的值
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(intentResult != null) {
            if(intentResult.getContents() == null) {
                Toast.makeText(this,"内容为空",Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this,"扫描成功",Toast.LENGTH_LONG).show();
                // ScanResult 为 获取到的字符串
                String ScanResult = intentResult.getContents();
                mQRcodeText.setText(ScanResult);
            }
        } else {
            super.onActivityResult(requestCode,resultCode,data);
        }
    }
}
