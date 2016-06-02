package io.piano.android.composer;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebView;

public class WebViewDialogFragment extends AppCompatDialogFragment {

    private String baseUrl;
    private String html;

    public static WebViewDialogFragment newInstance(String baseUrl, String html) {
        WebViewDialogFragment fragment = new WebViewDialogFragment();
        Bundle args = new Bundle();
        args.putString("baseUrl", baseUrl);
        args.putString("html", html);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        baseUrl = args.getString("baseUrl");
        html = args.getString("html");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AppCompatDialog dialog = (AppCompatDialog) super.onCreateDialog(savedInstanceState);
        dialog.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_template);

        WebView webView = (WebView) dialog.findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new ComposerJS(this), ComposerJS.NAME);
        webView.loadDataWithBaseURL(baseUrl, html, null, null, null);

        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();

        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }
}
