package com.cygest.easmobile

import android.content.Context
import com.cygest.easmobile.ui.warehouse.Warehouse
import com.google.gson.Gson

class User {
    var Culture: String = ""
    var Repository: Int = 0
    var Id: Int = 0
    var Login: String = ""
    var UserName: String = ""
    var Password: String = ""
    // var typeOfConnection: String = "barcode"
    var isDeveloper: Boolean? = null
    var isValid: Boolean? = null
    var token: String? = null

    fun serialize(): String {
        return Gson().toJson(this)
    }

    fun create(context: Context) {
        isValid = true
        CacheMemory.save(context, this)
    }

    companion object {
        fun deserialize(data: String?): User {
            return Gson().fromJson(data, User::class.java)
        }
    }

    fun setDeveloperMode(value: Boolean) {
        isDeveloper = value
    }
}