package io.piano.android.sample.feature.composer;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;

import java.util.Collections;

import io.piano.android.composer.Composer;
import io.piano.android.composer.listeners.ShowTemplateListener;
import io.piano.android.composer.model.Event;
import io.piano.android.composer.model.ExperienceRequest;
import io.piano.android.composer.model.events.ShowTemplate;
import io.piano.android.composer.showtemplate.ShowTemplateController;
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
            public void onExecuted(@NonNull Event<ShowTemplate> event) {
                ShowTemplate showTemplate = event.eventData;
                if (showTemplate.delayBy.isDelayedByScroll()) {
                    nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {

                        private boolean isShown;

                        @Override
                        public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                            if (!isShown) {
                                if (v.getScrollY() >= showTemplate.delayBy.value) {
                                    ShowTemplateController.show(ComposerScrollDepthActivity.this, event);
                                    isShown = true;
                                }
                            }
                        }
                    });
                }
            }
        };

        ExperienceRequest request = new ExperienceRequest.Builder()
                .debug(true)
                .build();

        Composer.getInstance().getExperience(request, Collections.singletonList(showTemplateListener), exception -> {
            String message = String.format("[%s] %s", Thread.currentThread().getName(), exception.getCause() == null ? exception.getMessage() : exception.getCause().getMessage());
            Toast.makeText(
                    ComposerScrollDepthActivity.this, message, Toast.LENGTH_LONG
            ).show();
        });
    }
}
