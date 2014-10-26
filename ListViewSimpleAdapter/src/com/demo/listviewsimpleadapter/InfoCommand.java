package com.demo.listviewsimpleadapter;

public class InfoCommand {
	
	private Integer id;
	private String name;
	
	public InfoCommand(){}
	
	public InfoCommand(Integer id, String name){
		this.id = id;
		this.name = name;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
