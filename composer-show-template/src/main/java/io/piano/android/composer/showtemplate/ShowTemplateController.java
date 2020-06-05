package io.piano.android.composer.showtemplate;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;

import androidx.fragment.app.FragmentActivity;

import io.piano.android.composer.model.DelayBy;
import io.piano.android.composer.model.Event;
import io.piano.android.composer.model.events.ShowTemplate;

import static android.view.View.VISIBLE;

public class ShowTemplateController {

    private WebView webView;
    private ShowTemplateDialogFragment fragment;

    private ShowTemplateController() {
    }

    private ShowTemplateController(WebView webView) {
        this.webView = webView;
    }

    private ShowTemplateController(ShowTemplateDialogFragment fragment) {
        this.fragment = fragment;
    }

    public void reloadWithToken(String userToken) {
        String jsSnippet = "javascript:piano.reloadTemplateWithUserToken('" + userToken + "')";

        if (fragment != null) {
            fragment.getWebView().loadUrl(jsSnippet);
        } else {
            webView.loadUrl(jsSnippet);
        }
    }

    public static ShowTemplateController show(final Context context, final Event<ShowTemplate> showTemplateEvent) {
        return show(context, showTemplateEvent, null);
    }

    public static ShowTemplateController show(final Context context, final Event<ShowTemplate> showTemplateEvent, final Object javascriptInterface) {
        switch (showTemplateEvent.eventData.displayMode) {
            case MODAL:
                return showModal(context, showTemplateEvent, javascriptInterface);
            case INLINE:
                return showInline(context, showTemplateEvent, javascriptInterface);
        }

        return null;
    }

    private static ShowTemplateController showModal(final Context context, Event<ShowTemplate> showTemplateEvent, Object javascriptInterface) {
        if (!(context instanceof FragmentActivity)) {
            return null;
        }

        final ShowTemplateDialogFragment fragment = new ShowTemplateDialogFragment();
        ShowTemplate showTemplate = showTemplateEvent.eventData;

        boolean cancelable = showTemplate.showCloseButton;
        if (!cancelable) {
            fragment.setCancelable(false);
        }

        if (javascriptInterface != null) {
            fragment.setJavascriptInterface(javascriptInterface);
        }

        Bundle args = new Bundle();
        args.putString("url", showTemplate.url);
        args.putString("trackingId", showTemplateEvent.eventExecutionContext.trackingId);
        fragment.setArguments(args);

        boolean showImmediately = true;
        if (showTemplate.delayBy.type == DelayBy.DelayType.TIME) {
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

        return new ShowTemplateController(fragment);
    }

    private static ShowTemplateController showInline(final Context context, final Event<ShowTemplate> showTemplateEvent, Object javascriptInterface) {
        ShowTemplate showTemplate = showTemplateEvent.eventData;
        if (TextUtils.isEmpty(showTemplate.containerSelector)) {
            return null;
        }

        int id = context.getResources().getIdentifier(showTemplate.containerSelector, "id", context.getPackageName());
        if (id == 0) {
            return null;
        }

        View view = ((Activity) context).getWindow().getDecorView().findViewById(id);
        if (!(view instanceof WebView)) {
            return null;
        }

        final WebView webView = (WebView) view;
        InitWebViewHelper.initWebView(null, webView, javascriptInterface, showTemplateEvent.eventExecutionContext.trackingId);

        boolean showImmediately = true;
        if (showTemplate.delayBy.type == DelayBy.DelayType.TIME) {
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

        return new ShowTemplateController(webView);
    }

}
