package io.piano.android.composer;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;

@RestrictTo(RestrictTo.Scope.LIBRARY)
class ExperienceIdsProvider {
    static final String PAGE_VIEW_DATE_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS";
    static final String PAGE_VIEW_ID_FORMAT = "%s-%s-%s";
    static final String VISIT_ID_PREFIX = "v-";
    static final int RANDOM_STRING_SIZE = 16;
    static final int HASH_SIZE = 32;

    final DateFormat dateFormat = new SimpleDateFormat(PAGE_VIEW_DATE_FORMAT, Locale.US);
    private final Random random = new Random();
    private final PrefsStorage prefsStorage;

    private boolean isVisitIdGenerated = false;

    ExperienceIdsProvider(PrefsStorage prefsStorage) {
        this.prefsStorage = prefsStorage;
    }

    @NonNull
    String getPageViewId(Date date) {
        return String.format(
                Locale.US,
                PAGE_VIEW_ID_FORMAT,
                dateFormat.format(date),
                generateRandomAlphaNumString(RANDOM_STRING_SIZE),
                generateRandomAlphaNumString(HASH_SIZE)
        );
    }

    @NonNull
    String getVisitId(Date date) {
        long visitIdTimestamp = prefsStorage.getVisitTimestamp();
        long timeout = prefsStorage.getVisitTimeout();
        if (visitIdTimestamp < date.getTime() - timeout) {
            return generateVisitId(date);
        }
        if (visitIdTimestamp < getServerMidnightTimestamp(date)) {
            return generateVisitId(date);
        }
        String visitId = prefsStorage.getVisitId();
        if (visitId == null) {
            return generateVisitId(date);
        }
        prefsStorage.setVisitDate(date);
        isVisitIdGenerated = false;
        return visitId;
    }

    boolean isVisitIdRegenerated() {
        return isVisitIdGenerated;
    }

    @NonNull
    String generateVisitId(Date date) {
        String visitId = VISIT_ID_PREFIX + getPageViewId(date);
        prefsStorage.setVisitId(visitId);
        prefsStorage.setVisitDate(date);
        isVisitIdGenerated = true;
        return visitId;
    }

    long getServerMidnightTimestamp(Date date) {
        int offset = prefsStorage.getServerTimezoneOffset();
        String[] zoneIds = TimeZone.getAvailableIDs(offset);
        TimeZone timeZone = (zoneIds != null && zoneIds.length > 0) ? TimeZone.getTimeZone(zoneIds[0]) : TimeZone.getDefault();
        Calendar calendar = Calendar.getInstance(timeZone);
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    @NonNull
    String generateRandomAlphaNumString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(Constants.ALLOWED_RANDOM_CHARS.charAt(random.nextInt(Constants.ALLOWED_RANDOM_CHARS.length())));
        }
        return sb.toString();
    }
}
