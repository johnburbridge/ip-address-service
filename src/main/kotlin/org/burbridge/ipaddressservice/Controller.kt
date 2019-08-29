package org.burbridge.ipaddressservice

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.net.InetAddress
import java.net.NetworkInterface

@RestController
class Controller(
        @Value("\${info.app.name}")
        val appName: String,

        @Autowired
        val details: Details
) {

    @GetMapping("/")
    fun appName() = appName

    @GetMapping("/details")
    fun details(): Dto {
        return Dto(hostname = details.hostName(),
                fqdn = details.canonicalHostName(),
                interfaces = details.interfaces())
    }
}