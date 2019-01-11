package view;


import java.awt.BorderLayout;
import java.util.*;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.sql.*;
import java.text.SimpleDateFormat;

import javax.swing.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.poi.xwpf.usermodel.Borders;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;

import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import dao.PharmaceuticalsDao;
import dao.PrescriptionDao;
import dao.mysql_connection;
import models.PrescriptionModel;

import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.awt.event.ActionEvent;

import java.io.File;  
import java.io.FileOutputStream;  
import org.apache.poi.xwpf.usermodel.XWPFDocument;  
import org.apache.poi.xwpf.usermodel.XWPFTable;  
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import java.awt.Color;
import java.awt.Font;

public class Prescription extends JFrame {

	private JPanel contentPane;
	private JFrame pr;
	private static JTextField textFieldID;
	private static JTextField textFieldName;
	private JTextField textFieldDiagnosis;
	private static String Diagnosis = "";
	private static JTextField textFieldPharma1Quantity;
	private static JTextField textFieldPharma2Quantity;
	private static JTextField textFieldPharma3Quantity;
	private static JTextField textFieldPharma4Quantity;
	private static JTextField textFieldPharma5Quantity;
	private static JTextField textFieldPharma6Quantity;
	private JButton btnSave;
	private static ArrayList<Pair> arr=new ArrayList<>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args, String Staff_ID) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Prescription frame = new Prescription(Staff_ID);
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	
	public static String output ;
	public class Pair {
		String name;
		int quantity;
		public Pair(String name,int quantity) {
			this.name=name;
			this.quantity=quantity;
		}
	}
	
	public static void setTableAlign(XWPFTable table,ParagraphAlignment align) {
	    CTTblPr tblPr = table.getCTTbl().getTblPr();
	    CTJc jc = (tblPr.isSetJc() ? tblPr.getJc() : tblPr.addNewJc());
	    STJc.Enum en = STJc.Enum.forInt(align.getValue());
	    jc.setVal(en);
	}
	
	public static  void handleSimpleDoc(String Staff_ID) throws Exception {
	       XWPFDocument document = new XWPFDocument();
	       
	       
	       
	       XWPFParagraph title = document.createParagraph();
	       title.setAlignment(ParagraphAlignment.CENTER);
	       XWPFRun titleRun = title.createRun();
	       titleRun.setText("The LNM Institute of Information Technology");
	       //titleRun.setColor("009933");
	       titleRun.setBold(true);
	       titleRun.setFontFamily("Arial");
	       titleRun.setFontSize(16);
	       titleRun.addBreak();
	       titleRun.addBreak();
	       titleRun.addBreak();

	       XWPFParagraph para1 = document.createParagraph();
	       para1.setAlignment(ParagraphAlignment.LEFT);
	       XWPFRun para1Run = para1.createRun();
	       para1Run.setText("Patient ID : " + textFieldID.getText());
	       para1Run.addBreak();
//	       para1Run.addTab();
	       para1Run.setText("Patient Name : " + textFieldName.getText());
	       //titleRun.setColor("009933");
	       para1Run.setBold(true);
	       para1Run.setFontFamily("Arial");
	       para1Run.setFontSize(16);
	       para1Run.addBreak();
	       
	       
	       
	       XWPFParagraph para4 = document.createParagraph();
	       para4.setAlignment(ParagraphAlignment.LEFT);
	       XWPFRun para4Run = para4.createRun();
	       para4Run.setText("Diagnosis : " +Diagnosis );
	       //titleRun.setColor("009933");
	       para4Run.setBold(true);
	       para4Run.setFontFamily("Arial");
	       para4Run.setFontSize(16);
	       para4Run.addBreak();
	       para4Run.addBreak();
	       
	       XWPFParagraph para5 = document.createParagraph();
	       para5.setAlignment(ParagraphAlignment.CENTER);
	       XWPFRun para5Run = para5.createRun();
	       para5Run.setText("Allotted Pharmaceuticals");
	       //titleRun.setColor("009933");
	       para5Run.setBold(true);
	       para5Run.setFontFamily("Arial");
	       para5Run.setFontSize(18);
	       para5Run.addBreak();
	       
	       XWPFTable table = document.createTable();
	       table.setCellMargins(0, 1000, 0, 1000);	 
	       setTableAlign(table, ParagraphAlignment.CENTER);
	       XWPFTableRow row = table.getRow(0);
	       row.getCell(0).setText("Name");
	       row.addNewTableCell().setText("Quantity");
	       
	       for(int i=0;i<arr.size();i++) {
	    	   row = table.createRow();
	    	   row.getCell(0).setText(arr.get(i).name);
	    	   row.getCell(1).setText(String.valueOf(arr.get(i).quantity));
	       }
	       
	       
	       
	      
	       output = "/home/rohan/eclipse-workspace/Medical_Unit/"+textFieldID.getText()+".docx";
	       FileOutputStream out = new FileOutputStream(output);
	       document.write(out);
	       out.close();
	       document.close();
	       String query = "SELECT ";
	       if(textFieldID.getText().compareTo("Other")!=0)     Send_Email2.sendMail(textFieldID.getText()+"@lnmiit.ac.in", "demomedicallnmiit@gmail.com", "Qwerty@1234", output);
	   }

	   public static String convertTextFileToString(String fileName) {
	       try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
	           return stream.collect(Collectors.joining(" "));
	       } catch (IOException e) {
	           e.printStackTrace();
	       }
	       return null;
	   }

	/**
	 * Create the frame.
	 */
	static Connection conn=null;
	private JButton btnBack;
	private JButton btnLogout;
	private JButton btnSearch;
	private JLabel label;
	private JLabel label_1;
	private JLabel lblLogout;
	private JComboBox textFieldPharma1Name;
	private JComboBox textFieldPharma2Name;
	private JComboBox textFieldPharma3Name;
	private JComboBox textFieldPharma4Name;
	private JComboBox textFieldPharma5Name;
	private JComboBox textFieldPharma6Name;
	
	
	
	
	public static int validateIntegerField(String s) {
		for(int i=0;i<s.length();i++) {
			if(s.charAt(i)<'0' || s.charAt(i)>'9')	return 0;
		}
		return 1;
	}
	
	
	
	
	public Prescription(String Staff_ID) {
		pr = new JFrame();
		pr.setTitle("Prescription");
		System.out.println("Prescription : "+Staff_ID);
		
		
		JLabel lblNewLabel = new JLabel("PRESCRIPTION");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBounds(506, 11, 218, 41);
		pr.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel_1.setBounds(85, 121, 151, 31);
		pr.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("NAME");
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel_2.setBounds(602, 121, 151, 31);
		pr.getContentPane().add(lblNewLabel_2);
		
		textFieldID = new JTextField();
		textFieldID.setBounds(210, 122, 218, 31);
		pr.getContentPane().add(textFieldID);
		textFieldID.setColumns(10);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(771, 122, 218, 31);
		pr.getContentPane().add(textFieldName);
		textFieldName.setColumns(10);
		
		JLabel lblDiagnosis = new JLabel("DIAGNOSIS");
		lblDiagnosis.setFont(new Font("Dialog", Font.BOLD, 18));
		lblDiagnosis.setBounds(85, 183, 124, 32);
		pr.getContentPane().add(lblDiagnosis);
		
		JComboBox comboBoxDiagnosis = new JComboBox();
		comboBoxDiagnosis.setModel(new DefaultComboBoxModel(new String[] {"Allergy", "Cough&Cold", "Headache", "Muscle Sprain", "Other"}));
		comboBoxDiagnosis.setBounds(210, 184, 218, 31);
		pr.getContentPane().add(comboBoxDiagnosis);
		
		textFieldDiagnosis = new JTextField();
		textFieldDiagnosis.setBounds(602, 183, 671, 110);
		pr.getContentPane().add(textFieldDiagnosis);
		textFieldDiagnosis.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("PHARMACEUTICAL NAME");
		lblNewLabel_4.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel_4.setForeground(new Color(0, 0, 0));
		lblNewLabel_4.setBounds(178, 370, 346, 31);
		pr.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("QUANTITY");
		lblNewLabel_5.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel_5.setBounds(777, 374, 234, 22);
		pr.getContentPane().add(lblNewLabel_5);
		
		textFieldPharma1Name = new JComboBox();
        textFieldPharma1Name.setBounds(178, 439, 218, 28);
        pr.getContentPane().add(textFieldPharma1Name);
        
        textFieldPharma2Name = new JComboBox();
        textFieldPharma2Name.setBounds(178, 482, 219, 28);
        pr.getContentPane().add(textFieldPharma2Name);
        
        textFieldPharma3Name = new JComboBox();
        textFieldPharma3Name.setBounds(178, 525, 218, 28);
        pr.getContentPane().add(textFieldPharma3Name);
        
        textFieldPharma4Name = new JComboBox();
        textFieldPharma4Name.setBounds(178, 568, 218, 31);
        pr.getContentPane().add(textFieldPharma4Name);
        
        textFieldPharma5Name = new JComboBox();
        textFieldPharma5Name.setBounds(178, 611, 218, 28);
        pr.getContentPane().add(textFieldPharma5Name);
        
        textFieldPharma6Name = new JComboBox();
        textFieldPharma6Name.setBounds(178, 654, 218, 24);
        pr.getContentPane().add(textFieldPharma6Name);
		
		textFieldPharma1Quantity = new JTextField();
		textFieldPharma1Quantity.setColumns(10);
		textFieldPharma1Quantity.setBounds(777, 436, 218, 31);
		pr.getContentPane().add(textFieldPharma1Quantity);
		
		textFieldPharma2Quantity = new JTextField();
		textFieldPharma2Quantity.setColumns(10);
		textFieldPharma2Quantity.setBounds(777, 479, 218, 31);
		pr.getContentPane().add(textFieldPharma2Quantity);
		
		textFieldPharma3Quantity = new JTextField();
		textFieldPharma3Quantity.setColumns(10);
		textFieldPharma3Quantity.setBounds(777, 522, 218, 31);
		pr.getContentPane().add(textFieldPharma3Quantity);
		
		textFieldPharma4Quantity = new JTextField();
		textFieldPharma4Quantity.setColumns(10);
		textFieldPharma4Quantity.setBounds(777, 565, 218, 31);
		pr.getContentPane().add(textFieldPharma4Quantity);
		
		textFieldPharma5Quantity = new JTextField();
		textFieldPharma5Quantity.setColumns(10);
		textFieldPharma5Quantity.setBounds(777, 608, 218, 31);
		pr.getContentPane().add(textFieldPharma5Quantity);
		
		textFieldPharma6Quantity = new JTextField();
		textFieldPharma6Quantity.setColumns(10);
		textFieldPharma6Quantity.setBounds(777, 651, 218, 31);
		pr.getContentPane().add(textFieldPharma6Quantity);
		
		Object[] elements = new Object[1000];
		Connection conn = mysql_connection.DBConnector();
		HashMap<String, Integer> hm  = new HashMap<String,Integer>();
		try {
			String query = "SELECT Name,Quantity from Pharmaceuticals;";
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			int i = 0;
			elements[i++] = "SELECT";
			while(rs.next()) {
				String name = rs.getString("Name");
				int quantity = rs.getInt("Quantity");
				hm.put(name, quantity);
				elements[i++] = name;
			}
		} catch (SQLException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		
		AutoCompleteSupport.install(textFieldPharma1Name, GlazedLists.eventList(Arrays.asList(elements)));
		AutoCompleteSupport.install(textFieldPharma2Name, GlazedLists.eventList(Arrays.asList(elements)));
		AutoCompleteSupport.install(textFieldPharma3Name, GlazedLists.eventList(Arrays.asList(elements)));
		AutoCompleteSupport.install(textFieldPharma4Name, GlazedLists.eventList(Arrays.asList(elements)));
		AutoCompleteSupport.install(textFieldPharma5Name, GlazedLists.eventList(Arrays.asList(elements)));
		AutoCompleteSupport.install(textFieldPharma6Name, GlazedLists.eventList(Arrays.asList(elements)));
		
		btnSave = new JButton("SAVE");
		btnSave.setFont(new Font("Dialog", Font.BOLD, 18));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Date dNow = new Date(); 
				SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
				System.out.println(ft.format(dNow));
				String Patient_ID = textFieldID.getText();
				String Patient_Name = textFieldName.getText();
				
				if(comboBoxDiagnosis.getSelectedItem().toString().compareTo("Other")==0) {
					Diagnosis = textFieldDiagnosis.getText();
				}else {
					Diagnosis = comboBoxDiagnosis.getSelectedItem().toString();
				}
				if(validateIntegerField(textFieldPharma1Quantity.getText()) ==0 || validateIntegerField(textFieldPharma2Quantity.getText()) ==0 || validateIntegerField(textFieldPharma3Quantity.getText()) ==0 || validateIntegerField(textFieldPharma4Quantity.getText()) ==0 || validateIntegerField(textFieldPharma5Quantity.getText()) ==0 || validateIntegerField(textFieldPharma6Quantity.getText()) ==0) {
					
					JOptionPane.showMessageDialog(null,"Please enter integer value in Quantity");
				}
				else {
					
					int flag = 1;
					
					
					if(textFieldPharma1Name.getSelectedIndex()!=-1 && textFieldPharma1Quantity.getText().compareTo("")!=0) {	
						if(Integer.parseInt(textFieldPharma1Quantity.getText()) > hm.get(textFieldPharma1Name.getSelectedItem().toString())) {
							JOptionPane.showMessageDialog(null,"Required Quantity not availabe for "+textFieldPharma1Name.getSelectedItem().toString());
							flag = 0;
						}
					}
					
					if(textFieldPharma2Name.getSelectedIndex()!=-1 && textFieldPharma2Quantity.getText().compareTo("")!=0) {	
						if(Integer.parseInt(textFieldPharma2Quantity.getText()) > hm.get(textFieldPharma2Name.getSelectedItem().toString())) {
							JOptionPane.showMessageDialog(null,"Required Quantity not availabe for "+textFieldPharma2Name.getSelectedItem().toString());
							flag = 0;
						}
					}
					
					if(textFieldPharma3Name.getSelectedIndex()!=-1 && textFieldPharma3Quantity.getText().compareTo("")!=0) {	
						if(Integer.parseInt(textFieldPharma3Quantity.getText()) > hm.get(textFieldPharma3Name.getSelectedItem().toString())) {
							JOptionPane.showMessageDialog(null,"Required Quantity not availabe for "+textFieldPharma3Name.getSelectedItem().toString());
							flag = 0;
						}
					}
					
					if(textFieldPharma4Name.getSelectedIndex()!=-1 && textFieldPharma4Quantity.getText().compareTo("")!=0) {	
						if(Integer.parseInt(textFieldPharma4Quantity.getText()) > hm.get(textFieldPharma4Name.getSelectedItem().toString())) {
							JOptionPane.showMessageDialog(null,"Required Quantity not availabe for "+textFieldPharma4Name.getSelectedItem().toString());
							flag = 0;
						}
					}
					
					if(textFieldPharma5Name.getSelectedIndex()!=-1 && textFieldPharma5Quantity.getText().compareTo("")!=0) {	
						if(Integer.parseInt(textFieldPharma5Quantity.getText()) > hm.get(textFieldPharma5Name.getSelectedItem().toString())) {
							JOptionPane.showMessageDialog(null,"Required Quantity not availabe for "+textFieldPharma5Name.getSelectedItem().toString());
							flag = 0;
						}
					}
					
					if(textFieldPharma6Name.getSelectedIndex()!=-1 && textFieldPharma6Quantity.getText().compareTo("")!=0) {	
						if(Integer.parseInt(textFieldPharma6Quantity.getText()) > hm.get(textFieldPharma6Name.getSelectedItem().toString())) {
							JOptionPane.showMessageDialog(null,"Required Quantity not availabe for "+textFieldPharma6Name.getSelectedItem().toString());
							flag = 0;
						}
					}
					
					
					
					
					if(flag==1) {
						
						int prescription_id = 0;
						try {
							if(Patient_ID.compareTo("")==0) {
								Patient_ID = "Other";
							}
							PrescriptionModel pres1 = new PrescriptionModel();
							pres1.setPatientId(Patient_ID);
							pres1.setDiagnosis(Diagnosis);
							pres1.setDoctorId(Staff_ID);
							prescription_id = PrescriptionDao.savePrescription(pres1);
						} catch (SQLException e) {
							e.printStackTrace();
						}
						
						
						int no_of_meds = 0;
						

						
						
						
						if(textFieldPharma1Name.getSelectedIndex()!=-1 && textFieldPharma1Quantity.getText().compareTo("")!=0) {	
							PharmaceuticalsDao.Insert_Into_Alloted_Pharma(prescription_id,textFieldPharma1Name.getSelectedItem().toString(),Integer.parseInt(textFieldPharma1Quantity.getText()));
							no_of_meds++;
							PharmaceuticalsDao.Delete_From_Pharma(textFieldPharma1Name.getSelectedItem().toString(),Integer.parseInt(textFieldPharma1Quantity.getText()));
							arr.add(new Pair(textFieldPharma1Name.getSelectedItem().toString(),Integer.parseInt(textFieldPharma1Quantity.getText())));
							
						}
						
						if(textFieldPharma2Name.getSelectedIndex()!=-1 && textFieldPharma2Quantity.getText().compareTo("")!=0) {	
							PharmaceuticalsDao.Insert_Into_Alloted_Pharma(prescription_id,textFieldPharma2Name.getSelectedItem().toString(),Integer.parseInt(textFieldPharma2Quantity.getText()));
							no_of_meds++;
							PharmaceuticalsDao.Delete_From_Pharma(textFieldPharma2Name.getSelectedItem().toString(),Integer.parseInt(textFieldPharma2Quantity.getText()));
							arr.add(new Pair(textFieldPharma2Name.getSelectedItem().toString(),Integer.parseInt(textFieldPharma2Quantity.getText())));
						}
						
						if(textFieldPharma3Name.getSelectedIndex()!=-1 && textFieldPharma3Quantity.getText().compareTo("")!=0) {	
							PharmaceuticalsDao.Insert_Into_Alloted_Pharma(prescription_id,textFieldPharma3Name.getSelectedItem().toString(),Integer.parseInt(textFieldPharma3Quantity.getText()));
							no_of_meds++;	
							PharmaceuticalsDao.Delete_From_Pharma(textFieldPharma3Name.getSelectedItem().toString(),Integer.parseInt(textFieldPharma3Quantity.getText()));
							arr.add(new Pair(textFieldPharma3Name.getSelectedItem().toString(),Integer.parseInt(textFieldPharma3Quantity.getText())));
						}
						
						if(textFieldPharma4Name.getSelectedIndex()!=-1 && textFieldPharma4Quantity.getText().compareTo("")!=0) {	
							PharmaceuticalsDao.Insert_Into_Alloted_Pharma(prescription_id,textFieldPharma4Name.getSelectedItem().toString(),Integer.parseInt(textFieldPharma4Quantity.getText()));
							no_of_meds++;	
							PharmaceuticalsDao.Delete_From_Pharma(textFieldPharma4Name.getSelectedItem().toString(),Integer.parseInt(textFieldPharma4Quantity.getText()));
							arr.add(new Pair(textFieldPharma4Name.getSelectedItem().toString(),Integer.parseInt(textFieldPharma4Quantity.getText())));
						}
						
						if(textFieldPharma5Name.getSelectedIndex()!=-1 && textFieldPharma5Quantity.getText().compareTo("")!=0) {	
							PharmaceuticalsDao.Insert_Into_Alloted_Pharma(prescription_id,textFieldPharma5Name.getSelectedItem().toString(),Integer.parseInt(textFieldPharma5Quantity.getText()));
							no_of_meds++;	
							PharmaceuticalsDao.Delete_From_Pharma(textFieldPharma5Name.getSelectedItem().toString(),Integer.parseInt(textFieldPharma5Quantity.getText()));
							arr.add(new Pair(textFieldPharma5Name.getSelectedItem().toString(),Integer.parseInt(textFieldPharma5Quantity.getText())));
						}
						
						if(textFieldPharma6Name.getSelectedIndex()!=-1 && textFieldPharma6Quantity.getText().compareTo("")!=0) {	
							PharmaceuticalsDao.Insert_Into_Alloted_Pharma(prescription_id,textFieldPharma6Name.getSelectedItem().toString(),Integer.parseInt(textFieldPharma6Quantity.getText()));
							no_of_meds++;	
							PharmaceuticalsDao.Delete_From_Pharma(textFieldPharma6Name.getSelectedItem().toString(),Integer.parseInt(textFieldPharma6Quantity.getText()));
							arr.add(new Pair(textFieldPharma6Name.getSelectedItem().toString(),Integer.parseInt(textFieldPharma6Quantity.getText())));
						}
						
						HomePage hp1 = new HomePage(Staff_ID);
						pr.dispose();
						hp1.setVisible(true);
						
						try {
							handleSimpleDoc(Staff_ID);
						} catch (Exception e) {
							e.printStackTrace();
						}
				}
				
					
				}
				
				
				
				
			}
		});
		btnSave.setBounds(539, 697, 159, 45);
		pr.getContentPane().add(btnSave);
		
		pr.setSize(1298, 808); 
        pr.getContentPane().setLayout(null);
        
        btnBack = new JButton("BACK");
        btnBack.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		pr.dispose();
				HomePage hp = new HomePage(Staff_ID);
        	}
        });
        btnBack.setBounds(12, 15, 131, 37);
        pr.getContentPane().add(btnBack);
        
        btnLogout = new JButton("LOGOUT");
        btnLogout.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		pr.dispose();
				new Login();
        	}
        });
        btnLogout.setBounds(1149, 11, 124, 31);
        pr.getContentPane().add(btnLogout);
        
        btnSearch = new JButton("VIEW PREVIOUS PRESCRIPTIONS");
        btnSearch.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		pr.dispose();
        		new Search_Prescription(Staff_ID);
				
        	}
        });
        btnSearch.setBounds(938, 55, 335, 31);
        pr.getContentPane().add(btnSearch);
        
        
        
        label = new JLabel("");
        label.setIcon(new ImageIcon("/home/rohan/Desktop/Light-Blue-HD-Backgrounds.jpg"));
        label.setBounds(-172, -76, 1468, 858);
        pr.getContentPane().add(label);
        
        label_1 = new JLabel("");
        label_1.setIcon(new ImageIcon("/home/rohan/Desktop/Logout-512.png"));
        label_1.setBounds(1002, 20, 70, 15);
        pr.getContentPane().add(label_1);
        
        lblLogout = new JLabel("LOGOUT");
        lblLogout.setIcon(new ImageIcon("/home/rohan/Desktop/Logout-512.png"));
        lblLogout.setBounds(716, 55, 139, 97);
        pr.getContentPane().add(lblLogout);
        pr.setVisible(true);
	}
}
