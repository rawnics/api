package com.aw.rest.dal.entity;

import java.io.Serializable;




public class Continent implements Serializable {

	private static final long serialVersionUID = 1L;

	private String code;
	private String name;

	public Continent() {
	}

	public Continent(String code) {
		this.code = code;
	}

	public Continent(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
