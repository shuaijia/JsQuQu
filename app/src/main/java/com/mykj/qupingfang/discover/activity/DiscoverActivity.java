package com.mykj.qupingfang.discover.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mykj.qupingfang.R;

import org.zackratos.ultimatebar.UltimateBar;

import static android.webkit.WebSettings.LOAD_CACHE_ELSE_NETWORK;

public class DiscoverActivity extends Activity implements View.OnClickListener{
    private ImageView iv_discover_back;
    private TextView tv_discover_title;
    private WebView wv_discover_content;
    private ProgressBar pb_discover_progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);

        UltimateBar ultimateBar = new UltimateBar(this);
        ultimateBar.setColorBar(ContextCompat.getColor(this, R.color.green_title_discoverbc));

        iv_discover_back= (ImageView) findViewById(R.id.iv_discover_back);
        iv_discover_back.setOnClickListener(this);
        tv_discover_title = (TextView) findViewById(R.id.tv_discover_title);
        pb_discover_progress = (ProgressBar) findViewById(R.id.pb_discover_progress);
        wv_discover_content = (WebView) findViewById(R.id.wv_discover_content);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        wv_discover_content.getSettings().setJavaScriptEnabled(true);
        wv_discover_content.getSettings().setCacheMode(LOAD_CACHE_ELSE_NETWORK);
        wv_discover_content.setWebViewClient(new MyWebViewClient());
        wv_discover_content.setWebChromeClient(new MyWebViewChrome());
        wv_discover_content.loadUrl(url);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_discover_back:
                if (wv_discover_content.canGoBack()){
                    wv_discover_content.goBack();
                }else {
                    finish();
                }
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            if (wv_discover_content.canGoBack()){
                wv_discover_content.goBack();
            }else {
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    public class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            pb_discover_progress.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            pb_discover_progress.setVisibility(View.GONE);

        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
        }
    }

    public class MyWebViewChrome extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            if (newProgress != 100){
                pb_discover_progress.setVisibility(View.VISIBLE);
                pb_discover_progress.setProgress(newProgress);
            }else {
                pb_discover_progress.setProgress(newProgress);
                pb_discover_progress.setVisibility(View.GONE);
            }
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            tv_discover_title.setText(title);
        }

        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            return super.onJsAlert(view, url, message, result);
        }

        @Override
        public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
            return super.onJsConfirm(view, url, message, result);
        }

        @Override
        public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
            return super.onJsPrompt(view, url, message, defaultValue, result);
        }

        @Override
        public boolean onJsTimeout() {
            return super.onJsTimeout();
        }

        @Override
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
            return super.onShowFileChooser(webView, filePathCallback, fileChooserParams);
        }
    }
}
