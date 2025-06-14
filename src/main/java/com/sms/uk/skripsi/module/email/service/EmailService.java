package com.sms.uk.skripsi.module.email.service;

import com.sms.uk.skripsi.module.user.entities.MasterUser;
import jakarta.mail.MessagingException;
import org.springframework.scheduling.annotation.Async;
import org.thymeleaf.context.Context;

public interface EmailService {


    void sendEmailRegistration(MasterUser user) throws MessagingException;


    @Async
    void sendEmailInterviewNotification(MasterUser user, boolean isValid) throws MessagingException;

    void sendForgotPasswordHtmlEmail(String to, Context context) throws MessagingException;

    void sendEmailApprovalNotification(MasterUser user, boolean isApproved) throws MessagingException;

}
