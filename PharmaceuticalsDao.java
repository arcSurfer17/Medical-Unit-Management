package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import models.PharmaceuticalsModel;
import view.Add_Pharmaceuticals;

public class PharmaceuticalsDao {
	private static Connection conn = mysql_connection.DBConnector(); 
	
	public static ResultSet getPharmaThreshold() throws SQLException {
		String query = "select * from Pharmaceuticals Where Quantity < Threshold;";
		PreparedStatement pst=conn.prepareStatement(query);
		ResultSet rs=pst.executeQuery();
		return rs;
	}
	
	public static void addPharmaceutical(PharmaceuticalsModel phm) {
		try {
			String query = "SELECT * FROM Pharmaceuticals";
			PreparedStatement pst1 = conn.prepareStatement(query);
			ResultSet rs = pst1.executeQuery();
			int count = 0;
			while(rs.next()) {
				count++;
			}
			count++;
			String query1 = "INSERT INTO Pharmaceuticals (Pharma_ID,Name,Category,Quantity,Threshold) VALUES ('"+count+"',?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(query1);
			pst.setString(1, phm.getPharmaName());
			pst.setString(2, phm.getCategory());
			pst.setInt(3, phm.getQuantity());
			pst.setInt(4, phm.getThreshold());
			pst.execute();
			
		}catch(Exception e1) {
			JOptionPane.showMessageDialog(null,e1);
		}
	}
	
	public static ResultSet getAllPharmaName() throws SQLException {
		String query = "SELECT Name from Pharmaceuticals;";
		PreparedStatement pst = conn.prepareStatement(query);
		ResultSet rs = pst.executeQuery();
		return rs;
	}
	
	public static ResultSet getAllPharma() throws SQLException {
		String query = "SELECT * from Pharmaceuticals;";
		PreparedStatement pst = conn.prepareStatement(query);
		ResultSet rs = pst.executeQuery();
		return rs;
	}
	
	
	public static void updatePharma(int quantity,String item) throws SQLException {
		String query = "UPDATE Pharmaceuticals SET Quantity = Quantity + '"+ quantity +"' WHERE Name='"+item+"'";
		PreparedStatement pst = conn.prepareStatement(query);
		pst.execute();
		
	}
	
	public static void Insert_Into_Alloted_Pharma(int prescription_id,String textFieldPharmaName,int textFieldPharmaQuantity) {
		
		try {
			String query1 = "SELECT Pharma_ID FROM Pharmaceuticals WHERE Name ='"+textFieldPharmaName+"'; ";
			PreparedStatement pst1 = conn.prepareStatement(query1);
			ResultSet rs1 = pst1.executeQuery();
			rs1.next();
			int pharma_id = rs1.getInt("Pharma_ID");
			
			query1 = "INSERT INTO Alloted_Pharma VALUES('"+prescription_id+"','"+pharma_id+"', '"+textFieldPharmaQuantity+"')";
			pst1 = conn.prepareStatement(query1);
			pst1.execute();
			
		}catch(Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public static void Delete_From_Pharma(String textFieldPharmaName,int textFieldPharmaQuantity) {
		try {
			String query1 = "SELECT Pharma_ID FROM Pharmaceuticals WHERE Name ='"+textFieldPharmaName+"'; ";
			PreparedStatement pst1 = conn.prepareStatement(query1);
			ResultSet rs1 = pst1.executeQuery();
			rs1.next();
			int pharma_id = rs1.getInt("Pharma_ID");
			
			query1 = "Update Pharmaceuticals SET Quantity = Quantity - '"+textFieldPharmaQuantity+"' WHERE Pharma_ID = '"+pharma_id+"' ;";
			pst1 = conn.prepareStatement(query1);
			pst1.execute();
			
		}catch(Exception e1) {
			e1.printStackTrace();
		}
	}
	

}
