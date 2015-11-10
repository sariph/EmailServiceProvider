package com.company.email.service;

import java.util.Comparator;

public class EmailDomainNameComparatorAsc implements Comparator<String>{
	@Override
	public int compare(String oneEmail, String anotherEmail) {
		return (oneEmail.split("@")[1]).compareTo(anotherEmail.split("@")[1]);
	}
}
