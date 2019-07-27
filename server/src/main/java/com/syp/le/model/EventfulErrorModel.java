package com.syp.le.model;

import org.pojomatic.annotations.AutoProperty;

/**
 * Hold the information of Eventful error response
 * 
 * @author <a href="mailto:ricky.shiyouping@gmail.com">Ricky Shi</a>
 *
 * @since Jul 27, 2019
 */
@AutoProperty
public class EventfulErrorModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	private String error;
	private String status;
	private String description;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
