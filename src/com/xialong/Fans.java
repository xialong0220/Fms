package com.xialong;

public class Fans {
	private long id;
	private String name;
	private int age;
	private String sex;
	private String rank;

	public Fans(long id, String name, int age, String sex, String rank) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.rank = rank;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String toString() {
		return id + "\t" + name + "\t" + age + "\t" + sex + "\t" + rank;
	}

}
