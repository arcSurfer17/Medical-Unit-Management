package view;

import java.awt.BorderLayout;
import java.sql.*;
import javax.swing.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.PharmaceuticalsDao;
import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class Pharmaceuticals extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JFrame ph;

	public Pharmaceuticals(String Staff_ID) {
		ph = new JFrame();
		ph.setTitle("Pharmaceutical");
	
		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ph.dispose();
				new Add_Pharmaceuticals(Staff_ID);
			}
		});
		btnAdd.setBounds(468, 26, 117, 25);
		ph.getContentPane().add(btnAdd);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ph.dispose();
				new Update_Pharmaceuticals(Staff_ID);
				
			}
		});
		btnUpdate.setBounds(669, 26, 117, 25);
		ph.getContentPane().add(btnUpdate);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ph.dispose();
				new HomePage(Staff_ID);
				
			}
		});
		btnBack.setBounds(12, 26, 117, 25);
		ph.getContentPane().add(btnBack);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 134, 1176, 414);
		ph.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		try {
			ResultSet rs = PharmaceuticalsDao.getPharmaThreshold();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch (Exception e1){
			e1.printStackTrace();

		}
		ph.setSize(1287, 810); 
        ph.getContentPane().setLayout(null);
        
        JButton btnLogout = new JButton("LOGOUT");
        btnLogout.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ph.dispose();
				new Login();
        	}
        });
        btnLogout.setBounds(1138, 26, 117, 25);
        ph.getContentPane().add(btnLogout);
        
        
        JLabel lblPharmaceuticalsLessThan = new JLabel("Pharmaceuticals Less than Threshold");
        lblPharmaceuticalsLessThan.setBounds(458, 111, 279, 11);
        ph.getContentPane().add(lblPharmaceuticalsLessThan);
        ph.setVisible(true);
		
        JLabel label = new JLabel("");
        label.setIcon(new ImageIcon("/home/rohan/Desktop/Light-Blue-HD-Backgrounds.jpg"));
        label.setBounds(0, 0, 1305, 784);
        ph.getContentPane().add(label);
        
	}
}
