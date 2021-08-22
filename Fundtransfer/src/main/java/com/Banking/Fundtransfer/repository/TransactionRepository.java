package com.Banking.Fundtransfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Banking.Fundtransfer.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction,Long>{

}
