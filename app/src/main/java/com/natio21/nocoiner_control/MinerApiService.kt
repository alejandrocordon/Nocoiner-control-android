package com.natio21.nocoiner_control

import com.natio21.nocoiner_control.openapi.client.models.AntmChain
import com.natio21.nocoiner_control.openapi.client.models.MinerStatus
import com.natio21.nocoiner_control.openapi.client.models.Summary
import com.natio21.nocoiner_control.openapi.client.models.TempSensor
import retrofit2.http.GET
import retrofit2.http.POST

interface MinerApiService {

    // Define the API get endpoints
    @GET("api/v1/info")
    suspend fun getMinerInfo(): MinerInfo

    @GET("api/v1/status")
    suspend fun getMinerStatus(): MinerStatus

    //@GET ("api/v1/settings")
    //suspend fun getMinerSettings(): MinerSettings

    @GET("api/v1/temp")
    suspend fun getTemperatureStatus(): TempSensor

    @GET("api/v1/summary")
    suspend fun getSummary(): Summary

    @GET("api/v1/chains")
    suspend fun getChains(): List<AntmChain>


    // Define the API post endpoints
    @POST("api/v1/reboot")
    suspend fun rebootMiner()


}   