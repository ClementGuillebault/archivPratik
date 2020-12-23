package com.cygest.easmobile

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cygest.easmobile.ui.warehouse.WarehouseFragment
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class WarehouseRepository @Inject constructor(
        // private val webservice: WebService
        // Simple in-memory cache. Details omitted for brevity.
        //private val warehouseCache: WarehouseCache
) {

    fun getWarehouses(): LiveData<List<WarehouseFragment.Warehouse>> {
//        var cached : LiveData<WarehouseFragment.Warehouse> = CacheMemory.getUser(Activity()).value
//        if (cached != null) {
//            return cached
//        }
        val data = MutableLiveData<List<WarehouseFragment.Warehouse>>()
        // warehouseCache.put(data)
        val request = ServiceBuilder.buildService(WebService::class.java)
        request.getWareHouses(CacheMemory.getUser(Activity()).id).enqueue(object : Callback<List<WarehouseFragment.Warehouse>> {
            override fun onResponse(call: Call<List<WarehouseFragment.Warehouse>>, response: Response<List<WarehouseFragment.Warehouse>>) {
                Utility.developerlog(Activity(), "Récupération des dépots OK.list.size=" + response.body()?.size)
                data.value = response.body()
            }

            override fun onFailure(call: Call<List<WarehouseFragment.Warehouse>>, t: Throwable) {
                Utility.developerlog(Activity(), t.message)
            }
        })
        return data
    }
}

//class WarehouseCache {
//
//    fun put(data: LiveData<WarehouseFragment.Warehouse>) {
//
//    }
//}