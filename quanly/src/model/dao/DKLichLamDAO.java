package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;

import util.DBConnectionUtil;
import model.bean.DKLichLam;
import model.bean.LuongCB;

public class DKLichLamDAO {
	private Connection conn;
	private PreparedStatement pst;
	private Statement st;
	private ResultSet rs;
	
	public ArrayList<DKLichLam> getItems() {
		ArrayList<DKLichLam> dkLichLams = new ArrayList<>();
		conn = DBConnectionUtil.getConnection();
		String query = "SELECT * FROM dklichlam";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()){
				int id = rs.getInt("id");
				Date date = rs.getDate("date");
				Time timebd = rs.getTime("timebd");
				Time timekt = rs.getTime("timekt");
				int number = rs.getInt("number");
				DKLichLam item = new DKLichLam(id, date, timebd, timekt, number);
				dkLichLams.add(item);
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
		return dkLichLams;
	}

	public ArrayList<DKLichLam> getItems(Date date) {
		ArrayList<DKLichLam> dkLichLams = new ArrayList<>();
		conn = DBConnectionUtil.getConnection();
		String query = "SELECT * FROM dklichlam WHERE date > ?";
		try {
			pst = conn.prepareStatement(query);
			pst.setDate(1, date);
			rs = pst.executeQuery();
			while(rs.next()){
				int id = rs.getInt("id");
				Date date1 = rs.getDate("date");
				Time timebd = rs.getTime("timebd");
				Time timekt = rs.getTime("timekt");
				int number = rs.getInt("number");
				DKLichLam item = new DKLichLam(id, date1, timebd, timekt, number);
				dkLichLams.add(item);
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
		return dkLichLams;
	}
}
