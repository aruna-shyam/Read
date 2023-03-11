package com.techpalle;

public class Department 
{
	//sql resultset we have 4 columns
	//for the data security we use private a.m
	private int id;
	private String name;
	private String location;
	private int strength;
	
	//inside class for assingning data into class we need constructor
	public Department(int id, String name, String location, int strength) 
	{
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.strength = strength;
	}
	
	
	/* private a.m 
	so give getter and setters(encapsulated class or bean class)*/
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getStrength() {
		return strength;
	}
	public void setStrength(int strength) {
		this.strength = strength;
	}
	
	

}
