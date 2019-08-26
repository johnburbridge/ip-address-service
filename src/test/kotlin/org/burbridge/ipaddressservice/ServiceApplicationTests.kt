package org.burbridge.ipaddressservice

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@RunWith(SpringRunner::class)
@Import(TestConfig::class)
@SpringBootTest
@AutoConfigureMockMvc
class ServiceApplicationTests {

	@Autowired
	lateinit var mockMvc: MockMvc

	@Test
	fun `The root url returns the service's name`() {
		mockMvc.perform(get("/"))
				.andExpect(status().isOk)
	}

	@Test
	fun `The details url returns the hostname, fqdn and interfaces`() {

		mockMvc.perform(get("/details"))
				.andExpect(status().isOk)
				.andExpect(content().json(("""
					{"hostname":"razer-blade",
					 "fqdn":"razer-blade",
					  "interfaces": {
					  	"lo":["127.0.0.1","::1"],
					  "eth0":["192.168.1.2"]
					  }
					}
					  """.trimIndent())))
	}
}
