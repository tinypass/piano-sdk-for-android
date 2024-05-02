package io.piano.android.composer.model

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Collections
import java.util.Date
import java.util.Locale

/**
 * @property isDebug "Debug" value
 * @property customVariables Custom variables value
 * @property url Url value
 * @property referer Referer value
 * @property tags Tags value
 * @property keywords Keywords value
 * @property zone Zone value
 * @property title Title value
 * @property description Description value
 * @property contentId Content id
 * @property contentType Content type
 * @property contentCreated Content created value
 * @property contentAuthor Content author value
 * @property contentSection Content section value
 * @property contentIsNative Content is native flag
 * @property customParameters Custom parameters
 * @property edgeResult Edge result
 */
public class ExperienceRequest private constructor(
    public val isDebug: Boolean,
    public val customVariables: Map<String, List<String>?>,
    public val url: String?,
    public val referer: String?,
    public val tags: List<String>,
    public val keywords: List<String>,
    public val zone: String?,
    public val title: String?,
    public val description: String?,
    public val contentId: String?,
    public val contentType: String?,
    public val contentCreated: String?,
    public val contentAuthor: String?,
    public val contentSection: String?,
    public val contentIsNative: Boolean?,
    public val customParameters: CustomParameters?,
    public val edgeResult: EdgeResult?,
) {

    public data class Builder @JvmOverloads constructor(
        var debug: Boolean = false,
        var customVariables: MutableMap<String, List<String>?> = mutableMapOf(),
        var url: String? = null,
        var referer: String? = null,
        var tags: MutableList<String> = mutableListOf(),
        var keywords: MutableList<String> = mutableListOf(),
        var zone: String? = null,
        var title: String? = null,
        var description: String? = null,
        var contentId: String? = null,
        var contentType: String? = null,
        var contentCreated: String? = null,
        var contentAuthor: String? = null,
        var contentSection: String? = null,
        var contentIsNative: Boolean? = null,
        var customParameters: CustomParameters? = null,
        var edgeResult: EdgeResult? = null,
    ) {
        /**
         * Sets "debug" flag for request
         * @param debug Debug value
         * @return Builder instance
         */
        @Suppress("unused") // Public API.
        public fun debug(debug: Boolean): Builder = apply { this.debug = debug }

        /**
         * Adds value for custom variable
         * @param key Custom variable key
         * @param value Custom variable value
         * @return Builder instance
         */
        @Suppress("unused") // Public API.
        public fun customVariable(key: String, value: String?): Builder = apply {
            customVariables[key] = value?.let { v ->
                (customVariables[key]?.toMutableList() ?: mutableListOf()).apply { add(v) }
            }
        }

        /**
         * Adds custom variable to request
         * @param key Custom variable key
         * @param value Custom variable value
         * @return Builder instance
         */
        @Suppress("unused") // Public API.
        public fun customVariable(key: String, value: List<String>?): Builder = apply {
            customVariables[key] = value
        }

        /**
         * Adds map of custom variables for request
         * @param customVariables Map of custom variables' values by their keys
         * @return Builder instance
         */
        @Suppress("unused") // Public API.
        public fun customVariables(customVariables: Map<String, List<String>?>): Builder =
            apply { this.customVariables.putAll(customVariables) }

        /**
         * Clears added to request custom variables
         * @return Builder instance
         */
        @Suppress("unused") // Public API.
        public fun clearCustomVariables(): Builder = apply { customVariables.clear() }

        /**
         * Sets "url" parameter for request
         * @param url Url value
         * @return Builder instance
         */
        @Suppress("unused") // Public API.
        public fun url(url: String): Builder = apply { this.url = url }

        /**
         * Sets "referrer" parameter for request
         * @param referer Referrer value
         * @return Builder instance
         */
        @Suppress("unused") // Public API.
        public fun referer(referer: String?): Builder = apply { this.referer = referer }

        /**
         * Adds "tag" parameter to request
         * @param tag Tag value
         * @return Builder instance
         */
        @Suppress("unused") // Public API.
        public fun tag(tag: String): Builder = apply { tags.add(tag) }

        /**
         * Adds multiple "tag" parameters to request
         * @param tags Collection of tag values
         * @return Builder instance
         */
        @Suppress("unused") // Public API.
        public fun tags(tags: Collection<String>): Builder = apply { this.tags.addAll(tags) }

        /**
         * Adds "keyword" parameter to request
         * @param keyword Keyword value
         * @return Builder instance
         */
        @Suppress("unused") // Public API.
        public fun keyword(keyword: String): Builder = apply { keywords.add(keyword) }

        /**
         * Adds multiple "keyword" parameters to request
         * @param keywords Collection of keyword values
         * @return Builder instance
         */
        @Suppress("unused") // Public API.
        public fun keywords(keywords: Collection<String>): Builder = apply { this.keywords.addAll(keywords) }

        /**
         * Sets "zone" parameter for request
         * @param zone Zone value
         * @return Builder instance
         */
        @Suppress("unused") // Public API.
        public fun zone(zone: String?): Builder = apply { this.zone = zone }

        /**
         * Sets "title" parameter for request
         * @param title Title value
         * @return Builder instance
         */
        @Suppress("unused") // Public API.
        public fun title(title: String?): Builder = apply { this.title = title }

        /**
         * Sets "description" parameter for request
         * @param description Description value
         * @return Builder instance
         */
        @Suppress("unused") // Public API.
        public fun description(description: String?): Builder = apply { this.description = description }

        /**
         * Sets "content id" parameter for request
         * @param contentId Content Id
         * @return Builder instance
         */
        @Suppress("unused") // Public API.
        public fun contentId(contentId: String?): Builder = apply { this.contentId = contentId }

        /**
         * Sets "content type" parameter for request
         * @param contentType Content Type
         * @return Builder instance
         */
        @Suppress("unused") // Public API.
        public fun contentType(contentType: String?): Builder = apply { this.contentType = contentType }

        /**
         * Sets "content created" parameter for request
         * @param contentCreated ISO 8601-formatted string that includes the published date and time of the content
         * @return Builder instance
         */
        @Suppress("unused") // Public API.
        public fun contentCreated(contentCreated: String?): Builder = apply { this.contentCreated = contentCreated }

        /**
         * Sets "content created" parameter for request
         * @param contentCreated Content created date value
         * @return Builder instance
         */
        @Suppress("unused") // Public API.
        public fun contentCreated(contentCreated: Date): Builder = apply {
            this.contentCreated = DATE_FORMAT.format(contentCreated)
        }

        /**
         * Sets "content author" parameter for request
         * @param contentAuthor Content author value
         * @return Builder instance
         */
        @Suppress("unused") // Public API.
        public fun contentAuthor(contentAuthor: String?): Builder = apply { this.contentAuthor = contentAuthor }

        /**
         * Sets "content section" parameter for request
         * @param contentSection Content section value
         * @return Builder instance
         */
        @Suppress("unused") // Public API.
        public fun contentSection(contentSection: String?): Builder = apply { this.contentSection = contentSection }

        /**
         * Sets "content is native" flag for request
         * @param contentIsNative True, if content is native, otherwise False
         * @return Builder instance
         */
        @Suppress("unused") // Public API.
        public fun contentIsNative(contentIsNative: Boolean?): Builder = apply {
            this.contentIsNative = contentIsNative
        }

        /**
         * Sets custom parameters for request
         * @param customParameters Custom parameters object
         * @return Builder instance
         */
        @Suppress("unused") // Public API.
        public fun customParams(customParameters: CustomParameters?): Builder = apply {
            this.customParameters = customParameters
        }

        /**
         * Sets result from Edge CDN
         * @param edgeResult Edge result object
         * @return Builder instance
         */
        @Suppress("unused") // Public API.
        public fun edgeResult(edgeResult: EdgeResult?): Builder = apply { this.edgeResult = edgeResult }

        /**
         * Builds request
         * @return ExperienceRequest instance
         */
        @Suppress("unused") // Public API.
        public fun build(): ExperienceRequest {
            return ExperienceRequest(
                debug,
                Collections.unmodifiableMap(customVariables),
                url,
                referer,
                Collections.unmodifiableList(tags),
                Collections.unmodifiableList(keywords),
                zone,
                title,
                description,
                contentId,
                contentType,
                contentCreated,
                contentAuthor,
                contentSection,
                contentIsNative,
                customParameters,
                edgeResult
            )
        }
    }

    public companion object {
        private const val ISO_8601 = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
        public val DATE_FORMAT: DateFormat =
            SimpleDateFormat(ISO_8601, Locale.US)
    }
}
