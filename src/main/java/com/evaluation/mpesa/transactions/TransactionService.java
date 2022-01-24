package com.evaluation.mpesa.transactions;

import com.evaluation.mpesa.customers.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public void addNewTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    public void transferMoney(Transaction transaction) {

        System.out.println("inafika maze");
    }
}
