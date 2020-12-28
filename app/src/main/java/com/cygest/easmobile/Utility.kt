package com.cygest.easmobile

import android.app.Activity
import android.content.Context
import android.widget.Toast
import java.lang.Exception

class Utility {
    companion object {
        fun developerlog(context: Context, exception: Exception) {
            if (CacheMemory.getUser(context).isDeveloper == true)
                Toast.makeText(context, exception.message, Toast.LENGTH_LONG).show()
        }

        fun developerlog(context: Context, msg: String?) {
            if (CacheMemory.getUser(context).isDeveloper == true)
                Toast.makeText(context, msg ?: "Erreur inconnue", Toast.LENGTH_LONG).show()
        }
    }
}