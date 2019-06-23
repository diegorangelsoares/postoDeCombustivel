package br.com.diego.apipostocombustivel.repository;

import javax.validation.ConstraintViolationException;

import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.diego.apipostocombustivel.models.Usuario;


@RunWith(SpringRunner.class)
@DataJpaTest
//Para utilizar o banco de dados real da aplicação em ambiente de teste
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository urRepository;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void criarUsuarioPersistData(){
		Usuario user = new Usuario("Maria", "maria@email.com","1");
		this.urRepository.save(user);
		Assertions.assertThat(user.getId()).isNotNull();
		Assertions.assertThat(user.getNome()).isEqualTo("Maria");
		Assertions.assertThat(user.getEmail()).isEqualTo("maria@email.com");
		
	}
	
	@Test
	public void deleteUsuarioPersistData(){
		Usuario user = new Usuario("Maria", "maria@email.com","1");
		this.urRepository.save(user);
		this.urRepository.delete(user);
		Assertions.assertThat(this.urRepository.findById((long)user.getId())).isNull();
		
	}
	
	@Test
	public void atualizandoUsuarioEPersistData(){
		Usuario user = new Usuario("Maria", "maria@email.com","1");
		user = this.urRepository.save(user);
		user.setNome("Maria Jose");
		user.setEmail("mariajose@email.com");
		this.urRepository.save(user);
		user = this.urRepository.findById((long)user.getId());
		Assertions.assertThat(user.getNome()).isEqualTo("Maria Jose");
		Assertions.assertThat(user.getEmail()).isEqualTo("mariajose@email.com");
		
	}
	
	@Test
	public void criandoUsuarioComNomeNullThrowConstraintViolationException(){
		thrown.expect(ConstraintViolationException.class);
		thrown.expectMessage("O campo nome é obrigatório");
		this.urRepository.save(new Usuario());
	}
	@Test
	public void criandoUsuarioQuandoEmailNullThrowConstraintViolationException(){
		thrown.expect(ConstraintViolationException.class);
		thrown.expectMessage("o campo email é obrigatório");
		Usuario user = new Usuario("Maria", "","1");
		this.urRepository.save(user);
	}
	@Test
	public void criandoEmailQuandoNomeInvalidoThrowConstraintViolationException(){
		thrown.expect(ConstraintViolationException.class);
		thrown.expectMessage("Email inválido.");
		Usuario user = new Usuario("Maria", "email","1");
		this.urRepository.save(user);
	}
}
