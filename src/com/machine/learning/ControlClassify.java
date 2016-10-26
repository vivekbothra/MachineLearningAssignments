package com.machine.learning;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.machine.distancemetrices.DistMetric;
import com.machine.distancemetrices.EucDist;
import com.machine.distancemetrices.PolyKernal;
import com.machine.distancemetrices.RadialBasisKernel;

public class ControlClassify {
	
	
	
	private DistMetric distMetric;
	
	private ReadTheData readData;
	private List<Attr> attributesList;
	private String[] names ;
	List<Attr> subSetExampleList;
	private List<ExmpleSet> exampleSetList;
	
	private ArrayList<List<Attr>> listBynames ;
	
	private  String ecoliPath = "C:/Users/vivek/workspace/ML_Assignment_1/SampleData/ecoli.csv";
	private  String glassPath = "C:/Users/vivek/workspace/ML_Assignment_1/SampleData/glass.csv";
	private  String yeastPath = "C:/Users/vivek/workspace/ML_Assignment_1/SampleData/yeast.csv";
	
	/*
	 * List to store the attributes on basis of Attribute Names
	 */
	private List<Attr> MCGname ;
	private List<Attr> GVHname ;
	private List<Attr> LIPname ;
	private List<Attr> CHGname ;
	private List<Attr> AACname ;
	private List<Attr> ALM1name ;
	private List<Attr> ALM2name;
	
	private List<Attr> RIGlassname ;
	private List<Attr> NAGlassname ;
	private List<Attr> MgGlassname ;
	private List<Attr> ALGlassname ;
	private List<Attr> SIGlassname ;
	private List<Attr> KGlassname ;
	private List<Attr> CAGlassname;
	private List<Attr> BAGlassname;
	private List<Attr> FEGlassname;
	
	private List<Attr> mcgYeast ;
	private List<Attr> gvhYeast ;
	private List<Attr> almYeast ;
	private List<Attr> mitYeast ;
	private List<Attr> erlYeast ;
	private List<Attr> poxYeast ;
	private List<Attr> vacYeast;
	private List<Attr> nucYeast;

	
	/*
	 * SuperSetDataList is the List which contains Training Data Set and 
	 * Testing Data Set
	 * Since the value of k is 10 , we have 9 Training data sets and 
	 * 1 Testing data set
	 */
	private List<List> superSetList;

	private List<ExmpleSet> trainingList1;
	private List<ExmpleSet> trainingList2;
	private List<ExmpleSet> trainingList3;
	private List<ExmpleSet> trainingList4;
	private List<ExmpleSet> trainingList5;
	private List<ExmpleSet> trainingList6;
	private List<ExmpleSet> trainingList7;
	private List<ExmpleSet> trainingList8;
	private List<ExmpleSet> trainingList9;
	private List<ExmpleSet> testingList;
	
	
	public void preProcessDataSet(int typeOfdata){
		
		if(typeOfdata == 1){
			
			initializeEcoli(ecoliPath);
		}
		else if(typeOfdata == 2)
		{
			initializeGlass(glassPath);
		}
		else
		{
			initializeYeast(yeastPath);
		}
		
		performNormalization();
		performKFoldCrossValidation();
		
		
	}
	
	
	public void startClassification(int distanceMetrices,
			int trainingSetChoices, int testingDataIndex,
			int Kvalue) {
		
		List<ExmpleSet> testingList = (List<ExmpleSet>) superSetList.get(9);
		ExmpleSet testingExampleSet = testingList.get(testingDataIndex);
		List trainingList = superSetList.get(trainingSetChoices-1);
		List<ExmpleSet> exampleSetList = null;		
			if(distanceMetrices == 1)
			{
				distMetric = new EucDist();
			}
			else if(distanceMetrices == 2){
				
				distMetric = new PolyKernal();
			}
			else
			{
				distMetric = new RadialBasisKernel();
			}
			
			System.out.println("you choose testingExampleSet index= " + testingExampleSet.getIndex() + " testinExampleSetClass = " + testingExampleSet.getExampleClass());
		
			exampleSetList = distMetric.calculateDistance(trainingList, testingExampleSet,Kvalue);
			
			for(ExmpleSet exampleSet: exampleSetList){
				
				System.out.println("exampleset index = " +exampleSet.getIndex() + "exampeSet class = " + exampleSet.getExampleClass());
			}
		
		
	}


	private void initializeYeast(String yeastPath) {
		
		ExmpleSet exampleSet = null;
		Attr attribute = null;
		readData = new ReadTheData(yeastPath);
		List<?> sampleData = readData.getSamplData();
		attributesList = new ArrayList<Attr>();
		
		exampleSetList = new ArrayList<ExmpleSet>();
		
		listBynames = new ArrayList<List<Attr>>();
		
		/*
		 * Initializing the name List
		 */
		mcgYeast = new ArrayList<Attr>();
		gvhYeast = new ArrayList<Attr>();
		almYeast = new ArrayList<Attr>();
		mitYeast = new ArrayList<Attr>();
		erlYeast = new ArrayList<Attr>();
		poxYeast = new ArrayList<Attr>();
		vacYeast = new ArrayList<Attr>();
		nucYeast = new ArrayList<Attr>();
		
		listBynames.add(mcgYeast);
		listBynames.add(gvhYeast);
		listBynames.add(almYeast);
		listBynames.add(mitYeast);
		listBynames.add(erlYeast);
		listBynames.add(poxYeast);
		listBynames.add(vacYeast);
		listBynames.add(nucYeast);
		
		//Names of Attributes
		initializeNames(sampleData);
		
		for(int i=1;i<sampleData.size();i++)
		{
			List<String> dataSet = (List<String>) sampleData.get(i);
			
			exampleSet = new ExmpleSet();
			
			subSetExampleList = new ArrayList<Attr>();
			
			for(int j=0; j< dataSet.size()-1;j++)
			{
				attribute = new Attr();
				attribute.setValue(Double.valueOf(dataSet.get(j).trim()));
				attribute.setName(names[j]);
				attribute.setClassType((dataSet.get(dataSet.size()-1)));
				
				/*
				 * Adding the attributes to the Attributes List
				 */
				attributesList.add(attribute);
				
				/*
				 * Adding the attributes to their respective row wise exampleSet
				 */
				subSetExampleList.add(attribute);
				
				/*
				 * Adding the attributes to their respective names List
				 */
				listBynames.get(j).add(attribute);
				
			}
			
			exampleSet.setexampleSetElementList(subSetExampleList);
			exampleSet.setIndex(i);
			exampleSet.setExampleClass(dataSet.get(dataSet.size()-1));
			exampleSetList.add(exampleSet);
			
			
		}

		
	}


	private void initializeGlass(String glassPath) {
		
		ExmpleSet exampleSet = null;
		Attr attribute = null;
		readData = new ReadTheData(glassPath);
		List<?> sampleData = readData.getSamplData();
		attributesList = new ArrayList<Attr>();
		
		exampleSetList = new ArrayList<ExmpleSet>();
		
		listBynames = new ArrayList<List<Attr>>();
		
		/*
		 * Initializing the name List
		 */
		RIGlassname = new ArrayList<Attr>();
		NAGlassname = new ArrayList<Attr>();
		MgGlassname = new ArrayList<Attr>();
		ALGlassname = new ArrayList<Attr>();
		SIGlassname = new ArrayList<Attr>();
		KGlassname = new ArrayList<Attr>();
		CAGlassname = new ArrayList<Attr>();
		BAGlassname = new ArrayList<Attr>();
		FEGlassname = new ArrayList<Attr>();
		
		listBynames.add(RIGlassname);
		listBynames.add(NAGlassname);
		listBynames.add(MgGlassname);
		listBynames.add(ALGlassname);
		listBynames.add(SIGlassname);
		listBynames.add(KGlassname);
		listBynames.add(CAGlassname);
		listBynames.add(BAGlassname);
		listBynames.add(FEGlassname);
		
		//Names of Attributes
		initializeNames(sampleData);
		
		for(int i=1;i<sampleData.size();i++)
		{
			List<String> dataSet = (List<String>) sampleData.get(i);
			
			exampleSet = new ExmpleSet();
			
			subSetExampleList = new ArrayList<Attr>();
			
			for(int j=0; j< dataSet.size()-1;j++)
			{
				
				attribute = new Attr();
				attribute.setValue(Double.valueOf(dataSet.get(j)));
				attribute.setName(names[j]);
				attribute.setClassType((dataSet.get(dataSet.size()-1)));
				
				/*
				 * Adding the attributes to the Attributes List
				 */
				attributesList.add(attribute);
				
				/*
				 * Adding the attributes to their respective row wise exampleSet
				 */
				subSetExampleList.add(attribute);
				
				/*
				 * Adding the attributes to their respective names List
				 */
				listBynames.get(j).add(attribute);
				
			}
			
			exampleSet.setexampleSetElementList(subSetExampleList);
			exampleSet.setIndex(i);
			exampleSet.setExampleClass(dataSet.get(dataSet.size()-1));
			exampleSetList.add(exampleSet);
			
			
		}

		
	}


	private void initializeEcoli(String ecoliPath) {
		
		ExmpleSet exampleSet = null;
		Attr attribute = null;
		readData = new ReadTheData(ecoliPath);
		List<?> sampleData = readData.getSamplData();
		attributesList = new ArrayList<Attr>();
		
		exampleSetList = new ArrayList<ExmpleSet>();
		
		listBynames = new ArrayList<List<Attr>>();
		
		/*
		 * Initializing the name List
		 */
		MCGname = new ArrayList<Attr>();
		GVHname = new ArrayList<Attr>();
		LIPname = new ArrayList<Attr>();
		CHGname = new ArrayList<Attr>();
		AACname = new ArrayList<Attr>();
		ALM1name = new ArrayList<Attr>();
		ALM2name = new ArrayList<Attr>();
		
		listBynames.add(MCGname);
		listBynames.add(GVHname);
		listBynames.add(LIPname);
		listBynames.add(CHGname);
		listBynames.add(AACname);
		listBynames.add(ALM1name);
		listBynames.add(ALM2name);
		
		//Names of Attributes
		initializeNames(sampleData);
		
		for(int i=1;i<sampleData.size();i++)
		{
			List<String> dataSet = (List<String>) sampleData.get(i);
			
			exampleSet = new ExmpleSet();
			
			subSetExampleList = new ArrayList<Attr>();
			
			for(int j=0; j< dataSet.size()-1;j++)
			{
				
				attribute = new Attr();
				attribute.setValue(Double.valueOf(dataSet.get(j)));
				attribute.setName(names[j]);
				attribute.setClassType((dataSet.get(dataSet.size()-1)));
				
				/*
				 * Adding the attributes to the Attributes List
				 */
				attributesList.add(attribute);
				
				/*
				 * Adding the attributes to their respective row wise exampleSet
				 */
				subSetExampleList.add(attribute);  // all rows 
				
				/*
				 * Adding the attributes to their respective names List
				 */
				listBynames.get(j).add(attribute);   //this list is column list used for normalization
				
			}
			
			exampleSet.setexampleSetElementList(subSetExampleList);
			exampleSet.setIndex(i);
			exampleSet.setExampleClass(dataSet.get(dataSet.size()-1));
			exampleSetList.add(exampleSet);
			
			
		}

		
	}
	
	private void initializeNames(List<?> sampleDataSet) {

		int i = 0;
		
			
			List dataSet = (List) sampleDataSet.get(0);
			Iterator dataSetIterator = dataSet.iterator();
			
			names = new String[dataSet.size()];  //initializing names dynamically
			while(dataSetIterator.hasNext())
			{
				names[i] = (String) dataSet.get(i);
				i++;
				dataSetIterator.next();
			}
		
	
	}
	
	
	/*
	 * This method is used to normalize the namedAttribute list
	 * according to their respective names 
	 * normalizing formula used newValue = (value - minValue) / (maxValue - minValue)
	 */
	public void performNormalization()
	{
		Double maximum;
		Double minimum;
		Double diff;
		int i =1;
		
		Iterator listAccordingToNamesItr = listBynames.iterator();
		while(listAccordingToNamesItr.hasNext())
		{
			List<Attr> attributeNamesList = (List<Attr>) listAccordingToNamesItr.next();
			maximum = calculateMaxValue(attributeNamesList);
			minimum = calculateMinValue(attributeNamesList);
			diff = maximum - minimum;
			Iterator attributeNamesListItr = attributeNamesList.iterator();
			while(attributeNamesListItr.hasNext())
			{
				Attr attribute = (Attr) attributeNamesListItr.next();
				Double newValue = (attribute.getValue() - minimum)/ diff ;
				
				
				attribute.setValue(newValue);
				i++;
			}
			i=1;
		}
		
	}//performNormalization
	
	/*
	 * Calculate maximum value in the given List
	 */
	private Double calculateMaxValue(List<Attr> namesList)
	{
		Double maximum = 0.00;
		for(Attr attribute: namesList)
		{
			if(attribute.getValue() > maximum)
			{
				maximum = attribute.getValue();
			}
			
		}
		
		return maximum;
	}//Calculate Max
	
	/*
	 * Calculate minimum value in the given List
	 */
	private Double calculateMinValue(List<Attr> namesList)
	{
		Double minimum = 1.00;
		for(Attr attribute: namesList)
		{
			if(attribute.getValue() < minimum)
			{
				minimum = attribute.getValue();
			}
			
		}
		
		return minimum;
	}//calculate Min
	
		
	public void performKFoldCrossValidation()
	{
		int k = 10;
		
		//Randomizing Example Set
		Collections.shuffle(exampleSetList);
		
		superSetList = new ArrayList<List>();
		trainingList1 = new ArrayList<ExmpleSet>();
		trainingList2 = new ArrayList<ExmpleSet>();
		trainingList3 = new ArrayList<ExmpleSet>();
		trainingList4 = new ArrayList<ExmpleSet>();
		trainingList5 = new ArrayList<ExmpleSet>();
		trainingList6 = new ArrayList<ExmpleSet>();
		trainingList7 = new ArrayList<ExmpleSet>();
		trainingList8 = new ArrayList<ExmpleSet>();
		trainingList9 = new ArrayList<ExmpleSet>();
		testingList = new ArrayList<ExmpleSet>();
		
		superSetList.add(trainingList1);
		superSetList.add(trainingList2);
		superSetList.add(trainingList3);
		superSetList.add(trainingList4);
		superSetList.add(trainingList5);
		superSetList.add(trainingList6);
		superSetList.add(trainingList7);
		superSetList.add(trainingList8);
		superSetList.add(trainingList9);
		superSetList.add(testingList);
		
		/*
		 * add examples from examplesetlist to supersetlist
		 * i%k will get Lists from 0 to 9 inside superSetList 
		 * From 0 to 8 , this will fetch all training data set and 9th will be testing data set
		 */
		for(int i = 0; i< exampleSetList.size(); i++){
			
			superSetList.get(i % k).add(exampleSetList.get(i));
			
		}

	}
	
	
	public void showTestingDataSet(int typeOfData) {
	
		int index=0;
		List<ExmpleSet> testingList = superSetList.get(9);
		
		if(typeOfData == 1)
		{
			System.out.println("index \t mcg \t gvh \t lip \t chg \t aac \t alm1 \t alm2 \t class");
		}
		else if(typeOfData == 2)
		{
			System.out.println("index \t RI \t NA \t Mg \t Al \t Si \t K \t Ca \t Ba \t Fe  \t Type_Of_Glass");
		}
		else
		{
			System.out.println("index \t mcg \t gvh \t alm \t mit \t erl \t pox \t vac \t nuc \t Class_Distribution");
		}
		
		System.out.println();
		for(ExmpleSet exampleSet : testingList){
			
			List<Attr> attributeList = exampleSet.getexampleSetElementList();
			System.out.print(index++);
			for(Attr attribute : attributeList){
				
				System.out.print("\t" + attribute.getValue());
				
			}
			
			System.out.print("\t" + exampleSet.getExampleClass());
			System.out.println();
			
		}
	
	}
	

}
