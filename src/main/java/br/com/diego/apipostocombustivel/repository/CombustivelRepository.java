package br.com.diego.apipostocombustivel.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.diego.apipostocombustivel.models.Combustivel;

/**Interface do Combustivel
* @author Diego Rangel
* @return -
*/
public interface CombustivelRepository extends JpaRepository<Combustivel, Long> {
	
	Combustivel findByRegiao(String regiao);
		
	/**
	@Query("SELECT * from TAB_COMBUSTIVEL ORDER BY BANDEIRA")
	List<Combustivel> ReturnPorBandeira();
	
	/*
	@Query("SELECT t FROM Todo t WHERE " +
            "LOWER(t.title) LIKE LOWER(CONCAT('%',:searchTerm, '%')) OR " +
            "LOWER(t.description) LIKE LOWER(CONCAT('%',:searchTerm, '%'))")
    List<Todo> findBySearchTerm(@Param("searchTerm") String searchTerm, Sort sort);
    */

}
