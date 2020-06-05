package io.piano.android.composer.showtemplate;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import io.piano.android.composer.Composer;

public class ShowTemplateDialogFragment extends AppCompatDialogFragment {

    private String url;
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
        trackingId = args.getString("trackingId");
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), getTheme());

        View view = LayoutInflater.from(builder.getContext()).inflate(R.layout.fragment_show_template, null);
        webView = (WebView) view;
        InitWebViewHelper.initWebView(this, webView, javascriptInterface, trackingId);
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

        Composer.getInstance().trackExternalEvent(trackingId);

        if (javascriptInterface instanceof ClosableJs) {
            ClosableJs closableJs = (ClosableJs) javascriptInterface;
            closableJs.closeOverridden(null);
        }
    }
}
