package com.softra.bankingapp.account;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private String driver;
	private String url;
	private String user;
	private String pass;
	
	public Connection getConnection() {
		readFile();
		Connection con = null;
		try {
			Class.forName(driver); //name of package, actually java class name = Driver
//			System.out.println("Type 4 Driver successfully loaded into memory");
			
//			Set connection to the database using DriverManager
			con = DriverManager.getConnection(url,user,pass);
//			System.out.println("Connection to database successful");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	public void readFile() {
		try {
	         File file = new File ("./../../../Mthree/workspace/EngageAssignments/lib/dbconfig.properties");
	         FileReader fileReader = new FileReader(file);
	         BufferedReader bufferedRead = new BufferedReader(fileReader);

	         String line;
	         int n = 0;
	         while((line=bufferedRead.readLine())!=null) {
	            String[] data = line.split("=");
//	            System.out.println(n + " " + data[1]);

	            switch (n){
	               case 0:
	                  driver = data[1];
	                  break;
	               case 1:
	                  url = data[1];
	                  break;
	               case 2:
	                  user = data[1];
	                  break;
	               case 3:
	                  pass = data[1];
	                  break;
	               default:
	                  System.out.println("Invalid data.");
	                  break;
	            }

	            n++;
	         }
	      } catch (FileNotFoundException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
	}
}
