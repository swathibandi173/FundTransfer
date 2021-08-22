package com.Banking.Fundtransfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Banking.Fundtransfer.entity.Transaction;
@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long>{

}
