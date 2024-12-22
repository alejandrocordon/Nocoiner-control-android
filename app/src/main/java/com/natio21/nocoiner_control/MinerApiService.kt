package com.natio21.nocoiner_control

import com.natio21.nocoiner_control.openapi.client.models.MinerStatus
import com.natio21.nocoiner_control.openapi.client.models.TempSensor
import retrofit2.http.GET

interface MinerApiService {
    @GET("api/v1/info")
    suspend fun getMinerInfo(): MinerInfo

    @GET("api/v1/status")
    suspend fun getMinerStatus(): MinerStatus

    //@GET ("api/v1/summary")
    //suspend fun getMinerSummary(): MinerSu

    @GET("api/v1/temp")
    suspend fun getTemperatureStatus(): TempSensor

}   