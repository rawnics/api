package com.aw.rest.dal.entity;

import java.io.Serializable;


public class Status implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int statusId;
	private String status;

	public Status() {
	}

	public Status(int statusId) {
		this.statusId = statusId;
	}

	public Status(int statusId, String status) {
		this.statusId = statusId;
		this.status = status;
	}

	/**
	 * @return the statusId
	 */
	public int getStatusId() {
		return statusId;
	}

	/**
	 * @param statusId the statusId to set
	 */
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Status [statusId=" + statusId + ", status=" + status + "]";
	}

	
}
