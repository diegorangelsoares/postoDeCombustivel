package br.com.diego.apipostocombustivel.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.diego.apipostocombustivel.repository.CombustivelRepository;

@RestController
@RequestMapping(value="/api")
@CrossOrigin(origins="*")
public class CombustivelResource {
	
	@Autowired
	private CombustivelRepository cRepository;
	
	public CombustivelResource() {
		//insertCSVBanco();
	}
	
	/**Código lista todos os combustiveis
	* @author Diego Rangel
	* @return ResponseEntity<?> - Retorna todos os combustiveis
	*/
	@GetMapping(path = "combustiveis")
	public ResponseEntity<?> listAll(){
		//cRepository.findByRegiao("Teste");
		return new ResponseEntity<>(cRepository.findAll(), HttpStatus.OK);
	}
	
	

}
