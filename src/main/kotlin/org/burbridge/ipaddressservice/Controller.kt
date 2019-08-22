package org.burbridge.ipaddressservice

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller {

    @Autowired
    lateinit var interfaces: Map<String, String>

    @Value("\${info.app.name}")
    lateinit var appName: String

    @GetMapping("/")
    fun appName() = appName

    @GetMapping("/interfaces")
    fun interfaces() = interfaces
}