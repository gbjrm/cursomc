package com.gabrielmatos.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.gabrielmatos.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
