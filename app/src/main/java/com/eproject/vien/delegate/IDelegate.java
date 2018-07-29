package com.eproject.vien.delegate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2018/7/8/008.
 */

public interface IDelegate {

    void create(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    View getRootView();

    void initWidget();
}
