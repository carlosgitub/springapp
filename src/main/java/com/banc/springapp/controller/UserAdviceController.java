package com.banc.springapp.controller;

import com.banc.springapp.authorisation.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * @project spring-bootbank
 * @autor carlosd on 2022-05-18
 */
@ControllerAdvice
public class UserAdviceController {

    @ModelAttribute("currentUser")
    public CustomUserDetails getCurrentUser(
            @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        return customUserDetails;
    }
}
