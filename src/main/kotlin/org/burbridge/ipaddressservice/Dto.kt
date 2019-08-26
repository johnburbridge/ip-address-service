package org.burbridge.ipaddressservice

class Dto(
        val hostname: String,
        val fqdn: String,
        val interfaces: Map<String, List<String>>
)