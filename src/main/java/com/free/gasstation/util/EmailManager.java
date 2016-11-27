/**
 * 
 */
package com.free.gasstation.util;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

/**
 * @author Mohamed265
 *
 */
public class EmailManager {

	private static final Logger logger = Logger.getLogger(EmailManager.class);

	private static Session session;

	private static String from;
	private static String username;
	private static String password;

	public static void init() {
		Properties props = new EmailProperties().loadConfig();

		from = props.getProperty("from");
		username = props.getProperty("username");
		password = props.getProperty("password");

		session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		logger.info("EmailManager init successfly");
	}

	public static synchronized void send(String to, String subject, String content) {

		if (session == null)
			init();

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);
			message.setText(content);
			Transport.send(message);
			logger.info("message sent to " + to + " successfully");
		} catch (MessagingException e) {
			logger.error(e);
			throw new RuntimeException(e);
		}
	}

	public static void close() {
		session = null;
		logger.info("EmailManager closed successfly");
	}
}
