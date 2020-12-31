package com.cygest.easmobile.libs

import com.cygest.easmobile.User
import com.cygest.easmobile.ui.warehouse.Warehouse
import retrofit2.http.*

interface WebService {
    @GET("/Repository/GetRepositories")
    suspend fun getWareHouses(@Query("id") key: Int): List<Warehouse>

    @GET("/Repository/GetRepositories")
    suspend fun getWareHouses(@Query("id") id: Int, @Query("asc") asc: Boolean): List<Warehouse>

    @FormUrlEncoded
    @POST("/Token")
    suspend fun getToken(@Field("UserName") login: String, @Field("Password") password: String, @Field("grant_type") type: String = "password"): Any

    @GET("/User/GetUser")
    suspend fun getUser(): User

    @FormUrlEncoded
    @POST("/Information/Process")
    suspend fun getInformation(@Field("cb") cb: String): Any
}
