package br.com.dimdim.controller;

import br.com.dimdim.dto.ResultObject;
import br.com.dimdim.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/api/transaction")
    public ResponseEntity<ResultObject> getAllTransactions() {
        try {
            var result = transactionService.listAllTransactions();
            return ResponseEntity.status(HttpStatus.OK).body(new ResultObject(result, false, null));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResultObject(null, true, ex.getMessage()));
        }
    }
}
