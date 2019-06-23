package br.com.diego.apipostocombustivel.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.diego.apipostocombustivel.erro.ResourceNotFoundException;
import br.com.diego.apipostocombustivel.models.Usuario;
import br.com.diego.apipostocombustivel.repository.UsuarioRepository;

@RestController
@RequestMapping(value="/api")
//@Api(value="API REST Clientes")//isso é pra o Swagger depois coloco
@CrossOrigin(origins="*")
public class UsuarioResource {
	
	@Autowired
	private UsuarioRepository uRepository;
	
	
	@GetMapping(path="usuarios")
	public ResponseEntity<?> listAll(Pageable pageable){
		return new ResponseEntity<>(uRepository.findAll(pageable),HttpStatus.OK);
	}
	
	@GetMapping( path="usuarios/{id}")
	public ResponseEntity<?> getStudentById(@PathVariable("id") long id){
		verifyIfStudentExists(id);
		Usuario user = uRepository.findById(id);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	@PostMapping(path="usuarios")
	public ResponseEntity<?> save(@Validated @RequestBody Usuario user){
		uRepository.save(user);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	@DeleteMapping(path="usuarios/{id}")
	public ResponseEntity<?> delete(@PathVariable(name="id") long id){
		verifyIfStudentExists(id);
		uRepository.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping(path="usuarios")
	public ResponseEntity<?> update(@RequestBody Usuario user){
		verifyIfStudentExists(user.getId());
		uRepository.save(user);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	private void verifyIfStudentExists(long id){
		if(uRepository.findById(id) == null)
			throw new ResourceNotFoundException("Usuario não encontrado para o Id: " + id);
}

}
