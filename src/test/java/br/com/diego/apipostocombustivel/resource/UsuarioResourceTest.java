package br.com.diego.apipostocombustivel.resource;

import static java.util.Arrays.asList;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.diego.apipostocombustivel.models.Usuario;
import br.com.diego.apipostocombustivel.repository.UsuarioRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UsuarioResourceTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@LocalServerPort
	private int port;
	
	@MockBean
	private UsuarioRepository uRepository;
	
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
		
		/* ========== configurando o cenario principal ========== */
		Usuario user = new Usuario(1, "Diego", "diego@email.com", "1");
		BDDMockito.when(this.uRepository.findById(user.getId())).thenReturn(user);
	}
	
	@Test
	public void checandoListaDeUsuarios(){
		/* ========== Configurando o cenário ========== */
		List<Usuario> users = asList(new Usuario(1,"Diego","diego@email.com","1"),new Usuario(2,"Diego 2","diego@email.com","2"));
		BDDMockito.when(this.uRepository.findAll()).thenReturn(users);

		/* ========== Execução -> Request Usuarios========== */
		 ResponseEntity<Usuario>  response = restTemplate.getForEntity("/api/usuarios/", Usuario.class);
		 
		 /* ========== Verificações========== */
		 Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
	}
	@Test
	public void checandoUsuarioConsultaPeloIdStatusCode200(){
		
		/* ========== Execução -> Requisição Usuario========== */
		 ResponseEntity<Usuario>  response = restTemplate.getForEntity("/api/usuarios/{id}", Usuario.class,1);
		 /* ========== Verificações========== */
		 Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
	}
	
	@Test
	public void checandoPeloIddoUsarioEOUsuarioNaoExisteStatusCode404(){
		/* ========== Configurando o cenário ========== */
		int idUser = 2;
		/* ========== Execução -> Requisição Usuario========== */
		 ResponseEntity<Usuario>  response = restTemplate.getForEntity("/api/usuarios/{id}", Usuario.class,idUser);
		 /* ========== Verificações========== */
		 Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(404);
	}
	
	@Test
	public void deleteUsuarioRetornarStatusCode200(){
		/* ========== Configurando o cenário ========== */
		BDDMockito.doNothing().when(this.uRepository).delete((long) 1);
		/* ========== Execução -> delete Student========== */
		 ResponseEntity<String>  response = restTemplate.exchange("/api/usuarios/{id}", HttpMethod.DELETE,null,String.class,1);
		 /* ========== Verificações========== */
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
	}
	
	@Test
	public void CriarUsuarioComNomeNullRetornarStatusCode400() throws Exception{
		/* ========== Configurando o cenário ========== */
		Usuario user = new Usuario(3,"","maria@email.com","1");
		BDDMockito.when(uRepository.save(user)).thenReturn(user);
		
		/* ========== Execução========== */
		 ResponseEntity<String>  response = restTemplate.postForEntity("/api/usuarios/", user, String.class);
		 
		 /* ========== Verificações========== */
		 Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(400);
		 
	}
	
	@Test
	public void CriarUsuarioRetornarStatusCode200() throws Exception{
		/* ========== Configurando o cenário ========== */
		Usuario user = new Usuario(3,"Maria","maria@email.com","1");
		BDDMockito.when(uRepository.save(user)).thenReturn(user);
		
		/* ========== Execução ========== */
		 ResponseEntity<Usuario>  response = restTemplate.postForEntity("/api/usuarios/", user, Usuario.class);
		 
		 /* ========== Verificações========== */
		 Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
		 Assertions.assertThat(response.getBody().getId()).isNotNull();
		
	}
	

}
