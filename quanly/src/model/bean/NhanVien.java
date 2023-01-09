package model.bean;

public class NhanVien {
	private int id;
	private String gioitinh;
	private String address;
	private LuongCB luongCB;
	private TaiKhoan taiKhoan;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGioitinh() {
		return gioitinh;
	}
	public void setGioitinh(String gioitinh) {
		this.gioitinh = gioitinh;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public LuongCB getLuongCB() {
		return luongCB;
	}
	public void setLuongCB(LuongCB luongCB) {
		this.luongCB = luongCB;
	}
	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}
	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}
	public NhanVien() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NhanVien(int id, String gioitinh, String address, LuongCB luongCB,
			TaiKhoan taiKhoan) {
		super();
		this.id = id;
		this.gioitinh = gioitinh;
		this.address = address;
		this.luongCB = luongCB;
		this.taiKhoan = taiKhoan;
	}
	
	
}
