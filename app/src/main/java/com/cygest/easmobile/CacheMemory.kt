package com.cygest.easmobile

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

class CacheMemory {
    companion object {
        fun save(user: User, activity: Activity) {
            val sharedPref: SharedPreferences =
                activity.getSharedPreferences(Constants.USER, Context.MODE_PRIVATE) ?: return
            with(sharedPref.edit()) {
                putString(Constants.USER, user.serialize())
                apply()
            }
        }
        fun getUser(activity: Activity): User {
            val sharedPref: SharedPreferences =
                activity.getSharedPreferences(Constants.USER, Context.MODE_PRIVATE) ?: return User()
            val str: String? = sharedPref.getString(Constants.USER, "")
            if (str == null || str.isEmpty()) {
                var user = User()
                user.isValid = true
                return user
            }
            return User.deserialize(str)
        }
    }
}