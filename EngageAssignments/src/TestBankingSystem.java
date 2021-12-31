import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import com.softra.bankingapp.account.Account;
import com.softra.bankingapp.account.Customer;
import com.softra.bankingapp.account.DBConnection;
import com.softra.bankingapp.account.FixedDeposit;
import com.softra.bankingapp.account.SavingsAccount;
import com.softra.bankingapp.exceptions.AccountNotFoundException;
import com.softra.bankingapp.exceptions.CustomerNotFoundException;
import com.softra.bankingapp.exceptions.InsufficientFundsException;

public class TestBankingSystem {
	
	static DBConnection con = new DBConnection();
	private static int customerId;
	private static String fname;
	private static String lname;
	private static int age;
	private static int mobileNo;
	
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) throws InsufficientFundsException{
		
		int option = 5;
		
		while(option!=0) {
			
			try {
				System.out.println("Select operation: ");
				System.out.println("1. Create account");
				System.out.println("2. Search");
				System.out.println("0. Exit");
				option = scan.nextInt();
				
				switch(option) {
				
				case 1:
				{
//					createAccount();
					Customer c1 = new Customer("Ricardo","Jasper", 21, 93543432);
					Account[] account = new Account[4];
					account[0] = new SavingsAccount("OCBC","SG","15/12/2021",199.0, c1);
					account[1] = new FixedDeposit("OCBC","SG","15/12/2021",1000, c1, 1);
					account[2] = new FixedDeposit("OCBC","SG","02/01/2021",2000,c1, 3);
					account[3] = new FixedDeposit("OCBC","SG","12/08/2021",5000,c1, 5);
					
					Set<Account> acc = new TreeSet<>();
					acc.add(account[0]);
					acc.add(account[1]);
					acc.add(account[2]);
					acc.add(account[3]);
					
					Iterator<Account> iter = acc.iterator();

			        while(iter.hasNext()){
			        	Account obj = iter.next();
			            System.out.println(obj);
			        }
					break;
				}
				case 2:
					System.out.println("Select search function:");
					System.out.println("1. Search by ID");
					System.out.println("2. Search by Account number");
					System.out.println("0. Return");
					int search = scan.nextInt();
					if(search==1) {
						searchCustomerId();
					}else if(search==2) {
						searchAccountNum();
					}
					break;
				case 0: 
					System.out.println("Exiting program...");
					System.exit(0);
					break;
				default:
					System.out.println("Invalid option");
					break;
				}
			}catch(Exception e) {
				e.printStackTrace();
//				System.out.println("Invalid input");
				break;
			}
				
		}
		
	}//end of main
	
	
	private static void searchAccountNum() throws AccountNotFoundException {
//		Check for valid account number
		
		try {
			System.out.printf("Please enter account number: ");
			int accNumber = scan.nextInt();
			System.out.println();
			
			Statement stmt=con.getConnection().createStatement(); 
			
			String select = "SELECT * FROM ACCOUNT WHERE id = " + accNumber;
			ResultSet rs = stmt.executeQuery(select);
			if(!rs.isBeforeFirst()){
				AccountNotFoundException anf = new AccountNotFoundException("Invalid Account number.");
				throw anf;
			}
			while(rs.next()) {
				int accountid = rs.getInt(1);
//				System.out.println(accountid);
				if(accountid == accNumber) {
					String branchName = rs.getString(2);
					String bankName = rs.getString(3);
					String type = rs.getString(4);
					double balance = rs.getDouble(5);
					String openingDate = rs.getString(6);
					
					System.out.println("Account [balance=" + balance + ", bankName=" + bankName + ", branchName=" + branchName
							+ ", openingDate=" + openingDate + ", type=" + type + "]");
				}
				
				
			}
			
		}catch(NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public static void createAccount() {
		try {
			scan.nextLine();
			System.out.println("Please enter your firstname: ");
			fname = scan.nextLine();
			scan.nextLine();
			
			System.out.println("Please enter your lastname: ");
			lname = scan.nextLine();
			scan.nextLine();
			
			System.out.println("Please enter your age: ");
			age = scan.nextInt();
			scan.nextLine();
			
			System.out.println("Please enter your mobile number: ");
			mobileNo = scan.nextInt();
			scan.nextLine();
			
//			creating customer account
			Customer c1 = new Customer(fname,lname,age,mobileNo);
			System.out.println("Customer account created: Customer id - " + c1.getCustomerid());
			
			System.out.println("1. Create savings account");
			System.out.println("2. Create fixed deposit account");
			int accountType = scan.nextInt();
			scan.nextLine();
			
			if(accountType!=1 || accountType !=2) {
				System.out.println("Invalid input");
			}else {
			
				System.out.println("Please enter your bank name: ");
				String bankName = scan.nextLine();
				scan.nextLine();
				
				System.out.println("Please enter your bank branch: ");
				String bankBranch = scan.nextLine();
				scan.nextLine();
				
				String[] date = LocalDate.now().toString().split("-");
			    String newDate = date[2]+"/"+date[1]+"/"+date[0];
//			    System.out.println(newDate);
				
				if(accountType==1) {
					System.out.println("Please enter deposit amount: ");
					int dep = scan.nextInt();
					scan.nextLine();
					
					Account savings = new SavingsAccount(bankName,bankBranch,newDate,dep, c1);
					System.out.println("Savings account created - " + savings.getAccountNumber());
				}else if(accountType == 2) {
					System.out.println("Please enter number of accounts to open: ");
					int NoAcc = scan.nextInt();
					scan.nextLine();
					
					for(int i = 0; i<NoAcc;i++) {
						System.out.println("Please enter tenure years: ");
						int tenure = scan.nextInt();
						scan.nextLine();
						
						System.out.println("Please enter deposit amount: ");
						int dep = scan.nextInt();
						scan.nextLine();
						
						Account fixed = new FixedDeposit(bankName,bankBranch,newDate,dep,c1, tenure);
						System.out.println("Fixed deposit account created - " + fixed.getAccountNumber());
					}
				}
			}
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void searchCustomerId() throws CustomerNotFoundException {
//		Check for valid customer id
		
		try {
			System.out.printf("Please enter ID number: ");
			int accNumber = scan.nextInt();
			System.out.println();
			
			Statement stmt=con.getConnection().createStatement(); 
			
			String select = "SELECT * FROM CUSTOMER WHERE id = \"" + accNumber +"\"";
			ResultSet rs = stmt.executeQuery(select);
			
			if(!rs.isBeforeFirst()){
				CustomerNotFoundException cnf = new CustomerNotFoundException("Invalid customer ID.");
				throw cnf;
			}
			while(rs.next()) {
				customerId = rs.getInt(1);
				if(customerId == accNumber) {
					fname = rs.getString(2);
					lname = rs.getString(3);
					age = rs.getInt(4);
					mobileNo = rs.getInt(5);
					System.out.println("Customer [fname=" + fname + ", lname=" + lname + ", age= " + age + ", mobileNumber= " + mobileNo
							+ "] " );
				}
				
			}
			
		}catch(NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//end of searchCustomerId()
	

}
