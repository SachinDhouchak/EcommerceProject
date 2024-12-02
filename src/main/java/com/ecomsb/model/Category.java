package com.ecomsb.model;

import javax.persistence.*;

@Entity
public class Category {

	@Id	
    @GeneratedValue(strategy=GenerationType.AUTO)      // this should be come
	@Column(name= "category_id")
	private int id;
	
	private String name;

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

	
	
	
}
