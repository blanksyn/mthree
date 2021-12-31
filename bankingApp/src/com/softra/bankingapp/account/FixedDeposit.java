package com.softra.bankingapp.account;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class FixedDeposit extends Account {
	
	private double depositAmount, interestEarned;
	private int tenure;
	private double minDeposit = 50;
	private boolean senior =false;

	public FixedDeposit(String bankName, String branchName, String openingDate, double balance,Customer customer,int tenure) {
		super(bankName, branchName, openingDate,balance, customer);
		if(balance<minDeposit) {
			System.out.println("Error: Minimum deposit amount required ($50)");
		}else { 
//			System.out.println(this.getBalance());
			
			if(tenure<1 || tenure >5) {
				System.out.println("Error: Enter valid tenure period of 1-5 years");
			}else {
				this.tenure= tenure;
				if(customer.getAge()>59) {
					senior = true;
				}
				try {
//					PreparedStatement
					String pinsert = "INSERT INTO ACCOUNT(bankName,branchName,type,openingDate,balance,tenure,customerid) VALUES (?,?,?,?,?,?,?)";
					PreparedStatement pstat = con.getConnection().prepareStatement(pinsert);
//					insert values in the ? field in the PreparedStatement
					pstat.setString(1, bankName);
					pstat.setString(2, branchName);
					pstat.setString(3, "Fixed");
					pstat.setString(4, openingDate);
					pstat.setDouble(5, balance);
					pstat.setInt(6, tenure);
					pstat.setInt(7, customer.getCustomerid());
					pstat.execute();
//					System.out.println("Prepared statement executed");
					this.setAccountNumber(getMaxId());
					System.out.println("Fixed deposit account created - ID:" + this.getAccountNumber());
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
				
				System.out.println("Total interest(per annum): $" + fixedOperation(senior,tenure));
			}
			
		}
	}//end of constructor

	@Override
	protected double calculateInterest(double interest) {
		
		double interestValue = (this.getBalance()/100)*interest;
		
		return interestValue;
	}
	
	private double fixedOperation(boolean senior, int tenure) {
		double interest = 0;
		try {
			if(tenure>0 && tenure< 3) {
//				tenure 1 - 2 years
				if(senior == false) {
//					interest is 4%
					interest =calculateInterest(4);
				}else {
//					interest is 4.5%	
					interest =calculateInterest(4.5);
				}
			}else if(tenure>2 && tenure< 5){
//				tenure 3 -4 years
				if(senior == false) {
//					interest is 5%
					interest =calculateInterest(5);
				}else {
//					interest is 5.5%	
					interest =calculateInterest(5.5);
				}
			}else if(tenure ==5) {
//				tenure 5 years
				if(senior == false) {
//					interest is 6%
					interest =calculateInterest(6);
				}else {
//					interest is 6.5%	
					interest =calculateInterest(6.5);
				}
			}

		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			return interest;
		}
		
	}
	
	

	@Override
	public int compareTo(Account o) {
		if(o.getBalance()>this.getBalance()){
            return -1;
        }else if(this.getBalance()==o.getBalance()){
            return 0;
        }else{
            return 1;
        }
	}
	
}
