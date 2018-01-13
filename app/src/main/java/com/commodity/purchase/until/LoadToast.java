package com.commodity.purchase.until;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.commodity.purchase.R;



public  class LoadToast {
    private TextView mytoast_tv;
    private Context context;
    private LayoutInflater inflater;
    private Dialog dialog;
    private String title="正在加载";

    public LoadToast(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        init();
    }
    public LoadToast(Context context, String  title) {
        this.title = title;

        this.context = context;
        inflater = LayoutInflater.from(context);
        init();
    }
    public void init() {
        dialog = new Dialog(context,R.style.LoadStyle);
        View view = inflater.inflate(R.layout.mytoast, null);
        mytoast_tv = (TextView) view.findViewById(R.id.mytoast_tv);
        mytoast_tv.setText(title);
        dialog.setContentView(view);
    }

    public void setCanceListener(DialogInterface.OnDismissListener listener) {
        dialog.setOnDismissListener(listener);
    }

    public void setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
    }



    public enum Types {
        OK(1000, 0), ERREY(1500, 1), NOTI(1500, 2), GO(0, 3);

        private Types(int time, int indexs) {
            this.indexs = indexs;
            this.time = time;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        private int time;
        private int indexs;

        public int getIndexs() {
            return indexs;
        }

        public void setIndexs(int indexs) {
            this.indexs = indexs;
        }

    }

    public void show() {
       dialog.show();
    }

    public void dissmiss() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }




    public boolean isShow() {
        if (dialog == null) {
            return false;
        }
        else {
            return dialog.isShowing();
        }
    }
}
