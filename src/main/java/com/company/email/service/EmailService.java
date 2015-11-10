package com.company.email.service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Sariph Shrestha
 * 
 * Email Service has a method to take FilePath and Output Sorted List of Emails
 *
 */
@Component
public class EmailService {
	private static final Logger emailServiceHelperLogger = Logger.getLogger(EmailService.class);
	private static final String applicationContextXmlName = "applicationContext.xml";
	private static final String className="emailService";
	private static final String inputFileName="emailList_bucket_unsorted_A.txt";
	
	@Autowired
	private EmailServiceHelper emailServiceHelper;
	
	/**
	 * Sorts Email in ascending order
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public List<String> sortEmailFromFile(String filePath) throws IOException{
		List<String> emailList = emailServiceHelper.getEmailListFromFile(filePath);
		Collections.sort(emailList, new EmailDomainNameComparatorAsc());
		return emailList;
	}
	
	public static void main(String arg[]) throws IOException{
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext(applicationContextXmlName);
		EmailService emailService=(EmailService)applicationContext.getBean(className);
		emailServiceHelperLogger.debug(emailService.sortEmailFromFile(inputFileName));
		((ConfigurableApplicationContext)applicationContext).close();
	}
}
