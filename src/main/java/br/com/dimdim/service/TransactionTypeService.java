package br.com.dimdim.service;

import java.util.List;

import br.com.dimdim.dto.TransactionTypeInput;
import br.com.dimdim.dto.TransactionTypeResult;
import br.com.dimdim.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.dimdim.entity.TransactionType;
import br.com.dimdim.repository.TransactionTypeRepository;

@Service
public class TransactionTypeService {
    private final TransactionTypeRepository transactionTypeRepository;

    public TransactionTypeService(TransactionTypeRepository transactionTypeRepository) {
        this.transactionTypeRepository = transactionTypeRepository;
    }

    public Page<TransactionTypeResult> listAllTransactionTypes(Pageable pageable){
        return  transactionTypeRepository.findAll(pageable).map(tt -> new TransactionTypeResult(tt.getId(), tt.getType()));
    }

    public TransactionTypeResult getTransactionTypeById(Integer id) throws Exception {
        var transactionType = transactionTypeRepository.findById(id).orElseThrow(() -> new Exception("Transaction type not found"));
        return new TransactionTypeResult(transactionType.getId(), transactionType.getType());
    }

    public void updateTransactionType(Integer id, TransactionTypeInput transactionTypeInput) throws Exception {
        var transactionType = transactionTypeRepository.findById(id).orElseThrow(() -> new Exception("Transaction type not found"));
        transactionType.setType(transactionTypeInput.type());
        transactionTypeRepository.save(transactionType);
    }

    public void createTransactionType(TransactionTypeInput transactionTypeInput) throws Exception {
        if (transactionTypeInput == null)
            throw new NullPointerException("Null object");
        if (transactionTypeInput.type() == null)
            throw new Exception("Not accept null values");
        var transactionType = new TransactionType(transactionTypeInput.type());
        transactionTypeRepository.save(transactionType);
    }

    public void deleteTransactionType(Integer id) throws Exception {
        if (transactionTypeRepository.findById(id).isEmpty())
            throw new Exception("Transaction not found");
        transactionTypeRepository.deleteById(id);
    }
}
