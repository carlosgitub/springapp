package com.banc.springapp.service;

import com.banc.springapp.entity.CustomerAccount;
import com.banc.springapp.entity.Deposits;
import com.banc.springapp.entity.User;
import com.banc.springapp.entity.Withdrawals;
import com.banc.springapp.resources.UserCreateForm;

import java.util.List;
import java.util.Optional;

/**
 * @project spring-bootbank
 * @autor carlosd on 2022-05-18
 */
public interface UserService {

    Optional<com.banc.springapp.entity.User> findUserByUsername(String username);
    Optional<User> findUserByEmail(String email);
    List<CustomerAccount> findCustomerAccountById(long id);
    List<Withdrawals> findTransactionsByCustomerAccountId(long id);
    User registerUser(UserCreateForm userCreateForm);
    boolean hasValidPassword(User user, String pwd);
    List<Deposits> findTransactionsByAccountId(long id);
}
