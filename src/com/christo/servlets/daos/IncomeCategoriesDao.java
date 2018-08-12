package com.christo.servlets.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.christo.servlets.pojos.IncomeCategories;
import com.christo.servlets.utilities.ConnectionPool;


public class IncomeCategoriesDao {
	
	public void create(IncomeCategories category) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		try {
			String sql = "insert into income_category (inc_catname, inc_catdetails, userid) values(?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, category.getInc_catname());
			ps.setString(2, category.getInc_catdetails());
			ps.setInt(3, category.getUserid());
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to create a new row." + sq);
		} finally {
			pool.putConnection(conn);
		}
	}

	public void edit(IncomeCategories category) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		try {
			String sql = "update income_category set inc_catname = ?, inc_catdetails = ? where inc_catid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, category.getInc_catname());
			ps.setString(2, category.getInc_catdetails());
			ps.setInt(3, category.getInc_catid());
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
			String sql = "delete from income_category where inc_catid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, catid);
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to create a new row." + sq);
		} finally {
			pool.putConnection(conn);
		}
	}

	public static int findByCategoryName(String inc_catname) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		int categoryid = 0;
		try {
			String sql = "select inc_catid from income_category where inc_catname = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, inc_catname);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				categoryid = rs.getInt("inc_catid");
			}
		} catch (SQLException sq) {
			System.out.println("Unable to create a new row." + sq);
		} finally {
			pool.putConnection(conn);
		}
		return categoryid;
	}
	
	public IncomeCategories find(int catid) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		IncomeCategories category = new IncomeCategories();
		try {
			String sql = "select * from income_category where inc_catid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, catid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				category.setInc_catid(catid);
				category.setInc_catname(rs.getString("inc_catname"));
				category.setInc_catdetails(rs.getString("inc_catdetails"));
				category.setUserid(rs.getInt("userid"));
			}
		} catch (SQLException sq) {
			System.out.println("Unable to create a new row." + sq);
		} finally {
			pool.putConnection(conn);
		}
		return category;
	}

	public ArrayList<IncomeCategories> findAll() {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		ArrayList<IncomeCategories> listCategory = new ArrayList<IncomeCategories>();
		try {
			String sql = "select * from income_category";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				IncomeCategories category = new IncomeCategories();
				category.setInc_catid(rs.getInt("inc_catid"));
				category.setInc_catname(rs.getString("inc_catname"));
				category.setInc_catdetails(rs.getString("inc_catdetails"));
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
	
	public static ArrayList<IncomeCategories> findAllByUserId(int userid) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		ArrayList<IncomeCategories> listCategory = new ArrayList<IncomeCategories>();
		try {
			String sql = "select * from income_category where userid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				IncomeCategories category = new IncomeCategories();
				category.setInc_catid(rs.getInt("inc_catid"));
				category.setInc_catname(rs.getString("inc_catname"));
				category.setInc_catdetails(rs.getString("inc_catdetails"));
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

		
//		  IncomeCategoriesDao dc = new IncomeCategoriesDao();
//		  IncomeCategories obj = new IncomeCategories("Interest", "Saving account interest", 1);
//		  dc.create(obj);
		 
		
//		 IncomeCategories category = new IncomeCategories(1, "Daddy", "Monthly Allowance", 1);
//		 IncomeCategoriesDao cd = new IncomeCategoriesDao();
//		 cd.edit(category);
		 

		
//		  IncomeCategoriesDao cd = new IncomeCategoriesDao();
//		  cd.remove(4);
		 

		
//		  IncomeCategoriesDao cd = new IncomeCategoriesDao();
//		  IncomeCategories category = new IncomeCategories();
//		  category = cd.find(5);
//		  System.out.println(category);
		 

		IncomeCategoriesDao cd = new IncomeCategoriesDao();
		ArrayList<IncomeCategories> al = cd.findAllByUserId(1);
		for (IncomeCategories category : al) {
			System.out.println(category);
		}

	}
}

