package com.softra.bankingapp.account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.softra.bankingapp.account.Account;

public class Customer {
	private  static int customerid = 100000;
//	private static int customerid;
	private String fname;
	private String lname;
	private int age;
	private int mobileNumber;
	private Account account;
	
	DBConnection con = new DBConnection();
	
	public Customer(String fname,String lname, int age, int mobileNumber) {
		
//		++customerid;
		this.fname = fname;
		this.lname = lname;
		this.age = age;
		this.mobileNumber = mobileNumber;
		
		try {
//		PreparedStatement
		String pinsert = "INSERT INTO CUSTOMER(FNAME,LNAME,AGE,CONTACTNO) VALUES (?,?,?,?)";
		PreparedStatement pstat = con.getConnection().prepareStatement(pinsert);
//		insert values in the ? field in the PreparedStatement
		pstat.setString(1, fname);
		pstat.setString(2, lname);
		pstat.setInt(3, age);
		pstat.setInt(4, mobileNumber);
		pstat.execute();
//		System.out.println("Prepared statement executed");
		getMaxCustId();
		System.out.printf("Customer account %d created\n",customerid);
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void getMaxCustId() {
		
		try {
		String select = "select MAX(id) from customer";
		Statement stmt=con.getConnection().createStatement();  
		ResultSet rs=stmt.executeQuery(select);  
		while(rs.next()) {
			customerid = rs.getInt(1);
		}
		}catch(SQLException e) {
			
		}
	}
	
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public int getCustomerid() {
		return customerid;
	}
	
	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(int mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@Override
	public String toString() {
		return "Customer [fname=" + fname + ", lname=" + lname + ", age=" + age + ", mobileNumber=" + mobileNumber
				 + "]";
	}

	
	
	

	

	
}
