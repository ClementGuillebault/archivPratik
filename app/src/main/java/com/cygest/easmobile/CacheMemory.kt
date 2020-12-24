package com.cygest.easmobile

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

class CacheMemory {
    companion object {
        fun save(user: User, context: Context) {
            val sharedPref: SharedPreferences =
                    context.getSharedPreferences(Constants.USER, Context.MODE_PRIVATE) ?: return
            with(sharedPref.edit()) {
                putString(Constants.USER, user.serialize())
                apply()
            }
        }
        fun getUser(context: Context?): User {
            val sharedPref: SharedPreferences =
                    context?.getSharedPreferences(Constants.USER, Context.MODE_PRIVATE) ?: return User()
            val str: String? = sharedPref.getString(Constants.USER, "")
            if (str == null || str.isEmpty()) {
                val user = User()
                user.isValid = true
                user.id = 1
                user.login = ""
                user.name = "Cygest"
                return user
            }
            return User.deserialize(str)
        }
        fun getToken(context: Context): String {
            val sharedPref: SharedPreferences =
                context.getSharedPreferences(Constants.TOKEN, Context.MODE_PRIVATE) ?: return ""
            return sharedPref.getString(Constants.TOKEN, "") ?: ""
        }
        fun saveToken(context: Context, token: String) {
            val sharedPref: SharedPreferences = context.getSharedPreferences(Constants.TOKEN, Context.MODE_PRIVATE) ?: return
            with(sharedPref.edit()) {
                putString(Constants.TOKEN, token)
                apply()
            }
        }
    }
}