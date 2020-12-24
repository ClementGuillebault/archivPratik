package com.cygest.easmobile.ui.information

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cygest.easmobile.ServiceBuilder
import com.cygest.easmobile.Utility
import com.cygest.easmobile.libs.WebService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InformationRepository {

    fun getInformation(): LiveData<Any> {
        val data = MutableLiveData<Any>()
        // warehouseCache.put(data)
        val request = ServiceBuilder.buildService(WebService::class.java)
        request.getInformation("").enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                //Utility.developerlog(Activity(), "Récupération des dépots OK.list.size=" + response.body()?.size)
                data.value = response.body()
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                Utility.developerlog(Activity(), t.message)
            }
        })
        return data
    }

}