package io.piano.android.composer;

import java.util.concurrent.TimeUnit;

class Constants {
    private Constants() {}

    static final String BASE_URL_SANDBOX = "https://sandbox.tinypass.com/";
    static final String BASE_URL_EXPERIENCE = "https://experience.tinypass.com/";
    static final String BASE_URL_BUY = "https://buy.tinypass.com/";
    static final String URL_EXPERIENCE_EXECUTE = "xbuilder/experience/executeMobile";
    static final String URL_TRACK_EXTERNAL_EVENT = "api/v3/conversion/logAutoMicroConversion";
    static final String URL_TEMPLATE = "checkout/template/show";

    static final String ALLOWED_RANDOM_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    static final int PROTOCOL_VERSION = 1;
    static final long VISIT_TIMEOUT_FALLBACK = TimeUnit.MILLISECONDS.convert(30, TimeUnit.MINUTES);
}
