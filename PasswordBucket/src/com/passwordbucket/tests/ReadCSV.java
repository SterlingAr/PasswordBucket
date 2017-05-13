package com.passwordbucket.tests;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;

import com.opencsv.CSVReader;

public class ReadCSV {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		  CSVReader reader = new CSVReader(new FileReader("yourfile2.csv"), ',' , '"' , 1);
	       
	      //Read CSV line by line and use the string array as you want
	      String[] nextLine;
	      try {
			while ((nextLine = reader.readNext()) != null) {
			     if (nextLine != null) {
			        //Verifying the read data here
			        System.out.println(Arrays.toString(nextLine));
			     }
			   }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
