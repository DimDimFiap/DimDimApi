package br.com.dimdim.controller;

import br.com.dimdim.dto.ResultObject;
import br.com.dimdim.dto.TransactionInput;
import br.com.dimdim.service.TransactionService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<ResultObject> getAllTransactions(@PageableDefault(size = 10) Pageable pageable) {
        try {
            var result = transactionService.listAllTransactions(pageable);
            return ResponseEntity.status(HttpStatus.OK).body(new ResultObject(result, false, null));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResultObject(null, true, ex.getMessage()));
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<ResultObject> getTransactionById(@PathVariable Integer id) {
        try {
            var result = transactionService.getTransactionById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new ResultObject(result, false, null));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResultObject(null, true, ex.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<ResultObject> newTransaction(@RequestBody TransactionInput transactionInput) {
        try {
            transactionService.createTransaction(transactionInput);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResultObject(transactionInput, false, null));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResultObject(null, true, ex.getMessage()));
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<ResultObject> updateTransaction(@PathVariable Integer id, @RequestBody TransactionInput transactionInput) {
        try {
            transactionService.updateTransaction(id, transactionInput);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResultObject(transactionInput, false, null));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResultObject(null, true, ex.getMessage()));
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResultObject> deleteTransaction(@PathVariable Integer id) {
        try {
            transactionService.deleteTransaction(id);
            return ResponseEntity.status(HttpStatus.OK).body(new ResultObject(null, false, null));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResultObject(null, true, ex.getMessage()));
        }
    }
}
