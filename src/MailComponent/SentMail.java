package MailComponent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.mail.internet.AddressException;

public class SentMail {
	public static void main(String[] args) throws FileNotFoundException, IOException, AddressException {
		SendComponent sentmail = new SendComponent();
		Properties pro = new Properties();
		pro.load(new FileInputStream("C:/Users/abc.properties"));
		//String sFrom = pro.getProperty("FROMADDRESS");
		 String sFrom = "xxxxx.xxxxx@xxxxx.com";
		// System.out.println("sFrom > "+sFrom);
		//final String sPassword = pro.getProperty("PASSWORD");
		final String sPassword = "-=-=-=-=-=-=-=-=-";
		final String sPort = pro.getProperty("PORT");
		String sTo = "abc@gmail.com";
		String sSubject = "Test Mail From Gokul for Mail API";
		String sContent = "Test Mail\n";
		sContent += "TESTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT\n";
		sentmail.sent(sFrom, sTo, sSubject, sContent, sPassword, "", "abc", sPort);
	}
}
