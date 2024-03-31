package com.example.onlinefoodstore.service;

import com.example.onlinefoodstore.model.dto.EmailDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;

    @Value(value = "${spring.mail.username}")
    private String username;

    public void postEmail(EmailDTO emailDTO) throws MessagingException, IOException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(username, "noreply@gmail.com");
        helper.setTo(emailDTO.getEmail());
        helper.setSubject(emailDTO.getSubject());
        helper.setText(getContentFromTemplate(emailDTO.getEmail(), emailDTO.getPassword()), true);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> javaMailSender.send(mimeMessage));
    }

    private String getContentFromTemplate(String login, String password) throws IOException {
        return Files.readString(Paths.get("src/main/resources/email-templates/activate_account.html")).replace("{{LOGIN}}", login).replace("{{PASSWORD}}", password);
    }
}