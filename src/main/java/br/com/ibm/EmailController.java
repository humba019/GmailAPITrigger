package br.com.ibm;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    
	private EmailService emailService;
	
	@Autowired
    public EmailController(EmailService emailService) {
		this.emailService = emailService;
	}



	@GetMapping("/email-send")
    public String sendMail() {    	
    	Email email = new Email("paulo.cezar.dias@ibm.com","cloudsystem.teste@gmail.com","Teste de Envio de ticket para o Paulo");
    	
    	try {
    		emailService.sendEmailToRequester(email.getTo(), email.getFrom(), email.getText());
    		
    		return "Email enviado com sucesso!"; 
    		
    	}catch(Exception e) {
    		
    		return "Email nao enviado com sucesso!"; 
    		
    	}
    }

}
