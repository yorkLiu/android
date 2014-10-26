package com.demo.listviewsimpleadapter2;

public class AnimalCommand {
	
	private String title;
	private String name;
	private int age;
	private int score;
	private int profileIcon;
	
	public AnimalCommand(){}
	
	public AnimalCommand(String title, String name, int age,int profileIcon, int score){
		this.title = title;
		this.name = name;
		this.age = age;
		this.profileIcon = profileIcon;
		this.score = score;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getProfileIcon() {
		return profileIcon;
	}

	public void setProfileIcon(int profileIcon) {
		this.profileIcon = profileIcon;
	}
}
