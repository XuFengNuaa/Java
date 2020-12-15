package com.nuaa.bean;

public class Person {
	private int  did;
	
    private String name;

    private String pwd;

    private String age;

    private String gender;
    
    
    public Person(int did, String name, String pwd, String age, String gender) {
		super();
		this.did=did;
		this.name = name;
		this.pwd = pwd;
		this.age = age;
		this.gender = gender;
	}

	public Person() {
		super();
	}

	public int getDid() {
		return did;
	}

	public void setDid(int did) {
		this.did = did;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "Person [did=" + did + ", name=" + name + ", pwd=" + pwd + ", age=" + age + ", gender=" + gender + "]";
	}
	
}
	