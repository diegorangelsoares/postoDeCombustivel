package br.com.diego.apipostocombustivel.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
	*/
	
	@Query(value = "select * from TB_COMBUSTIVEL order by bandeira limit 200", nativeQuery = true)
	List<Combustivel> ReturnPorBandeira();
	
	@Query(value = "select * from TB_COMBUSTIVEL order by data_da_coleta limit 200", nativeQuery = true)
	List<Combustivel> ReturnPorDataDaColeta();
	
	/*
	@Query("SELECT c FROM Combustivel c WHERE LOWER(c.id)")
    List<Combustivel> findBySearchTerm(@Param("searchTerm") String searchTerm);
	*/
}
