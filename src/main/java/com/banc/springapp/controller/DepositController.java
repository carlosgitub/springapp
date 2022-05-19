package com.banc.springapp.controller;

import com.banc.springapp.authorisation.CustomUserDetails;
import com.banc.springapp.entity.CustomerAccount;
import com.banc.springapp.entity.Deposits;
import com.banc.springapp.enums.Currency;
import com.banc.springapp.repository.CustomerAccountRepository;
import com.banc.springapp.repository.DepositRepository;
import com.banc.springapp.resources.DepositForm;
import com.banc.springapp.service.UserService;
import com.banc.springapp.utils.CurrencyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @project spring-bootbank
 * @autor carlosd on 2022-05-18
 */
@Controller
@Slf4j
public class DepositController {

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerAccountRepository customerAccountRepository;


    @Autowired
    private DepositRepository depositRepository;

    @Autowired
    private DepositForm depositForm;


    @ModelAttribute("currentCustomerAccounts")
    public List<CustomerAccount> getCurrentCustomerAccounts(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        return userService.findCustomerAccountById(customUserDetails.getId());
    }


    @ModelAttribute("addDepositForm")
    public DepositForm getDepositForm() {
        return depositForm;
    }

    @GetMapping("/deposit/transactions")
    public String getDepositTransactions(){
        return "deposit-transactions";
    }


    @GetMapping("/deposits/account/{id}")
    public String getDepositAccountIdTransactions(@PathVariable("id") long accountId, Model model) {
        List<Deposits>  depositsList = depositRepository.findTransactionsByCustomerAccountId(accountId);
        model.addAttribute("depositsList", depositsList);
        depositForm.setCustomerAccountId(accountId);
        return "customer-deposit-transactions";

    }

    @PostMapping("/deposit")
    public String processDeposit(@ModelAttribute("addDepositForm") DepositForm depositForm,
                                 Model model) {

        var customerAccountId = depositForm.getCustomerAccountId();
        var amount = depositForm.getAmount();
        Currency currency = CurrencyUtils.convertStringToCurrency(depositForm.getCurrency());

        CustomerAccount customerAccount =  customerAccountRepository.findCustomerAccountById(customerAccountId);

        if(customerAccount !=null && customerAccount.getCustomerAccountBalance() > 0) {

            Deposits deposits = new Deposits(customerAccount, currency, amount);
            customerAccount.setCustomerAccountBalance(customerAccount.getCustomerAccountBalance() + amount);
            customerAccountRepository.save(customerAccount);
            depositRepository.save(deposits);

            model.addAttribute("successfulTransaction","successfulTransaction");
        } else {

            model.addAttribute("it reach the max deposit limit", "maxDepositLimitError");
        }

        return "deposit-transactions";

    }
}
