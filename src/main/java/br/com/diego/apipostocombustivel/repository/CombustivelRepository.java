package br.com.diego.apipostocombustivel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.diego.apipostocombustivel.models.Combustivel;

public interface CombustivelRepository extends JpaRepository<Combustivel, Long> {
	
	Combustivel findByRegiao(String regiao);
}
