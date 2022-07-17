package com.example.dec2021springboot.services;

import com.example.dec2021springboot.models.User;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class MailService {
    private JavaMailSender javaMailSender;

    public void sendEmail(User user) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        try {
            //mimeMessage.setFrom("nankovoj799@gmail.com");
            helper.setFrom("nankovoj799@gmail.com");
            helper.setTo(user.getEmail());
            helper.setText("to activat your account click <a href='http://localhost:8080/users/activateAccount/"+user.getId()+"'>here</a>", true);

        } catch (MessagingException exception) {
            exception.printStackTrace();
        }
 javaMailSender.send(mimeMessage);
    }
}
