import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class LogInPage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7552119602288279726L;
	private JPanel contentPane;
	private JTextField textField_1;
	private JPasswordField passwordField;
	private JFrame f;

	int xx, xy;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogInPage frame = new LogInPage();
					frame.setUndecorated(true);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LogInPage() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
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
				LogInPage.this.setLocation(x - xx, y - xy);
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

		Button button = new Button("SignUp");
		button.addActionListener((ActionListener) new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Register register = new Register();
				register.setVisible(true);
				dispose();
			}

		});
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(241, 57, 83));
		button.setBounds(395, 363, 283, 36);
		contentPane.add(button);

		Button logIn = new Button("LogIn");
		logIn.addActionListener((ActionListener) new ActionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				String email = textField_1.getText();
				String password = passwordField.getText();
				String name = "";
				if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
					f = new JFrame();
					JOptionPane.showMessageDialog(f, "please vaild input");
				}
				try {
					Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/swing", "root",
							"");
					String query = "SELECT * FROM swingapp WHERE Email = ? && Password = ?";
					PreparedStatement pstmt = connection.prepareStatement(query);
					pstmt.setString(1, email);
					pstmt.setString(2, password);
					ResultSet rs = pstmt.executeQuery();
					if (rs.next()) {
						name = rs.getString(2);
					}
					if (name.isEmpty() || name == null) {
						f = new JFrame();
						JOptionPane.showMessageDialog(f, "please password and user name input");
					} else {
						ViewData view = new ViewData();
						view.setVisible(true);
						dispose();
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}

		});
		logIn.setForeground(Color.WHITE);
		logIn.setBackground(new Color(241, 57, 83));
		logIn.setBounds(395, 303, 283, 36);
		contentPane.add(logIn);

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

		passwordField = new JPasswordField();
		passwordField.setBounds(395, 229, 283, 36);
		contentPane.add(passwordField);

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

	}

}
