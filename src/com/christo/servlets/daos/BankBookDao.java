package com.christo.servlets.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.christo.servlets.pojos.BankBookPojo;
import com.christo.servlets.utilities.ConnectionPool;
import com.christo.servlets.utilities.DateUtils;

public class BankBookDao {
	
	public void create(BankBookPojo transaction) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		try {
			String sql = "insert into bankbook (description, tran_date, amount, userid, operation) values(?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, transaction.getDescription());
			java.sql.Date dt = new java.sql.Date(transaction.getTran_date().getTime());
			ps.setDate(2, dt);
			ps.setInt(3, transaction.getAmount());
			ps.setInt(4, transaction.getUserid());
			ps.setString(5, transaction.getOperation());
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to create a new row." + sq);
		} finally {
			pool.putConnection(conn);
		}
	}

	public void edit(BankBookPojo transaction) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		try {
			String sql = "update bankbook set description = ?, tran_date = ?, amount = ?, userid = ?, operation = ? where acid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, transaction.getDescription());
			java.sql.Date dt = new java.sql.Date(transaction.getTran_date().getTime());
			ps.setDate(2, dt);
			ps.setInt(3, transaction.getAmount());
			ps.setInt(4, transaction.getUserid());
			ps.setString(5, transaction.getOperation());
			ps.setInt(6, transaction.getAcid());
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to create a new row." + sq);
		} finally {
			pool.putConnection(conn);
		}
	}

	public void remove(int acid) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		try {
			String sql = "delete from bankbook where acid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, acid);
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to create a new row." + sq);
		} finally {
			pool.putConnection(conn);
		}
	}

	public BankBookPojo find(int acid) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		BankBookPojo BankBookPojo = new BankBookPojo();
		try {
			String sql = "select * from bankbook where acid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, acid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				BankBookPojo.setAcid(acid);
				BankBookPojo.setDescription(rs.getString("description"));
				java.sql.Date dt = rs.getDate("tran_date");
				BankBookPojo.setTran_date(new java.util.Date(dt.getTime()));
				BankBookPojo.setAmount(rs.getInt("amount"));
				BankBookPojo.setUserid(rs.getInt("userid"));
				BankBookPojo.setOperation(rs.getString("operation"));
			}
		} catch (SQLException sq) {
			System.out.println("Unable to create a new row." + sq);
		} finally {
			pool.putConnection(conn);
		}
		return BankBookPojo;
	}

	public ArrayList<BankBookPojo> findAll() {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		ArrayList<BankBookPojo> listBankBookPojo = new ArrayList<BankBookPojo>();
		try {
			String sql = "select * from bankbook";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				BankBookPojo BankBookPojo = new BankBookPojo();
				BankBookPojo.setAcid(rs.getInt("acid"));
				BankBookPojo.setDescription(rs.getString("description"));
				java.sql.Date dt = rs.getDate("tran_date");
				BankBookPojo.setTran_date(new java.util.Date(dt.getTime()));
				BankBookPojo.setAmount(rs.getInt("amount"));
				BankBookPojo.setUserid(rs.getInt("userid"));
				BankBookPojo.setOperation(rs.getString("operation"));
				listBankBookPojo.add(BankBookPojo);
			}
		} catch (SQLException sq) {
			System.out.println("unable to find the record." + sq);
		} finally {
			pool.putConnection(conn);
		}
		return listBankBookPojo;
	}

	public ArrayList<BankBookPojo> findAllByUserid(int userid) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		ArrayList<BankBookPojo> listBankBookPojo = new ArrayList<BankBookPojo>();
		try {
			String sql = "select * from bankbook where userid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				BankBookPojo BankBookPojo = new BankBookPojo();
				//BankBookPojo.setAcid(rs.getInt("acid"));
				BankBookPojo.setDescription(rs.getString("description"));
				java.sql.Date dt = rs.getDate("tran_date");
				BankBookPojo.setTran_date(new java.util.Date(dt.getTime()));
				BankBookPojo.setAmount(rs.getInt("amount"));
				BankBookPojo.setUserid(rs.getInt("userid"));
				BankBookPojo.setOperation(rs.getString("operation"));
				listBankBookPojo.add(BankBookPojo);
			}
		} catch (SQLException sq) {
			System.out.println("unable to find the record." + sq);
		} finally {
			pool.putConnection(conn);
		}
		return listBankBookPojo;
	}
	
	public static ArrayList<BankBookPojo> findByDates(Date d1, Date d2, int userid) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		ArrayList<BankBookPojo> listBankBookPojo = new ArrayList<BankBookPojo>();
		try {
			String sql = "select * from bankbook where tran_date between ? and ? having userid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDate(1, new java.sql.Date(d1.getTime()));
			ps.setDate(2, new java.sql.Date(d2.getTime()));
			ps.setInt(3, userid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				BankBookPojo BankBookPojo = new BankBookPojo();
				//BankBookPojo.setAcid(rs.getInt("acid"));
				BankBookPojo.setDescription(rs.getString("description"));
				java.sql.Date dt = rs.getDate("tran_date");
				BankBookPojo.setTran_date(new java.util.Date(dt.getTime()));
				BankBookPojo.setAmount(rs.getInt("amount"));
				BankBookPojo.setUserid(rs.getInt("userid"));
				BankBookPojo.setOperation(rs.getString("operation"));
				listBankBookPojo.add(BankBookPojo);
			}
		} catch (SQLException sq) {
			System.out.println("unable to find the record." + sq);
		} finally {
			pool.putConnection(conn);
		}
		return listBankBookPojo;
	}

	public static void main(String args[]) {

//		BankBookDao dc = new BankBookDao();
//		BankBookPojo obj1 = new BankBookPojo("Food", DateUtils.convertDate("02-12-2016"),
//				1000, 1,  "Pay");
//		dc.create(obj1);
//		BankBookPojo obj = new BankBookPojo("Interest", DateUtils.convertDate("5-12-2016"),
//				500, 1, "Receive");
//		dc.create(obj);
//		BankBookPojo obj2 = new BankBookPojo("Daddy", DateUtils.convertDate("1-12-2016"),
//				5000, 1, "Receive");
//		dc.create(obj2);
//		

//		BankBookPojo obj = new BankBookPojo(2,"Daddy", 1, 5, 7000, DateUtils.convertDate("02-12-2016"),
//				"NEFT", "Monthly allowance");
//		 BankBookPojoDao cd = new BankBookPojoDao();
//		 cd.edit(obj);

//		 BankBookPojoDao cd = new BankBookPojoDao();
//		 cd.remove(2);

//		 BankBookDao cd = new BankBookDao();
//		 BankBookPojo BankBookPojo = new BankBookPojo();
//		 BankBookPojo = cd.find(1);
//		 System.out.println(BankBookPojo);
//		

//		 BankBookDao cd = new BankBookDao();
//		 ArrayList<BankBookPojo> al = cd.findAllByUserid(1);
//		 for (BankBookPojo BankBookPojo : al) {
//		 System.out.println(BankBookPojo);
		// }

	}


}
