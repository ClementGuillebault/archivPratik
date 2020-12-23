package com.cygest.easmobile

import android.app.Activity
import android.widget.Toast
import java.lang.Exception

class Utility {
    companion object {
        fun developerlog(activity: Activity, exception: Exception) {
            if (CacheMemory.getUser(activity).isDeveloper)
                Toast.makeText(activity, exception.message, Toast.LENGTH_LONG).show()
        }

        fun developerlog(activity: Activity, msg: String?) {
            if (CacheMemory.getUser(activity).isDeveloper)
                Toast.makeText(activity, msg ?: "Erreur inconnue", Toast.LENGTH_LONG).show()
        }
    }
}