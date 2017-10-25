package com.accolite.newsapplication.ui.base;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.accolite.newsapplication.R;
import com.accolite.newsapplication.network.ResponseCodes;
import com.accolite.newsapplication.utils.NetworkUtils;

public abstract class BaseActivity extends AppCompatActivity implements BaseView{

    private ProgressDialog mProgressDialog;
    protected AlertDialog mAlertDialog;

    @Override
    public void showLoading() {
        if(mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.show();
        } else if(!mProgressDialog.isShowing()){
            mProgressDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if(mProgressDialog !=null){
            mProgressDialog.hide();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(getPresenter()!=null){
            getPresenter().onStart();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(getPresenter()!=null){
            getPresenter().onStop();
        }
    }

    protected abstract BasePresenter getPresenter();

    @Override
    public void showError(String status) {
        String message, title;
        switch (status) {
            case ResponseCodes.NOT_FOUND:
                message = getString(R.string.not_found);
                title = getString(R.string.error);
                break;
            default:
                message = "Something went wrong: " + status;
                title = getString(R.string.error);
                break;
        }

        if (mAlertDialog == null) {
            AlertDialog.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
            } else {
                builder = new AlertDialog.Builder(this);
            }
                mAlertDialog = builder
                        .setTitle(title)
                        .setMessage(message)
                    .setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            retry();
                        }
                    })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                onCancelErrorDialog(dialog);
                            }
                        })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        } else {
            mAlertDialog.setMessage(message);
            mAlertDialog.setTitle(title);
            mAlertDialog.show();
        }
    }

    protected abstract void retry();

    protected abstract void onCancelErrorDialog(DialogInterface dialog);

    @Override
    public boolean isNetworkAvailable() {
        return NetworkUtils.isNetworkAvailable(this);
    }
}
