package com.cygest.easmobile

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cygest.easmobile.ui.warehouse.WarehouseFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthRepository {

    fun tryToAuthenticate(): LiveData<User> {
//        var cached : LiveData<User> = CacheMemory.getUser(Activity()).value
//        if (cached != null) {
//            return cached
//        }
        val data = MutableLiveData<User>()
        // userCache.put(data)
        val request = ServiceBuilder.buildService(WebService::class.java)
        request.tryToAuthenticateByBarcode("","fefe").enqueue(object :
            Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                Utility.developerlog(Activity(), "Récupération du user et de la clé OK. user id=" + response.body()?.id)
                data.value = response.body()
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Utility.developerlog(Activity(), t.message)
            }
        })
        return data
    }

}