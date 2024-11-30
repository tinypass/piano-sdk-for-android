package io.piano.android.id.models

public sealed class OAuthResult
public class OAuthSuccessResult(
    public val provider: String,
    public val token: String,
) : OAuthResult()
public data object OAuthCancelledResult : OAuthResult()
