package com.softra.bankingapp.account;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.softra.bankingapp.exceptions.InsufficientFundsException;

public class SavingsAccount extends Account implements CurrencyConvertor{
	
	private int minBalance = 100;
	Scanner scan = new Scanner(System.in);
	private boolean salaryAcc = false;
	private String currency= "SGD";
	DBConnection con = new DBConnection();
	

	public SavingsAccount(String bankName, String branchName, String openingDate,double balance, Customer customer) throws InsufficientFundsException {
		super(bankName, branchName, openingDate,balance, customer);

		System.out.println("Is this a salary account? y/n");
		String result = scan.nextLine();
		
		if(result.equalsIgnoreCase("n")){
//			savings account
//			balance compared to min balance
			if(this.getBalance()<minBalance){	
				System.out.println("Insufficient balance for account creation. Minimum balance is $100");
				System.exit(0);
			}else {
				try {
//					PreparedStatement
					String pinsert = "INSERT INTO ACCOUNT(bankName,branchName,type,openingDate,balance,salAcc,customerid,currency) VALUES (?,?,?,?,?,?,?,?)";
					PreparedStatement pstat = con.getConnection().prepareStatement(pinsert);
//					insert values in the ? field in the PreparedStatement
					pstat.setString(1, bankName);
					pstat.setString(2, branchName);
					pstat.setString(3, "Savings");
					pstat.setString(4, openingDate);
					pstat.setDouble(5, balance);
					pstat.setBoolean(6, salaryAcc);
					pstat.setInt(7, customer.getCustomerid());
					pstat.setString(8, currency);
					pstat.execute();
//					System.out.println("Prepared statement executed");
					this.setAccountNumber(getMaxId());
					System.out.println("Savings account created - " + this.getAccountNumber());
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
//				savings account
				savingsOperation();
				
			}
			
		}else if(result.equalsIgnoreCase("y")){
			salaryAcc = true;
			try {
				try {
//					PreparedStatement
					String pinsert = "INSERT INTO ACCOUNT(bankName,branchName,type,openingDate,balance,salAcc,customerid,currency) VALUES (?,?,?,?,?,?,?,?)";
					PreparedStatement pstat = con.getConnection().prepareStatement(pinsert);
//					insert values in the ? field in the PreparedStatement
					pstat.setString(1, bankName);
					pstat.setString(2, branchName);
					pstat.setString(3, "Salary");
					pstat.setString(4, openingDate);
					pstat.setDouble(5, balance);
					pstat.setBoolean(6, salaryAcc);
					pstat.setInt(7, customer.getCustomerid());
					pstat.setString(8, currency);
					pstat.execute();
//					System.out.println("Prepared statement executed");
					this.setAccountNumber(getMaxId());
					System.out.println("Savings(salary) account created - ID:" + this.getAccountNumber());
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
				
				System.out.println("Would you like to withdraw? (y/n)");
				String salWithdraw = scan.nextLine();
				
				if(salWithdraw.equalsIgnoreCase("y")) {
				withdraw();
				}
			} catch (InsufficientFundsException e) {
				// TODO Auto-generated catch block
				InsufficientFundsException ife = new InsufficientFundsException("Not enough balance in account");
				throw ife;
			}
			System.out.println("Total interest: $" + calculateInterest(this.getBalance()) + currency);
		}else {
			System.out.println("Error: Invalid input");
			
		}
		
	}//end of SavingsAccount
	
	public void savingsOperation() {
		
		int choice = 5;
		while(choice!=0) {
			try {
				System.out.println("Please select operation: ");
				System.out.println("1. Deposit");
				System.out.println("2. Withdraw");
				System.out.println("3. Check balance");
				System.out.println("4. Convert money");
				System.out.println("0. Exit");
				choice = scan.nextInt();
				
				switch(choice) {
				case 1:
//					Deposit 
					deposit();
					break;
				case 2:
//					Withdraw
					withdraw();
					break;
				case 3:
//					Check balance
					System.out.println("Current account balance: $"+ this.getBalance() + currency);
					break;
				case 4:
//					Convert money
					convert(this.getBalance());
					break;
				case 0:
//					System exit
					System.out.println("Total interest: $" + calculateInterest(this.getBalance())+ currency);
					System.out.println("System exiting... Goodbye");
					break;
				default:
					System.out.println("Invalid input. Please key in a valid input");
					break;
				}//end of switch
			}catch(Exception e) {
				System.out.println("Invalid input: "+ e.getMessage());
				break;
			}
			
		}
	}//end of operation
	
	
	private void convert(double balance) {
		int loop = 5;
		try {
			while(loop!=0) {
				System.out.println("Please select convert option: ");
				System.out.println("Current currency: " + currency);
				System.out.println("1. USD -> AUD");
				System.out.println("2. AUD -> USD");
				System.out.println("3. USD -> SGD");
				System.out.println("4. SGD -> USD");
				System.out.println("0. Exit");
				loop = scan.nextInt();
				
				switch(loop) {
				case 1: 
					if(currency.equals("USD")) {
						convertUSDToAUD(balance);
						System.out.println("Balance after conversion: $"+ balance+currency);
					}else {
						System.out.println("Invalid option. Please choose valid conversion");
					}
					break;
				case 2: 
					if(currency.equals("AUD")) {
						convertAUDToUSD(balance);
						System.out.println("Balance after conversion: $"+ balance+currency);
					}else {
						System.out.println("Invalid option. Please choose valid conversion");
					}
					
					break;
				case 3:
					if(currency.equals("USD")) {
						convertUSDToSGD(balance);
						System.out.println("Balance after conversion: $"+ balance+currency);
					}else {
						System.out.println("Invalid option. Please choose valid conversion");
					}
					
					break;
				case 4: 
					if(currency.equals("SGD")) {
						convertSGDToUSD(balance);
						System.out.println("Balance after conversion: $"+ balance+currency);
					}else {
						System.out.println("Invalid option. Please choose valid conversion");
					}
					break;
				case 0: 
					System.out.println("Back to main");
					break;
				default:
					System.out.println("Enter valid option");
				}
			}
			
			
		}catch(Exception e) {
			
		}
		
	}

	public void withdraw() throws InsufficientFundsException{
		try {
			System.out.println("Please enter amount to withdraw");
			double amount = scan.nextDouble();
			
			if(amount>this.getBalance()) {
				System.out.println("Error: Insufficient balance. Please enter a valid amount");
			}else {
				
				if(salaryAcc==false) {
//					System.out.println("Inside savings withdrawal");
//					savings account
					double diff = this.getBalance() - amount;
					if(diff<(minBalance)) {
						//throw custom exception
						InsufficientFundsException ife = new InsufficientFundsException("Error: Withdrawal declined. Minimum balance < $100 ");
						throw ife;
					}else {
						double newBalance = this.getBalance() - amount;
						this.setBalance(newBalance);
						
						String update = "UPDATE ACCOUNT SET balance = ? WHERE ID = ?";
						PreparedStatement pstat = con.getConnection().prepareStatement(update);
//						insert values in the ? field in the PreparedStatement
						pstat.setDouble(1, this.getBalance());
						pstat.setInt(2, this.getAccountNumber());
						pstat.execute();
						
						System.out.printf("$%.2f %s successfully withdrawn\n",amount,currency);
						System.out.printf("New balance: $%.2f %s\n", this.getBalance(),currency);
					}
				}else {
//					System.out.println("Inside salary withdrawal" + salaryAcc);
					double newBalance = this.getBalance() - amount;
					this.setBalance(newBalance);
					System.out.printf("$%.2f %s successfully withdrawn\n",amount,currency);
					System.out.printf("New balance: $%.2f %s\n", this.getBalance(),currency);
				}
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}//end of withdraw

	public void deposit() {
		try {
			System.out.println("Please enter amount to deposit");
			double amount = scan.nextDouble();
			if(amount<0) {
				System.out.println("Error: Invalid value. Please enter valid amount.");
			}else {
				double newBalance = this.getBalance() + amount;
				this.setBalance(newBalance);
				
				String update = "UPDATE ACCOUNT SET balance = ? WHERE ID = ?";
				PreparedStatement pstat = con.getConnection().prepareStatement(update);
//				insert values in the ? field in the PreparedStatement
				pstat.setDouble(1, this.getBalance());
				pstat.setInt(2, this.getAccountNumber());
				pstat.execute();
				
				System.out.printf("Amount $%.2f %s has successfully deposited!\n", amount,currency);
				System.out.printf("New balance: $%.2f %s\n", this.getBalance(),currency);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}//end of deposit

	@Override
	protected double calculateInterest(double balance) {
//		4% per annum
		return (balance/100)*4;
	}

	@Override
	public double convertUSDToAUD(double USD) {
		USD -= currencyConversionFee();
		currency = "AUD";
		updateCurrency();
		return USD*1.4;
	}

	@Override
	public double convertAUDToUSD(double AUD) {
		AUD -= currencyConversionFee();
		currency = "USD";
		updateCurrency();
		return AUD*0.71;
	}

	@Override
	public double convertUSDToSGD(double USD) {
		USD -= currencyConversionFee();
		currency = "SGD";
		updateCurrency();
		return USD*1.37;
	}

	@Override
	public double convertSGDToUSD(double SGD) {
		SGD -= currencyConversionFee();
		currency = "USD";
		updateCurrency();
		return SGD*0.73;
	}
	
	public double currencyConversionFee() {
		double fee = 0.02;
		if(salaryAcc ==true) {
			fee = 0.01;
		}
		
		double result = this.getBalance()*fee;
		System.out.println("Conversion fee deducted: $" + result + currency);
		return result;
	}
	
	public void updateCurrency() {
		try {
			String update = "UPDATE ACCOUNT SET currency = ? WHERE ID = ?";
			PreparedStatement pstat = con.getConnection().prepareStatement(update);
//			insert values in the ? field in the PreparedStatement
			pstat.setString(1, currency);
			pstat.setInt(2, this.getAccountNumber());
			pstat.execute();
		}catch(SQLException e) {
			
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
