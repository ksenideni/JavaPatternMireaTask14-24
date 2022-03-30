package ru.ksenideni;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class EmailService {
    @Autowired
    private final JavaMailSender emailSender;
    @Value("${spring.mail.username}")
    private String EMAIL;

    @Async
    //without async: create completed in 3016ms
    //1830ms
    //2364ms
    //with async:create completed in 2526ms
    //1913ms
    //1668ms
    public void sendSimpleMessage(Object o) {
        System.out.println("start sending");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(EMAIL);
        message.setTo(EMAIL);
        message.setSubject("this is a message about saving an object in the database");
        //pretty time
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        message.setText(o.getClass().getSimpleName() + " is saved at " + formatter.format(date));
        emailSender.send(message);
        System.out.println("end sending");
    }
}
