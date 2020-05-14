package com.common.BankData.dao;


import com.common.BankData.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface AccountDao extends JpaRepository<Account,Long> {
    List<Account> findByAccountStatus(Integer status);

    Account findByAccountId(long number);





}
