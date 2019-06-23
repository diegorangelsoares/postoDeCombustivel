package br.com.diego.apipostocombustivel.resource;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.diego.apipostocombustivel.repository.CombustivelRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CombustivelResourceTest {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@LocalServerPort
	private int port;
	
	@MockBean
	private CombustivelRepository crRepository;
	
	@Autowired
	private MockMvc mockmvc;
	
	
	@TestConfiguration
	static class Config{
		@Bean
		public RestTemplateBuilder restTemplateBuilder(){
			return new RestTemplateBuilder().basicAuthorization("diego", "12345");
		}
	}
	
	@Before
	public void setUp(){
		
	}
	
	
}
