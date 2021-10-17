package main.webapp;

import java.awt.EventQueue;


import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.*;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class addDetailsStudent {

	private JFrame frame;
	private JTextField txtname;
	private JTextField txtage;
	private JTextField txtemail;
	private JTextField txtmobile;
	private JTextField txtaddress;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addDetailsStudent window = new addDetailsStudent();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public addDetailsStudent() {
		initialize();
		Connect();
	}

	Connection con;
	PreparedStatement stmt;
	ResultSet rs = null;
	private JTextField txtsearch;
	/**
	 * Initialize the contents of the frame.
	 */
	 public void Connect() {
		 try {
			Class.forName("com.mysql.jdbc.Driver"); //loaded mysql driver and registration done
			
			con = DriverManager.getConnection("jdbc:mysql://localhost/database_1","root","root"); //created connection to database
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	 }
	 
	
	
	
	
	
	
	
	
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.PINK);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("STUDENTS INFORMATION");
		lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblNewLabel.setBounds(131, 11, 206, 26);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("NAME");
		lblNewLabel_1.setBounds(88, 48, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("AGE");
		lblNewLabel_1_1.setBounds(88, 73, 46, 14);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("EMAIL");
		lblNewLabel_1_1_1.setBounds(88, 99, 46, 14);
		frame.getContentPane().add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("MOBILE");
		lblNewLabel_1_1_1_1.setBounds(88, 124, 46, 14);
		frame.getContentPane().add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("ADDRESS");
		lblNewLabel_1_1_1_1_1.setBounds(88, 149, 46, 14);
		frame.getContentPane().add(lblNewLabel_1_1_1_1_1);
		
		txtname = new JTextField();
		txtname.setBounds(141, 45, 120, 20);
		frame.getContentPane().add(txtname);
		txtname.setColumns(10);
		
		txtage = new JTextField();
		txtage.setColumns(10);
		txtage.setBounds(141, 70, 120, 20);
		frame.getContentPane().add(txtage);
		
		txtemail = new JTextField();
		txtemail.setColumns(10);
		txtemail.setBounds(141, 96, 120, 20);
		frame.getContentPane().add(txtemail);
		
		txtmobile = new JTextField();
		txtmobile.setColumns(10);
		txtmobile.setBounds(141, 121, 120, 20);
		frame.getContentPane().add(txtmobile);
		
		txtaddress = new JTextField();
		txtaddress.setColumns(10);
		txtaddress.setBounds(141, 146, 120, 20);
		frame.getContentPane().add(txtaddress);
		
		JButton btnadd = new JButton("ADD");
		btnadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name = txtname.getText();
				int age = Integer.parseInt(txtage.getText());
				String email = txtemail.getText();
				String mobile = txtmobile.getText();
				String address = txtaddress.getText();
				
				
				//create prepared statement
				
				try {
					stmt = con.prepareStatement("insert into student_master(name,age,email,mobile,address) values(?,?,?,?,?)");
					stmt.setString(1, name);
					stmt.setInt(2, age);
					stmt.setString(3, email);
					stmt.setString(4, mobile);
					stmt.setString(5, address);
					
					int k = stmt.executeUpdate();
					
					
				if(k==1) {
					
					JOptionPane.showMessageDialog(new JFrame(), "record added");
					
					txtname.setText("");
					txtage.setText("");
					txtemail.setText("");
					txtmobile.setText("");
					txtaddress.setText("");
					txtsearch.setText("");
					
					txtname.requestFocus();
				}
				
				else
				{
					JOptionPane.showMessageDialog(new JFrame(), "record add failed");
				}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			
				
				
			}
		});
		btnadd.setBounds(88, 173, 89, 23);
		frame.getContentPane().add(btnadd);
		
		txtsearch = new JTextField();
		txtsearch.setToolTipText("search using name");
		txtsearch.setColumns(10);
		txtsearch.setBounds(114, 207, 120, 20);
		frame.getContentPane().add(txtsearch);
		
		JButton btnNewButton = new JButton("SEARCH");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String enteredName = txtsearch.getText();
				char []name = enteredName.toCharArray();
				
				if(name.length == 0)
				{
					
					JOptionPane.showMessageDialog(new JFrame(), "enter a value");
					return;
					
				}
				
				
				try {
					stmt = con.prepareStatement("select * from student_master where name = ?");
					stmt.setString(1, enteredName);
					rs=stmt.executeQuery();
					
					while(rs.next()==true) {
					txtname.setText(rs.getString(2));
					txtage.setText(rs.getString(3));
					txtemail.setText(rs.getString(4));
					txtmobile.setText(rs.getString(5));
					txtaddress.setText(rs.getString(6));
					}
					JOptionPane.showMessageDialog(new JFrame(), "search complete");
					
					txtname.setText("");
					txtage.setText("");
					txtemail.setText("");
					txtmobile.setText("");
					txtaddress.setText("");
					txtsearch.setText("");
					
					
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
			}
		});
		btnNewButton.setBounds(176, 173, 89, 23);
		frame.getContentPane().add(btnNewButton);
		


	}

}
