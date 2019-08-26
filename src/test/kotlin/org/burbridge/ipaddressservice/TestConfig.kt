package org.burbridge.ipaddressservice

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import java.net.InetAddress

@TestConfiguration
class TestConfig {

    @Bean
    @Primary
    fun testInterfaces(): Map<String, List<String>> {
        return mapOf(
                "lo" to listOf("127.0.0.1", "::1"),
                "eth0" to listOf("192.168.1.2")
        )
    }

    @Bean
    @Primary
    fun testLocalHost(): InetAddress {
        return InetAddress.getLocalHost()
    }
}