package br.com.dimdim.service;

import br.com.dimdim.entity.Transaction;
import br.com.dimdim.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> listAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction getTransactionById(Integer id) throws Exception {
        return transactionRepository.findById(id).orElseThrow(() -> new Exception("Transaction not found"));
    }

    public void updateTransaction(Integer id, Transaction transaction) throws Exception {
        if (transactionRepository.findById(id).isEmpty())
            throw new Exception("Transaction not found");
        transaction.setId(id);
        transactionRepository.save(transaction);
    }

    public void createTransaction(Transaction transaction) throws Exception {
        if (transaction == null)
            throw new NullPointerException("Null object");

        if (transaction.getTitle() == null || transaction.getValue() == null)
            throw new Exception("Not accept null values");

        transactionRepository.save(transaction);
    }

    public void deleteTransaction(Integer id) throws Exception {
        if (transactionRepository.findById(id).isEmpty())
            throw new Exception("Transaction not found");

        transactionRepository.deleteById(id);
    }
}
