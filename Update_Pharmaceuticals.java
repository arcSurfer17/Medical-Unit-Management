package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import dao.PharmaceuticalsDao;
import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Update_Pharmaceuticals extends JFrame {

	private JPanel contentPane;
	private JFrame uph;
	private JTextField textFeildPharma_ID;
	private JTextField textFieldQuantity;


	
	public static int validateIntegerField(String s) {
		for(int i=0;i<s.length();i++) {
			if(s.charAt(i)<'0' || s.charAt(i)>'9')	return 0;
		}
		return 1;
	}
	
	private JTable table;

	
	public Update_Pharmaceuticals(String Staff_ID) {
		uph = new JFrame();
		uph.setTitle("Update Pharmaceutical");
		
		JLabel lblUpdatePharmaceuticals = new JLabel("UPDATE PHARMACEUTICALS");
		lblUpdatePharmaceuticals.setFont(new Font("Dialog", Font.BOLD, 18));
		lblUpdatePharmaceuticals.setBounds(439, 11, 306, 31);
		uph.getContentPane().add(lblUpdatePharmaceuticals);
		
		JLabel lblNewLabel = new JLabel("PHARMA NAME");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel.setBounds(64, 138, 175, 31);
		uph.getContentPane().add(lblNewLabel);
		
		JLabel lblQuantity = new JLabel("QUANTITY");
		lblQuantity.setFont(new Font("Dialog", Font.BOLD, 18));
		lblQuantity.setBounds(64, 239, 156, 31);
		uph.getContentPane().add(lblQuantity);
		
		JComboBox comboBoxName = new JComboBox();
		comboBoxName.setBounds(256, 142, 206, 25);
		uph.getContentPane().add(comboBoxName);
		
		Object[] elements = new Object[1000];		
		try {
			ResultSet rs = PharmaceuticalsDao.getAllPharmaName();
			int i = 0;
			while(rs.next()) {
				String name = rs.getString("Name");
				elements[i++] = name;
			}
		} catch (SQLException e3) {
			e3.printStackTrace();
		}

		AutoCompleteSupport.install(comboBoxName, GlazedLists.eventList(Arrays.asList(elements)));
		
		textFieldQuantity = new JTextField();
		textFieldQuantity.setColumns(10);
		textFieldQuantity.setBounds(256, 239, 206, 31);
		uph.getContentPane().add(textFieldQuantity);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.setFont(new Font("Dialog", Font.BOLD, 18));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					if(comboBoxName.getSelectedIndex()==-1 || textFieldQuantity.getText().compareTo("")==0 || validateIntegerField(textFieldQuantity.getText()) ==0) {
						JOptionPane.showMessageDialog(null,"Please Enter valid details.");
					}
					else {
						PharmaceuticalsDao.updatePharma(Integer.parseInt(textFieldQuantity.getText()), comboBoxName.getSelectedItem().toString());
						JOptionPane.showMessageDialog(null,"Pharma Details Updatated");
						uph.dispose();
						new Update_Pharmaceuticals(Staff_ID);
					}	
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null,e1);
				}
				
			}
		});
		btnUpdate.setBounds(150, 325, 163, 42);
		uph.getContentPane().add(btnUpdate);
		
		JButton btnBack = new JButton("BACK");
		btnBack.setFont(new Font("Dialog", Font.BOLD, 18));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				uph.dispose();
				new Pharmaceuticals(Staff_ID);
				
			}
		});
		btnBack.setBounds(12, 14, 128, 25);
		uph.getContentPane().add(btnBack);
		
		uph.setSize(1284, 812); 
        uph.getContentPane().setLayout(null);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(506, 145, 748, 426);
        uph.getContentPane().add(scrollPane);
        
        table = new JTable();
        scrollPane.setViewportView(table);
        uph.setVisible(true);
        
        try {
        	ResultSet rs=PharmaceuticalsDao.getAllPharma();
    		table.setModel(DbUtils.resultSetToTableModel(rs));
    		
    		JButton btnLogout = new JButton("LOGOUT");
    		btnLogout.setFont(new Font("Dialog", Font.BOLD, 18));
    		btnLogout.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				uph.dispose();
    				new Login();
    				
    			}
    		});
    		btnLogout.setBounds(1142, 14, 128, 25);
    		uph.getContentPane().add(btnLogout);
    		
    		JLabel label = new JLabel("");
    		label.setIcon(new ImageIcon("/home/rohan/Desktop/Light-Blue-HD-Backgrounds.jpg"));
    		label.setBounds(0, 0, 1282, 786);
    		uph.getContentPane().add(label);
    		
    		
    		
    		
    		
    		
        }catch(Exception e2) {
        	JOptionPane.showMessageDialog(null,e2);
        }
        
        
        uph.setVisible(true);
	}
}
