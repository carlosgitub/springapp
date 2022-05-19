package com.banc.springapp.repository;

import com.banc.springapp.entity.CustomerAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @project spring-bootbank
 * @autor carlosd on 2022-05-18
 */
@Repository
public interface CustomerAccountRepository extends JpaRepository<CustomerAccount, Long> {

    List<CustomerAccount> findCustomerAccountsByUserId(long id);

    CustomerAccount findCustomerAccountById(long id);

}
