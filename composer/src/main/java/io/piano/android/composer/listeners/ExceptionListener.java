package io.piano.android.composer.listeners;

import io.piano.android.composer.ComposerException;

/**
 * Listener for exceptions
 */
public interface ExceptionListener {
    /**
     * Processes exception
     *
     * @param exception exception thrown at processing event
     */
    void onException(ComposerException exception);
}
