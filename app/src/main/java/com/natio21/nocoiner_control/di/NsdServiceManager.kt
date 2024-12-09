package com.natio21.nocoiner_control.di

import android.content.Context
import android.net.nsd.NsdManager
import android.net.nsd.NsdServiceInfo
import android.util.Log

//TAG
private const val TAG = "NsdServiceManager"

class NsdServiceManager(private val context: Context) {

    private val nsdManager: NsdManager = context.getSystemService(Context.NSD_SERVICE) as NsdManager
    private var discoveryListener: NsdManager.DiscoveryListener? = null
    private val discoveredServices = mutableListOf<NsdServiceInfo>()

    fun discoverAntminerServices() {
        stopDiscovery() // Detener cualquier descubrimiento anterior

        discoveryListener = object : NsdManager.DiscoveryListener {
            override fun onDiscoveryStarted(serviceType: String) {
                Log.d(TAG, "Descubrimiento iniciado para el tipo de servicio: $serviceType")
            }

            override fun onServiceFound(serviceInfo: NsdServiceInfo) {
                Log.d(TAG, "Servicio encontrado: ${serviceInfo.serviceName}")
                if (serviceInfo.serviceName.contains("Antminer", ignoreCase = true)) {
                    Log.d(TAG, "Servicio Antminer detectado: ${serviceInfo.serviceName}")
                    nsdManager.resolveService(serviceInfo, object : NsdManager.ResolveListener {
                        override fun onServiceResolved(resolvedServiceInfo: NsdServiceInfo) {
                            Log.d(TAG, "Servicio resuelto: ${resolvedServiceInfo.serviceName} en ${resolvedServiceInfo.host.hostAddress}:${resolvedServiceInfo.port}")
                            discoveredServices.add(resolvedServiceInfo)
                        }

                        override fun onResolveFailed(serviceInfo: NsdServiceInfo, errorCode: Int) {
                            Log.e(TAG, "Fallo al resolver el servicio: ${serviceInfo.serviceName}, código de error: $errorCode")
                        }
                    })
                }
            }

            override fun onServiceLost(serviceInfo: NsdServiceInfo) {
                Log.e(TAG, "Servicio perdido: ${serviceInfo.serviceName}")
                discoveredServices.remove(serviceInfo)
            }

            override fun onDiscoveryStopped(serviceType: String) {
                Log.i(TAG, "Descubrimiento detenido para el tipo de servicio: $serviceType")
            }

            override fun onStartDiscoveryFailed(serviceType: String, errorCode: Int) {
                Log.e(TAG, "Fallo al iniciar el descubrimiento para el tipo de servicio: $serviceType, código de error: $errorCode")
                stopDiscovery()
            }

            override fun onStopDiscoveryFailed(serviceType: String, errorCode: Int) {
                Log.e(TAG, "Fallo al detener el descubrimiento para el tipo de servicio: $serviceType, código de error: $errorCode")
                stopDiscovery()
            }
        }

        nsdManager.discoverServices("_http._tcp.", NsdManager.PROTOCOL_DNS_SD, discoveryListener)
    }

    fun stopDiscovery() {
        discoveryListener?.let {
            nsdManager.stopServiceDiscovery(it)
        }
        discoveryListener = null
    }

    fun getDiscoveredServices(): List<NsdServiceInfo> {
        return discoveredServices
    }

    companion object {
        private const val TAG = "NsdServiceManager"
    }
}