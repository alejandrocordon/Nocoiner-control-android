package com.natio21.nocoiner_control

data class MinerInfo(
    val miner: String,
    val model: String,
    val fw_name: String,
    val fw_version: String,
    // Otros campos según la respuesta de la API
)