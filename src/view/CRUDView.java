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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import java.awt.Font;

public class CRUDView {

	private JFrame frame;
	private JTextField tfID;
	private JTextField tfIdDepartment;
	private JTextField tfName;
	private JTextField tfEmail;
	private JTextField tfBirthDate;
	private JTextField tfBaseSalary;
	private JTextField tfIdDepartmentAdd;
	private JTextField tfNameDepartment;

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
		frame.setBounds(100, 100, 660, 367);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 644, 328);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Consultar Vendedores", null, panel, null);
		panel.setLayout(null);
		JPanel panel2 = new JPanel();
		tabbedPane.addTab("Adicionar Vendedores", null, panel2, null);
		panel2.setLayout(null);
		
		tfName = new JTextField();
		tfName.setBounds(66, 11, 257, 20);
		panel2.add(tfName);
		tfName.setColumns(10);
		
		JLabel lblName = new JLabel("Nome:");
		lblName.setBounds(10, 14, 46, 14);
		panel2.add(lblName);
		
		tfEmail = new JTextField();
		tfEmail.setBounds(66, 42, 257, 20);
		panel2.add(tfEmail);
		tfEmail.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(10, 45, 46, 14);
		panel2.add(lblEmail);
		
		JLabel lblBirthDate = new JLabel("Data de Nascimento:");
		lblBirthDate.setBounds(10, 76, 127, 14);
		panel2.add(lblBirthDate);
		
		tfBirthDate = new JTextField();
		tfBirthDate.setColumns(10);
		tfBirthDate.setBounds(147, 73, 176, 20);
		panel2.add(tfBirthDate);
		
		JLabel lblBaseSalary = new JLabel("Sal\u00E1rio Base:");
		lblBaseSalary.setBounds(10, 104, 115, 14);
		panel2.add(lblBaseSalary);
		
		tfBaseSalary = new JTextField();
		tfBaseSalary.setBounds(101, 101, 222, 20);
		panel2.add(tfBaseSalary);
		tfBaseSalary.setColumns(10);
		
		tfIdDepartmentAdd = new JTextField();
		tfIdDepartmentAdd.setBounds(132, 129, 191, 20);
		panel2.add(tfIdDepartmentAdd);
		tfIdDepartmentAdd.setColumns(10);
		
		JLabel lblIdDepartment = new JLabel("ID do departemento:");
		lblIdDepartment.setBounds(10, 132, 124, 14);
		panel2.add(lblIdDepartment);
		
		tfNameDepartment = new JTextField();
		tfNameDepartment.setBounds(160, 160, 163, 20);
		panel2.add(tfNameDepartment);
		tfNameDepartment.setColumns(10);
		
		JLabel lblNameDepartment = new JLabel("Nome do departamento:");
		lblNameDepartment.setBounds(10, 163, 145, 14);
		panel2.add(lblNameDepartment);
		
		JButton btnNewButton = new JButton("Inserir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Department dep = new Department(Integer.parseInt(tfIdDepartmentAdd.getText()), tfNameDepartment.getText());
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				SellerDAO dao = DaoFactory.createSellerDAO();
				try {
					Seller seller = new Seller(null, tfName.getText(), tfEmail.getText(), sdf.parse(tfBirthDate.getText()), Double.parseDouble(tfBaseSalary.getText()), dep);
					dao.insert(seller);
				} catch (NumberFormatException | ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(101, 213, 152, 50);
		panel2.add(btnNewButton);
		panel.setLayout(null);
		
		JButton btnFindAll = new JButton("Mostrar todos");
		btnFindAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SellerDAO dao = DaoFactory.createSellerDAO();
				String sellers = "Sellers: ";
				
				if(!dao.findAll().isEmpty()) {
					for(Seller s : dao.findAll()) {
						sellers += "\n-> " + s;
					}
					
					JOptionPane.showMessageDialog(null, sellers);
				} else {
					JOptionPane.showMessageDialog(null, "Nenhum vendedor encontrado!");
				}
			}
		});
		btnFindAll.setBounds(24, 91, 119, 48);
		panel.add(btnFindAll);
		
		JButton btnSearch = new JButton("Pesquisar");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SellerDAO dao = DaoFactory.createSellerDAO();
				Seller seller = dao.findByID(Integer.parseInt(tfID.getText()));
				if(seller != null) {
					JOptionPane.showMessageDialog(null, seller);
				} else {
					JOptionPane.showMessageDialog(null, "Nenhum vendedor encontrado!");
				}
			}
		});
		btnSearch.setBounds(162, 11, 105, 23);
		panel.add(btnSearch);
		
		tfID = new JTextField();
		tfID.setColumns(10);
		tfID.setBounds(49, 12, 98, 20);
		panel.add(tfID);
		
		JLabel IDlabel = new JLabel("ID:");
		IDlabel.setBounds(24, 15, 27, 14);
		panel.add(IDlabel);
		
		JLabel departmentIDLabel = new JLabel("ID do departamento:");
		departmentIDLabel.setBounds(24, 54, 119, 14);
		panel.add(departmentIDLabel);
		
		tfIdDepartment = new JTextField();
		tfIdDepartment.setColumns(10);
		tfIdDepartment.setBounds(152, 51, 98, 20);
		panel.add(tfIdDepartment);
		
		JButton btnSearch2 = new JButton("Pesquisar");
		btnSearch2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SellerDAO dao = DaoFactory.createSellerDAO();
				Department dep = new Department(Integer.parseInt(tfIdDepartment.getText()), null);
				String sellers = "Sellers: ";
				
				if(!dao.findByDepartment(dep).isEmpty()) {
					for(Seller s : dao.findByDepartment(dep)) {
						sellers += "\n-> " + s;
					}
					
					JOptionPane.showMessageDialog(null, sellers);
				} else {
					JOptionPane.showMessageDialog(null, "Nenhum vendedor encontrado!");
				}
			}
		});
		btnSearch2.setBounds(260, 50, 105, 23);
		panel.add(btnSearch2);
	}
}
