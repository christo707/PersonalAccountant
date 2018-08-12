package com.christo.servlets.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.christo.servlets.pojos.Users;
import com.christo.servlets.utilities.ConnectionPool;

public class UsersDao {
	
	public static int verify(String username, String password) {
		int id = -1;
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		try {
			String sql = "select uid from users where  username = ? and password = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				id = rs.getInt("uid");
			}
		} catch (SQLException sq) {
			System.out.println("Unable to create a new row." + sq);
		} finally {
			pool.putConnection(conn);
		}
		return id;
	}

	public void create(Users user) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		try {
			String sql = "insert into users (username, password, name, address, mobile, email) values(?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());
			ps.setString(4, user.getAddress());
			ps.setString(5, user.getMobile());
			ps.setString(6, user.getEmail());
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to create a new row." + sq);
		} finally {
			pool.putConnection(conn);
		}
	}

	public void edit(Users user) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		try {
			String sql = "update users set username = ?, password = ?, name = ?, address = ?, mobile = ?, email = ? where uid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());
			ps.setString(4, user.getAddress());
			ps.setString(5, user.getMobile());
			ps.setString(6, user.getEmail());
			ps.setInt(7, user.getUserid());
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to create a new row." + sq);
		} finally {
			pool.putConnection(conn);
		}
	}

	public void remove(int uid) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		try {
			String sql = "delete from users where uid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, uid);
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to create a new row." + sq);
		} finally {
			pool.putConnection(conn);
		}
	}

	public Users find(int uid) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		Users user = new Users();
		try {
			String sql = "select * from users where uid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, uid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user.setUserid(uid);
				;
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setAddress(rs.getString("address"));
				user.setMobile(rs.getString("mobile"));
				user.setEmail(rs.getString("email"));
			}
		} catch (SQLException sq) {
			System.out.println("Unable to create a new row." + sq);
		} finally {
			pool.putConnection(conn);
		}
		return user;
	}

	public ArrayList<Users> findAll() {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		ArrayList<Users> listusers = new ArrayList<Users>();
		try {
			String sql = "select * from users";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Users user = new Users();
				user.setUserid(rs.getInt("uid"));
				;
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setAddress(rs.getString("address"));
				user.setMobile(rs.getString("mobile"));
				user.setEmail(rs.getString("email"));
				listusers.add(user);
			}
		} catch (SQLException sq) {
			System.out.println("unable to find the record." + sq);
		} finally {
			pool.putConnection(conn);
		}
		return listusers;
	}

	public static void main(String args[]) {

//		UsersDao dc = new UsersDao();
//		Users obj1 = new Users("christo", "root", "CHRISTOPHER MICHAEL ROZARIO", "NEEMUCH", "9479710111",
//				"christopherrozario7@gmail.com");
//		dc.create(obj1);
//		Users obj2 = new Users("oswin", "root", "OSWIN", "NEEMUCH", "8962370277", "oswinrozario7@gmail.com");
//		dc.create(obj2);
//		Users obj = new Users("christo", "root", "CHRISTOPHER MICHAEL ROZARIO", "NEEMUCH", "9479710111",
//				"christopherrozario7@gmail.com");
//		dc.create(obj);

//		 Users user = new Users(3, "christo", "root", "CHRISTOPHER ROZARIO", "NEEMUCH", "9479710111",
//					"christopherrozario7@gmail.com");
//		 UsersDao cd = new UsersDao();
//		 cd.edit(user);

//		 UsersDao cd = new UsersDao();
//		 cd.remove(3);

		// UsersDao cd = new UsersDao();
		// Users user = new Users();
		// user = cd.find(2);
		// System.out.println(user);
		//

//		 UsersDao cd = new UsersDao();
//		 ArrayList<Users> al = cd.findAll();
//		 for (Users user : al) {
//		 System.out.println(user);
//		 }
		
		//System.out.println(verify("oswin", "root"));

	}
}
