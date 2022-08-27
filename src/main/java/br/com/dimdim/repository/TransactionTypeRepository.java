package br.com.dimdim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.dimdim.entity.TransactionType;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionTypeRepository extends JpaRepository<TransactionType, Integer>{

}
