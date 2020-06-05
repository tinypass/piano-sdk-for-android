package io.piano.android.id.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Date;

public class PianoIdToken implements Parcelable {
    @NonNull
    public final String accessToken;
    @NonNull
    public final Date expiresIn;
    @NonNull
    public final String refreshToken;

    public PianoIdToken(@NonNull String accessToken, @NonNull Date expiresIn, @NonNull String refreshToken) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.refreshToken = refreshToken;
    }

    protected PianoIdToken(Parcel in) {
        //noinspection ConstantConditions
        this(in.readString(), new Date(in.readLong()), in.readString());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(accessToken);
        dest.writeLong(expiresIn.getTime());
        dest.writeString(refreshToken);
    }

    public static final Creator<PianoIdToken> CREATOR = new Creator<PianoIdToken>() {
        @Override
        public PianoIdToken createFromParcel(Parcel in) {
            return new PianoIdToken(in);
        }

        @Override
        public PianoIdToken[] newArray(int size) {
            return new PianoIdToken[size];
        }
    };
}
