package com.jordifierro.androidbase.presentation.view.fragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.jordifierro.androidbase.presentation.R;
import com.jordifierro.androidbase.presentation.view.activity.base.WebViewActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WebViewFragment extends Fragment {

    @Bind(R.id.webview)
    WebView webView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_webview, container, false);
        ButterKnife.bind(this, fragmentView);
        this.initWebView();
        return fragmentView;
    }

    private void initWebView(){
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int progress) {
                WebViewActivity activity = (WebViewActivity) getActivity();
                if (activity != null) {
                    if (!activity.isLoaderShowing()) activity.showLoader();
                    if (progress == 100) activity.hideLoader();
                }
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(((Listener)getActivity()).getUrl());
    }

    public interface Listener {
        String getUrl();
    }

    public WebView getWebView() {
        return webView;
    }
}
