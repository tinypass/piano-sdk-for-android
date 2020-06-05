package io.piano.android.composer.model;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Keep
public class ExperienceRequest {
    static final String ISO_8601 = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    static final DateFormat DATE_FORMAT = new SimpleDateFormat(ISO_8601, Locale.US);

    private final boolean debug;
    private final Map<String, String> customVariables;
    private final String url;
    private final String referer;
    private final List<String> tags;
    private final String zone;
    private final String contentCreated;
    private final String contentAuthor;
    private final String contentSection;
    private final Boolean contentIsNative;
    private final CustomParameters customParameters;

    public static class Builder {
        boolean debug;
        Map<String, String> customVariables = new HashMap<>();
        String url;
        String referer;
        List<String> tags = new ArrayList<>();
        String zone;
        String contentCreated;
        String contentAuthor;
        String contentSection;
        Boolean contentIsNative;
        CustomParameters customParameters;

        @SuppressWarnings({"UnusedDeclaration", "WeakerAccess"}) // Public API.
        public Builder() {
        }

        /**
         * Sets "debug" flag for request
         * @param debug Debug value
         * @return Builder instance
         */
        @SuppressWarnings({"UnusedDeclaration", "WeakerAccess"}) // Public API.
        public Builder debug(boolean debug) {
            this.debug = debug;
            return this;
        }

        /**
         * Adds custom variable to request
         * @param key Custom variable key
         * @param value Custom variable value
         * @return Builder instance
         */
        @SuppressWarnings({"UnusedDeclaration", "WeakerAccess"}) // Public API.
        public Builder customVariable(@NonNull String key, @Nullable String value) {
            customVariables.put(key, value);
            return this;
        }

        /**
         * Adds map of custom variables for request
         * @param customVariables Map of custom variables' values by their keys
         * @return Builder instance
         */
        @SuppressWarnings({"UnusedDeclaration", "WeakerAccess"}) // Public API.
        public Builder customVariables(@NonNull Map<String, String> customVariables) {
            this.customVariables.putAll(customVariables);
            return this;
        }

        /**
         * Clears added to request custom variables
         * @return Builder instance
         */
        @SuppressWarnings({"UnusedDeclaration", "WeakerAccess"}) // Public API.
        public Builder clearCustomVariables() {
            this.customVariables.clear();
            return this;
        }

        /**
         * Sets "url" parameter for request
         * @param url Url value
         * @return Builder instance
         */
        @SuppressWarnings({"UnusedDeclaration", "WeakerAccess"}) // Public API.
        public Builder url(@NonNull String url) {
            this.url = url;
            return this;
        }

        /**
         * Sets "referrer" parameter for request
         * @param referer Referrer value
         * @return Builder instance
         */
        @SuppressWarnings({"UnusedDeclaration", "WeakerAccess"}) // Public API.
        public Builder referer(String referer) {
            this.referer = referer;
            return this;
        }

        /**
         * Adds "tag" parameter to request
         * @param tag Tag value
         * @return Builder instance
         */
        @SuppressWarnings({"UnusedDeclaration", "WeakerAccess"}) // Public API.
        public Builder tag(String tag) {
            tags.add(tag);
            return this;
        }

        /**
         * Adds multiple "tag" parameters to request
         * @param tags Collection of tag values
         * @return Builder instance
         */
        @SuppressWarnings({"UnusedDeclaration", "WeakerAccess"}) // Public API.
        public Builder tags(@NonNull Collection<String> tags) {
            this.tags.addAll(tags);
            return this;
        }

        /**
         * Sets "zone" parameter for request
         * @param zone Zone value
         * @return Builder instance
         */
        @SuppressWarnings({"UnusedDeclaration", "WeakerAccess"}) // Public API.
        public Builder zone(String zone) {
            this.zone = zone;
            return this;
        }

        /**
         * Sets "content created" parameter for request
         * @param contentCreated ISO 8601-formatted string that includes the published date and time of the content
         * @return Builder instance
         */
        @SuppressWarnings({"UnusedDeclaration", "WeakerAccess"}) // Public API.
        public Builder contentCreated(String contentCreated) {
            this.contentCreated = contentCreated;
            return this;
        }

        /**
         * Sets "content created" parameter for request
         * @param contentCreated Content created date value
         * @return Builder instance
         */
        @SuppressWarnings({"UnusedDeclaration", "WeakerAccess"}) // Public API.
        public Builder contentCreated(Date contentCreated) {
            this.contentCreated = DATE_FORMAT.format(contentCreated);
            return this;
        }

        /**
         * Sets "content author" parameter for request
         * @param contentAuthor Content author value
         * @return Builder instance
         */
        @SuppressWarnings({"UnusedDeclaration", "WeakerAccess"}) // Public API.
        public Builder contentAuthor(String contentAuthor) {
            this.contentAuthor = contentAuthor;
            return this;
        }

        /**
         * Sets "content section" parameter for request
         * @param contentSection Content section value
         * @return Builder instance
         */
        @SuppressWarnings({"UnusedDeclaration", "WeakerAccess"}) // Public API.
        public Builder contentSection(String contentSection) {
            this.contentSection = contentSection;
            return this;
        }

        /**
         * Sets "content is native" flag for request
         * @param contentIsNative True, if content is native, otherwise False
         * @return Builder instance
         */
        @SuppressWarnings({"UnusedDeclaration", "WeakerAccess"}) // Public API.
        public Builder contentIsNative(Boolean contentIsNative) {
            this.contentIsNative = contentIsNative;
            return this;
        }

        /**
         * Sets custom parameters for request
         * @param customParameters Custom parameters object
         * @return Builder instance
         */
        @SuppressWarnings({"UnusedDeclaration", "WeakerAccess"}) // Public API.
        public Builder customParams(CustomParameters customParameters) {
            this.customParameters = customParameters;
            return this;
        }

        /**
         * Builds request
         * @return ExperienceRequest instance
         */
        @SuppressWarnings({"UnusedDeclaration", "WeakerAccess"}) // Public API.
        public ExperienceRequest build() {
            return new ExperienceRequest(this);
        }
    }

    private ExperienceRequest(Builder builder) {
        debug = builder.debug;
        customVariables = Collections.unmodifiableMap(builder.customVariables);
        url = builder.url;
        referer = builder.referer;
        tags = Collections.unmodifiableList(builder.tags);
        zone = builder.zone;
        contentCreated = builder.contentCreated;
        contentAuthor = builder.contentAuthor;
        contentSection = builder.contentSection;
        contentIsNative = builder.contentIsNative;
        customParameters = builder.customParameters;
    }


    /**
     * Gets request's "debug" flag
     * @return "Debug" value
     */
    @SuppressWarnings({"UnusedDeclaration", "WeakerAccess"}) // Public API.
    public boolean isDebug() {
        return debug;
    }

    /**
     * Gets request's custom variables
     * @return Custom variables value
     */
    @SuppressWarnings({"UnusedDeclaration", "WeakerAccess"}) // Public API.
    public Map<String, String> getCustomVariables() {
        return customVariables;
    }

    /**
     * Gets request's "url" parameter
     * @return Url value
     */
    @SuppressWarnings({"UnusedDeclaration", "WeakerAccess"}) // Public API.
    public String getUrl() {
        return url;
    }

    /**
     * Gets request's "referer" parameter
     * @return Referer value
     */
    @SuppressWarnings({"UnusedDeclaration", "WeakerAccess"}) // Public API.
    public String getReferer() {
        return referer;
    }

    /**
     * Gets request's tags
     * @return Tags value
     */
    @SuppressWarnings({"UnusedDeclaration", "WeakerAccess"}) // Public API.
    public List<String> getTags() {
        return tags;
    }

    /**
     * Gets request's "zone" parameter
     * @return Zone value
     */
    @SuppressWarnings({"UnusedDeclaration", "WeakerAccess"}) // Public API.
    public String getZone() {
        return zone;
    }

    /**
     * Gets request's "content created" parameter
     * @return Content created value
     */
    @SuppressWarnings({"UnusedDeclaration", "WeakerAccess"}) // Public API.
    public String getContentCreated() {
        return contentCreated;
    }

    /**
     * Gets request's "content author" parameter
     * @return Content author value
     */
    @SuppressWarnings({"UnusedDeclaration", "WeakerAccess"}) // Public API.
    public String getContentAuthor() {
        return contentAuthor;
    }

    /**
     * Gets request's "content section" parameter
     * @return Content section value
     */
    @SuppressWarnings({"UnusedDeclaration", "WeakerAccess"}) // Public API.
    public String getContentSection() {
        return contentSection;
    }

    /**
     * Gets request's "content is native" flag
     * @return Content is native flag
     */
    @SuppressWarnings({"UnusedDeclaration", "WeakerAccess"}) // Public API.
    public Boolean getContentIsNative() {
        return contentIsNative;
    }

    /**
     * Gets request's custom parameters
     * @return Custom parameters
     */
    @SuppressWarnings({"UnusedDeclaration", "WeakerAccess"}) // Public API.
    public CustomParameters getCustomParameters() {
        return customParameters;
    }
}
