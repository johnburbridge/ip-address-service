package org.burbridge.ipaddressservice

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.net.InetAddress

@RestController
class Controller(
        @Value("\${info.app.name}")
        val appName: String,

        @Autowired
        val localHost: InetAddress,

        @Autowired
        val interfaces: Map<String, List<String>>
) {

    @GetMapping("/")
    fun appName() = appName

    @GetMapping("/details")
    fun details(): Dto {
        return Dto(hostname = localHost.hostName, fqdn = localHost.canonicalHostName, interfaces = interfaces)
    }
}