package com.cygest.easmobile

import android.app.Activity
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cygest.easmobile.libs.WebService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthRepository {

    fun tryToAuthenticate(context: Context): LiveData<User> {
//        var cached : LiveData<User> = CacheMemory.getUser(Activity()).value
//        if (cached != null) {
//            return cached
//        }
        val data = MutableLiveData<User>()
        // userCache.put(data)
        val r = HttpBuilder().build(WebService::class.java, context)
        val request = ServiceBuilder.buildService(WebService::class.java)
        r.tryToAuthenticate().enqueue(object :
            Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                Utility.developerlog(context, "Récupération du user et de la clé OK. user id=" + response.body()?.id)
                data.value = response.body()
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Utility.developerlog(context, t.message)
            }
        })
        return data
    }

}