package com.machine.learning;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * ReadData class reads the data from .csv file
 * path of the file is assigned to Filecsv variable
 */
public class ReadTheData {
	
	String Filecsv = "";
	BufferedReader buffreader = null;
	String line = null;
	List sampleData = new LinkedList();
	List dataSet = null;
	
	
	
	
	public ReadTheData(String Filecsv) {
		
		this.Filecsv = Filecsv;
	}


	public List getSamplData() {
		
		
		try {
			
			buffreader = new BufferedReader(new FileReader(Filecsv));
			while((line = buffreader.readLine()) != null){
				
				String[] st = line.split(","); 
				dataSet = new LinkedList();
				/*
				 * Store each row in a List
				 */
                for ( int i = 0 ; i < st.length ; i++)
                {       
                	dataSet.add(st[i]);
                	 
                }
                /*
                 * Store the row list in the SampleData List
                 */
                sampleData.add(dataSet);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return sampleData;

	   }

}
