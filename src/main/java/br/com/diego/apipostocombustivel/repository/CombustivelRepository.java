package br.com.diego.apipostocombustivel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.diego.apipostocombustivel.models.Combustivel;

/**Interface do Combustivel
* @author Diego Rangel
* @return -
*/
public interface CombustivelRepository extends JpaRepository<Combustivel, Long> {
	
	Combustivel findByRegiao(String regiao);
}
