package com.evaluation.mpesa.transactions;

import com.evaluation.mpesa.customers.Customer;
import com.evaluation.mpesa.customers.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/transactions")
@AllArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping
    public List<Transaction> fetchAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @PostMapping
    public void registerNewTransaction (@RequestBody Transaction transaction) {
        transaction.setCreated(LocalDateTime.now());
        transactionService.addNewTransaction(transaction);
    }

//    @PostMapping
//    public Transaction registerNewTransaction(@RequestBody Transaction transaction) {
//        transaction.setCreated(LocalDateTime.now());
//        transactionService.addNewTransaction(transaction);
//        return transaction;
//    }
}
