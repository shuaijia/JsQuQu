package com.mykj.qupingfang.discover.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import com.bumptech.glide.Glide;
import com.mykj.qupingfang.R;
import com.mykj.qupingfang.adapter.home.DiscoverAdapter;
import com.mykj.qupingfang.adapter.home.HomeBannerAdapter;
import com.mykj.qupingfang.discover.activity.DiscoverActivity;
import com.mykj.qupingfang.domain.home.HomeJp;
import com.mykj.qupingfang.net.retrofit.HttpMethod;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

import static android.webkit.WebSettings.LOAD_CACHE_ELSE_NETWORK;

/**
 * Created by Administrator on 2017/7/23.
 */

public class DiscoverFragment extends Fragment {
    private static final String TAG = "DiscoverFragment";

    private TextView tv_discover_title;
    private ProgressBar pb_discover_progress;
    private WebView wv_discover_content;

    private Context context;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_discover_second, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv_discover_title = (TextView) view.findViewById(R.id.tv_discover_title);
        pb_discover_progress = (ProgressBar) view.findViewById(R.id.pb_discover_progress);
        wv_discover_content = (WebView) view.findViewById(R.id.wv_discover_content);
        wv_discover_content.getSettings().setJavaScriptEnabled(true);
        wv_discover_content.getSettings().setCacheMode(LOAD_CACHE_ELSE_NETWORK);
        wv_discover_content.setWebViewClient(new MyWebViewClient());
        wv_discover_content.setWebChromeClient(new MyWebViewChrome());
        wv_discover_content.loadUrl("http://api.lovek12.com/index.php?r=discovery/index&v=198###");
    }

    @Override
    public void onResume() {
        super.onResume();
//        wv_discover_content.reload();
    }

    public class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Intent intent = new Intent();
            intent.putExtra("url", url);
            intent.setClass(context, DiscoverActivity.class);
            context.startActivity(intent);
            return true;
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
