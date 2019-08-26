package org.burbridge.ipaddressservice

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import java.net.InetAddress
import java.net.NetworkInterface

@Configuration
@EnableWebMvc
class Config {

    @Bean
    fun interfaces(): Map<String, List<String>> {
        val interfaces = mutableMapOf<String, List<String>>()
        NetworkInterface.getNetworkInterfaces().toList().forEach { iface ->
            interfaces[iface.displayName] = iface.inetAddresses.toList().map { it.hostAddress }
        }
        return interfaces
    }

    @Bean
    fun localHost() = InetAddress.getLocalHost()
}