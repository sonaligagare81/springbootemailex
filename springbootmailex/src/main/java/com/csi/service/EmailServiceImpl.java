package com.csi.service;

import com.csi.model.EmailModel;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailServiceImpl implements IEmailService {

    @Autowired
    JavaMailSender javaMailSender;

    @Override
    public void sendEmail(EmailModel emailModel) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom("sonaligagare81@gmail.com");

            mimeMessageHelper.setTo(emailModel.getToEmail());
            mimeMessageHelper.setCc(emailModel.getCcEmail());
            mimeMessageHelper.setSubject(emailModel.getEmailSubject());
            mimeMessageHelper.setText(emailModel.getEmailBody());

            FileSystemResource fileSystemResource = new FileSystemResource(emailModel.getEmailAttachment());

            mimeMessageHelper.addAttachment(fileSystemResource.getFilename(),fileSystemResource);

            javaMailSender.send(mimeMessage);

            log.info("Email send successfully");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
