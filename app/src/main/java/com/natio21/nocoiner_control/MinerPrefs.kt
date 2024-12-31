package com.natio21.nocoiner_control

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MinerPrefs @Inject constructor(
    @ApplicationContext val context: Context
) {
    private val prefs: SharedPreferences
            = context.getSharedPreferences("nocoiner_prefs", Context.MODE_PRIVATE)

    fun getIp(): String? = prefs.getString("ip_address", null)

    fun getApiKey(): String? = prefs.getString("api_key", null)

    fun saveIp(ip: String) {
        prefs.edit().putString("ip_address", ip).apply()
    }

    fun saveApiKey(apiKey: String) {
        prefs.edit().putString("api_key", apiKey).apply()
    }
}