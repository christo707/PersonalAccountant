package com.christo.servlets.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.christo.servlets.pojos.ExpensesCategories;
import com.christo.servlets.utilities.ConnectionPool;

public class ExpensesCategoriesDao {
	public void create(ExpensesCategories category) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		try {
			String sql = "insert into expenses_category (exp_catname, exp_catdetails, userid) values(?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, category.getExp_catname());
			ps.setString(2, category.getExp_catdetails());
			ps.setInt(3, category.getUserid());
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to create a new row." + sq);
		} finally {
			pool.putConnection(conn);
		}
	}

	public void edit(ExpensesCategories category) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		try {
			String sql = "update expenses_category set exp_catname = ?, exp_catdetails = ? where exp_catid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, category.getExp_catname());
			ps.setString(2, category.getExp_catdetails());
			ps.setInt(3, category.getExp_catid());
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to create a new row." + sq);
		} finally {
			pool.putConnection(conn);
		}
	}

	public void remove(int catid) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		try {
			String sql = "delete from expenses_category where exp_catid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, catid);
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to create a new row." + sq);
		} finally {
			pool.putConnection(conn);
		}
	}

	public ExpensesCategories find(int catid) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		ExpensesCategories category = new ExpensesCategories();
		try {
			String sql = "select * from expenses_category where exp_catid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, catid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				category.setExp_catid(catid);
				category.setExp_catname(rs.getString("exp_catname"));
				category.setExp_catdetails(rs.getString("exp_catdetails"));
				category.setUserid(rs.getInt("userid"));
			}
		} catch (SQLException sq) {
			System.out.println("Unable to create a new row." + sq);
		} finally {
			pool.putConnection(conn);
		}
		return category;
	}

	public ArrayList<ExpensesCategories> findAll() {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		ArrayList<ExpensesCategories> listCategory = new ArrayList<ExpensesCategories>();
		try {
			String sql = "select * from expenses_category";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ExpensesCategories category = new ExpensesCategories();
				category.setExp_catid(rs.getInt("exp_catid"));
				category.setExp_catname(rs.getString("exp_catname"));
				category.setExp_catdetails(rs.getString("exp_catdetails"));
				category.setUserid(rs.getInt("userid"));
				listCategory.add(category);
			}
		} catch (SQLException sq) {
			System.out.println("unable to find the record." + sq);
		} finally {
			pool.putConnection(conn);
		}
		return listCategory;
	}

	public static ArrayList<ExpensesCategories> findAllByUserId(int userid) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		ArrayList<ExpensesCategories> listCategory = new ArrayList<ExpensesCategories>();
		try {
			String sql = "select * from expenses_category where userid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ExpensesCategories category = new ExpensesCategories();
				category.setExp_catid(rs.getInt("exp_catid"));
				category.setExp_catname(rs.getString("exp_catname"));
				category.setExp_catdetails(rs.getString("exp_catdetails"));
				category.setUserid(rs.getInt("userid"));
				listCategory.add(category);
			}
		} catch (SQLException sq) {
			System.out.println("unable to find the record." + sq);
		} finally {
			pool.putConnection(conn);
		}
		return listCategory;
	}
	
	public static void main(String args[]) {

		
//		  ExpensesCategoriesDao dc = new ExpensesCategoriesDao();
//		  ExpensesCategories obj = new ExpensesCategories("Tution", "College and Coaching fees", 1);
//		  dc.create(obj);
		 
		
//		 ExpensesCategories category = new ExpensesCategories(1, "Daddy", "Monthly Allowance", 1);
//		 ExpensesCategoriesDao cd = new ExpensesCategoriesDao();
//		 cd.edit(category);
		 

		
//		  ExpensesCategoriesDao cd = new ExpensesCategoriesDao();
//		  cd.remove(1);
		 

		
//		  ExpensesCategoriesDao cd = new ExpensesCategoriesDao();
//		  ExpensesCategories category = new ExpensesCategories();
//		  category = cd.find(2);
//		  System.out.println(category);
//		 

//		ExpensesCategoriesDao cd = new ExpensesCategoriesDao();
//		ArrayList<ExpensesCategories> al = cd.findAllByUserId(1);
//		for (ExpensesCategories category : al) {
//			System.out.println(category);
//		}

	}

}
