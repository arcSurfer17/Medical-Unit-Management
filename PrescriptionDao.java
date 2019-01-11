package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import models.PrescriptionModel;

public class PrescriptionDao {
		static Connection conn = mysql_connection.DBConnector();
	
	public static int savePrescription(PrescriptionModel pres) throws SQLException {
		Date dNow = new Date(); 
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		
		String query = "INSERT INTO Prescription (Patient_ID, Diagnosis,Doctor_ID, Date_Time) VALUES ('"+pres.getPatientId()+"','"+pres.getDiagnosis()+"','"+pres.getDoctorId()+"','"+ft.format(dNow).toString()+"');";
		PreparedStatement pst = conn.prepareStatement(query);
		pst.execute();
		
		query = "SELECT Prescription_ID FROM Prescription ORDER BY Prescription_ID DESC;";
		pst = conn.prepareStatement(query);
		ResultSet rs = pst.executeQuery();
		rs.next();
		int prescription_id = rs.getInt("Prescription_ID");					
		return prescription_id;
	}
	
	
	public static ResultSet searchById(String patientId) throws SQLException {
		String query = "SELECT * FROM Prescription WHERE Patient_ID = '"+patientId+"';";
		PreparedStatement pst = conn.prepareStatement(query);
		ResultSet rs = pst.executeQuery();
		return rs;
	}
	
	public static ResultSet showAllPrescription() throws SQLException {
		String query = "SELECT * FROM Prescription ORDER BY Prescription_ID DESC;";
		PreparedStatement pst = conn.prepareStatement(query);
		ResultSet rs = pst.executeQuery();
		return rs;
	}
}
