package com.projet.BackendPfe;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

	@Autowired JavaMailSender mailSender;
	public void sendEmailAttachment(final String subject, final String message, final String fromEmailAddress,
			final String toEmailAddresses, final boolean isHtmlMail /*, final File attachment*/) {
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			helper.setFrom(fromEmailAddress);
			helper.setTo(toEmailAddresses);
			helper.setSubject(subject);

			if (isHtmlMail) {
				helper.setText("<html><body><img width=auto;height=auto; src='cid:identifier1234'><div style=display: inline-block;\r\n"
						+ "    position: relative;></div></body></html>", true);
				FileSystemResource res1 = new FileSystemResource(new File("C:/Users/21698/files/rahma.png"));
				helper.addInline("identifier1234", res1);

			
			} else {
				helper.setText(message);
			}

			// attach the file into email body
			/*FileSystemResource file = new FileSystemResource(attachment);
			helper.addInline(attachment.getName(), file);*/

			mailSender.send(mimeMessage);

			System.out.println("Email sending complete.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	}

