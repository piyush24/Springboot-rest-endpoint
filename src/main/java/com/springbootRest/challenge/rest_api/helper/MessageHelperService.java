package com.springbootRest.challenge.rest_api.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MessageHelperService {
    @Autowired
    private MessageSource messageSource;
    private Locale locale = Locale.getDefault();

    public String getMessage(String messageCode, Object... args) {
        return messageSource.getMessage(messageCode, args, locale);
    }
}
