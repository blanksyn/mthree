package com.softra.bankingapp.account;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Random;

public abstract class Account implements Comparable<Account>{
//	private static int accountNumber = 10000000;
	private static int accountNumber;
	private double balance;
	private String bankName;
	private String branchName;
	private String openingDate;//DD/MM/YYYY
	private Customer customer;
	
	DBConnection con = new DBConnection();

	public Account(String bankName, String branchName, String openingDate, double balance, Customer customer) {
//		accountNumber++;
		this.balance = balance;
		this.bankName = bankName;
		this.branchName = branchName;
		this.customer = customer;
		
		Utility u = new Utility();
		
		if(u.isValidDate(openingDate)==true) {
			this.openingDate = openingDate;
		}else {
			System.out.println("Date not valid ");
			return;
		}
		
	}
	
	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(String openingDate) {
		this.openingDate = openingDate;
	}



	@Override
	public String toString() {
		return "Account [balance=" + balance + ", bankName=" + bankName + ", branchName=" + branchName
				+ ", openingDate=" + openingDate + ", customer=" + customer + "]";
	}

	protected abstract double calculateInterest(double balance);
	
	public int getMaxId() {
		int id =0;
		try {
			String select = "select MAX(id) from account";
			Statement stmt=con.getConnection().createStatement();  
			ResultSet rs=stmt.executeQuery(select);  
			while(rs.next()) {
				id = rs.getInt(1);
			}
			}catch(SQLException e) {
				
			}
		return id;
	}
	
	
	
	
}
