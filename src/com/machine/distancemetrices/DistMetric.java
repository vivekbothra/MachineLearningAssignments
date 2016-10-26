package com.machine.distancemetrices;

import java.util.List;

import com.machine.learning.ExmpleSet;

public interface DistMetric {
	
	public List calculateDistance(List trainingSet, ExmpleSet exampleSet,int valueK);

}
