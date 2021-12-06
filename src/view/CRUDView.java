package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.dao.DaoFactory;
import model.dao.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CRUDView {

	private JFrame frame;
	private JTextField tfID;
	private JLabel departmentIDLabel;
	private JTextField tfIdDepartment;
	private JButton btnSearch2;
	private JTextField tfNameDepartment;
	private JLabel nameDepatmentLbl;

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
		
		JButton btnSearch = new JButton("Pesquisar");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SellerDAO dao = DaoFactory.createSellerDAO();
				JOptionPane.showMessageDialog(null, dao.findByID(Integer.parseInt(tfID.getText())));
			}
		});
		btnSearch.setBounds(141, 20, 89, 23);
		frame.getContentPane().add(btnSearch);
		
		departmentIDLabel = new JLabel("ID do departamento:");
		departmentIDLabel.setBounds(360, 24, 119, 14);
		frame.getContentPane().add(departmentIDLabel);
		
		tfIdDepartment = new JTextField();
		tfIdDepartment.setBounds(493, 21, 86, 20);
		frame.getContentPane().add(tfIdDepartment);
		tfIdDepartment.setColumns(10);
		
		btnSearch2 = new JButton("Pesquisar");
		btnSearch2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SellerDAO dao = DaoFactory.createSellerDAO();
				Department dep = new Department(Integer.parseInt(tfIdDepartment.getText()), tfNameDepartment.getText());
				String sellers = "Sellers: ";
				
				for(Seller s : dao.findByDepartment(dep)) {
					sellers += "\n-> " + s.toString();
				}
				
				JOptionPane.showMessageDialog(null, sellers);
			}
		});
		btnSearch2.setBounds(589, 34, 89, 23);
		frame.getContentPane().add(btnSearch2);
		
		tfNameDepartment = new JTextField();
		tfNameDepartment.setBounds(493, 52, 86, 20);
		frame.getContentPane().add(tfNameDepartment);
		tfNameDepartment.setColumns(10);
		
		nameDepatmentLbl = new JLabel("Nome do departamento:");
		nameDepatmentLbl.setBounds(357, 55, 126, 14);
		frame.getContentPane().add(nameDepatmentLbl);
	}
}
