package com.evaluation.mpesa.customers;

import com.evaluation.mpesa.model.ApiResponse;
import com.evaluation.mpesa.transactions.Transaction;
import com.evaluation.mpesa.transactions.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    private final TransactionService transactionService;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public void addNewCustomer(Customer customer) {
        /* check if email and phone exist */
        Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(customer.getEmail());
        if (customerOptional.isPresent()) {
            throw new IllegalStateException(customer.getEmail() + " email taken");
        }
        Optional<Customer> customerPhone = customerRepository.findCustomerByPhone(customer.getPhone());
        if (customerPhone.isPresent()) {
            throw new IllegalStateException(customer.getPhone() + " phone number taken");
        }
        customerRepository.save(customer);
    }

    public ApiResponse transferMoney(String receiver_phone, String sender_phone, Double amountToSend) {

        Optional<Customer> the_sender = customerRepository.findCustomerByPhone(sender_phone);
        if (the_sender.isPresent()) {
            Customer sender = the_sender.get();
            System.out.println(sender);
            System.out.println("found one");

            if (amountToSend <= sender.getAmount()) {
              return handleReceiverAndSendMoney(sender, amountToSend, receiver_phone);
            } else {
                System.out.println("Insufficient funds");
            }
        } else {
            throw new IllegalStateException("Sender " + sender_phone + " cannot be found");
        }
//        System.out.println(customer.getAmount());
        System.out.println("fika");
        System.out.println(receiver_phone);
        System.out.println(sender_phone);
        System.out.println(amountToSend);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResponseCode(400);
        apiResponse.setResponseMessage("The transaction failed");

        return apiResponse;

    }

    public ApiResponse handleReceiverAndSendMoney(Customer sender, Double amountToSend, String receiver_phone) {
        Optional<Customer> the_receiver = customerRepository.findCustomerByPhone(receiver_phone);
        if (the_receiver.isPresent()) {
            Customer receiver = the_receiver.get();
            System.out.println(receiver);
            System.out.println("found 2");

            // reduce amount of sender
            // add amount of receiver
            // store this to transaction document

            Double receiverFinalAmount = amountToSend + receiver.getAmount();
            Double sendersFinalAmount = sender.getAmount() - amountToSend;

            receiver.setAmount(receiverFinalAmount);
            sender.setAmount(sendersFinalAmount);

            customerRepository.save(receiver);
            customerRepository.save(sender);

            Transaction transaction = new Transaction();
            transaction.setSender_phone(sender.getPhone());
            transaction.setReceiver_phone(receiver_phone);
            transaction.setAmount(amountToSend);
            transaction.setCreated(LocalDateTime.now());

            transactionService.addNewTransaction(transaction);

            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setResponseCode(200);
            apiResponse.setResponseMessage("Money Send Successfully");

            return apiResponse;

        } else {
            throw new IllegalStateException("Receiver " + receiver_phone + " cannot be found");
        }
    }
}
