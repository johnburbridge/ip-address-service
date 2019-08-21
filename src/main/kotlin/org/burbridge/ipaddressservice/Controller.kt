package org.burbridge.ipaddressservice

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class Controller {

    @GetMapping
    fun ipAddress(): DTO {
        return DTO(ipAddress = "127.0.0.1")
    }
}