package org.burbridge.ipaddressservice

import org.springframework.stereotype.Component
import java.net.InetAddress
import java.net.NetworkInterface

@Component
class Details {

    private val localHost = InetAddress.getLocalHost()!!

    fun interfaces(): Map<String, List<String>> {
        val interfaces = mutableMapOf<String, List<String>>()
        NetworkInterface.getNetworkInterfaces().toList().forEach { iface ->
            interfaces[iface.displayName] = iface.inetAddresses.toList().map { it.hostAddress }
        }
        return interfaces
    }

    fun hostName() = localHost.hostName

    fun canonicalHostName() = localHost.canonicalHostName
}