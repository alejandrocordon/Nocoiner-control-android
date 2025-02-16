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
data class SettingsRequestCooling(
    val miner: MinerSettingsCooling
)

@JsonClass(generateAdapter = true)
data class SettingsRequestPools(
    val miner: MinerSettingsPools
)

@JsonClass(generateAdapter = true)
data class MinerSettingsCooling(
    val cooling: CoolingSettings,
)

@JsonClass(generateAdapter = true)
data class MinerSettingsPools(
    val pools: List<PoolsSettings>
)

@JsonClass(generateAdapter = true)
data class MinerSettings(
    val cooling: CoolingSettings,
    val pools: List<PoolsSettings>
)

@JsonClass(generateAdapter = true)
data class CoolingSettings(
    val mode: ModeSettings,
    val fan_min_count: Int,
    val fan_min_duty: Int,
    val fan_max_duty: Int
)

@JsonClass(generateAdapter = true)
data class PoolsSettings(
    val url: String,
    val user: String,
    val pass: String
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
    val data: MetricData
)

data class MetricData(
    val hashrate: Float,
    val pcb_max_temp: Int,
    val chip_max_temp: Int,
    val fan_duty: Int,
    val power_consumption: Int
)


