package io.piano.sample

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.NestedScrollView
import io.piano.android.composer.Composer
import io.piano.android.composer.ComposerException
import io.piano.android.composer.listeners.ShowTemplateListener
import io.piano.android.composer.model.Event
import io.piano.android.composer.model.ExperienceRequest
import io.piano.android.composer.model.events.ShowTemplate
import io.piano.android.composer.showtemplate.ShowTemplateController

class ComposerScrollDepthActivity : AppCompatActivity() {
    private lateinit var nestedScrollView: NestedScrollView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_composer_scroll_depth)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        nestedScrollView = findViewById(R.id.nested_scroll_view)
        val showTemplateListener = ShowTemplateListener { event: Event<ShowTemplate> ->
            event.eventData.delayBy.let {
                if (it.isDelayedByScroll) {
                    val showTemplateController = ShowTemplateController(event)
                    nestedScrollView.setOnScrollChangeListener(
                        object : NestedScrollView.OnScrollChangeListener {
                            private var isShown = false
                            override fun onScrollChange(
                                v: NestedScrollView,
                                scrollX: Int,
                                scrollY: Int,
                                oldScrollX: Int,
                                oldScrollY: Int
                            ) {
                                if (!isShown) {
                                    if (v.scrollY >= it.value) {
                                        showTemplateController.show(this@ComposerScrollDepthActivity)
                                        isShown = true
                                    }
                                }
                            }
                        }
                    )
                }
            }
        }
        val request = ExperienceRequest.Builder()
            .debug(true)
            .build()
        Composer.getInstance().getExperience(
            request,
            listOf(showTemplateListener)
        ) { exception: ComposerException ->
            Toast.makeText(
                this@ComposerScrollDepthActivity,
                "[${Thread.currentThread().name}] ${exception.cause?.message ?: exception.message}",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}
