package io.piano.android.composer.showtemplate;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;

import io.piano.android.composer.Composer;
import io.piano.android.composer.model.DelayBy;
import io.piano.android.composer.model.ShowTemplate;

import static android.view.View.VISIBLE;
import static io.piano.android.composer.model.ShowTemplate.DISPLAY_MODE_INLINE;
import static io.piano.android.composer.model.ShowTemplate.DISPLAY_MODE_MODAL;

public class ShowTemplateDialogFragment extends AppCompatDialogFragment {

    private static final String JAVASCRIPT_INTERFACE = "PianoAndroid";

    private String url;
    private String endpointUrl;
    private String trackingId;

    private WebView webView;

    private Object javascriptInterface;

    private void setJavascriptInterface(Object javascriptInterface) {
        this.javascriptInterface = javascriptInterface;
    }

    public static ShowTemplateDialogFragment show(final Context context, final ShowTemplate showTemplate) {
        return show(context, showTemplate, null);
    }

    public static ShowTemplateDialogFragment show(final Context context, final ShowTemplate showTemplate, final Object javascriptInterface) {
        if (DISPLAY_MODE_MODAL.equals(showTemplate.displayMode)) {
            return showModal(context, showTemplate, javascriptInterface);
        } else if (DISPLAY_MODE_INLINE.equals(showTemplate.displayMode)) {
            showInline(context, showTemplate, javascriptInterface);
        }

        return null;
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
        initWebView(this, webView, javascriptInterface, endpointUrl, trackingId);
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

    private static void initWebView(DialogFragment dialogFragment, WebView webView, Object javascriptInterface, String endpointUrl, String trackingId) {
        webView.getSettings().setJavaScriptEnabled(true);

        if (javascriptInterface == null) {
            if (dialogFragment == null) {
                javascriptInterface = new ComposerJs(webView);
            } else {
                javascriptInterface = new ComposerJs(dialogFragment);
            }
        } else if (javascriptInterface instanceof ComposerJs) {
            ComposerJs composerJs = (ComposerJs) javascriptInterface;
            composerJs.dialogFragment = dialogFragment;
            composerJs.webView = webView;
        }

        if (javascriptInterface instanceof ComposerJs) {
            ComposerJs composerJs = (ComposerJs) javascriptInterface;
            composerJs.init(endpointUrl, trackingId);
        }

        webView.addJavascriptInterface(javascriptInterface, JAVASCRIPT_INTERFACE);
    }

    private static ShowTemplateDialogFragment showModal(final Context context, ShowTemplate showTemplate, Object javascriptInterface) {
        if (!(context instanceof FragmentActivity)) {
            return null;
        }

        final ShowTemplateDialogFragment fragment = new ShowTemplateDialogFragment();

        boolean cancelable = showTemplate.showCloseButton;
        if (!cancelable) {
            fragment.setCancelable(false);
        }

        if (javascriptInterface != null) {
            fragment.setJavascriptInterface(javascriptInterface);
        }

        Bundle args = new Bundle();
        args.putString("url", showTemplate.url);
        args.putString("endpointUrl", showTemplate.endpointUrl);
        args.putString("trackingId", showTemplate.eventExecutionContext.trackingId);
        fragment.setArguments(args);

        boolean showImmediately = true;
        if (DelayBy.TYPE_TIME.equals(showTemplate.delayBy.type)) {
            final int delaySecs = showTemplate.delayBy.value;
            if (delaySecs > 0) {
                showImmediately = false;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        FragmentActivity activity = (FragmentActivity) context;
                        if (!activity.isFinishing()) {
                            fragment.show(activity.getSupportFragmentManager(), "ShowTemplateDialogFragment");
                        }
                    }
                }, delaySecs * 1000);
            }
        }

        if (showImmediately) {
            fragment.show(((FragmentActivity) context).getSupportFragmentManager(), "ShowTemplateDialogFragment");
        }

        return fragment;
    }

    private static void showInline(final Context context, final ShowTemplate showTemplate, Object javascriptInterface) {
        if (TextUtils.isEmpty(showTemplate.containerSelector)) {
            return;
        }


        int id = context.getResources().getIdentifier(showTemplate.containerSelector, "id", context.getPackageName());
        if (id == 0) {
            return;
        }

        View view = ((Activity) context).getWindow().getDecorView().findViewById(id);
        if (!(view instanceof WebView)) {
            return;
        }

        final WebView webView = (WebView) view;
        initWebView(null, webView, javascriptInterface, showTemplate.endpointUrl, showTemplate.eventExecutionContext.trackingId);

        boolean showImmediately = true;
        if (DelayBy.TYPE_TIME.equals(showTemplate.delayBy.type)) {
            final int delaySecs = showTemplate.delayBy.value;
            if (delaySecs > 0) {
                showImmediately = false;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (!((Activity) context).isFinishing()) {
                            webView.loadUrl(showTemplate.url);
                            webView.setVisibility(VISIBLE);
                        }
                    }
                }, delaySecs * 1000);
            }
        }

        if (showImmediately) {
            webView.loadUrl(showTemplate.url);
            webView.setVisibility(VISIBLE);
        }
    }
}
