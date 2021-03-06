package com.evaluation.mpesa.customers;

import com.evaluation.mpesa.model.ApiResponse;
import com.evaluation.mpesa.transactions.TransactionDto;
import com.evaluation.mpesa.transactions.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final TransactionService transactionService;

    @GetMapping
    public List<Customer> fetchAllCustomers() {
        return customerService.getAllCustomers();
    }

//    @PostMapping
//    public void registerNewCustomer (@RequestBody Customer customer) {
//        customerService.addNewCustomer(customer);
//    }

    @PostMapping
    public ApiResponse registerNewCustomer(@RequestBody Customer customer) {
        customer.setCreated(LocalDateTime.now());
        return customerService.addNewCustomer(customer);
    }

    @PostMapping(path = "transfer")
    public ApiResponse transferMoney(@RequestBody TransactionDto transactionDto) {
        System.out.println("hapa");
       return customerService.transferMoney(transactionDto.getReceiver_phone(), transactionDto.getSender_phone(), transactionDto.getAmount());
    }
}
