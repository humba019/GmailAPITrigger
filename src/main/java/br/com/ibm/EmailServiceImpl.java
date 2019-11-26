package br.com.ibm;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{
	
    private JavaMailSender mailSender;
	private EmailContentBuilder mailContentBuilder;
	
	
	@Autowired
	public EmailServiceImpl(JavaMailSender mailSender, EmailContentBuilder mailContentBuilder) {
		this.mailSender = mailSender;
		this.mailContentBuilder = mailContentBuilder;
	}



	@Override
	public void sendEmailToRequester(String to, String from, String text) {
		 MimeMessagePreparator preparator = new MimeMessagePreparator() {
			 public void prepare(MimeMessage mimeMessage) throws Exception{
			 
				MimeMessageHelper  message = new MimeMessageHelper (mimeMessage);
				
		        message.setTo(to);
		        message.setFrom(from);
		        message.setSubject("BTP Cloud System - New Request!");
		        String content = mailContentBuilder.build(text);
		        message.setText(content, true);
		        
			 }
		 };
        
        try {
            mailSender.send(preparator);
   
            System.out.println("Email enviado com sucesso!"); 
            
        }catch (MailException e) {
            e.printStackTrace();
            System.out.println("Erro ao enviar email.");
        }
				
	}


 	

}
