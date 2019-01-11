package dao;

import java.sql.*;
import javax.swing.*;
public class mysql_connection {
	Connection conn = null;
	public static Connection DBConnector() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/medical_unit?useSSL=false","root","kiok17");
//			JOptionPane.showMessageDialog(null,"Connection Succesful");
			return conn;
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null,e);
			return null;
		}
	}
}