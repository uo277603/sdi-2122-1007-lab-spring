package com.uniovi.sdi21221007spring.validators;

import com.uniovi.sdi21221007spring.entities.Mark;
import com.uniovi.sdi21221007spring.entities.User;
import com.uniovi.sdi21221007spring.services.MarksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class AddMarkFormValidator implements Validator {

    @Autowired
    private MarksService marksService;


    @Override
    public boolean supports(Class<?> clazz) {
        return Mark.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Mark mark = (Mark) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "Error.empty");

        if(mark.getScore() < 0 || mark.getScore() > 10){
            errors.rejectValue("score", "Error.addMark.score.range");
        }
        if(mark.getDescription().length() < 20){
            errors.rejectValue("description", "Error.addMark.description.length");
        }
    }
}
