package com.eproject.vien.delegate;

import android.app.Activity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by Administrator on 2018/7/8/008.
 */

public abstract class AppDelegate implements IDelegate{

    protected final SparseArray<View> mViews = new SparseArray<View>();

    protected View rootView;

    @Override
    public void create(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int rootLayoutId = getRootLayoutId();
        rootView = inflater.inflate(rootLayoutId, container, false);
    }
    @Override
    public View getRootView() {
        return rootView;
    }

    public void setRootView(View rootView) {
        this.rootView = rootView;
    }

    @Override
    public void initWidget() {
    }

    public <T extends View> T bindView(int id) {
        T view = (T) mViews.get(id);
        if (view == null) {
            view = (T) rootView.findViewById(id);
            mViews.put(id, view);
        }
        return view;
    }

    public <T extends View> T get(int id) {
        return (T) bindView(id);
    }

    public void setOnClickListener(View.OnClickListener listener, int... ids) {
        if (ids == null) {
            return;
        }
        for (int id : ids) {
            get(id).setOnClickListener(listener);
        }
    }

    public void toast(CharSequence msg) {
        Toast.makeText(rootView.getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public <T extends Activity> T getActivity() {
        return (T) rootView.getContext();
    }

    public abstract int getRootLayoutId();

}
