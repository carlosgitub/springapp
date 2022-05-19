package com.banc.springapp.service;

import com.banc.springapp.entity.*;
import com.banc.springapp.repository.*;
import com.banc.springapp.resources.UserCreateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.banc.springapp.constants.GeneralConstants.ROLE_USER;

/**
 * @project spring-bootbank
 * @autor carlosd on 2022-05-18
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private WithdrawalRepository withdrawalRepository;

    @Autowired
    private DepositRepository depositRepository;

    @Autowired
    private CustomerAccountRepository customerAccountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public List<CustomerAccount> findCustomerAccountById(long id) {
     return customerAccountRepository.findCustomerAccountsByUserId(id);
    }

    @Override
    public List<Deposits> findTransactionsByAccountId(long id) {
        return depositRepository.findTransactionsByCustomerAccountId(id);
    }

    @Override
    public List<Withdrawals> findTransactionsByCustomerAccountId(long id) {
        return withdrawalRepository.findTransactionsByCustomerAccountId(id);
    }

    @Override
    public User registerUser(UserCreateForm userCreateForm) {
        User user = new User();

        user.setUsername(userCreateForm.getUsername());
        user.setEmail(userCreateForm.getEmail());
        user.setPassword(passwordEncoder.encode(userCreateForm.getPassword()));

        Set<Role> roles = generateRolesSet();
        user.setRoles(roles);
        user.setCustomerAccounts(new ArrayList<>());

        return userRepository.save(user);
    }

    private Set<Role> generateRolesSet() {
        Role role = roleRepository.findByRoleName(ROLE_USER);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        return roles;

    }

    @Override
    public boolean hasValidPassword(User user, String pwd) {
        return passwordEncoder.matches(pwd, user.getPassword());
    }


}
