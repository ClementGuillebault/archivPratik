package com.cygest.easmobile

import android.content.Context
import android.content.SharedPreferences

/**
 * Cached memory for user, settings and security token.
 * This class cannot be instantiated.
 */
class CacheMemory {

    init {
        throw IllegalAccessException("This class cannot be instantiated")
    }

    companion object {
        /**
         * Save [user] in cache memory.
         */
        fun save(context: Context?, user: User) {
            val sharedPref: SharedPreferences =
                    context?.getSharedPreferences(Constants.USER, Context.MODE_PRIVATE) ?: return
            with(sharedPref.edit()) {
                putString(Constants.USER, user.serialize())
                apply()
            }
        }

        /**
         * get [User] from cache memory.
         * @return user stored in cache memory.
         */
        fun getUser(context: Context?): User {
            val sharedPref: SharedPreferences =
                context?.getSharedPreferences(Constants.USER, Context.MODE_PRIVATE) ?: return User()
            val str: String = sharedPref.getString(Constants.USER, "") ?: ""
            if (str.isEmpty()) {
                val user = User()
                user.isValid = true
                user.Id = 1
                user.Login = ""
                user.UserName = "Cygest"
                return user
            }
            return User.deserialize(str)
        }

        /**
         * get token [token] from cache memory.
         * @return token stored in cache memory.
         */
//        fun getToken(context: Context?): String {
//            val user = getUser(context)
//            return user.token ?: ""
//        }

        /**
         * Save token [token] in cache memory.
         */
        fun saveToken(context: Context?, token: String) {
            val user = getUser(context)
            user.token = token
            save(context, user)
        }
    }
}