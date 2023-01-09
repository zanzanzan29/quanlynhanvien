package model.bean;

public class LuongCB {
	private int id;
	private String job;
	private int money;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public LuongCB() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LuongCB(int id, String job, int money) {
		super();
		this.id = id;
		this.job = job;
		this.money = money;
	}
	
}
