package com.company.email.service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.stereotype.Component;


@Component
public class EmailServiceHelper{
	public List<String> getEmailListFromFile(String filePath) throws IOException{
		return  Files.readAllLines(Paths.get(filePath), Charset.defaultCharset());
	}
}
