package com.natio21.nocoiner_control

import retrofit2.http.GET

interface MinerApiService {
    @GET("api/v1/info")
    suspend fun getMinerInfo(): MinerInfo
}