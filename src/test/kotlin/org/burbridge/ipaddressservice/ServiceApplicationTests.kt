package org.burbridge.ipaddressservice

import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Import
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@Import(TestConfig::class)
@SpringBootTest
@AutoConfigureMockMvc
class ServiceApplicationTests {

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var details: Details

    @Test
    fun `The root url returns the service's name`() {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk)
                .andExpect(content().string("IP Address Service"))
    }

    @Test
    fun `The details url returns the hostname, fqdn and interfaces`() {
        Mockito.`when`(details.hostName()).thenReturn("host-name")
        Mockito.`when`(details.canonicalHostName()).thenReturn("host-name.domain")
        Mockito.`when`(details.interfaces()).thenReturn(mapOf(
                "lo" to listOf("127.0.0.1", "::1"),
                "eth0" to listOf("192.168.1.2")
        ))

        mockMvc.perform(get("/details"))
                .andExpect(status().isOk)
                .andExpect(content().json(("""
					{"hostname":"host-name",
					 "fqdn":"host-name.domain",
					  "interfaces": {
					    "lo":["127.0.0.1","::1"],
					    "eth0":["192.168.1.2"]
					  }
					}
					  """.trimIndent())))
    }
}
