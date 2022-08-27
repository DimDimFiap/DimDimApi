package br.com.dimdim.service;

import br.com.dimdim.dto.TransactionInput;
import br.com.dimdim.dto.TransactionResult;
import br.com.dimdim.entity.Transaction;
import br.com.dimdim.repository.TransactionRepository;
import br.com.dimdim.repository.TransactionTypeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionTypeRepository transactionTypeRepository;

    public TransactionService(TransactionRepository transactionRepository, TransactionTypeRepository transactionTypeRepository) {
        this.transactionRepository = transactionRepository;
        this.transactionTypeRepository = transactionTypeRepository;
    }

    public Page<TransactionResult> listAllTransactions(Pageable pageable) {
        return transactionRepository.findAll(pageable)
                .map(t -> new TransactionResult(
                        t.getId(),
                        t.getTitle(),
                        t.getValue(),
                        t.getTransactionType().getId(),
                        t.getTransactionType().getType()));
    }

    public TransactionResult getTransactionById(Integer id) throws Exception {
        var transaction =  transactionRepository.findById(id).orElseThrow(() -> new Exception("Transaction not found"));
        return new TransactionResult(
                transaction.getId(),
                transaction.getTitle(),
                transaction.getValue(),
                transaction.getTransactionType().getId(),
                transaction.getTransactionType().getType());
    }

    public void updateTransaction(Integer id, TransactionInput transactionInput) throws Exception {
        var transaction = transactionRepository.findById(id).orElseThrow(() -> new Exception("Transaction not found"));
        var transactionType = transactionTypeRepository.findById(transactionInput.transactionTypeId()).orElseThrow(() -> new Exception("TransactionType not found"));

        transaction.setTitle(transactionInput.title());
        transaction.setValue(transactionInput.value());
        transaction.setTransactionType(transactionType);

        transactionRepository.save(transaction);
    }

    public void createTransaction(TransactionInput transactionInput) throws Exception {
        if (transactionInput == null)
            throw new NullPointerException("Null object");

        if (transactionInput.title() == null || transactionInput.value() == null || transactionInput.transactionTypeId() == null)
            throw new Exception("Not accept null values");

        var transactionType = transactionTypeRepository.findById(transactionInput.transactionTypeId()).orElseThrow(() -> new Exception("TransactionType not found"));
        var transaction = new Transaction(transactionInput.title(), transactionInput.value());



        transactionType.addTransaction(transaction);

        transactionRepository.save(transaction);
    }

    public void deleteTransaction(Integer id) throws Exception {
        if (transactionRepository.findById(id).isEmpty())
            throw new Exception("Transaction not found");

        transactionRepository.deleteById(id);
    }
}
