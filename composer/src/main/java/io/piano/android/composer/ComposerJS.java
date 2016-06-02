package io.piano.android.composer;

import android.support.v4.app.DialogFragment;
import android.view.View;
import android.webkit.JavascriptInterface;

public class ComposerJS {

    public static final String NAME = "ComposerJS";

    private DialogFragment dialogFragment;
    private View view;

    public ComposerJS(DialogFragment dialogFragment) {
        this.dialogFragment = dialogFragment;
    }

    public ComposerJS(View view) {
        this.view = view;
    }

    @JavascriptInterface
    public void close() {
        if (dialogFragment != null) {
            dialogFragment.dismiss();
        }

        if (view != null) {
            view.setVisibility(View.GONE);
        }
    }
}
