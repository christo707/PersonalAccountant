package com.christo.servlets.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.christo.servlets.pojos.BankBookPojo;
import com.christo.servlets.pojos.Income;
import com.christo.servlets.utilities.ConnectionPool;
import com.christo.servlets.utilities.DateUtils;

public class IncomeDao {
	
	public void create(Income income) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		try {
			String sql = "insert into incomes (inc_ac, userid, inc_catid, amount, tran_date, receiveby, remark) values(?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, income.getInc_ac());
			ps.setInt(2, income.getUserid());
			ps.setInt(3, income.getInc_catid());
			ps.setInt(4, income.getAmount());
			java.sql.Date dt = new java.sql.Date(income.getTran_date().getTime());
			ps.setDate(5, dt);
			ps.setString(6, income.getReceiveby());
			ps.setString(7, income.getRemark());
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to create a new row." + sq);
		} finally {
			pool.putConnection(conn);
		}
	}

	public void edit(Income income) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		try {
			String sql = "update incomes set inc_ac = ?, userid = ?, inc_catid = ?, amount = ?, tran_date = ?, receiveby = ?, remark = ? where inc_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, income.getInc_ac());
			ps.setInt(2, income.getUserid());
			ps.setInt(3, income.getInc_catid());
			ps.setInt(4, income.getAmount());
			java.sql.Date dt = new java.sql.Date(income.getTran_date().getTime());
			ps.setDate(5, dt);
			ps.setString(6, income.getReceiveby());
			ps.setString(7, income.getRemark());
			ps.setInt(8, income.getInc_id());
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to create a new row." + sq);
		} finally {
			pool.putConnection(conn);
		}
	}

	public void remove(int inc_id) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		try {
			String sql = "delete from incomes where inc_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, inc_id);
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to create a new row." + sq);
		} finally {
			pool.putConnection(conn);
		}
	}

	public Income find(int inc_id) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		Income income = new Income();
		try {
			String sql = "select * from incomes where inc_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, inc_id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				income.setInc_id(inc_id);
				income.setInc_ac(rs.getString("inc_ac"));
				income.setUserid(rs.getInt("userid"));
				income.setInc_catid(rs.getInt("inc_catid"));
				income.setAmount(rs.getInt("amount"));
				java.sql.Date dt = rs.getDate("tran_date");
				income.setTran_date(new java.util.Date(dt.getTime()));
				income.setReceiveby(rs.getString("receiveby"));
				income.setRemark(rs.getString("remark"));
			}
		} catch (SQLException sq) {
			System.out.println("Unable to create a new row." + sq);
		} finally {
			pool.putConnection(conn);
		}
		return income;
	}

	public ArrayList<Income> findAll() {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		ArrayList<Income> listIncome = new ArrayList<Income>();
		try {
			String sql = "select * from incomes";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Income income = new Income();
				income.setInc_id(rs.getInt("inc_id"));
				income.setInc_ac(rs.getString("inc_ac"));
				income.setUserid(rs.getInt("userid"));
				income.setInc_catid(rs.getInt("inc_catid"));
				income.setAmount(rs.getInt("amount"));
				java.sql.Date dt = rs.getDate("tran_date");
				income.setTran_date(new java.util.Date(dt.getTime()));
				income.setReceiveby(rs.getString("receiveby"));
				income.setRemark(rs.getString("remark"));
				listIncome.add(income);
			}
		} catch (SQLException sq) {
			System.out.println("unable to find the record." + sq);
		} finally {
			pool.putConnection(conn);
		}
		return listIncome;
	}

	public static ArrayList<Income> findByDates(Date d1, Date d2, int userid) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		ArrayList<Income> listincome = new ArrayList<Income>();
		try {
			String sql = "select * from incomes where tran_date between ? and ? having userid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDate(1, new java.sql.Date(d1.getTime()));
			ps.setDate(2, new java.sql.Date(d2.getTime()));
			ps.setInt(3, userid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Income i = new Income();
				//BankBookPojo.setAcid(rs.getInt("acid"));
				i.setInc_ac(rs.getString("inc_ac"));
				java.sql.Date dt = rs.getDate("tran_date");
				i.setTran_date(new java.util.Date(dt.getTime()));
				i.setAmount(rs.getInt("amount"));
				i.setUserid(rs.getInt("userid"));
				i.setInc_catid(rs.getInt("inc_catid"));
				i.setRemark(rs.getString("remark"));
				i.setReceiveby(rs.getString("receiveby"));
				listincome.add(i);
			}
		} catch (SQLException sq) {
			System.out.println("unable to find the record." + sq);
		} finally {
			pool.putConnection(conn);
		}
		return listincome;
	}
	
	public static void main(String args[]) {

//		IncomeDao dc = new IncomeDao();
//		Income obj1 = new Income("Daddy", 1, 5, 8000, DateUtils.convertDate("02-12-2016"),
//				"NEFT", "Monthly allowance");
//		dc.create(obj1);
//		Income obj = new Income("Interest", 1, 6, 1000, DateUtils.convertDate("10-12-2016"),
//				"NEFT", "Saving account interest");
//		dc.create(obj);
		

//		Income obj = new Income(1,"Daddy", 1, 5, 7000, DateUtils.convertDate("02-12-2016"),
//				"NEFT", "Monthly allowance");
//		 IncomeDao cd = new IncomeDao();
//		 cd.edit(obj);

//		 IncomeDao cd = new IncomeDao();
//		 cd.remove(1);

//		 IncomeDao cd = new IncomeDao();
//		 Income income = new Income();
//		 income = cd.find(2);
//		 System.out.println(income);
		

//		 IncomeDao cd = new IncomeDao();
//		 ArrayList<Income> al = cd.findAll();
//		 for (Income income : al) {
//		 System.out.println(income);
//		 }

	}
}
