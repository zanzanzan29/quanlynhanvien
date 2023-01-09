package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import util.DBConnectionUtil;
import model.bean.DKLichLam;
import model.bean.LichLam;
import model.bean.LuongCB;
import model.bean.TaiKhoan;

public class LichLamDAO {
	private Connection conn;
	private PreparedStatement pst;
	private Statement st;
	private ResultSet rs;
	
	public int addItem(LichLam item) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String query = "INSERT INTO lichlam(id_dklichlam,id_taikhoan) VALUES (?,?)";
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, item.getDkLichLam().getId());
			pst.setInt(2, item.getTaiKhoan().getId());
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

	public ArrayList<LichLam> getItems(int id_tk) {
		ArrayList<LichLam> lichLams = new ArrayList<>();
		conn = DBConnectionUtil.getConnection();
		String query = "SELECT * FROM lichlam WHERE id_taikhoan = ?";
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, id_tk);
			rs = pst.executeQuery();
			while(rs.next()){
				int id = rs.getInt("id");
				int id_dklichlam = rs.getInt("id_dklichlam");
				int id_taikhoan = rs.getInt("id_taikhoan");
				int status = rs.getInt("status");
				DKLichLam dkLichLam = new DKLichLam(id_dklichlam, null, null, null, 0);
				TaiKhoan taiKhoan = new TaiKhoan(id_taikhoan, null, null, null, null, null);
				LichLam item = new LichLam(id, dkLichLam, taiKhoan, status);
				lichLams.add(item);
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
		return lichLams;
	}

	public ArrayList<LichLam> getItemsTT(int id_tk) {
		ArrayList<LichLam> lichLams = new ArrayList<>();
		conn = DBConnectionUtil.getConnection();
		String query = "SELECT *,DATE_FORMAT(date,'%e')AS ngay,lichlam.id AS llid FROM lichlam "
				+ "INNER JOIN dklichlam "
				+ "ON lichlam.id_dklichlam = dklichlam.id "
				+ "WHERE id_taikhoan = ?";
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, id_tk);
			rs = pst.executeQuery();
			while(rs.next()){
				int id = rs.getInt("llid");
				int id_dklichlam = rs.getInt("id_dklichlam");
				int id_taikhoan = rs.getInt("id_taikhoan");
				int status = rs.getInt("status");
				DKLichLam dkLichLam = new DKLichLam(id_dklichlam, null, null, null, rs.getInt("ngay"));
				TaiKhoan taiKhoan = new TaiKhoan(id_taikhoan, null, null, null, null, null);
				LichLam item = new LichLam(id, dkLichLam, taiKhoan, status);
				lichLams.add(item);
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
		return lichLams;
	}

	public ArrayList<LichLam> getItems() {
		ArrayList<LichLam> lichLams = new ArrayList<>();
		conn = DBConnectionUtil.getConnection();
		String query = "SELECT * FROM lichlam";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()){
				int id = rs.getInt("id");
				int id_dklichlam = rs.getInt("id_dklichlam");
				int id_taikhoan = rs.getInt("id_taikhoan");
				int status = rs.getInt("status");
				DKLichLam dkLichLam = new DKLichLam(id_dklichlam, null, null, null, 0);
				TaiKhoan taiKhoan = new TaiKhoan(id_taikhoan, null, null, null, null, null);
				LichLam item = new LichLam(id, dkLichLam, taiKhoan, status);
				lichLams.add(item);
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
		return lichLams;
	}

	public ArrayList<LichLam> getItemsTTSS(int id_tk) {
		ArrayList<LichLam> lichLams = new ArrayList<>();
		conn = DBConnectionUtil.getConnection();
		String query = "SELECT *,DATE_FORMAT(date,'%e')AS ngay,lichlam.id AS llid FROM lichlam "
				+ "INNER JOIN dklichlam "
				+ "ON lichlam.id_dklichlam = dklichlam.id "
				+ "WHERE id_taikhoan = ?";
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, id_tk);
			rs = pst.executeQuery();
			while(rs.next()){
				int id = rs.getInt("llid");
				int id_dklichlam = rs.getInt("id_dklichlam");
				int id_taikhoan = rs.getInt("id_taikhoan");
				int status = rs.getInt("status");
				DKLichLam dkLichLam = new DKLichLam(id_dklichlam, rs.getDate("date"), rs.getTime("timebd"), rs.getTime("timekt"), rs.getInt("ngay"));
				TaiKhoan taiKhoan = new TaiKhoan(id_taikhoan, null, null, null, null, null);
				LichLam item = new LichLam(id, dkLichLam, taiKhoan, status);
				lichLams.add(item);
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
		return lichLams;
	}
}
