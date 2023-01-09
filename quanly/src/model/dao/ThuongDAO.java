package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import util.DBConnectionUtil;
import model.bean.LuongCB;
import model.bean.Thuong;

public class ThuongDAO {
	private Connection conn;
	private PreparedStatement pst;
	private Statement st;
	private ResultSet rs;
	
	public ArrayList<Thuong> getItems() {
		ArrayList<Thuong> thuong = new ArrayList<>();
		conn = DBConnectionUtil.getConnection();
		String query = "SELECT * FROM thuong";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int time = rs.getInt("time");
				int money = rs.getInt("money");
				Thuong item = new Thuong(id, name, time, money);
				thuong.add(item);
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
		return thuong;
	}

	public int addItem(Thuong item) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String query = "INSERT INTO thuong(name,time,money) VALUES (?,?,?)";
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, item.getName());
			pst.setInt(2, item.getTime());
			pst.setInt(3, item.getMoney());
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

	public Thuong getItem(int id) {
		Thuong item = null;
		conn = DBConnectionUtil.getConnection();
		String query = "SELECT * FROM thuong WHERE id = ?";
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()){
				String name = rs.getString("name");
				int time = rs.getInt("time");
				int money = rs.getInt("money");
				item = new Thuong(id, name, time, money);
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

	public int editItem(Thuong thuong) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String query = "UPDATE thuong SET name = ?, time = ?, money = ? WHERE id = ?";
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, thuong.getName());
			pst.setInt(2, thuong.getTime());
			pst.setInt(3, thuong.getMoney());
			pst.setInt(4, thuong.getId());
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
		String query = "DELETE FROM thuong WHERE id = ?";
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
