package io.piano.android.composer.showtemplate;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;

import io.piano.android.composer.Composer;

public class ShowTemplateDialogFragment extends AppCompatDialogFragment {

    private String url;
    private String endpointUrl;
    private String trackingId;

    private WebView webView;

    private Object javascriptInterface;

    public WebView getWebView() {
        return webView;
    }

    public void setJavascriptInterface(Object javascriptInterface) {
        this.javascriptInterface = javascriptInterface;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        url = args.getString("url");
        endpointUrl = args.getString("endpointUrl");
        trackingId = args.getString("trackingId");
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), getTheme());

        View view = LayoutInflater.from(builder.getContext()).inflate(R.layout.fragment_show_template, null);
        webView = (WebView) view;
        InitWebViewHelper.initWebView(this, webView, javascriptInterface, endpointUrl, trackingId);
        builder.setView(view);

        return builder.create();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState == null) {
            webView.loadUrl(url);
        }
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);

        Composer.trackExternalEvent(endpointUrl, trackingId);
    }
}
