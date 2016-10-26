package com.machine.distancemetrices;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.machine.learning.Attr;
import com.machine.learning.ExmpleSet;

public class EucDist implements DistMetric
{


	public List calculateDistance(List trainingSet, ExmpleSet testingExampleSet,
			int valueK) {
		
		List trainingExampleSetOfMinimumDist;
		Attr trainAttr;
		Attr testAttr;
		Double squareDifference = 0.0;
		Double dist = 0.00;
		Map<ExmpleSet, Double> MapDistance = new HashMap<ExmpleSet, Double>();
		
		List<Attr> testingAttrList = new ArrayList<Attr>();
		testingAttrList = testingExampleSet.getexampleSetElementList();
		
		List<Attr> trainAttrList = new ArrayList<Attr>();
		
		Iterator trainingSetItr = trainingSet.iterator();
		while(trainingSetItr.hasNext())
		{
			ExmpleSet trainingExampleSet = (ExmpleSet)trainingSetItr.next();
			trainAttrList = trainingExampleSet.getexampleSetElementList();
			squareDifference = 0.0;
			for(int i=0; i<(trainingExampleSet.getexampleSetElementList().size() - 1); i++)
			{
				trainAttr = trainAttrList.get(i);
				testAttr = testingAttrList.get(i);
				
				squareDifference += Math.pow((trainAttr.getValue() - testAttr.getValue()),2);
				
			}
			
			dist = Math.sqrt(squareDifference);
			
			MapDistance.put(trainingExampleSet, dist);
			
		}
		
		trainingExampleSetOfMinimumDist = findMinKDist(MapDistance, valueK);
		
		
		return trainingExampleSetOfMinimumDist;
	}
	
	
	private List findMinKDist(Map<ExmpleSet, Double> MapDistance, int valueK) {
		
		List MinValuesList = new ArrayList();
		ExmpleSet exampleSetMinDistance = null;

	    // take min values in hashmap

	   //go through hashmap to get key with the minimum value
	    
	    for(int i =0 ; i< valueK; i++){
	    	Double minValueInMap = (Collections.min(MapDistance.values()));
		    for (Map.Entry<ExmpleSet, Double> entry : MapDistance.entrySet()) {  //go through hashmap
		        if (entry.getValue() == minValueInMap) {
	
		        	exampleSetMinDistance = entry.getKey();     // this is the key that has minimum value
		        	MinValuesList.add(exampleSetMinDistance);
		        	MapDistance.remove(exampleSetMinDistance);
		        	break;
		        	}
	
		    	}
	    }
		
		
		return MinValuesList;
	}

}
