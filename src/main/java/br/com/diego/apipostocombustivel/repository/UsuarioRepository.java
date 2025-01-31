package br.com.diego.apipostocombustivel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.diego.apipostocombustivel.models.Usuario;

/**Interface do Usuario
* @author Diego Rangel
* @return -
*/
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Usuario findById(long id);
}
