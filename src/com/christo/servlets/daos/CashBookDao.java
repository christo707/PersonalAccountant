package com.christo.servlets.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.christo.servlets.pojos.BankBookPojo;
import com.christo.servlets.pojos.CashBookPojo;
import com.christo.servlets.utilities.ConnectionPool;
import com.christo.servlets.utilities.DateUtils;

public class CashBookDao {
	public void create(CashBookPojo transaction) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		try {
			String sql = "insert into cashbook (description, tran_date, amount, userid, operation) values(?,?,?,?,?)";
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

	public void edit(CashBookPojo transaction) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		try {
			String sql = "update cashbook set description = ?, tran_date = ?, amount = ?, userid = ?, operation = ? where acid = ?";
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
			String sql = "delete from cashbook where acid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, acid);
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to create a new row." + sq);
		} finally {
			pool.putConnection(conn);
		}
	}

	public CashBookPojo find(int acid) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		CashBookPojo CashBookPojo = new CashBookPojo();
		try {
			String sql = "select * from cashbook where acid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, acid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				CashBookPojo.setAcid(acid);
				CashBookPojo.setDescription(rs.getString("description"));
				java.sql.Date dt = rs.getDate("tran_date");
				CashBookPojo.setTran_date(new java.util.Date(dt.getTime()));
				CashBookPojo.setAmount(rs.getInt("amount"));
				CashBookPojo.setUserid(rs.getInt("userid"));
				CashBookPojo.setOperation(rs.getString("operation"));
			}
		} catch (SQLException sq) {
			System.out.println("Unable to create a new row." + sq);
		} finally {
			pool.putConnection(conn);
		}
		return CashBookPojo;
	}

	public ArrayList<CashBookPojo> findAll() {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		ArrayList<CashBookPojo> listCashBookPojo = new ArrayList<CashBookPojo>();
		try {
			String sql = "select * from cashbook";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CashBookPojo CashBookPojo = new CashBookPojo();
				CashBookPojo.setAcid(rs.getInt("acid"));
				CashBookPojo.setDescription(rs.getString("description"));
				java.sql.Date dt = rs.getDate("tran_date");
				CashBookPojo.setTran_date(new java.util.Date(dt.getTime()));
				CashBookPojo.setAmount(rs.getInt("amount"));
				CashBookPojo.setUserid(rs.getInt("userid"));
				CashBookPojo.setOperation(rs.getString("operation"));
				listCashBookPojo.add(CashBookPojo);
			}
		} catch (SQLException sq) {
			System.out.println("unable to find the record." + sq);
		} finally {
			pool.putConnection(conn);
		}
		return listCashBookPojo;
	}

	public ArrayList<CashBookPojo> findAllByUserid(int userid) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		ArrayList<CashBookPojo> listCashBookPojo = new ArrayList<CashBookPojo>();
		try {
			String sql = "select * from cashbook where userid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CashBookPojo CashBookPojo = new CashBookPojo();
				//CashBookPojo.setAcid(rs.getInt("acid"));
				CashBookPojo.setDescription(rs.getString("description"));
				java.sql.Date dt = rs.getDate("tran_date");
				CashBookPojo.setTran_date(new java.util.Date(dt.getTime()));
				CashBookPojo.setAmount(rs.getInt("amount"));
				CashBookPojo.setUserid(rs.getInt("userid"));
				CashBookPojo.setOperation(rs.getString("operation"));
				listCashBookPojo.add(CashBookPojo);
			}
		} catch (SQLException sq) {
			System.out.println("unable to find the record." + sq);
		} finally {
			pool.putConnection(conn);
		}
		return listCashBookPojo;
	}
	
	public ArrayList<BankBookPojo> findByDates(Date d1, Date d2) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		ArrayList<BankBookPojo> listBankBookPojo = new ArrayList<BankBookPojo>();
		try {
			String sql = "select * from bankbook where tran_date between ? and ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDate(1, new java.sql.Date(d1.getTime()));
			ps.setDate(1, new java.sql.Date(d2.getTime()));
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

	public static ArrayList<CashBookPojo> findByDates(Date d1, Date d2, int userid) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		ArrayList<CashBookPojo> listCashBookPojo = new ArrayList<CashBookPojo>();
		try {
			String sql = "select * from cashbook where tran_date between ? and ? having userid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDate(1, new java.sql.Date(d1.getTime()));
			ps.setDate(2, new java.sql.Date(d2.getTime()));
			ps.setInt(3, userid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CashBookPojo CashBookPojo = new CashBookPojo();
				//BankBookPojo.setAcid(rs.getInt("acid"));
				CashBookPojo.setDescription(rs.getString("description"));
				java.sql.Date dt = rs.getDate("tran_date");
				CashBookPojo.setTran_date(new java.util.Date(dt.getTime()));
				CashBookPojo.setAmount(rs.getInt("amount"));
				CashBookPojo.setUserid(rs.getInt("userid"));
				CashBookPojo.setOperation(rs.getString("operation"));
				listCashBookPojo.add(CashBookPojo);
			}
		} catch (SQLException sq) {
			System.out.println("unable to find the record." + sq);
		} finally {
			pool.putConnection(conn);
		}
		return listCashBookPojo;
	}

	public static void main(String args[]) {

//		CashBookDao dc = new CashBookDao();
//		CashBookPojo obj1 = new CashBookPojo("Lottery", DateUtils.convertDate("11-12-2016"),
//				1000, 1,  "Receive");
//		dc.create(obj1);
//		CashBookPojo obj = new CashBookPojo("Tution", DateUtils.convertDate("7-12-2016"),
//				3000, 1, "Pay");
//		dc.create(obj);

//		CashBookPojo obj = new CashBookPojo(2,"Daddy", 1, 5, 7000, DateUtils.convertDate("02-12-2016"),
//				"NEFT", "Monthly allowance");
//		 CashBookPojoDao cd = new CashBookPojoDao();
//		 cd.edit(obj);

//		 CashBookPojoDao cd = new CashBookPojoDao();
//		 cd.remove(2);

//		 cashbookDao cd = new cashbookDao();
//		 CashBookPojo CashBookPojo = new CashBookPojo();
//		 CashBookPojo = cd.find(1);
//		 System.out.println(CashBookPojo);
//		

		 CashBookDao cd = new CashBookDao();
		 ArrayList<CashBookPojo> al = cd.findAllByUserid(1);
		 for (CashBookPojo CashBookPojo : al) {
		 System.out.println(CashBookPojo);
		 }

	}


}
