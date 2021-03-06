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
 * @property zone Zone value
 * @property contentCreated Content created value
 * @property contentAuthor Content author value
 * @property contentSection Content section value
 * @property contentIsNative Content is native flag
 * @property customParameters Custom parameters
 */
class ExperienceRequest private constructor(
    val isDebug: Boolean,
    val customVariables: Map<String, String?>,
    val url: String?,
    val referer: String?,
    val tags: List<String>,
    val zone: String?,
    val contentCreated: String?,
    val contentAuthor: String?,
    val contentSection: String?,
    val contentIsNative: Boolean?,
    val customParameters: CustomParameters?
) {

    data class Builder @JvmOverloads constructor(
        var debug: Boolean = false,
        var customVariables: MutableMap<String, String?> = mutableMapOf(),
        var url: String? = null,
        var referer: String? = null,
        var tags: MutableList<String> = mutableListOf(),
        var zone: String? = null,
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
         * Adds custom variable to request
         * @param key Custom variable key
         * @param value Custom variable value
         * @return Builder instance
         */
        // Public API.
        fun customVariable(
            key: String,
            value: String?
        ) = apply { customVariables[key] = value }

        /**
         * Adds map of custom variables for request
         * @param customVariables Map of custom variables' values by their keys
         * @return Builder instance
         */
        // Public API.
        fun customVariables(customVariables: Map<String, String?>) =
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
         * Sets "zone" parameter for request
         * @param zone Zone value
         * @return Builder instance
         */
        // Public API.
        fun zone(zone: String?) = apply { this.zone = zone }

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
                zone,
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
