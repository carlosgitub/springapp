package com.banc.springapp.repository;

import com.banc.springapp.entity.Deposits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @project spring-bootbank
 * @autor carlosd on 2022-05-18
 */
@Repository
public interface DepositRepository extends JpaRepository<Deposits, Long> {

    List<Deposits> findTransactionsByCustomerAccountId(long id);
}
