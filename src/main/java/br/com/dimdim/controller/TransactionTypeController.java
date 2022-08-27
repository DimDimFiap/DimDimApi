package br.com.dimdim.controller;

import br.com.dimdim.dto.ResultObject;
import br.com.dimdim.dto.TransactionTypeInput;
import br.com.dimdim.service.TransactionTypeService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactionType")
public class TransactionTypeController {
    private final TransactionTypeService transactionTypeService;

    public TransactionTypeController(TransactionTypeService transactionTypeService) {
        this.transactionTypeService = transactionTypeService;
    }

    @GetMapping
    @Cacheable("transactionType")
    public ResponseEntity<ResultObject> getAllTransactionTypes(Pageable pageable) {
        try {
            var result = transactionTypeService.listAllTransactionTypes(pageable);
            return ResponseEntity.status(HttpStatus.OK).body(new ResultObject(result, false, null));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResultObject(null, true, ex.getMessage()));
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<ResultObject> getTransactionTypeById(@PathVariable Integer id) {
        try {
            var result = transactionTypeService.getTransactionTypeById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new ResultObject(result, false, null));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResultObject(null, true, ex.getMessage()));
        }
    }

    @PostMapping
    @CacheEvict(value = "transactionType", allEntries = true)
    public ResponseEntity<ResultObject> createTransactionType(@RequestBody TransactionTypeInput transactionTypeInput) {
        try {
            transactionTypeService.createTransactionType(transactionTypeInput);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResultObject(transactionTypeInput, false, null));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResultObject(null, true, ex.getMessage()));
        }
    }

    @PutMapping("{id}")
    @CacheEvict(value = "transactionType", allEntries = true)
    public ResponseEntity<ResultObject> updateTransactionType(@PathVariable Integer id, @RequestBody TransactionTypeInput transactionTypeInput) {
        try {
            transactionTypeService.updateTransactionType(id, transactionTypeInput);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResultObject(transactionTypeInput, false, null));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResultObject(null, true, ex.getMessage()));
        }
    }

    @DeleteMapping("{id}")
    @CacheEvict(value = "transactionType", allEntries = true)
    public ResponseEntity<ResultObject> deleteTransactionType(@PathVariable Integer id) {
        try {
            transactionTypeService.deleteTransactionType(id);
            return ResponseEntity.status(HttpStatus.OK).body(new ResultObject(null, false, null));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResultObject(null, true, ex.getMessage()));
        }
    }
}
