package br.com.diego.apipostocombustivel.erro;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**Classe que trata o exception
* @author Diego Rangel
* @return -
*/
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
   public ResourceNotFoundException(String mensagem) {
	   super(mensagem);
	// TODO Auto-generated constructor stub
}
}