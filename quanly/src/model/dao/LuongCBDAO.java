package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import util.DBConnectionUtil;
import model.bean.LuongCB;
import model.bean.TaiKhoan;

public class LuongCBDAO {
	private Connection conn;
	private PreparedStatement pst;
	private Statement st;
	private ResultSet rs;
	
	public ArrayList<LuongCB> getItems() {
		ArrayList<LuongCB> luongcb = new ArrayList<>();
		conn = DBConnectionUtil.getConnection();
		String query = "SELECT * FROM luongcb";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()){
				int id = rs.getInt("id");
				String job = rs.getString("job");
				int money = rs.getInt("money");
				LuongCB item = new LuongCB(id, job, money);
				luongcb.add(item);
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
		return luongcb;
	}

	public int addItem(LuongCB item) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String query = "INSERT INTO luongcb(job,money) VALUES (?,?)";
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, item.getJob());
			pst.setInt(2, item.getMoney());
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

	public LuongCB getItem(int id) {
		LuongCB item = null;
		conn = DBConnectionUtil.getConnection();
		String query = "SELECT * FROM luongcb WHERE id = ?";
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()){
				String job = rs.getString("job");
				int money = rs.getInt("money");
				item = new LuongCB(id, job, money);
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

	public int editItem(LuongCB luongCB) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String query = "UPDATE luongcb SET job = ?, money = ? WHERE id = ?";
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, luongCB.getJob());
			pst.setInt(2, luongCB.getMoney());
			pst.setInt(3, luongCB.getId());
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
		String query = "DELETE FROM luongcb WHERE id = ?";
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
