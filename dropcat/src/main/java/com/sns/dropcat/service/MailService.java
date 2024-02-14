package com.sns.dropcat.service;

import java.util.Collection;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import ch.qos.logback.classic.Logger;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailService {

	//private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private TemplateEngine templateEngine;

	public void TemplateMail(String toUser, String resetLink) throws MessagingException {

		MimeMessage messageTemplate = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(messageTemplate, true);
		helper.setFrom("Dropcat<cuezoe@gmail.com>");
		helper.setTo(toUser);
		helper.setSubject("Dropcat密碼重設驗證信");

		Context context = new Context();
		context.setVariable("username", "promise");
		context.setVariable("resetLink", resetLink);

		String template = templateEngine.process("MailTemplate", context);
		helper.setText(template, true);
		mailSender.send(messageTemplate);
	}

}
