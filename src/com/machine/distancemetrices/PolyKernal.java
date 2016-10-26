package com.machine.distancemetrices;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.machine.learning.Attr;
import com.machine.learning.ExmpleSet;

public class PolyKernal implements DistMetric 
{

	public List calculateDistance(List trainingSet, ExmpleSet testingExampleSet,
			int valueK) {
		
		List trainingExampleSetOfMinimumDist;
		Attr trainAttribute;
		Attr testAttr;
		Double sum = 0.0;
		Double distance = 0.00;
		Map<ExmpleSet, Double> distanceMap = new HashMap<ExmpleSet, Double>();
		
		List<Attr> testAttrList = new ArrayList<Attr>();
		testAttrList = testingExampleSet.getexampleSetElementList();
		
		List<Attr> trainAttrList = new ArrayList<Attr>();
		
		Iterator trainingSetIterator = trainingSet.iterator();
		while(trainingSetIterator.hasNext())
		{
			ExmpleSet trainingExampleSet = (ExmpleSet)trainingSetIterator.next();
			trainAttrList = trainingExampleSet.getexampleSetElementList();
			sum = 0.0;
			for(int i=0; i<(trainingExampleSet.getexampleSetElementList().size() - 1); i++)
			{
				trainAttribute = trainAttrList.get(i);
				testAttr = testAttrList.get(i);
				
				sum += (trainAttribute.getValue() * testAttr.getValue());
				
			}
			
			sum = sum + 1;
			distance = Math.pow(sum,3);
			
			distanceMap.put(trainingExampleSet, distance);
			
		}
		
		trainingExampleSetOfMinimumDist = findMinKDist(distanceMap, valueK);
		
		
		return trainingExampleSetOfMinimumDist;
	}
	
	private List findMinKDist(Map<ExmpleSet, Double> distanceMap, int valueK) {
	
		List listHavingMinValues = new ArrayList();
		ExmpleSet exampleSetMinDistance = null;

	    // getting the minimum value in the Hashmap

	    //go through the hashmap to get the key that corresponds to the minimum value in the Hashmap
	    
	    for(int i =0 ; i< valueK; i++){
	    	Double minValueInMap = (Collections.min(distanceMap.values()));
		    for (Map.Entry<ExmpleSet, Double> entry : distanceMap.entrySet()) {  // Iterate through hashmap
		        if (entry.getValue() == minValueInMap) {
	
		        	exampleSetMinDistance = entry.getKey();     // this is the key which has the minimum value
		        	listHavingMinValues.add(exampleSetMinDistance);
		        	distanceMap.remove(exampleSetMinDistance);
		        	break;
		        	}
	
		    	}
	    }
		
		
		return listHavingMinValues;
	}

}
