package hr.vub.itepavac.ui.shared

import android.util.Log
import hr.vub.itepavac.BuildConfig
import javax.inject.Inject

class Logger @Inject constructor() {

    fun log(message: String, exception: Throwable?) {
        if (BuildConfig.DEBUG) {
            if (exception != null) {
                Log.e(TAG, message, exception)
            } else {
                Log.d(TAG, message)
            }
        }
    }

    companion object {
        private const val TAG = "EventXyzApp"
    }
}
