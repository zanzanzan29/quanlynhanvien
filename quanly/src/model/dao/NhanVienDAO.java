package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import util.DBConnectionUtil;
import model.bean.LuongCB;
import model.bean.NhanVien;
import model.bean.TaiKhoan;

public class NhanVienDAO {
	private Connection conn;
	private PreparedStatement pst;
	private Statement st;
	private ResultSet rs;
	
	public ArrayList<NhanVien> getItems() {
		ArrayList<NhanVien> nhanViens = new ArrayList<>();
		conn = DBConnectionUtil.getConnection();
		String query = "SELECT * , nhanvien.id AS nvid FROM nhanvien "
				+ "INNER JOIN taikhoan "
				+ "ON nhanvien.id_taikhoan = taikhoan.id "
				+ "INNER JOIN luongcb "
				+ "ON nhanvien.id_luongcb = luongcb.id;";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()){
				int id = rs.getInt("nvid");
				String gioitinh = rs.getString("gioitinh");
				String address = rs.getString("address");
				TaiKhoan taiKhoan = new TaiKhoan(rs.getInt("id_taikhoan"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getString("picture"), rs.getString("position"));
				LuongCB luongCB = new LuongCB(rs.getInt("id_luongcb"), rs.getString("job"), rs.getInt("money"));
				NhanVien item = new NhanVien(id, gioitinh, address, luongCB, taiKhoan);
				nhanViens.add(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(rs != null && st != null && conn != null){
				try {
					rs.close();
					st.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return nhanViens;
	}

	public int addItem(NhanVien item) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String query = "INSERT INTO nhanvien(gioitinh,address,id_luongcb,id_taikhoan) VALUES (?,?,?,?)";
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, item.getGioitinh());
			pst.setString(2, item.getAddress());
			pst.setInt(3, item.getLuongCB().getId());
			pst.setInt(4, item.getTaiKhoan().getId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(pst != null && conn != null){
				try {
					pst.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	public NhanVien getItem(int id) {
		NhanVien item = null;
		conn = DBConnectionUtil.getConnection();
		String query = "SELECT * FROM nhanvien WHERE id = ?";
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()){
				String gioitinh = rs.getString("gioitinh");
				String address = rs.getString("address");
				TaiKhoan taiKhoan = new TaiKhoan(rs.getInt("id_taikhoan"), null, null, null, null, null);
				LuongCB luongCB = new LuongCB(rs.getInt("id_luongcb"), null, 0);
				item = new NhanVien(id, gioitinh, address, luongCB, taiKhoan);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(rs != null && pst != null && conn != null){
				try {
					rs.close();
					pst.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return item;
	}

	public int editItem(NhanVien nhanVien) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String query = "UPDATE nhanvien SET gioitinh = ?, address = ?, id_luongcb = ? WHERE id = ?";
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, nhanVien.getGioitinh());
			pst.setString(2, nhanVien.getAddress());
			pst.setInt(3, nhanVien.getLuongCB().getId());
			pst.setInt(4, nhanVien.getId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(pst != null && conn != null){
				try {
					pst.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	public int delItem(int id) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String query = "DELETE FROM nhanvien WHERE id = ?";
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, id);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(pst != null && conn != null){
				try {
					pst.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}
}
