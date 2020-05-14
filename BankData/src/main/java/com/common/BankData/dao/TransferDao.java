package com.common.BankData.dao;

import com.common.BankData.entity.PrimaryTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TransferDao extends JpaRepository<PrimaryTransaction, Long> {
   // List<PrimaryTransaction> findByAccountId(long accountId);
    Set<PrimaryTransaction> findByAccountId(long accountId);
    Set<PrimaryTransaction> findByRecipientAccountNo(long accountId);
//    List<PrimaryTransaction> findByRecipientAccountNo(long accountId);
}
