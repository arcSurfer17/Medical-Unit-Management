package dao;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class MedicalStaff {
	private static Connection conn = mysql_connection.DBConnector(); 
	
	public static boolean OAuth(String username,String password) throws SQLException {
		String query = "SELECT * FROM Medical_Staff WHERE Staff_ID=? AND Password=?";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, username);
		stmt.setString(2, password);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			return true;
		}
		return false;
	}
	
	public static String getDesignation(String staffId) throws SQLException {
		String query = "select Designation FROM Medical_Staff WHERE Staff_ID = '"+staffId+"';";
		PreparedStatement stmt = conn.prepareStatement(query);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			String designation = rs.getString("Designation");			
			return designation;
		}
		return null;
	}
	
}
