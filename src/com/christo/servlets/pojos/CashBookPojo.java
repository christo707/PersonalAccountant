package com.christo.servlets.pojos;

import java.util.Date;

public class CashBookPojo {
	
	private int acid;
	private String description;
	private Date tran_date;
	private int amount;
	private int userid;
	private String operation;
	public CashBookPojo() {
		super();
	}
	public CashBookPojo(String description, Date tran_date, int amount, int userid, String operation) {
		super();
		this.description = description;
		this.tran_date = tran_date;
		this.amount = amount;
		this.userid = userid;
		this.operation = operation;
	}
	public CashBookPojo(int acid, String description, Date tran_date, int amount, int userid, String operation) {
		super();
		this.acid = acid;
		this.description = description;
		this.tran_date = tran_date;
		this.amount = amount;
		this.userid = userid;
		this.operation = operation;
	}
	public int getAcid() {
		return acid;
	}
	public void setAcid(int acid) {
		this.acid = acid;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getTran_date() {
		return tran_date;
	}
	public void setTran_date(Date tran_date) {
		this.tran_date = tran_date;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	@Override
	public String toString() {
		return "CashBookPojo [acid=" + acid + ", description=" + description + ", tran_date=" + tran_date + ", amount="
				+ amount + ", userid=" + userid + ", operation=" + operation + "]";
	}
	
	

}
