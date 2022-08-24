package br.com.dimdim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.dimdim.entity.TransactionType;


public interface TransactionTypeRepository extends JpaRepository<TransactionType, Integer>{

}
