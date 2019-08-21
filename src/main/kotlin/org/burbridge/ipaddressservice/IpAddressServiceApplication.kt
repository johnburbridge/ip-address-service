package org.burbridge.ipaddressservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class IpAddressServiceApplication

fun main(args: Array<String>) {
	runApplication<IpAddressServiceApplication>(*args)
}
