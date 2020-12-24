package com.cygest.easmobile

import android.app.Activity
import com.cygest.easmobile.ui.warehouse.Warehouse
import com.cygest.easmobile.ui.warehouse.WarehouseFragment
import com.google.gson.Gson

class User {
    var culture: String = ""
    var warehouse: Warehouse? = null
    var id: Int = 0
    var login: String = ""
    var name: String = ""
    var password: String = ""
    var typeOfConnection: String = "barcode"
    var isDeveloper: Boolean = false
    var isValid: Boolean = false

    fun serialize(): String {
        return Gson().toJson(this)
    }

    fun create(activity: Activity) {
        isValid = true
        CacheMemory.save(this, activity)
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