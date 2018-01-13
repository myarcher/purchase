package com.commodity.purchase.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.commodity.purchase.R;
import com.commodity.purchase.net.AppNetApi;
import com.commodity.purchase.net.NetResponseListener;

public class MainActivity extends AppCompatActivity {
 private TextView test_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test_tv= (TextView) findViewById(R.id.test_tv);
    }

    @Override
    protected void onResume() {
        super.onResume();
        test();
    }
    private void test(){
        AppNetApi.get(this)
                .UrlAndWhat("http://192.168.1.182:8080/TestServlet",1)
                .with("status","1")
                .setOkListener(new NetResponseListener.NetOkListener() {
                    @Override
                    public void onSuccess(Object response, String mess, int requestCode) {
                        test_tv.setText(response+"");
                    }
                }).setFaildListener(null)
                .POST();
    }
}
