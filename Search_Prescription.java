package view;

import java.awt.BorderLayout;
import java.sql.*;
import javax.swing.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.PrescriptionDao;
import dao.mysql_connection;
import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Search_Prescription extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldID;
	private JTable table;
	private JFrame sp;


	/**
	 * Create the frame.
	 */
	Connection conn = null;
	public Search_Prescription(String Staff_ID) {
		
		sp = new JFrame();
		sp.setTitle("Search Prescriptions");
		
		
		textFieldID = new JTextField();
		textFieldID.setBounds(324, 34, 197, 37);
		sp.getContentPane().add(textFieldID);
		textFieldID.setColumns(10);
		
		JButton btnSearch = new JButton("SEARCH");
		btnSearch.setFont(new Font("Dialog", Font.BOLD, 18));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					ResultSet rs = PrescriptionDao.searchById(textFieldID.getText());
					table.setModel(DbUtils.resultSetToTableModel(rs));
					rs = PrescriptionDao.searchById(textFieldID.getText());
					int no_of_records = 0;
					while(rs.next()) {
						no_of_records++;
					}
					System.out.println(no_of_records);
					if(no_of_records==0) {
						JOptionPane.showMessageDialog(null,"No Previous History");
					}
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnSearch.setBounds(321, 81, 136, 31);
		sp.getContentPane().add(btnSearch);
		
		JButton btnLogout = new JButton("LOGOUT");
		btnLogout.setFont(new Font("Dialog", Font.BOLD, 18));
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sp.dispose();
				new Login();
				
			}
		});
		btnLogout.setBounds(1126, 36, 136, 31);
		sp.getContentPane().add(btnLogout);
		
		JButton btnBack = new JButton("BACK");
		btnBack.setFont(new Font("Dialog", Font.BOLD, 18));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sp.dispose();
				Prescription pr = new Prescription(Staff_ID);
				pr.setVisible(true);
				
			}
		});
		btnBack.setBounds(12, 36, 136, 31);
		sp.getContentPane().add(btnBack);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 124, 1208, 492);
		sp.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		sp.setSize(1277, 769); 
        sp.getContentPane().setLayout(null);
        

        
        JLabel lblNewLabel = new JLabel("SEARCH BY ID");
        lblNewLabel.setBounds(226, 34, 93, 37);
        sp.getContentPane().add(lblNewLabel);
        
        JButton btnViewAll = new JButton("VIEW ALL");
        btnViewAll.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		PreparedStatement pst;
				try {
					ResultSet rs = PrescriptionDao.showAllPrescription();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
        	}
        });
        btnViewAll.setBounds(662, 37, 160, 31);
        sp.getContentPane().add(btnViewAll);
        sp.setVisible(true);
        
        
        JLabel label = new JLabel("");
        label.setIcon(new ImageIcon("/home/rohan/Desktop/Light-Blue-HD-Backgrounds.jpg"));
        label.setBounds(-1, 0, 1276, 743);
        sp.getContentPane().add(label);
        sp.setVisible(true);
	}
}
