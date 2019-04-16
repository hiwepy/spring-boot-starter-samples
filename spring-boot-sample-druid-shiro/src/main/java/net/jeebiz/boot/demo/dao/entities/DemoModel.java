/** 
 * Copyright (C) 2018 Jeebiz (http://jeebiz.net).
 * All Rights Reserved. 
 */
package net.jeebiz.boot.demo.dao.entities;

import net.jeebiz.boot.api.dao.entities.BaseModel;

public class DemoModel extends BaseModel{
	
	private static final long serialVersionUID = 6189820231775242317L;
	
	private String id;
	
	private String name;

	private String text;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
 
}
