package io.piano.android.sample.feature.composer;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import io.piano.android.composer.Composer;
import io.piano.android.composer.ShowTemplateListener;
import io.piano.android.composer.model.ShowTemplate;
import io.piano.android.composer.showtemplate.ShowTemplateController;
import io.piano.android.sample.BuildConfig;
import io.piano.android.sample.R;

public class ComposerScrollDepthActivity extends AppCompatActivity {

    private NestedScrollView nestedScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_composer_scroll_depth);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nestedScrollView = findViewById(R.id.nested_scroll_view);

        ShowTemplateListener showTemplateListener = new ShowTemplateListener() {
            @Override
            public void onExecuted(final ShowTemplate showTemplate) {
                if (showTemplate.isDelayedByScroll()) {
                    nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {

                        private boolean isShown;

                        @Override
                        public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                            if (!isShown) {
                                if (v.getScrollY() >= showTemplate.delayBy.value) {
                                    ShowTemplateController.show(ComposerScrollDepthActivity.this, showTemplate);
                                    isShown = true;
                                }
                            }
                        }
                    });
                }
            }
        };

        new Composer(this, BuildConfig.PIANO_AID, BuildConfig.DEBUG)
                .addListener(showTemplateListener)
                .execute();
    }
}
