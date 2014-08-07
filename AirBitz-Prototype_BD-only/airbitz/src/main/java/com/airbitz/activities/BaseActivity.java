package com.airbitz.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.airbitz.R;

public class BaseActivity extends FragmentActivity {
    private final int DIALOG_TIMEOUT_MILLIS = 15000;
    Handler mHandler;

    private ProgressDialog mProgressDialog;
    public void showModalProgress(final boolean show) {
        if(show) {
            mProgressDialog = ProgressDialog.show(this, null, null);
            mProgressDialog.setContentView(R.layout.layout_modal_indefinite_progress);
            mProgressDialog.setCancelable(false);
            if(mHandler==null)
                mHandler = new Handler();
            mHandler.postDelayed(mProgressDialogKiller, DIALOG_TIMEOUT_MILLIS);
        } else {
            if(mProgressDialog!=null) {
                mProgressDialog.dismiss();
                mProgressDialog = null;
            }
        }
    }

    Runnable mProgressDialogKiller = new Runnable() {
        @Override
        public void run() {
            if(mProgressDialog!=null) {
                mProgressDialog.dismiss();
                showOkMessageDialog(getResources().getString(R.string.string_no_connection_response));
            }
        }
    };

    public void showOkMessageDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this,R.style.AlertDialogCustom));
        builder.setMessage(message)
                .setCancelable(false)
                .setNeutralButton(getResources().getString(R.string.string_ok),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void ShowMessageAlertAndExit(String reason) {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom));
        builder.setMessage(reason)
                .setCancelable(false)
                .setNeutralButton(getResources().getString(R.string.string_ok),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

}
