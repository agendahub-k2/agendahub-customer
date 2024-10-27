package com.agendahub.agendahub_customer.exception;

import com.agendahub.agendahub_customer.util.Constants;

public class EmailOrPasswordException extends RuntimeException {
    public EmailOrPasswordException() {
        super(Constants.PASSWORD_EMAIL_INVALID);
    }
}
