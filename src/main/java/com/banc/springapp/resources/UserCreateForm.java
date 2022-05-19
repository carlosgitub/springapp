package com.banc.springapp.resources;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;

/**
 * @project spring-bootbank
 * @autor carlosd on 2022-05-18
 */

@Component
@Setter
@Getter
@Slf4j
public class UserCreateForm {

    @NotEmpty
    @Length(min= 2, max=50)
    private String username;

    @NotEmpty
    private String password;

    @NotEmpty
    private String passwordConfirm;

    @NotEmpty
    @Length(min=2 , max= 50)
    private String email;


}
