package com.passwordbucket.tests;

import com.passwordbucket.model.services.PasswordServices;
import com.passwordbucket.model.services.impl.PasswordServicesImpl;

public class Testquery {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String name = "list1";
		
		String selectList = "SELECT * FROM list WHERE title = '"+name+"'";
		
		
		
		System.out.println(selectList);
		
		PasswordServices ps = new PasswordServicesImpl();
		
		System.out.println(ps.generatePassword());
	}

}
