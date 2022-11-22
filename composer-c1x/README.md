# Piano Composer C1X integration for Android
![Maven Central](https://img.shields.io/maven-central/v/io.piano.android/composer-c1x)

## Getting started
This is C1X integration artifact for Composer

### Dependencies

The Piano Composer C1X is available as an AAR via Maven Central. To add dependencies, open your project’s build.gradle/build.gradle.kts and update the dependencies block as follows:

Kotlin DSL
```kotlin
dependencies {
    implementation("io.piano.android:composer-c1x:$VERSION")
}
```

### Usage
1. Replace your `Composer.init(context, aid, endpoint)` with `PianoC1X.init(context, siteId, aid, endpoint)`, where `siteId` is Cxense Site ID
1. Add `url` parameter to all your experience's requests to Composer
1. Don't send Cxense PV events from screens with Composer

Add code into your show recommendations listener for displaying via built-in controller

Kotlin
```kotlin
ShowRecommendationsController(event).show(activity)
```

### Show recommendations by your own
You can implement displaying recommendations by your own for complex cases. For example, displaying recommendations in `RecyclerView`:
```kotlin
class RecommendationsAdapter(
    private val clickListener: (WidgetItem, String) -> Unit
): ListAdapter<WidgetItem, RecommendationViewHolder>(DIFF_CALLBACK) {
    private var trackingId = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RecommendationViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recommendation_item, parent)) {
            clickListener(it, trackingId)
        }

    override fun onBindViewHolder(holder: RecommendationViewHolder, position: Int) {
        holder.updateView(getItem(position))
    }

    fun submitRecommendations(trackingId: String, data: List<WidgetItem>?) {
        this.trackingId = trackingId
        submitList(data)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<WidgetItem>() {
            override fun areItemsTheSame(oldItem: WidgetItem, newItem: WidgetItem): Boolean {
                // implement it for your case
                TODO("Not yet implemented")
            }

            override fun areContentsTheSame(oldItem: WidgetItem, newItem: WidgetItem): Boolean {
                // implement it for your case
                TODO("Not yet implemented")
            }

        }
    }
}

class RecommendationViewHolder(
    containerView: View,
    clickListener: (WidgetItem) -> Unit
): RecyclerView.ViewHolder(containerView) {
    lateinit var item: WidgetItem

    init {
        containerView.setOnClickListener { clickListener(item) }
    }

    fun updateView(widgetItem: WidgetItem) {
        item = widgetItem
        // update items
    }
}
```

```kotlin
class RecommendationsDisplayActivity : AppCompatActivity() {
    val adapter = RecommendationsAdapter { item, trackingId ->
        // tracking for C1X
        CxenseSdk.getInstance().trackClick(item, object : LoadCallback<Unit> {
            override fun onError(throwable: Throwable) {
                Timber.e(throwable, "Can't track click on recommendation")
            }
            override fun onSuccess(data: Unit) {
                Timber.d("Tracked click on recommendation")
            }
        })
        Composer.getInstance().trackRecommendationsClick(trackingId, item.url)
        // process recommendation click. for example, start playing video, display news, etc...
    }
    val recommendationsListener = ShowRecommendationsListener { event: Event<ShowRecommendations> ->
        CxenseSdk.getInstance().loadWidgetRecommendations(
            widgetId = event.eventData.widgetId,
            experienceId = event.eventExecutionContext.experienceId,
            callback = object : LoadCallback<List<WidgetItem>> {
                override fun onError(throwable: Throwable) {
                    // process error at loading recommendations here
                    Timber.e(throwable, "Can't load widget recommendations")
                }
                override fun onSuccess(data: List<WidgetItem>) {
                    // recommendations are loaded, we can show them, for example via RecyclerView
                    val trackingId = event.eventExecutionContext.trackingId
                    adapter.submitRecommendations(trackingId, data)
                    Composer.getInstance().trackRecommendationsDisplay(trackingId)
                }
            }
        )
    }
    // other code here
}
```

