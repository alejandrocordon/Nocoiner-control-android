package com.natio21.nocoiner_control.openapi.client.models

import com.squareup.moshi.JsonClass

/**
 * 
 *
 * @param settings
 */

@JsonClass(generateAdapter = true)
data class SettingsResponse(
    val miner: MinerSettings
)

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

data class MiningResponse(
    val status: String,
    val message: String?
)

data class MetricsResponse(
    val timezone: String,
    val metrics: List<MetricEntry>
)

data class MetricEntry(
    val time: Long,  // Timestamp en segundos
    val data: HashrateData
)

data class HashrateData(
    val hashrate: Float,
    val pcb_max_temp: Int,
    val chip_max_temp: Int,
    val fan_duty: Int,
    val power_consumption: Int
)


