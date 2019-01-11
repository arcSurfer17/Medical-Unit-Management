package view;
import java.awt.BorderLayout;
import java.sql.*;
import javax.swing.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.PharmaceuticalsDao;
import models.PharmaceuticalsModel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Add_Pharmaceuticals extends JFrame {

	private JPanel contentPane;
	private JFrame aph;
	private JTextField textFieldName;
	private JTextField textFieldCategory;
	private JTextField textFieldQuantity;
	private JTextField textFieldThreshold;
	private JButton btnAdd;
	private JButton btnBack;


	
	public static int validateIntegerField(String s) {
		for(int i=0;i<s.length();i++) {
			if(s.charAt(i)<'0' || s.charAt(i)>'9')	return 0;
		}
		return 1;
	}
	private JButton btnLogout;
	private JLabel label;

	
	public Add_Pharmaceuticals(String Staff_ID) {
		aph = new JFrame();
		aph.setTitle("Add Pharmaceutical");
		
		JLabel lblNewLabel = new JLabel("ADD PHARMACEUTICALS");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel.setBounds(435, 13, 293, 29);
		aph.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("NAME");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel_1.setBounds(148, 118, 293, 44);
		aph.getContentPane().add(lblNewLabel_1);
		
		JLabel lblCategory = new JLabel("CATEGORY");
		lblCategory.setFont(new Font("Dialog", Font.BOLD, 18));
		lblCategory.setBounds(148, 213, 293, 44);
		aph.getContentPane().add(lblCategory);
		
		JLabel lblQuantity = new JLabel("QUANTITY");
		lblQuantity.setFont(new Font("Dialog", Font.BOLD, 18));
		lblQuantity.setBounds(148, 313, 293, 44);
		aph.getContentPane().add(lblQuantity);
		
		JLabel lblThreshold = new JLabel("THRESHOLD");
		lblThreshold.setFont(new Font("Dialog", Font.BOLD, 18));
		lblThreshold.setBounds(148, 404, 293, 44);
		aph.getContentPane().add(lblThreshold);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(622, 118, 293, 44);
		aph.getContentPane().add(textFieldName);
		textFieldName.setColumns(10);
		
		textFieldCategory = new JTextField();
		textFieldCategory.setColumns(10);
		textFieldCategory.setBounds(622, 213, 293, 44);
		aph.getContentPane().add(textFieldCategory);
		
		textFieldQuantity = new JTextField();
		textFieldQuantity.setColumns(10);
		textFieldQuantity.setBounds(622, 313, 293, 44);
		aph.getContentPane().add(textFieldQuantity);
		
		textFieldThreshold = new JTextField();
		textFieldThreshold.setColumns(10);
		textFieldThreshold.setBounds(622, 404, 293, 44);
		aph.getContentPane().add(textFieldThreshold);
		
		btnAdd = new JButton("ADD");
		btnAdd.setFont(new Font("Dialog", Font.BOLD, 18));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(textFieldName.getText().compareTo("")==0 || textFieldCategory.getText().compareTo("")==0 || textFieldQuantity.getText().compareTo("")==0 || textFieldThreshold.getText().compareTo("")==0 || validateIntegerField(textFieldQuantity.getText()) ==0 || validateIntegerField(textFieldThreshold.getText()) ==0 ) {
						JOptionPane.showMessageDialog(null,"Please Enter valid details.");
					}
					else {
						PharmaceuticalsModel phm = new PharmaceuticalsModel();
						phm.setPharmaName(textFieldName.getText());
						phm.setCategory(textFieldCategory.getText());
						phm.setQuantity(Integer.parseInt(textFieldQuantity.getText()));
						phm.setThreshold(Integer.parseInt(textFieldThreshold.getText()));
						PharmaceuticalsDao.addPharmaceutical( phm);
						JOptionPane.showMessageDialog(null,"Pharma Details Added");	
						aph.dispose();
						new Add_Pharmaceuticals(Staff_ID);
					}
					
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null,e1);
				}
			}
		});
		btnAdd.setBounds(371, 515, 198, 51);
		aph.getContentPane().add(btnAdd);
		
		btnBack = new JButton("BACK");
		btnBack.setFont(new Font("Dialog", Font.BOLD, 18));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aph.dispose();
				new Pharmaceuticals(Staff_ID);
				
			}
		});
		btnBack.setBounds(12, 13, 146, 28);
		aph.getContentPane().add(btnBack);
		
		aph.setSize(1295, 814); 
        aph.getContentPane().setLayout(null);
        
        btnLogout = new JButton("LOGOUT");
        btnLogout.setFont(new Font("Dialog", Font.BOLD, 18));
        btnLogout.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		aph.dispose();
				new Login();
				
        	}
        });
        btnLogout.setBounds(1143, 13, 138, 27);
        aph.getContentPane().add(btnLogout);
        
        label = new JLabel("");
        label.setIcon(new ImageIcon("/home/rohan/Desktop/Light-Blue-HD-Backgrounds.jpg"));
        label.setBounds(0, 0, 1293, 776);
        aph.getContentPane().add(label);
        aph.setVisible(true);
	}

}
