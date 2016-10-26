package com.machine.learning;
/*
 * 
 * ExampleSet is a class which defines
 * a set of attributes present row
 * wise in the .csv file
 * 
 * 
 */
import java.util.List;

public class ExmpleSet {
	
	private List<Attr> ElementExampleList;
	
	private int i;
	private String ClassExample;
	

	public String getExampleClass() {
		return ClassExample;
	}

	public void setExampleClass(String exampleClass) {
		this.ClassExample = exampleClass;
	}

	public int getIndex() {
		return i;
	}

	public void setIndex(int index) {
		this.i = index;
	}

	public List<Attr> getexampleSetElementList() {
		return ElementExampleList;
	}

	public void setexampleSetElementList(List<Attr> exampleElementList) {
		this.ElementExampleList = exampleElementList;
	}
	
	
	
	

}
