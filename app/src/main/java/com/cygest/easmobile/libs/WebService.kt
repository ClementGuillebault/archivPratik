package com.cygest.easmobile.libs

import com.cygest.easmobile.User
import com.cygest.easmobile.ui.warehouse.Warehouse
import retrofit2.Call
import retrofit2.http.*

interface WebService {
    @GET("/Repository/GetRepositories")
    fun getWareHouses(@Query("id") key: Int): Call<List<Warehouse>>

    @FormUrlEncoded
    @POST("/token")
    fun getToken(@Field("UserName") login: String, @Field("Password") password: String, @Field("grant_type") type: String = "password"): Call<Any>

    @POST("/User/GetUser")
    fun tryToAuthenticate(): Call<User>

    @FormUrlEncoded
    @POST("")
    fun getInformation(@Field("cb") cb: String): Call<Any>
}
