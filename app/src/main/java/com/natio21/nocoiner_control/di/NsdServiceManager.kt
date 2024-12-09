package com.natio21.nocoiner_control.di

import android.content.Context
import android.net.nsd.NsdManager
import android.net.nsd.NsdServiceInfo
import android.util.Log

//TAG
private const val TAG = "NsdServiceManager"

class NsdServiceManager(context: Context) {

    private val nsdManager = context.getSystemService(Context.NSD_SERVICE) as NsdManager
    private val serviceType = "_http._tcp." // Tipo de servicio a descubrir
    private val targetServiceName = "Antminer" // Nombre del servicio objetivo

    private val discoveryListener = object : NsdManager.DiscoveryListener {
        override fun onDiscoveryStarted(regType: String) {
            Log.d(TAG, "Descubrimiento de servicios iniciado")
        }

        override fun onServiceFound(serviceInfo: NsdServiceInfo) {
            Log.d(TAG, "Servicio encontrado: ${serviceInfo.serviceName}")

            //show in log all serviceinfo
            Log.d(TAG, "ServiceInfo: $serviceInfo")

            if (serviceInfo.serviceName.contains(targetServiceName, ignoreCase = true)) {
                nsdManager.resolveService(serviceInfo, resolveListener)
            }
        }

        override fun onServiceLost(serviceInfo: NsdServiceInfo) {
            Log.e(TAG, "Servicio perdido: ${serviceInfo.serviceName}")
        }

        override fun onDiscoveryStopped(serviceType: String) {
            Log.i(TAG, "Descubrimiento de servicios detenido")
        }

        override fun onStartDiscoveryFailed(serviceType: String, errorCode: Int) {
            Log.e(TAG, "Fallo al iniciar el descubrimiento: Error code:$errorCode")
            nsdManager.stopServiceDiscovery(this)
        }

        override fun onStopDiscoveryFailed(serviceType: String, errorCode: Int) {
            Log.e(TAG, "Fallo al detener el descubrimiento: Error code:$errorCode")
            nsdManager.stopServiceDiscovery(this)
        }
    }

    private val resolveListener = object : NsdManager.ResolveListener {
        override fun onServiceResolved(serviceInfo: NsdServiceInfo) {
            val hostAddress = serviceInfo.host.hostAddress
            val port = serviceInfo.port
            Log.d(TAG, "Servicio resuelto: $hostAddress:$port")
            // Aquí puedes manejar la dirección IP y el puerto del servicio encontrado
        }

        override fun onResolveFailed(serviceInfo: NsdServiceInfo, errorCode: Int) {
            Log.e(TAG, "Fallo al resolver el servicio: Error code:$errorCode")
        }
    }

    fun discoverServices() {
        Log.d(TAG, "Iniciando descubrimiento de servicios")
        nsdManager.discoverServices(serviceType, NsdManager.PROTOCOL_DNS_SD, discoveryListener)
    }

    fun stopDiscovery() {
        Log.d(TAG, "Deteniendo descubrimiento de servicios")
        nsdManager.stopServiceDiscovery(discoveryListener)
    }

    companion object {
        private const val TAG = "NsdServiceManager"
    }
}