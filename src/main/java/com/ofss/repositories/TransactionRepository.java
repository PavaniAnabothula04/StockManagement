package com.ofss.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ofss.model.Customer;
import com.ofss.model.Stock;
import com.ofss.model.Transaction;
import com.ofss.model.TransactionType;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByCustomerEmailId(String emailId);

    List<Transaction> findByStock(Stock stock);

    List<Transaction> findByCustomerAndStock(Customer customer, Stock stock);

    List<Transaction> findByCustomerAndStockAndType(Customer customer, Stock stock, TransactionType transactionType);

    List<Transaction> findTop3ByCustomer_EmailIdOrderByTimestampDesc(String emailId);
}