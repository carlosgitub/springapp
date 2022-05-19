package com.banc.springapp.entity;

import com.banc.springapp.enums.Currency;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * @project spring-bootbank
 * @autor carlosd on 2022-05-18
 */
@Entity
@Table(name= "deposits")
@Getter
@Setter
@ToString
public class Deposits {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "deposit_id")
    private long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_account_id", nullable = false)
    private CustomerAccount customerAccount;

    //it is attribute for enum mapping in hibernate
    @Enumerated(EnumType.STRING)
    @Column(name = "currency", nullable = false)
    private com.banc.springapp.enums.Currency currency;

    @Column(name = "amount", nullable = false)
    private Double amount;

    public Deposits()  {

    }

    public Deposits(CustomerAccount customerAccount, Currency currency, Double amount) {
        this.customerAccount = customerAccount;
        this.currency = currency;
        this.amount = amount;
    }
}
