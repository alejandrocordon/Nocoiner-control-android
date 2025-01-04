package com.natio21.nocoiner_control

import com.natio21.nocoiner_control.openapi.client.models.ApiKeysResponse
import com.natio21.nocoiner_control.openapi.client.models.AntmChain
import com.natio21.nocoiner_control.openapi.client.models.MinerStatus
import com.natio21.nocoiner_control.openapi.client.models.SettingsRequest
import com.natio21.nocoiner_control.openapi.client.models.SettingsResponse
import com.natio21.nocoiner_control.openapi.client.models.Summary
import com.natio21.nocoiner_control.openapi.client.models.SummaryResponse
import com.natio21.nocoiner_control.openapi.client.models.TempSensor
import com.natio21.nocoiner_control.openapi.client.models.UnlockRequest
import com.natio21.nocoiner_control.openapi.client.models.UnlockResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface MinerApiService {

    @GET("api/v1/apikeys")
    suspend fun getApiKeys(
        @Header("x-api-key") apiKey: String
    ): List<ApiKeysResponse>

    @GET("api/v1/auth-check")
    suspend fun getAuthCheck(
        @Header("x-api-key") apiKey: String
    ): String

    // Define the API get endpoints
    @GET("api/v1/info")
    suspend fun getMinerInfo(): MinerInfo

    @GET("api/v1/status")
    suspend fun getMinerStatus(): MinerStatus

    @GET("api/v1/temp")
    suspend fun getTemperatureStatus(): TempSensor

    //@GET("api/v1/summary")
    //suspend fun getSummary(
    //    @Header("x-api-key") apiKey: String
    //): Summary

    @GET("api/v1/summary")
    suspend fun getSummary(
        @Header("x-api-key") apiKey: String
    ): SummaryResponse

    @GET("api/v1/chains")
    suspend fun getChains(): List<AntmChain>

    @GET("api/v1/settings")
    suspend fun getSettings(
        @Header("x-api-key") apiKey: String
    ): SettingsResponse

    // Define the API post endpoints
    @POST("api/v1/reboot")
    suspend fun rebootMiner()

    @POST("api/v1/unlock")
    suspend fun unlockMiner(
        @Body request: UnlockRequest
    ): UnlockResponse

    @POST("api/v1/settings")
    suspend fun updateSettings(
        @Header("x-api-key") apiKey: String,
        @Body request: SettingsRequest
    ): SettingsResponse


}   