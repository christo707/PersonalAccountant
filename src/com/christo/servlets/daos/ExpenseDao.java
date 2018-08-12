package com.christo.servlets.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.christo.servlets.pojos.Expense;
import com.christo.servlets.pojos.Income;
import com.christo.servlets.utilities.ConnectionPool;
import com.christo.servlets.utilities.DateUtils;

public class ExpenseDao {
	public void create(Expense expense) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		try {
			String sql = "insert into expenses (exp_ac, userid, exp_catid, amount, tran_date, payby, remark) values(?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, expense.getExp_ac());
			ps.setInt(2, expense.getUserid());
			ps.setInt(3, expense.getExp_catid());
			ps.setInt(4, expense.getAmount());
			java.sql.Date dt = new java.sql.Date(expense.getTran_date().getTime());
			ps.setDate(5, dt);
			ps.setString(6, expense.getPayby());
			ps.setString(7, expense.getRemark());
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to create a new row." + sq);
		} finally {
			pool.putConnection(conn);
		}
	}

	public void edit(Expense expense) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		try {
			String sql = "update expenses set exp_ac = ?, userid = ?, exp_catid = ?, amount = ?, tran_date = ?, payby = ?, remark = ? where exp_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, expense.getExp_ac());
			ps.setInt(2, expense.getUserid());
			ps.setInt(3, expense.getExp_catid());
			ps.setInt(4, expense.getAmount());
			java.sql.Date dt = new java.sql.Date(expense.getTran_date().getTime());
			ps.setDate(5, dt);
			ps.setString(6, expense.getPayby());
			ps.setString(7, expense.getRemark());
			ps.setInt(8, expense.getExp_id());
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to create a new row." + sq);
		} finally {
			pool.putConnection(conn);
		}
	}

	public void remove(int exp_id) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		try {
			String sql = "delete from expenses where exp_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, exp_id);
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to create a new row." + sq);
		} finally {
			pool.putConnection(conn);
		}
	}

	public Expense find(int exp_id) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		Expense expense = new Expense();
		try {
			String sql = "select * from expenses where exp_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, exp_id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				expense.setExp_id(exp_id);
				expense.setExp_ac(rs.getString("exp_ac"));
				expense.setUserid(rs.getInt("userid"));
				expense.setExp_catid(rs.getInt("exp_catid"));
				expense.setAmount(rs.getInt("amount"));
				java.sql.Date dt = rs.getDate("tran_date");
				expense.setTran_date(new java.util.Date(dt.getTime()));
				expense.setPayby(rs.getString("payby"));
				expense.setRemark(rs.getString("remark"));
			}
		} catch (SQLException sq) {
			System.out.println("Unable to create a new row." + sq);
		} finally {
			pool.putConnection(conn);
		}
		return expense;
	}

	public ArrayList<Expense> findAll() {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		ArrayList<Expense> listExpense = new ArrayList<Expense>();
		try {
			String sql = "select * from expenses";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Expense Expense = new Expense();
				Expense.setExp_id(rs.getInt("exp_id"));
				Expense.setExp_ac(rs.getString("exp_ac"));
				Expense.setUserid(rs.getInt("userid"));
				Expense.setExp_catid(rs.getInt("exp_catid"));
				Expense.setAmount(rs.getInt("amount"));
				java.sql.Date dt = rs.getDate("tran_date");
				Expense.setTran_date(new java.util.Date(dt.getTime()));
				Expense.setPayby(rs.getString("payby"));
				Expense.setRemark(rs.getString("remark"));
				listExpense.add(Expense);
			}
		} catch (SQLException sq) {
			System.out.println("unable to find the record." + sq);
		} finally {
			pool.putConnection(conn);
		}
		return listExpense;
	}

	public static ArrayList<Expense> findByDates(Date d1, Date d2, int userid) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		ArrayList<Expense> listexpense = new ArrayList<Expense>();
		try {
			String sql = "select * from expenses where tran_date between ? and ? having userid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDate(1, new java.sql.Date(d1.getTime()));
			ps.setDate(2, new java.sql.Date(d2.getTime()));
			ps.setInt(3, userid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Expense i = new Expense();
				//BankBookPojo.setAcid(rs.getInt("acid"));
				i.setExp_ac(rs.getString("exp_ac"));
				java.sql.Date dt = rs.getDate("tran_date");
				i.setTran_date(new java.util.Date(dt.getTime()));
				i.setAmount(rs.getInt("amount"));
				i.setUserid(rs.getInt("userid"));
				i.setExp_catid(rs.getInt("exp_catid"));
				i.setRemark(rs.getString("remark"));
				i.setPayby(rs.getString("payby"));
				listexpense.add(i);
			}
		} catch (SQLException sq) {
			System.out.println("unable to find the record." + sq);
		} finally {
			pool.putConnection(conn);
		}
		return listexpense;
	}
	
	public static void main(String args[]) {

//		ExpenseDao dc = new ExpenseDao();
//		Expense obj1 = new Expense("Tution", 1, 2, 3000, DateUtils.convertDate("05-12-2016"),
//				"Cash", "Coaching Fees");
//		dc.create(obj1);
//		Expense obj = new Expense("Tution", 1, 2, 1000, DateUtils.convertDate("10-12-2016"),
//				"NEFT", "Saving account interest");
//		dc.create(obj);
		

//		Expense obj = new Expense(2,"Daddy", 1, 5, 7000, DateUtils.convertDate("02-12-2016"),
//				"NEFT", "Monthly allowance");
//		 ExpenseDao cd = new ExpenseDao();
//		 cd.edit(obj);

//		 ExpenseDao cd = new ExpenseDao();
//		 cd.remove(2);

//		 ExpenseDao cd = new ExpenseDao();
//		 Expense Expense = new Expense();
//		 Expense = cd.find(1);
//		 System.out.println(Expense);
//		

//		 ExpenseDao cd = new ExpenseDao();
//		 ArrayList<Expense> al = cd.findAll();
//		 for (Expense Expense : al) {
//		 System.out.println(Expense);
//		 }

	}

}
