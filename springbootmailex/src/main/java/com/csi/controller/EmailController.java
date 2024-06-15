package com.csi.controller;

import com.csi.model.EmailModel;
import com.csi.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private IEmailService emailServiceImpl;

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestBody EmailModel emailModel) {
        emailServiceImpl.sendEmail(emailModel);
        return new ResponseEntity<>("Email Send Successfully", HttpStatusCode.valueOf(200));
    }
}
