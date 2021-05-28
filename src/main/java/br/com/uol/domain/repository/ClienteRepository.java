package br.com.uol.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.com.uol.domain.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente , Long>, JpaSpecificationExecutor<Cliente>{
	
}
