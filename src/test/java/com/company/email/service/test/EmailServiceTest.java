package com.company.email.service.test;

import static com.jcabi.matchers.RegexMatchers.matchesPattern;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.company.email.service.EmailService;
import com.company.email.service.EmailServiceHelper;

/**
 * 
 * @author Sariph Shrestha
 * 
 * This class used for Testing Email Service
 * Test Case for Checking Whether The Input File is Empty or Not
 * Test Case for Checking Whether The Input File has Valid Emails
 * Test Case for Checking Whether The Sorting is Correctly Being Done
 *
 */
public class EmailServiceTest {
	private final String emailEmptyBucketA="emailList_bucket_empty_A.txt";
	private final String emailInvalidEmailsB="emailList_invalid_emails_A.txt";
	private final String emailUnsortedBucketA="emailList_bucket_unsorted_A.txt";
	private final String emailSortedBucketA="emailList_bucket_sorted_A.txt";
	private final String emailPattern="^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
	
    @InjectMocks
    private EmailService emailService;

    @Spy
    private EmailServiceHelper emailServiceHelper;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    
    //This tests whether a file is empty or not for Empty File
    @Test
    public void testEmptyFileForEmptyFile() throws IOException{
    	assertTrue(emailServiceHelper.getEmailListFromFile(emailEmptyBucketA).isEmpty());
    }
    
    //This tests whether a file is empty or not for Non Empty File
    @Test
    public void testEmptyFileForNonEmptyFile() throws IOException{
    	assertFalse(emailServiceHelper.getEmailListFromFile(emailUnsortedBucketA).isEmpty());
    }

    //This tests whether all email are valid or not for Valid Emails
    @Test
    public void testAllEmailsValidForValidEmails() throws IOException{
    	List<String> emailList=emailServiceHelper.getEmailListFromFile(emailUnsortedBucketA);
    	for(String email: emailList){
    		assertThat(email, matchesPattern(emailPattern));
    	}
    }
    
    //This tests whether all email are valid or not for Invalid Emails
    @Test
    public void testAllEmailsValidForInvalidEmails() throws IOException{
    	//Put Invalid email to fail the test: emailInvalidEmailsB
    	List<String> emailList=emailServiceHelper.getEmailListFromFile(emailUnsortedBucketA);
    	for(String email: emailList){
    		assertThat(email, matchesPattern(emailPattern));
    	}
    }
    
    //This tests whether a file is correctly sorted or not
    @Test
    public void testSortEmailFromFile() throws IOException{
    	List<String> emailList=emailServiceHelper.getEmailListFromFile(emailUnsortedBucketA);
    	//Check for null or not null
    	assertFalse(emailList.isEmpty());
    	
    	//Check for validity of email
    	for(String email: emailList){
    		assertThat(email, matchesPattern(emailPattern));
    	}
    	
    	//Check for sorting
    	assertEquals(emailService.sortEmailFromFile(emailUnsortedBucketA),emailServiceHelper.getEmailListFromFile(emailSortedBucketA));
    }
}