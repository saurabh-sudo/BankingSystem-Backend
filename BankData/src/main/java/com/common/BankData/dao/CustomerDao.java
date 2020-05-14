package com.common.BankData.dao;


import com.common.BankData.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

@Service
public interface CustomerDao extends JpaRepository<Customer, Long> {
    Customer findByUserNameContainingIgnoreCase(String username);
    Customer findByUserId(long userId);
    Customer findByToken(String token);

    @Query(value = "SELECT nextval('username')", nativeQuery =
            true)
    Long getNextCustomerId();


}
