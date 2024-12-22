package com.natio21.nocoiner_control

import com.natio21.nocoiner_control.openapi.client.infrastructure.ApiClient
import com.natio21.nocoiner_control.openapi.client.models.TempSensor
import okhttp3.OkHttpClient

class MinerApi(baseUrl: String = ApiClient.baseUrlKey, client: OkHttpClient = ApiClient.defaultClient) {
    private val apiClient = ApiClient(baseUrl, client)

    suspend fun getTemperatureStatus(): TempSensor? {
        // Lógica interna para llamar a request(...)

        return null
    }

}