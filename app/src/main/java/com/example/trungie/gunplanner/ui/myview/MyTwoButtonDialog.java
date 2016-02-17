package com.example.trungie.gunplanner.ui.myview;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.trungie.gunplanner.R;

public class MyTwoButtonDialog extends Dialog {

    private Context context;
    private View mViewParent = null;
    private Button btnPositive = null;
    private Button btnNegative = null;

    public MyTwoButtonDialog(Context context) {
        super(context);
        this.context = context;
    }

    public MyTwoButtonDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    protected MyTwoButtonDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void setContentView(int layoutResID) {
        mViewParent = LayoutInflater.from(context).inflate(R.layout.dialog_two_button, null);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        View v = LayoutInflater.from(context).inflate(layoutResID,
                null);
        ViewGroup layout = (ViewGroup) mViewParent.findViewById(R.id.layout_content);
        layout.addView(v, lp);
        initView();
        super.setContentView(mViewParent);
    }

    @Override
    public void setContentView(View view) {
        mViewParent = LayoutInflater.from(context).inflate(R.layout.dialog_two_button, null);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ViewGroup layout = (ViewGroup) mViewParent.findViewById(R.id.layout_content);
        layout.addView(view, lp);
        initView();
        super.setContentView(mViewParent);
    }

    private void initView() {
        btnPositive = (Button) mViewParent.findViewById(R.id.btn_positive);
        btnNegative = (Button) mViewParent.findViewById(R.id.btn_negative);
    }

    public void setPositiveInfo(String str, View.OnClickListener listener) {
        btnPositive.setText(str);
        btnPositive.setOnClickListener(listener);
    }

    public void setNegativeInfo(String str, View.OnClickListener listener) {
        btnNegative.setText(str);
        btnNegative.setOnClickListener(listener);
    }
}
