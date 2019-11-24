package com.eproject.vien.widget.control;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import com.eproject.vien.vienhome.R;
import com.nineoldandroids.animation.ValueAnimator;


public class ControlLayout extends ViewGroup {

    /**
     * 时间插值的最大数，附带幂次方数值
     */
    private final int MAX_ANIMATION_OFFSET = 256;
    private final int MAX_ANIMATION_POWER = 8;

    /** 下次移动的索引 */
    private int mNextIndex;
    /** 当前显示的索引 */
    private int mCurrentIndex;
    /** 控制台上方距离父布局高度 */
    private int mControlMarginTop;

    /** 是否正在layout过程中 */
    private boolean mInLayout;
    /** 是否需要切换动画*/
    private boolean mIsNeeAnim;

    private int measureWith;
    private int measureHeight;

    private ControlAdapterImpl adapter;

    private int mCurrentFocusSubIndex = 0;

    private ValueAnimator mValueAnimator;

    private boolean isInAnimation = false;

    /**
     * 动画的位移偏移值，用来刷新子view的位置，最大数值取2^n
     */
    private int mAnimOffset;

    public ControlLayout(Context context) {
        super(context);
        init(context, null);
    }

    public ControlLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ControlLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        measureWith = MeasureSpec.getSize(widthMeasureSpec);
        measureHeight = MeasureSpec.getSize(heightMeasureSpec);

        int count = getChildCount();
        for (int i = 0; i < count; i++) {

            measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec);

        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mInLayout = true;

        final int childCount = getChildCount();
        if (changed) {
            for (int i = 0; i < childCount; i++) {
                getChildAt(i).forceLayout();
            }
        }

        layoutChildren();
        mInLayout = false;
    }

    /**
     * 初始化
     */
    private void init(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return;
        }
        final TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.ControlLayout);

        mControlMarginTop = a.getDimensionPixelSize(R.styleable.ControlLayout_marginSpace, 0);
        mIsNeeAnim = a.getBoolean(R.styleable.ControlLayout_withAnim, true);

        a.recycle();

        setClipChildren(false);
        setClipToPadding(false);
    }

    private void layoutChildren() {

        if(adapter == null){
            return;
        }
        int mMenuCount = adapter.getCount();

        int mMenuBtnHeight = 0;
        //布局对应的二级菜单按钮
        for (int i = mMenuCount; i < getChildCount(); i++) {
            View view = getChildAt(i);

            if (view != null && view.getVisibility() == View.VISIBLE) {
                mMenuBtnHeight = view.getMeasuredHeight();
                view.layout(0, mControlMarginTop, view.getMeasuredWidth(), mControlMarginTop + mMenuBtnHeight);
            }
        }
        //布局对应的一级标题
        for (int i = 0; i < mMenuCount; i++) {

            View view = getChildAt(i);

            int itemHeight = 30 + view.getMeasuredHeight();
            int selectedMoveHeight = 30 + mMenuBtnHeight;

            int left = 0;
            int top;
            if (mAnimOffset >= 0) {
                if (i <= mCurrentIndex) {
                    top = mControlMarginTop - (itemHeight * (mCurrentIndex - i + 1) + (itemHeight * mAnimOffset >> MAX_ANIMATION_POWER));
                } else if (i == mCurrentIndex + 1) {
                    top = mControlMarginTop + mMenuBtnHeight + 30 - ((itemHeight + selectedMoveHeight) * mAnimOffset >> MAX_ANIMATION_POWER);
                } else {
                    top = mControlMarginTop + mMenuBtnHeight + 30 - (itemHeight * (mCurrentIndex - i + 1) + (itemHeight * mAnimOffset >> MAX_ANIMATION_POWER));
                }
            } else {
                if (i < mCurrentIndex) {
                    top = mControlMarginTop - (itemHeight * (mCurrentIndex - i + 1) + (itemHeight * mAnimOffset >> MAX_ANIMATION_POWER));
                } else if (i == mCurrentIndex) {
                    top = mControlMarginTop - itemHeight - ((itemHeight + selectedMoveHeight) * mAnimOffset >> MAX_ANIMATION_POWER);
                } else {
                    top = mControlMarginTop + mMenuBtnHeight + 30 - (itemHeight * (mCurrentIndex - i + 1) + (itemHeight * mAnimOffset >> MAX_ANIMATION_POWER));
                }
            }
            int right = view.getMeasuredWidth();
            int bottom = top + view.getMeasuredHeight();

            view.layout(left, top, right, bottom);

        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    /**
     * 设置适配器
     *
     * @param adapter 适配器
     */
    public void setAdapter(ControlAdapterImpl adapter) {
        this.adapter = adapter;
        this.adapter.registerDataSetObserver(observer);
        //首次刷新数据
        refreshAdapterView();
    }

    /**
     * 通过adapter数据刷新列表
     */
    private void refreshAdapterView() {
        removeAllViews();
        int count = adapter.getCount();

        for (int i = 0; i < count; i++) {
            View view = adapter.getTitleView(i, this, i == mCurrentIndex);
            addViewInLayout(view, -1, view.getLayoutParams());
        }

        for (int i = 0; i < count; i++) {
            View subView = adapter.getSubView(i, this);
            if (i != mCurrentIndex) {
                subView.setVisibility(View.GONE);
            }
            addViewInLayout(subView, -1, subView.getLayoutParams());
        }

        requestLayout();
        invalidate();
    }

    public int getSelectedPosition() {
        return mCurrentIndex;
    }

    /**
     * 切换一级菜单
     *
     * @param position 切换位置
     */
    public void setSelectedPosition(int position, boolean isNeeAnim) {

        if (position < 0 || position >= adapter.getCount()) {
            return;
        }

        mNextIndex = position;
        if (mNextIndex == mCurrentIndex) {
            return;
        }
        if (isNeeAnim) {
            startAnimation();
        } else {
            mCurrentIndex = mNextIndex;
            requestLayout();
        }

        //修改字体颜色
        if(adapter != null) {
            for (int i = 0; i < adapter.getCount(); i++) {
                View view = getChildAt(i);
                if (view instanceof TextView) {
                    if(i == mNextIndex) {
                        ((TextView) view).setTextColor(getResources().getColor(R.color.colorAccent));
                    }else {
                        ((TextView) view).setTextColor(getResources().getColor(R.color.colorPrimary));
                    }
                }
            }
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (adapter != null) {
            adapter.unregisterDataSetObserver(observer);
        }
    }

    /**
     * 开始切换动画
     */
    public void startAnimation() {
        if (isInAnimation) {
            return;
        }

        isInAnimation = true;
        if (mValueAnimator == null) {
            mValueAnimator = ValueAnimator.ofInt(0, 256);
            mValueAnimator.setDuration(300);
            mValueAnimator.setInterpolator(new SelectAnimInterpolator());
            mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {

                    if (mNextIndex == mCurrentIndex) {
                        return;
                    }
                    int value = (int) animation.getAnimatedValue();
                    mAnimOffset =  value * (mNextIndex - mCurrentIndex);

                    if(value != 0) {
                        requestLayout();
                        if (value >= MAX_ANIMATION_OFFSET) {
                            mCurrentIndex = mNextIndex;
                            mAnimOffset = 0;
                            isInAnimation = false;
                        }
                    }
                }

            });
        } else {
            mValueAnimator.cancel();
        }
        mValueAnimator.start();
    }

    /**
     * 适配器的数据变化监听
     */
    DataSetObserver observer = new DataSetObserver() {
        @Override
        public void onChanged() {
            refreshAdapterView();
        }
    };

    /**
     * 动画插值器，主要实现y=x^2换算
     */
    class SelectAnimInterpolator extends LinearInterpolator {

        private final float mFactor;
        private final double mDoubleFactor;

        SelectAnimInterpolator() {
            mFactor = 1.0f;
            mDoubleFactor = 2.0;
        }

        @Override
        public float getInterpolation(float input) {

            if (mFactor == 1.0f) {
                return input * input;
            } else {
                return (float) Math.pow(input, mDoubleFactor);
            }

        }
    }


}
