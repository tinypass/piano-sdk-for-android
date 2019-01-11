package io.piano.android.oauth.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;

public class PianoIdOAuthRedirectActivity extends Activity {

    public static final String EXTRA_ACCESS_TOKEN = "accessToken";
    public static final String EXTRA_URL = "url";
    public static final String EXTRA_AID = "aid";

    private static final int RC_CUSTOM_TABS = 5001;

    protected String url;
    protected String aid;

    private boolean customTabsWasOpen = false;
    private boolean customTabsWasClosed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        url = intent.getStringExtra(EXTRA_URL);
        aid = intent.getStringExtra(EXTRA_AID);
        if (url != null && !url.isEmpty() && aid != null && !aid.isEmpty()) {
            customTabsWasOpen = true;
            CustomTabsIntent customTabsIntent = new CustomTabsIntent.Builder().build();
            customTabsIntent.intent.setData(Uri.parse(url));
            startActivityForResult(customTabsIntent.intent, RC_CUSTOM_TABS, customTabsIntent.startAnimationBundle);
        } else {
            finish();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        Uri uri = intent.getData();
        if (Intent.ACTION_VIEW.equals(intent.getAction()) && uri != null) {
            String accessToken = uri.getQueryParameter("token");
            Intent resultIntent = new Intent();
            resultIntent.putExtra(EXTRA_ACCESS_TOKEN, accessToken);
            setResult(RESULT_OK, resultIntent);

            if (customTabsWasOpen && !customTabsWasClosed) {
                finishActivity(RC_CUSTOM_TABS);
            }
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (customTabsWasOpen && customTabsWasClosed) {
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_CUSTOM_TABS) {
            customTabsWasClosed = true;
        }
    }
}
