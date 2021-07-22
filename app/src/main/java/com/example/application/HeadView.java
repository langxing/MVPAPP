package com.example.application;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.LayoutRes;

public class HeadView extends LinearLayout implements View.OnClickListener {
    /**
     * 不适用的选择框
     */
    private CheckBox mCbNotApplicable;
    /**
     * 检测结果的根布局
     */
    private FrameLayout mFlResult;
    /**
     * 指标的根布局
     */
    private FrameLayout mFlNorm;
    private TextView mTvReset;
    private TextView mTvClaim;
    private TextView mTvSave;
    private TextView mTvNorm;
    private TextView mTvName;

    private Context mContext;
    private OnClickListener mOnClickListener;

    /**
     * 检测项目名称的textview
     * @return
     */
    public TextView getTvName() {
        return mTvName;
    }

    /**
     * 检测指标要求的textview
     * @return
     */
    public TextView getTvNorm() {
        return mTvNorm;
    }

    /**
     * 试验要求的textview
     * @return
     */
    public TextView getTvClaim() {
        return mTvClaim;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    public HeadView(Context context) {
        this(context, null);
    }

    public HeadView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.view_head, this);
        setBackgroundColor(context.getResources().getColor(R.color.white));
        setOrientation(LinearLayout.VERTICAL);
        setPadding(3, 3, 3, 3);
        mContext = context;
        initView();
        initListener();
    }

    private void initView() {
        mCbNotApplicable = findViewById(R.id.cbNotApplicable);
        mFlResult = findViewById(R.id.flResult);
        mTvClaim = findViewById(R.id.tvClaim);
        mTvReset = findViewById(R.id.tvReset);
        mTvSave = findViewById(R.id.tvSave);
        mFlNorm = findViewById(R.id.flNorm);
        mTvNorm = findViewById(R.id.tvNorm);
        mTvName = findViewById(R.id.tvName);
    }

    private void initListener() {
        mTvSave.setOnClickListener(this);
        mTvReset.setOnClickListener(this);
        mCbNotApplicable.setOnClickListener(this);
    }

    /**
     * 填充检测结果的布局
     * @param layout
     */
    public void setResultLayout(@LayoutRes int layout) {
        View resultView = LayoutInflater.from(mContext).inflate(layout, null);
        setResultView(resultView);
    }

    /**
     * 填充检测结果的view
     * @param resultView
     */
    public void setResultView(View resultView) {
        mFlResult.removeAllViews();
        if (resultView == null) {
            throw new NullPointerException("检测结果view不能为null");
        }
        mFlResult.addView(resultView);
    }

    /**
     * 填充指标要求布局
     * @param layout
     */
    public void setNormLayout(@LayoutRes int layout) {
        View normView = LayoutInflater.from(mContext).inflate(layout, null);
        setResultView(normView);
    }

    /**
     * 填充指标要求view
     * @param normView
     */
    public void setNormView(View normView) {
        mFlResult.removeAllViews();
        if (normView == null) {
            throw new NullPointerException("指标要求view不能为null");
        }
        mFlNorm.addView(normView);
    }

    /**
     * 隐藏不适用勾选框
     */
    public void hideCbNotApplicable() {
        mCbNotApplicable.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvSave:
                if (mOnClickListener != null) {
                    mOnClickListener.onSaveClick();
                }
                break;
            case R.id.tvReset:
                if (mOnClickListener != null) {
                    mOnClickListener.onResetClick();
                }
                break;
            case R.id.cbNotApplicable:
                if (mOnClickListener != null) {
                    mOnClickListener.onNotApplicableClick(mCbNotApplicable.isChecked());
                }
                break;
        }
    }

    public interface OnClickListener {
        void onSaveClick();
        void onResetClick();
        void onNotApplicableClick(boolean isChecked);
    }
}
