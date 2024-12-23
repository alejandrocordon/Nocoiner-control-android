package com.natio21.nocoiner_control.openapi.client.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiKeysResponse(
    val key: String,
    val description: String
)