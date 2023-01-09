package model.bean;

public class LichLam {
	private int id;
	private DKLichLam dkLichLam;
	private TaiKhoan taiKhoan;
	private int status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public DKLichLam getDkLichLam() {
		return dkLichLam;
	}
	public void setDkLichLam(DKLichLam dkLichLam) {
		this.dkLichLam = dkLichLam;
	}
	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}
	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public LichLam() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LichLam(int id, DKLichLam dkLichLam, TaiKhoan taiKhoan, int status) {
		super();
		this.id = id;
		this.dkLichLam = dkLichLam;
		this.taiKhoan = taiKhoan;
		this.status = status;
	}
	
}
