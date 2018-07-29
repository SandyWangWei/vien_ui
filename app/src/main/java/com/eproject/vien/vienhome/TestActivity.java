package com.eproject.vien.vienhome;

import com.eproject.vien.delegate.TestDelegate;
import com.eproject.vien.presenter.ActivityPresenter;

/**
 * Created by Administrator on 2018/6/28/028.
 */

public class TestActivity extends ActivityPresenter <TestDelegate>{

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
    }

    @Override
    protected Class getDelegateClass() {
        return TestDelegate.class;
    }

}
