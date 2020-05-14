package com.common.BankData.dao;

import com.common.BankData.entity.OtherAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OtherBankAccountDao extends JpaRepository<OtherAccount,Long> {
}
