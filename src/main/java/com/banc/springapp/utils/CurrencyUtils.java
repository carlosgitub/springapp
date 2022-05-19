package com.banc.springapp.utils;

import com.banc.springapp.enums.Currency;

import static com.banc.springapp.enums.Currency.*;

/**
 * @project spring-bootbank
 * @autor carlosd on 2022-05-18
 */
public class CurrencyUtils {

    public static Currency convertStringToCurrency (String currency) {
        switch (currency.toLowerCase()) {
            case "euro":
                return EURO;
            case "swedishkrona":
                return SWEDISHKRONA;
            case "pound":
                return POUND;
            default:
                return EURO;

        }
    }
}
