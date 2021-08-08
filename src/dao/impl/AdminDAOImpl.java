package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import util.DBUtil;
import java.util.List;


import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.DbDoc;

import dao.AdminDAO;
import entity.Admin;

public class AdminDAOImpl implements AdminDAO {

	@Override
	public List<Admin> findAll(int page, int pageSize) throws Exception {
		List<Admin> list = new ArrayList<>();
		Connection conn = DBUtil.getConnection();
		String sql = "select * from admin limit ?,?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, (page - 1) * pageSize);
		pstmt.setInt(2, pageSize);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			Admin admin = new Admin();
			admin.setId(rs.getInt("id"));
			admin.setUsername(rs.getString("username"));
			admin.setPassword(rs.getString("password"));
			admin.setRealname(rs.getString("realname"));
			list.add(admin);
		}
		return list;
	}

	@Override
	public void add(Admin admin) throws Exception {
		Connection conn = DBUtil.getConnection();
		String sql = "insert into admin values (null,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, admin.getUsername());
		pstmt.setString(2, admin.getPassword());
		pstmt.setString(3, admin.getRealname());
		pstmt.executeUpdate();
		DBUtil.close(conn);

	}

	@Override
	public void del(int id) throws Exception {
		Connection conn = DBUtil.getConnection();
		String sql = "delete from admin where id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		pstmt.executeUpdate();
		DBUtil.close(conn);
	}

	@Override
	public Admin findById(int id) throws Exception {
		Connection conn = DBUtil.getConnection();
		String sql = "select * from admin where id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		Admin admin = new Admin();
		if (rs.next()) {
			admin.setId(rs.getInt("id"));
			admin.setUsername(rs.getString("username"));
			admin.setPassword(rs.getString("password"));
			admin.setRealname(rs.getString("realname"));
		}
		DBUtil.close(conn);
		return admin;
	}

	@Override
	public void update(Admin admin) throws Exception {
		Connection conn = DBUtil.getConnection();
		String sql = "update admin set username=?,password=?,realname=? where id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, admin.getUsername());
		pstmt.setString(2, admin.getPassword());
		pstmt.setString(3, admin.getRealname());
		pstmt.setInt(4, admin.getId());
		pstmt.executeUpdate();
		DBUtil.close(conn);

	}

	@Override
	public int getTotalPages(int pageSize) throws Exception {
//		获取总记录数
		Connection conn = DBUtil.getConnection();
		String sql = "select count(*) from admin";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		int rows = 0;
		if (rs.next()) {
			rows = rs.getInt(1);
		}
		DBUtil.close(conn);
		return rows % pageSize == 0 ? rows / pageSize : rows / pageSize + 1;
	}

	@Override
	public Admin findByUsername(String username) throws Exception {
		Admin admin = null;
		Connection conn = DBUtil.getConnection();
		String sql = "select * from admin where username = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, username);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			admin = new Admin();
			admin.setPassword(rs.getString("password"));
			admin.setRealname(rs.getString("realname"));
			admin.setId(rs.getInt("id"));
		}
		DBUtil.close(conn);
		return admin;
	}

}
