package com.cygest.easmobile

import com.cygest.easmobile.ui.warehouse.WarehouseFragment
import retrofit2.Call
import retrofit2.http.*

interface WebService {
    @GET("/Repository/GetRepositories")
    fun getWareHouses(@Query("id") key: Int): Call<List<WarehouseFragment.Warehouse>>

    @FormUrlEncoded
    @POST("")
    fun tryToAuthenticateByBarcode(@Field("UserName") login: String, @Field("Password") password: String, @Field("grant_type") type: String = "password"): Call<User>
}
