package chencheng.bwie.com.chencheng_autobanner;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by dell on 2017/12/2.
 */

public class WebViewAcitivity extends AppCompatActivity {
    private WebView mWeb;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.webview);
        initView();
        Intent it=getIntent();
        String  url=it.getStringExtra("url");
    }

    private void initView() {
        mWeb = (WebView) findViewById(R.id.web);
        WebSettings str=mWeb.getSettings();
        str.setJavaScriptEnabled(true);
    }
}
