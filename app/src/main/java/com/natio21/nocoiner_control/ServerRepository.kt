package com.natio21.nocoiner_control

import javax.inject.Inject

class ServerRepository @Inject constructor() {
    suspend fun pingServer(apiService: MinerApiService) {
        //val response = apiService.ping() // GET /ping
        //if (!response.isSuccessful) {
        //    throw Exception("Código de error: ${response.code()}")
        //}
    }
}
