package br.com.dimdim.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dimdim.entity.TransactionType;
import br.com.dimdim.repository.TransactionTypeRepository;

@Service
public class TransactionTypeService {
    @Autowired
    private TransactionTypeRepository transactionTypeRepository;

    public List<TransactionType> listAllTransactions(){return  transactionTypeRepository.findAll();}

    public TransactionType getTransactionById(Integer id) throws Exception {
        return transactionTypeRepository.findById(id).orElseThrow(() -> new Exception("Transaction not found"));
    }

    public void updateTransaction(Integer id, TransactionType transactionType) throws Exception {
        if (transactionTypeRepository.findById(id).isEmpty())
            throw new Exception("Transaction not found");
        transactionType.setId(id);
        transactionTypeRepository.save(transactionType);
    }

    public void createTransaction(TransactionType transactionType) throws Exception {
        if (transactionType == null)
            throw new NullPointerException("Null object");

        if (transactionType.getType() == null)
            throw new Exception("Not accept null values");

        transactionTypeRepository.save(transactionType);
    }

    public void deleteTransaction(Integer id) throws Exception {
        if (transactionTypeRepository.findById(id).isEmpty())
            throw new Exception("Transaction not found");

        transactionTypeRepository.deleteById(id);
    }



}
