package model.bean;

public class Thuong {
	private int id;
	private String name;
	private int time;
	private int money;
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
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public Thuong() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Thuong(int id, String name, int time, int money) {
		super();
		this.id = id;
		this.name = name;
		this.time = time;
		this.money = money;
	}
	
}
