package com.natio21.nocoiner_control.openapi.client.models

import com.squareup.moshi.JsonClass

/**
 * 
 *
 * @param settings
 */


@JsonClass(generateAdapter = true)
data class SettingsRequest(
    val miner: MinerSettings
)

@JsonClass(generateAdapter = true)
data class MinerSettings(
    val cooling: CoolingSettings
)

@JsonClass(generateAdapter = true)
data class CoolingSettings(
    val mode: ModeSettings,
    val fan_min_count: Int,
    val fan_min_duty: Int
)

@JsonClass(generateAdapter = true)
data class ModeSettings(
    val name: String,
    val param: Int
)

@JsonClass(generateAdapter = true)
data class SettingsResponse(
    val success: Boolean,
    val message: String
)