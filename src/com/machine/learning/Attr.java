package com.machine.learning;
/*
 * 
 * Attribute class shows the elements of dataset
 * nameattr
 * value
 * classtype
 */
public class Attr {
	
	private String nameattr;
	private String typeClass;
	private Double value;
	
	
	
	
	public String getName() {
		return nameattr;
	}
	public void setName(String nameattr) {
		this.nameattr = nameattr;
	}
	public String getClassType() {
		return typeClass;
	}
	public void setClassType(String typeClass) {
		this.typeClass = typeClass;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	
	

}
