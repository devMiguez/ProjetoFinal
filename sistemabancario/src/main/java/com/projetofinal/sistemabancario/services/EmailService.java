package com.projetofinal.sistemabancario.services;

import org.springframework.stereotype.Service;

@Service
public class EmailService {
    
    @Autowired
    public JavaMailSender mailSender;

    public void enviarEmail(String para, String assunto, String conteudo) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(para);
        message.setSubject(assunto);
        message.setText(conteudo);

        emailSender.send(message);
    }

}
