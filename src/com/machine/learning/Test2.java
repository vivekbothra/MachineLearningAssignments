package com.machine.learning;

import java.util.Scanner;

public class Test2 {

	public static void main(String[] args) {
	
			int resp = 0;
			int distMetrix = 0;
			int trainChoice = 0;
			int TestIndex = 0;
			int DataSetType = 0; 
			int Kvalue = 0;
			
			ControlClassify classifyController = new ControlClassify();
			
		
		Scanner scanner = new Scanner(System.in);
			
			while(true)
			{
				System.out.println();
				System.out.println("#############################################");
				System.out.println("Choose operation");
				System.out.println("Press 1 : Choose distant metrice : ");
				System.out.println("Press 2 : Choose the dataSet");
				System.out.println("Press 3 : Input value of k for KNN  ");
				System.out.println("Press 4 : Choose the Training Set ");
				System.out.println("Press 5 : Choose a testing data ");
				System.out.println("Press 6 : Start Cassification ");
				System.out.println("Press 0 : To EXIT !!! ");
				System.out.println();
				
				resp = scanner.nextInt();
				
				switch(resp)
				{
				case 1: System.out.println("Press 1: For Eucledian");
						System.out.println("Press 2: For Polynomial Kernel");
						System.out.println("Press 3: For Radial Basis Kernel");
						distMetrix = scanner.nextInt();
				   break;
				   
				case 2: System.out.println("Press 1: For Ecoli dataset");
						System.out.println("Press 2: For Glass dataset");
						System.out.println("Press 3: For Yeast dataset");
						DataSetType = scanner.nextInt();
						
						classifyController.preProcessDataSet(DataSetType);
					break;
				
				case 3: System.out.println("Enter the value for k");
						Kvalue = scanner.nextInt(); 
		 			break;
		 			
				case 4: System.out.println("Enter a number between 1 to 9 to choose training set");
						trainChoice = scanner.nextInt();
					break;
					
				case 5: classifyController.showTestingDataSet(DataSetType);
					    System.out.println("Select a testing data from the above shown list");
					    TestIndex = scanner.nextInt();
					break;
					
				case 6: classifyController.startClassification(distMetrix, trainChoice, TestIndex, Kvalue);	
					break;
					
				default: System.exit(0);
				
				
				}
				
				
			}
	
}

}
