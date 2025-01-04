package com.natio21.nocoiner_control.openapi.client.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SummaryResponse(
    val miner: Summary
)

@JsonClass(generateAdapter = true)
data class Summary(
    val miner_status: MinerSummaryStatus,
    val miner_type: String,
    val hr_stock: Int,
    val average_hashrate: Double,
    val instant_hashrate: Double,
    val hr_realtime: Double,
    val hr_nominal: Double,
    val hr_average: Double,
    val pcb_temp: TemperatureRange,
    val chip_temp: TemperatureRange,
    val power_consumption: Int,
    val power_usage: Int,
    val power_efficiency: Double,
    val hw_errors_percent: Double,
    val hr_error: Int,
    val hw_errors: Int,
    val devfee_percent: Double,
    val devfee: Double,
    val pools: List<SummaryPool>,
    val cooling: SummaryCooling,
    val chains: List<SummaryChain>,
    val found_blocks: Int,
    val best_share: Long
)

@JsonClass(generateAdapter = true)
data class MinerSummaryStatus(
    val miner_state: String,
    val miner_state_time: Long
)

@JsonClass(generateAdapter = true)
data class TemperatureRange(
    val min: Int,
    val max: Int
)

@JsonClass(generateAdapter = true)
data class SummaryPool(
    val id: Int,
    val url: String,
    val pool_type: String,
    val user: String,
    val status: String,
    val asic_boost: Boolean,
    val diff: String,
    val accepted: Int,
    val rejected: Int,
    val stale: Int,
    val ls_diff: Int,
    val ls_time: String,
    val diffa: Long,
    val ping: Int
)

@JsonClass(generateAdapter = true)
data class SummaryCooling(
    val fan_num: Int,
    val fans: List<Fan>,
    val settings: CoolingSettings,
    val fan_duty: Int
)

@JsonClass(generateAdapter = true)
data class SummaryFan(
    val id: Int,
    val rpm: Int,
    val status: String,
    val max_rpm: Int
)


@JsonClass(generateAdapter = true)
data class ModeName(
    val name: String
)

@JsonClass(generateAdapter = true)
data class SummaryChain(
    val id: Int,
    val frequency: Int,
    val voltage: Int,
    val power_consumption: Int,
    val hashrate_ideal: Double,
    val hashrate_rt: Double,
    val hashrate_percentage: Double,
    val hr_error: Int,
    val hw_errors: Int,
    val pcb_temp: TemperatureRange,
    val chip_temp: TemperatureRange,
    val chip_statuses: ChipStatuses,
    val status: ChainStatus
)

@JsonClass(generateAdapter = true)
data class SummaryChipStatuses(
    val red: Int,
    val orange: Int,
    val grey: Int
)

@JsonClass(generateAdapter = true)
data class SummaryChainStatus(
    val state: String
)
