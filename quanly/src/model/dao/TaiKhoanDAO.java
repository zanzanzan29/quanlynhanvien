package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import util.DBConnectionUtil;
import model.bean.TaiKhoan;

public class TaiKhoanDAO {
	private Connection conn;
	private PreparedStatement pst;
	private Statement st;
	private ResultSet rs;
	
	public ArrayList<TaiKhoan> getItems() {
		ArrayList<TaiKhoan> taikhoan = new ArrayList<>();
		conn = DBConnectionUtil.getConnection();
		String query = "SELECT * FROM taikhoan";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()){
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String fullname = rs.getString("fullname");
				String picture = rs.getString("picture");
				String position = rs.getString("position");
				TaiKhoan item = new TaiKhoan(id, username, password, fullname, picture, position);
				taikhoan.add(item);
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
		return taikhoan;
	}

	public int addItem(TaiKhoan item) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String query = "INSERT INTO taikhoan(username,password,fullname,picture,position) VALUES (?,?,?,?,?)";
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, item.getUsername());
			pst.setString(2, item.getPassword());
			pst.setString(3, item.getFullname());
			pst.setString(4, item.getPicture());
			pst.setString(5, item.getPosition());
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

	public TaiKhoan getItem(int id) {
		TaiKhoan item = null;
		conn = DBConnectionUtil.getConnection();
		String query = "SELECT * FROM taikhoan WHERE id = ?";
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()){
				String username = rs.getString("username");
				String password = rs.getString("password");
				String fullname = rs.getString("fullName");
				String position = rs.getString("position");
				String picture = rs.getString("picture");
				item = new TaiKhoan(id, username, password, fullname, picture, position);
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

	public int editItem(TaiKhoan taiKhoan) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String query = "UPDATE taikhoan SET password = ?, fullname = ?, picture = ?, position = ? WHERE id = ?";
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, taiKhoan.getPassword());
			pst.setString(2, taiKhoan.getFullname());
			pst.setString(3, taiKhoan.getPicture());
			pst.setString(4, taiKhoan.getPosition());
			pst.setInt(5, taiKhoan.getId());
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
		String query = "DELETE FROM taikhoan WHERE id = ?";
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

	public ArrayList<TaiKhoan> getItemsNV() {
		ArrayList<TaiKhoan> taikhoan = new ArrayList<>();
		conn = DBConnectionUtil.getConnection();
		String query = "SELECT * FROM taikhoan WHERE position = 'Nhân Viên'";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()){
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String fullname = rs.getString("fullname");
				String picture = rs.getString("picture");
				String position = rs.getString("position");
				TaiKhoan item = new TaiKhoan(id, username, password, fullname, picture, position);
				taikhoan.add(item);
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
		return taikhoan;
	}

	public TaiKhoan kiemtra(String username, String password) {
		TaiKhoan item = null;
		conn = DBConnectionUtil.getConnection();
		String query = "SELECT * FROM taikhoan WHERE username = ? AND password = ? ";
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, username);
			pst.setString(2, password);
			rs = pst.executeQuery();
			if(rs.next()){
				int id = rs.getInt("id");
				String fullname = rs.getString("fullname");
				String picture = rs.getString("picture");
				String position = rs.getString("position");
				item = new TaiKhoan(id, username, password, fullname, picture, position);
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
	
}
