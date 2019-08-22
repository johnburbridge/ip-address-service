package org.burbridge.ipaddressservice

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import java.net.NetworkInterface

@Configuration
@EnableWebMvc
class Config {

    @Bean
    fun interfaces(): Map<String, String> {
        val interfaces = mutableMapOf<String, String>()
        NetworkInterface.getNetworkInterfaces().toList().forEach {
            interfaces[it.displayName] = it.inetAddresses.nextElement().hostAddress
        }
        return interfaces
    }
}