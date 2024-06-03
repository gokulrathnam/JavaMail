package MailComponent;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendComponent {

	public void sent(String sFrom, String sTo, String sSubject, String sContent, String sPassword, String sAttachment,
			String sService,String sPort) throws IOException, AddressException {
		final String from = sFrom;
		final String password = sPassword;

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.trust", "*");
		props.put("mail.smtp.starttls.enable", "true");
		if (sService.equals("Inspirisys")) {
			props.put("mail.smtp.host", "mail.inspirisys.com");
		} else if (sService.equals("Gmail")) {
			props.put("mail.smtp.host", "smtp.gmail.com");
		} else {
			System.out.println("Mentioned Service Not Provide.........");
			return;
		}
		props.put("mail.smtp.ssl.protocols", "TLSv1.2");
		props.put("mail.smtp.port", sPort);
		//System.out.println(from+"     "+password);
		InternetAddress emailAddr = new InternetAddress(sTo);

		emailAddr.validate();

		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		});
		session.setDebug(true);
		try {
			Message message = new MimeMessage(session);
			MimeBodyPart mimeBodyPart = new MimeBodyPart();
			MimeBodyPart htmlpart = new MimeBodyPart();
			Multipart multipart = new MimeMultipart();
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sTo));
			message.addRecipient(Message.RecipientType.CC, new InternetAddress("xxxxxxxx@gmail.com"));
			message.setSubject(sSubject);
			//mimeBodyPart.setText(sContent);
			//multipart.addBodyPart(mimeBodyPart);

			StringBuffer sbHTML = new StringBuffer();
			sbHTML.append("<html>");
			sbHTML.append("<body>");
			sbHTML.append("<table border='3'>");
			sbHTML.append("<tr>");
			sbHTML.append("<td align='center'><b>TYPE</b></td>");
			sbHTML.append("<td align='center'><b>COUNT</b></td>");
			sbHTML.append("<td align='center'><b>AMOUNT</b></td>");
			sbHTML.append("</tr>");
			sbHTML.append("<tr>");
			sbHTML.append("<td align='center'>PHYSICAL DEPOSIT</td>");
			sbHTML.append("<td align='center'>5</td>");
			sbHTML.append("<td align='center'>50</td> ");
			sbHTML.append("</tr>");
			sbHTML.append("<tr>");
			sbHTML.append("<td align='center'>SI</td>");
			sbHTML.append("<td align='center'>5</td>");
			sbHTML.append("<td align='center'>50</td>");
			sbHTML.append("</tr>");
			sbHTML.append("<tr>");
			sbHTML.append("<td align='center'>PHYSICAL DEPOSIT</td>");
			sbHTML.append("<td align='center'>5</td>");
			sbHTML.append("<td align='center'>50</td>");
			sbHTML.append("</tr>");
			sbHTML.append("<tr>");
			sbHTML.append("<td align='center' colspan='2'>TOTAL</td>");
			sbHTML.append("<td align='center'>100</td>");
			sbHTML.append("</tr>");
			sbHTML.append("</table>");
			sbHTML.append("</body>");
			sbHTML.append("</html>");
			System.out.println("HTML CONTENT : >>>>>>>>   >>>>   "+sbHTML.toString());
			
			mimeBodyPart.setContent(sbHTML.toString(), "text/html");
			multipart.addBodyPart(mimeBodyPart);
			
			message.setContent(multipart);

			if (!sAttachment.equals("")) {
				mimeBodyPart.setContent(sContent, "text/html");
				multipart.addBodyPart(mimeBodyPart);

				MimeBodyPart attachmentBodyPart = new MimeBodyPart();
				attachmentBodyPart.attachFile(new File(sAttachment));
				multipart.addBodyPart(attachmentBodyPart);
				message.setContent(multipart);
			}
			Transport.send(message);

			System.out.println("Mail successfully sent..");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
