package com.example.trungie.gunplanner.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.trungie.gunplanner.R;
import com.example.trungie.gunplanner.object.DiskLruUtils;
import com.jakewharton.disklrucache.DiskLruCache;

import java.io.File;

public class BaseActivity extends Activity {

    private ImageView ivBack = null;
    private TextView tvTitle = null;
    private RelativeLayout mRlHead = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);
        initView();
        initEvent();
    }

    @Override
    public void setContentView(int layoutResID) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        View v = LayoutInflater.from(getApplicationContext()).inflate(layoutResID,
                null);
        ViewGroup layout = (ViewGroup) findViewById(R.id.layout_content);
        layout.addView(v, lp);
    }

    private void initView() {
        mRlHead = (RelativeLayout) findViewById(R.id.rl_head);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
    }

    private void initEvent() {
        setBackButtonEvent(mOnClickListener);
    }

    public void setHeadVisibility(int visibility) {
        mRlHead.setVisibility(visibility);
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.iv_back:
                    finish();
                    break;
            }
        }
    };

    protected void setBackButtonEvent(View.OnClickListener listener) {
        ivBack.setOnClickListener(listener);
    }

    protected void setTitle(String title) {
        tvTitle.setText(title);
    }

    protected void setActivityTitle(int id) {
        String title = getResources().getString(id);
        setTitle(title);
    }

}
