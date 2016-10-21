package com.mdw360.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This class is used to expose the RestFul Endpoint for mailing
 * Created by Omkar Marathe
 */
@Controller
public class SendController {

    @Autowired
    private MailSender mailSender;

    private SimpleMailMessage templateMessage;

    @Value("${send.from.email}")
    private String fromEmail;

    @Value("${send.to.email}")
    private String toEmail;

    @RequestMapping("/send.html")
    public String send(Model model){
        System.out.println("Starting Send...");
        String response = "OK";
        this.templateMessage = new SimpleMailMessage();

        this.templateMessage.setSubject("Dinner Party");
        this.templateMessage.setFrom(this.fromEmail);
        this.templateMessage.setTo(this.toEmail);

        SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
        msg.setText("Please come for dinner tonight.");

        try{
            this.mailSender.send(msg);
        }
        catch(MailException ex){
            response = "NO_OK";
            System.err.println(ex.getMessage());
        }
        System.out.println("Finished Send...");
        return response;
    }

}
