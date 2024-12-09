package com.natio21.nocoiner_control.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.natio21.nocoiner_control.MinerApiService
import com.natio21.nocoiner_control.MinerInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class HomeViewModel @Inject constructor(
    private val minerApiService: MinerApiService
) : ViewModel() {

    private val _minerInfo = MutableStateFlow<MinerInfo?>(null)
    val minerInfo: StateFlow<MinerInfo?> get() = _minerInfo


    init {
        fetchMinerInfo()
    }


    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text


    private fun fetchMinerInfo() {
        viewModelScope.launch {
            try {
                val info = minerApiService.getMinerInfo()
                Log.d("HomeViewModel", info.toString())
                _minerInfo.value = info

            } catch (e: Exception) {
                // Manejar el error
                Log.e("HomeViewModel", e.message.toString())
            }
        }
    }

}