import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;

public class ViewData extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1087964040070299594L;
	private JPanel contentPane;
	private JTextField textField_1;
	private JTable table;
	private JScrollPane sp;
	private JTableHeader tableHeader;
	private JPasswordField passwordField;
	private JFrame f;
	private JScrollPane js;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewData view = new ViewData();
					view.setUndecorated(true);
					view.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		ViewData v = new ViewData();
		v.fetchData();
	}

	public void fetchData() {
		String[] columns = new String[] { "Id", "Madicine", "Price", "Quntity", "Bill No", "TotalBill" };
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/swing", "root", "");
			String query = "SELECT * FROM records ";
			PreparedStatement pstmt = connection.prepareStatement(query);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Object[][] data = new Object[][] {
						{ rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getInt(6) } };
				table = new JTable(data, columns);
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}
		table.setForeground(Color.white);
		table.setBackground(new Color(115, 115, 115));
		table.setBounds(25, 300, 700, 175);
		table.setVisible(true);
		contentPane.add(table);
	}

	public ViewData() {
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
		panel.setBounds(0, 0, 20, 500);
		contentPane.add(panel);
		panel.setLayout(null);

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
