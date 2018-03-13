package com.jordifierro.androidbase.presentation.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.jordifierro.androidbase.presentation.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewFragment extends Fragment {

    @BindView(R.id.webview)
    WebView webView;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_webview, container, false);
        ButterKnife.bind(this, fragmentView);
        this.initWebView();
        return fragmentView;
    }

    private void initWebView() {
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int progress) {
                if (progress == 100) WebViewFragment.this.hideProgressBar();
            }
        });
        webView.loadUrl(((Listener)getActivity()).getUrl());
    }

    private void hideProgressBar() {
        this.progressBar.setVisibility(View.GONE);
    }

    public interface Listener {
        String getUrl();
    }

    public WebView getWebView() {
        return webView;
    }
}
