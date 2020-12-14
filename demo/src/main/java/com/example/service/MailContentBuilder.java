package com.example.service;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MailContentBuilder {

	private final TemplateEngine templateEngine;
	
	public String build(String message) {
		/*
		 * prende il mio messaggio come input e usa il templateEngine
		 * per generare il messaggio nell'email
		 * Stiamo iniettando il messaggio nel template utilizzando il context
		 */
		Context context = new Context();
		context.setVariable("message", message);
		return templateEngine.process("mailTemplate", context);
	}
}
