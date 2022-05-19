package com.banc.springapp;

import com.banc.springapp.repository.UserRepository;
import com.banc.springapp.resources.UserCreateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


/**
 * @project spring-bootbank
 * @autor carlosd on 2022-05-18
 */
@Component
public class UserCreatorFormValidator  implements Validator {


    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UserCreateForm.class);
    }

    @Override
    public void validate(Object obj, Errors errors) {
       UserCreateForm form = (UserCreateForm) obj;
       validatePasswords(errors, form);
    }

    private void validatePasswords(Errors errors, UserCreateForm userCreateForm){
        if(!userCreateForm.getPassword().equals(userCreateForm.getPasswordConfirm())){
            errors.rejectValue("password", "message.PasswordNotMatch");

        }
    }

    private void validateUsername(Errors errors, UserCreateForm userCreateForm){
        if(errors.hasFieldErrors("username"))
            return;

        if(userRepository.findUserByUsername(userCreateForm.getUsername()).isPresent()){
            errors.rejectValue("username", "message.DuplicateName" );
        }
    }

    private void validateEmail(Errors errors, UserCreateForm userCreateForm) {
        if(errors.hasFieldErrors("email")){
            return;
        }
        if(userRepository.findUserByEmail(userCreateForm.getEmail()).isPresent()){
            errors.rejectValue("username", "message.DuplicateEmail" );
        }

    }
}
