package com.extremesolution.commonservice.model.base;

import org.springframework.data.annotation.Id;

public abstract class AbstractEntity {

	@Id
	String id;

	Boolean retire;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public AbstractEntity() {
		super();
	}

	public Boolean getRetire() {
		return retire;
	}

	public void setRetire(Boolean retire) {
		this.retire = retire;
	}

}
