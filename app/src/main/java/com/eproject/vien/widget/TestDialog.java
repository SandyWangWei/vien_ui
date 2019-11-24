package com.eproject.vien.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.WindowManager;

import com.eproject.vien.vienhome.R;

public class TestDialog extends Dialog {

    public TestDialog(@NonNull Context context) {
        super(context);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setType(WindowManager.LayoutParams.TYPE_TOAST);

    }
}
