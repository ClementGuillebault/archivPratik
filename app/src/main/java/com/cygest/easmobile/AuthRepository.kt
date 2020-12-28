package com.cygest.easmobile

import android.content.Context
import android.util.Log
import com.cygest.easmobile.libs.HttpBuilder
import com.cygest.easmobile.libs.WebService
import com.google.gson.internal.LinkedTreeMap

class AuthRepository() {

//    suspend fun getToken(login: String, pwd: String, context: Context) {
//        try {
//            val response = HttpBuilder().build(WebService::class.java, context)
//                    .getToken(login, pwd)
//                    .awaitResponse()
//            if (!response.isSuccessful) {
//                return
//            }
//            val accessToken: String = (response.body() as LinkedTreeMap<*, *>)["access_token"].toString()
//            Utility.developerlog(context, "token: $accessToken")
//            Constants.TOKEN = accessToken
//            CacheMemory.saveToken(context, accessToken)
//        }
//        catch (t: Throwable) {
//
//        }
//    }

//    fun getTokenV3(login: String, pwd: String, context: Context) {
//        runBlocking {
//            try {
//                val response = HttpBuilder().build(WebService::class.java, context).getTokenAsync(login, pwd)
//
//                val accessToken: String = (response as LinkedTreeMap<*, *>)["access_token"].toString()
//                Utility.developerlog(context, "token: $accessToken")
//                Constants.TOKEN = accessToken
//                CacheMemory.saveToken(context, accessToken)
//            } catch (t: Throwable) {
//
//            }
//        }
//    }

    suspend fun getToken(login: String, pwd: String) {
            val response = HttpBuilder().build(WebService::class.java).getToken(login, pwd)
            val accessToken: String = (response as LinkedTreeMap<*, *>)["access_token"].toString()
            Constants.TOKEN = accessToken
    }

    suspend fun getUser(): User {
        return HttpBuilder().build(WebService::class.java).getUser()
    }
//        HttpBuilder().build(WebService::class.java, context).tryToAuthenticate().enqueue(object :
//            Callback<User> {
//            override fun onResponse(call: Call<User>, response: Response<User>) {
//                data.value = response.body()
//            }
//
//            override fun onFailure(call: Call<User>, t: Throwable) {
//                // Error
//            }
//        })
//        return data
//    }
}