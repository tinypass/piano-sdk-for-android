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
 */
class ExperienceRequest private constructor(
    val isDebug: Boolean,
    val customVariables: Map<String, List<String>?>,
    val url: String?,
    val referer: String?,
    val tags: List<String>,
    val keywords: List<String>,
    val zone: String?,
    val title: String?,
    val description: String?,
    val contentId: String?,
    val contentType: String?,
    val contentCreated: String?,
    val contentAuthor: String?,
    val contentSection: String?,
    val contentIsNative: Boolean?,
    val customParameters: CustomParameters?
) {

    data class Builder @JvmOverloads constructor(
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
        var customParameters: CustomParameters? = null
    ) {
        /**
         * Sets "debug" flag for request
         * @param debug Debug value
         * @return Builder instance
         */
        // Public API.
        fun debug(debug: Boolean) = apply { this.debug = debug }

        /**
         * Adds value for custom variable
         * @param key Custom variable key
         * @param value Custom variable value
         * @return Builder instance
         */
        // Public API.
        fun customVariable(
            key: String,
            value: String?
        ) = apply {
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
        // Public API.
        fun customVariable(
            key: String,
            value: List<String>?
        ) = apply {
            customVariables[key] = value
        }

        /**
         * Adds map of custom variables for request
         * @param customVariables Map of custom variables' values by their keys
         * @return Builder instance
         */
        // Public API.
        fun customVariables(customVariables: Map<String, List<String>?>) =
            apply { this.customVariables.putAll(customVariables) }

        /**
         * Clears added to request custom variables
         * @return Builder instance
         */
        // Public API.
        fun clearCustomVariables() = apply { customVariables.clear() }

        /**
         * Sets "url" parameter for request
         * @param url Url value
         * @return Builder instance
         */
        // Public API.
        fun url(url: String) = apply { this.url = url }

        /**
         * Sets "referrer" parameter for request
         * @param referer Referrer value
         * @return Builder instance
         */
        // Public API.
        fun referer(referer: String?) = apply { this.referer = referer }

        /**
         * Adds "tag" parameter to request
         * @param tag Tag value
         * @return Builder instance
         */
        // Public API.
        fun tag(tag: String) = apply { tags.add(tag) }

        /**
         * Adds multiple "tag" parameters to request
         * @param tags Collection of tag values
         * @return Builder instance
         */
        // Public API.
        fun tags(tags: Collection<String>) = apply { this.tags.addAll(tags) }

        /**
         * Adds "keyword" parameter to request
         * @param keyword Keyword value
         * @return Builder instance
         */
        // Public API.
        fun keyword(keyword: String) = apply { keywords.add(keyword) }

        /**
         * Adds multiple "keyword" parameters to request
         * @param keywords Collection of keyword values
         * @return Builder instance
         */
        // Public API.
        fun keywords(keywords: Collection<String>) = apply { this.keywords.addAll(keywords) }

        /**
         * Sets "zone" parameter for request
         * @param zone Zone value
         * @return Builder instance
         */
        // Public API.
        fun zone(zone: String?) = apply { this.zone = zone }

        /**
         * Sets "title" parameter for request
         * @param title Title value
         * @return Builder instance
         */
        // Public API.
        fun title(title: String?) = apply { this.title = title }

        /**
         * Sets "description" parameter for request
         * @param description Description value
         * @return Builder instance
         */
        // Public API.
        fun description(description: String?) = apply { this.description = description }

        /**
         * Sets "content id" parameter for request
         * @param contentId Content Id
         * @return Builder instance
         */
        // Public API.
        fun contentId(contentId: String?) = apply { this.contentId = contentId }

        /**
         * Sets "content type" parameter for request
         * @param contentType Content Type
         * @return Builder instance
         */
        // Public API.
        fun contentType(contentType: String?) = apply { this.contentType = contentType }

        /**
         * Sets "content created" parameter for request
         * @param contentCreated ISO 8601-formatted string that includes the published date and time of the content
         * @return Builder instance
         */
        // Public API.
        fun contentCreated(contentCreated: String?) = apply { this.contentCreated = contentCreated }

        /**
         * Sets "content created" parameter for request
         * @param contentCreated Content created date value
         * @return Builder instance
         */
        // Public API.
        fun contentCreated(contentCreated: Date) = apply { this.contentCreated = DATE_FORMAT.format(contentCreated) }

        /**
         * Sets "content author" parameter for request
         * @param contentAuthor Content author value
         * @return Builder instance
         */
        // Public API.
        fun contentAuthor(contentAuthor: String?) = apply { this.contentAuthor = contentAuthor }

        /**
         * Sets "content section" parameter for request
         * @param contentSection Content section value
         * @return Builder instance
         */
        // Public API.
        fun contentSection(contentSection: String?) = apply { this.contentSection = contentSection }

        /**
         * Sets "content is native" flag for request
         * @param contentIsNative True, if content is native, otherwise False
         * @return Builder instance
         */
        // Public API.
        fun contentIsNative(contentIsNative: Boolean?) = apply { this.contentIsNative = contentIsNative }

        /**
         * Sets custom parameters for request
         * @param customParameters Custom parameters object
         * @return Builder instance
         */
        // Public API.
        fun customParams(customParameters: CustomParameters?) = apply { this.customParameters = customParameters }

        /**
         * Builds request
         * @return ExperienceRequest instance
         */
        // Public API.
        fun build(): ExperienceRequest {
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
                customParameters
            )
        }
    }

    companion object {
        private const val ISO_8601 = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
        val DATE_FORMAT: DateFormat =
            SimpleDateFormat(ISO_8601, Locale.US)
    }
}
