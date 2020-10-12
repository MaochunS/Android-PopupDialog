package com.maochun.popupinputdialog;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;

public class InputDialog {

    private AlertDialog mAlertDialog;
    private View mLoadView;
    private Context mContext;
    private EditText mRefundMemo;

    protected View.OnFocusChangeListener mViewFocusChangeListener = new View.OnFocusChangeListener(){
        @Override
        public void onFocusChange(View v, boolean hasFocus) {

            if (!hasFocus) {
                hideKeyboard(v);
            }
        }
    };

    public void show(Context context, Button.OnClickListener okBtnListener, Button.OnClickListener cancelBtnListener) {
        mContext = context;
        mAlertDialog = new AlertDialog.Builder(context).create();
        mLoadView = LayoutInflater.from(context).inflate(R.layout.dialog_popup, null);
        mAlertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mAlertDialog.setView(mLoadView, 0, 0, 0, 0);
        mAlertDialog.setCanceledOnTouchOutside(true);

        Button btnCancel = mLoadView.findViewById(R.id.button_common_alert_cancel);
        btnCancel.setOnClickListener(okBtnListener);

        Button btnOk = mLoadView.findViewById(R.id.button_common_alert_ok);
        btnOk.setOnClickListener(cancelBtnListener);

        mRefundMemo = mLoadView.findViewById(R.id.editText_refund_memo);
        mRefundMemo.setOnFocusChangeListener(mViewFocusChangeListener);

        try {
            mAlertDialog.show();
        }catch (Exception e){
            e.printStackTrace();

        }
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)mContext.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager!=null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void dismiss(){
        if (mAlertDialog != null) {
            mAlertDialog.dismiss();
            mAlertDialog = null;
        }
    }

    public boolean isShown(){
        return mAlertDialog!=null;
    }


    public String getInputText(){
        if (mRefundMemo!=null) {
            return mRefundMemo.getText().toString();
        }
        return "";
    }
}
