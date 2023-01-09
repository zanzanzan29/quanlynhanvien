package model.bean;

import java.sql.Date;
import java.sql.Time;

public class DKLichLam {
	private int id;
	private Date date;
	private Time timebd;
	private Time timekt;
	private int number;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Time getTimebd() {
		return timebd;
	}
	public void setTimebd(Time timebd) {
		this.timebd = timebd;
	}
	public Time getTimekt() {
		return timekt;
	}
	public void setTimekt(Time timekt) {
		this.timekt = timekt;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public DKLichLam() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DKLichLam(int id, Date date, Time timebd, Time timekt, int number) {
		super();
		this.id = id;
		this.date = date;
		this.timebd = timebd;
		this.timekt = timekt;
		this.number = number;
	}
	
}
