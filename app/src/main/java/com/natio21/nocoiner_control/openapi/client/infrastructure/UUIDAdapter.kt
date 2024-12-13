package com.natio21.nocoiner_control.openapi.client.infrastructure

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.util.UUID

class UUIDAdapter {
    @ToJson
    fun toJson(uuid: UUID): String = uuid.toString()

    @FromJson
    fun fromJson(s: String): UUID = UUID.fromString(s)
}
