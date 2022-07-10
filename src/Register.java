import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Button;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Register extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JFrame f;

	int xx, xy;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
					frame.setUndecorated(true);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// going to borrow code from a gist to move frame.

	/**
	 * Create the frame.
	 */
	public Register() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 740, 525);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 346, 500);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("KeepToo");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setForeground(new Color(240, 248, 255));
		lblNewLabel.setBounds(139, 305, 84, 27);
		panel.add(lblNewLabel);

		JLabel label = new JLabel("");

		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				xx = e.getX();
				xy = e.getY();
			}
		});
		label.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {

				int x = arg0.getXOnScreen();
				int y = arg0.getYOnScreen();
				Register.this.setLocation(x - xx, y - xy);
			}
		});
		label.setBounds(-38, 0, 420, 275);
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setIcon(new ImageIcon(Register.class.getResource("/images/bg.jpg")));
		panel.add(label);

		JLabel lblWeGotYou = new JLabel("....We got you....");
		lblWeGotYou.setHorizontalAlignment(SwingConstants.CENTER);
		lblWeGotYou.setForeground(new Color(240, 248, 255));
		lblWeGotYou.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblWeGotYou.setBounds(111, 343, 141, 27);
		panel.add(lblWeGotYou);

		Button logIn = new Button("LogIn");
		logIn.addActionListener((ActionListener) new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LogInPage logInPage = new LogInPage();
				logInPage.setVisible(true);
				dispose();

			}

		});
		logIn.setForeground(Color.WHITE);
		logIn.setBackground(new Color(241, 57, 83));
		logIn.setBounds(395, 415, 283, 36);
		contentPane.add(logIn);

		textField = new JTextField();
		textField.setBounds(395, 83, 283, 36);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblUsername = new JLabel("USERNAME");
		lblUsername.setBounds(395, 58, 114, 14);
		contentPane.add(lblUsername);

		JLabel lblEmail = new JLabel("EMAIL");
		lblEmail.setBounds(395, 132, 54, 14);
		contentPane.add(lblEmail);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(395, 157, 283, 36);
		contentPane.add(textField_1);

		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setBounds(395, 204, 96, 14);
		contentPane.add(lblPassword);

		JLabel lblRepeatPassword = new JLabel("REPEAT PASSWORD");
		lblRepeatPassword.setBounds(395, 275, 133, 14);
		contentPane.add(lblRepeatPassword);

		passwordField = new JPasswordField();
		passwordField.setBounds(395, 229, 283, 36);
		contentPane.add(passwordField);

		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(395, 293, 283, 36);
		contentPane.add(passwordField_1);

		JLabel lbl_close = new JLabel("X");
		lbl_close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				System.exit(0);

			}
		});
		lbl_close.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_close.setForeground(new Color(241, 57, 83));
		lbl_close.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_close.setBounds(691, 0, 37, 27);
		contentPane.add(lbl_close);

		Button button = new Button("SignUp");
		button.addActionListener((ActionListener) new ActionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				String userName = textField.getText();
				String email = textField_1.getText();
				String password = passwordField.getText();
				String repassword = passwordField_1.getText();

				if(userName == null ||userName.isEmpty()|| email == null||email.isEmpty() || password == null || password.isEmpty()|| repassword == null) {
					f = new JFrame();
					JOptionPane.showMessageDialog(f, "please vaild input");
				}
				if (!password.equals(repassword)) {
					f = new JFrame();
					JOptionPane.showMessageDialog(f, "please check password");
				} else {
					try {
						Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/swing", "root",
								"");
						String query = "INSERT INTO swingapp values('" + Math.random() + "','" + userName + "','" + email + "','" + password + "')";
						Statement sta = connection.createStatement();
						sta.executeUpdate(query);
	                    connection.close();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}

		});
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(241, 57, 83));
		button.setBounds(395, 363, 283, 36);
		contentPane.add(button);

	}
}
