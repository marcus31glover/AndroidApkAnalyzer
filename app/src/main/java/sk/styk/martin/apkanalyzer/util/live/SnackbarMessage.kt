package sk.styk.martin.appshare.util.live

import androidx.annotation.StringRes
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import sk.styk.martin.apkanalyzer.util.live.SingleLiveEvent

/**
 * A SingleLiveEvent used for Snackbar messages. Like a [SingleLiveEvent] but also prevents
 * null messages and uses a custom observer.
 *
 *
 * Note that only one observer is going to be notified of changes.
 */
class SnackbarMessage : SingleLiveEvent<Any>() {

    fun observe(owner: LifecycleOwner, observer: SnackbarObserver) {
        super.observe(owner, Observer { t ->
            if (t == null) {
                return@Observer
            }
            when (t) {
                is Int -> observer.onNewMessage(t)
                is String -> observer.onNewMessage(t)
            }
        })
    }

    interface SnackbarObserver {
        /**
         * Called when there is a new message to be shown.
         * @param snackbarMessageResourceId The new message, non-null.
         */
        fun onNewMessage(@StringRes snackbarMessageResourceId: Int) {}

        fun onNewMessage(text: String) {}
    }

}