package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.dao.DaoFactory;
import model.dao.SellerDAO;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CRUDView {

	private JFrame frame;
	private JTextField tfID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CRUDView window = new CRUDView();
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
	public CRUDView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 770, 523);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel IDlabel = new JLabel("ID:");
		IDlabel.setBounds(24, 24, 46, 14);
		frame.getContentPane().add(IDlabel);
		
		tfID = new JTextField();
		tfID.setBounds(45, 21, 86, 20);
		frame.getContentPane().add(tfID);
		tfID.setColumns(10);
		
		JButton btnNewButton = new JButton("Pesquisar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SellerDAO dao = DaoFactory.createSellerDAO();
				JOptionPane.showMessageDialog(null, dao.findByID(Integer.parseInt(tfID.getText())));
			}
		});
		btnNewButton.setBounds(141, 20, 89, 23);
		frame.getContentPane().add(btnNewButton);
	}
}
