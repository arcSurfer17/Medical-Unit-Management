package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import dao.MedicalStaff;

public class Login {

	private JFrame frame;

	private JTextField txtUsername;
	private JPasswordField passwordField;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel lblMedicalUnitMan;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1060, 531);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("USERNAME");
		lblUsername.setFont(new Font("Dialog", Font.BOLD, 15));
		lblUsername.setBounds(245, 189, 109, 29);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPassword.setBounds(245, 248, 109, 42);
		frame.getContentPane().add(lblPassword);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(372, 189, 196, 29);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(372, 260, 196, 29);
		frame.getContentPane().add(passwordField);
		
		
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.setFont(new Font("Dialog", Font.BOLD, 15));
		btnLogin.setForeground(UIManager.getColor("Button.focus"));
		btnLogin.setBackground(SystemColor.activeCaption);
		btnLogin.setBounds(290, 340, 169, 42);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = txtUsername.getText();
				String password = passwordField.getText();
				try {
					if(MedicalStaff.OAuth(username, password)){
						frame.dispose();
						new HomePage(username);
						
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}});
		
		frame.getContentPane().add(btnLogin);
		
		label = new JLabel("");
		label.setBounds(12, 0, 137, 57);
		label.setIcon(new ImageIcon("/home/rohan/Desktop/logo.jpg"));
		frame.getContentPane().add(label);
		
		label_1 = new JLabel("");
		label_1.setBounds(234, 0, 641, 71);
		label_1.setIcon(new ImageIcon("/home/rohan/Desktop/text_img.jpg"));
		frame.getContentPane().add(label_1);
		
		label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon("/home/rohan/Desktop/Fotolia_133334155_M-e1488185705261.jpg"));
		label_2.setBounds(0, 69, 1086, 436);
		frame.getContentPane().add(label_2);
		frame.setVisible(true);
		
}
	
}
