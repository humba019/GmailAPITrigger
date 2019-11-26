package br.com.ibm;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {
		
	public void sendEmailToRequester(String to, String from, String text);
		
}
