package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import dao.MedicalStaff;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Font;

public class HomePage extends JFrame {

	private JPanel contentPane;
	private JFrame fr;
		
	
	public HomePage(String Staff_ID) {
		fr = new JFrame();
		fr.setTitle("Home Page");
		System.out.println("HomePage : "+Staff_ID);
		
		
		JButton btnPrescription = new JButton("Prescription");
		btnPrescription.setFont(new Font("Dialog", Font.BOLD, 18));
		btnPrescription.setBackground(UIManager.getColor("Button.focus"));
		btnPrescription.setForeground(SystemColor.activeCaption);
		btnPrescription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					if(MedicalStaff.getDesignation(Staff_ID).equals("Doctor")) {
						fr.dispose();
						new Prescription(Staff_ID);
					}
					else {
						JOptionPane.showMessageDialog(null,"Sorry, you are not authorised to suggest Prescription.");

					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnPrescription.setBounds(330, 323, 275, 103);
		
		JButton btnPharmaceuticals = new JButton("Pharmaceuticals");
		btnPharmaceuticals.setFont(new Font("Dialog", Font.BOLD, 19));
		btnPharmaceuticals.setBackground(UIManager.getColor("Button.focus"));
		btnPharmaceuticals.setForeground(SystemColor.activeCaption);
		btnPharmaceuticals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fr.dispose();
				new Pharmaceuticals(Staff_ID);
				
			}
		});
		btnPharmaceuticals.setBounds(638, 322, 275, 103);
		
		fr.getContentPane().add(btnPrescription);
		fr.getContentPane().add(btnPharmaceuticals);
        fr.setSize(1289, 813); 
        fr.getContentPane().setLayout(null);
        
        JButton btnBack = new JButton("BACK");
        btnBack.setFont(new Font("Dialog", Font.BOLD, 18));
        btnBack.setForeground(SystemColor.activeCaption);
        btnBack.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		fr.dispose();
				new Login();
				
        	}
        });
        btnBack.setBounds(991, 29, 242, 55);
        fr.getContentPane().add(btnBack);
        
        JLabel label = new JLabel("");
        label.setBounds(242, 410, 70, 15);
        fr.getContentPane().add(label);
        
        JLabel label_1 = new JLabel("");
        label_1.setIcon(new ImageIcon("/home/rohan/Desktop/Light-Blue-HD-Backgrounds.jpg"));
        label_1.setBounds(-65, 0, 1368, 787);
        fr.getContentPane().add(label_1);
		fr.setVisible(true);
		
	}

}