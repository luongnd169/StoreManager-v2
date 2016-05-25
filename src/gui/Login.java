package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Login {

	private JFrame frame;
	private JTextField txtUsername;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		
		JLabel lblLogin = new JLabel("Ä�Ä‚NG NHáº¬P");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLogin.setBounds(175, 38, 90, 30);
		panel.add(lblLogin);
		
		JLabel lblUsername = new JLabel("TÃªn Ä‘Äƒng nháº­p");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblUsername.setBounds(36, 76, 100, 30);
		panel.add(lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(146, 77, 200, 30);
		panel.add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblPwd = new JLabel("Máº­t kháº©u");	
		lblPwd.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPwd.setBounds(36, 136, 100, 30);
		panel.add(lblPwd);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(146, 136, 200, 30);
		panel.add(textField);
		
		JButton btnLogin = new JButton("Ä�Äƒng nháº­p");
		btnLogin.setBounds(204, 200, 89, 23);
		panel.add(btnLogin);
	}
}
