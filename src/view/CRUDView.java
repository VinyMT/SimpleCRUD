package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.dao.DaoFactory;
import model.dao.DepartmentDAO;
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
	private JTextField tfNameDepartment1;
	private JTextField tfId2;
	private JTextField tfName2;
	private JTextField tfEmail2;
	private JTextField tfBirthDate2;
	private JTextField tfBaseSalary2;
	private JTextField tfIdDepartment3;
	private JTextField tfNameDepartment2;
	private JTextField tfId3;
	private JTextField tfNameDepartment3;
	private JTextField tfNameDepartment4;
	private JTextField tfIdDepartment4;
	private JTextField tfIdDepartment5;
	private JTextField tfIdDepartment6;

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
		frame.setBounds(100, 100, 682, 514);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 666, 475);
		frame.getContentPane().add(tabbedPane);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Consultar Vendedores", null, panel, null);
		panel.setLayout(null);
		JPanel panel2 = new JPanel();
		tabbedPane.addTab("Adicionar Vendedores", null, panel2, null);
		panel2.setLayout(null);
		JPanel panel3 = new JPanel();
		tabbedPane.addTab("Atualizar Vendedores", null, panel3, null);
		panel3.setLayout(null);
		JPanel panel4 = new JPanel();
		tabbedPane.addTab("Deletar Vendedores", null, panel4, null);
		panel4.setLayout(null);
		JPanel panel5 = new JPanel();
		tabbedPane.addTab("Adicionar Departamentos", null, panel5, null);
		panel5.setLayout(null);
		JPanel panel6 = new JPanel();
		tabbedPane.addTab("Atualizar Departamentos", null, panel6, null);
		panel6.setLayout(null);
		JPanel panel7 = new JPanel();
		tabbedPane.addTab("Consultar Departamentos", null, panel7, null);
		panel7.setLayout(null);
		JPanel panel8 = new JPanel();
		tabbedPane.addTab("Deletar Departamentos", null, panel8, null);
		panel8.setLayout(null);
		
		JLabel lblIdDepartment4 = new JLabel("ID:");
		lblIdDepartment4.setBounds(20, 22, 46, 14);
		panel8.add(lblIdDepartment4);
		
		tfIdDepartment6 = new JTextField();
		tfIdDepartment6.setBounds(54, 19, 86, 20);
		panel8.add(tfIdDepartment6);
		tfIdDepartment6.setColumns(10);
		
		JButton btnDeleteDepartment = new JButton("Deletar");
		btnDeleteDepartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DepartmentDAO dao = DaoFactory.createDepartmentDAO();
				if(!tfIdDepartment6.getText().isEmpty()) {
					try {
						dao.deleteByID(Integer.parseInt(tfIdDepartment6.getText()));
						JOptionPane.showMessageDialog(null, "Departamento deletado com sucesso!");
					} catch(IllegalArgumentException ex) {
						JOptionPane.showMessageDialog(null, "Erro inesperado, verifique os campos e tente novamente.");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Erro inesperado, verifique os campos e tente novamente.");
				}
			}
		});
		btnDeleteDepartment.setBounds(171, 18, 89, 23);
		panel8.add(btnDeleteDepartment);
		
		JLabel lblIdDepartment3 = new JLabel("ID:");
		lblIdDepartment3.setBounds(10, 11, 46, 14);
		panel7.add(lblIdDepartment3);
		
		tfIdDepartment5 = new JTextField();
		tfIdDepartment5.setBounds(45, 8, 86, 20);
		panel7.add(tfIdDepartment5);
		tfIdDepartment5.setColumns(10);
		
		JButton btnSearchDepartment = new JButton("Pesquisar");
		btnSearchDepartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DepartmentDAO dao = DaoFactory.createDepartmentDAO();
				try {
					Department dep = dao.findByID(Integer.parseInt(tfIdDepartment5.getText()));
					JOptionPane.showMessageDialog(null, dep);
				} catch(IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(null, "Erro de input! Verifique o ID do departamento.");
				}
			}
		});
		btnSearchDepartment.setBounds(158, 7, 96, 23);
		panel7.add(btnSearchDepartment);
		
		JButton btnSearchDepartment2 = new JButton("Mostrar Todos");
		btnSearchDepartment2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DepartmentDAO dao = DaoFactory.createDepartmentDAO();
				String departments = "Departamentos: ";
				
				for(Department dep : dao.findAll()) {
					departments += "\n-> " + dep;
				}
				JOptionPane.showMessageDialog(null, departments);
			}
		});
		btnSearchDepartment2.setBounds(10, 48, 124, 48);
		panel7.add(btnSearchDepartment2);
		
		JLabel lblNameDepartment3 = new JLabel("Nome:");
		lblNameDepartment3.setBounds(10, 59, 46, 14);
		panel6.add(lblNameDepartment3);
		
		tfNameDepartment4 = new JTextField();
		tfNameDepartment4.setBounds(61, 56, 140, 20);
		panel6.add(tfNameDepartment4);
		tfNameDepartment4.setColumns(10);
		
		JLabel lblIdDepartment2 = new JLabel("ID:");
		lblIdDepartment2.setBounds(10, 23, 46, 14);
		panel6.add(lblIdDepartment2);
		
		tfIdDepartment4 = new JTextField();
		tfIdDepartment4.setBounds(61, 20, 140, 20);
		panel6.add(tfIdDepartment4);
		tfIdDepartment4.setColumns(10);
		
		JButton btnUpdateDepartment = new JButton("Atualizar");
		btnUpdateDepartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DepartmentDAO dao = DaoFactory.createDepartmentDAO();
				try {
					Department d = dao.findByID(Integer.parseInt(tfIdDepartment4.getText()));
					if(!tfNameDepartment4.getText().isEmpty()) {
						d.setName(tfNameDepartment4.getText());
						dao.update(d);
						JOptionPane.showMessageDialog(null, "Departamento atualizado com sucesso!");
					} else {
						JOptionPane.showMessageDialog(null, "Preencha o novo nome do departamento!");
					}
				} catch(IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(null, "Erro inesperado, verifique os campos e tente novamente.");
				}
			}
		});
		btnUpdateDepartment.setBounds(229, 34, 89, 23);
		panel6.add(btnUpdateDepartment);
		panel5.setLayout(null);
		
		JLabel lblNameDepartment2 = new JLabel("Nome:");
		lblNameDepartment2.setBounds(10, 28, 46, 14);
		panel5.add(lblNameDepartment2);
		
		tfNameDepartment3 = new JTextField();
		tfNameDepartment3.setBounds(56, 25, 130, 20);
		panel5.add(tfNameDepartment3);
		tfNameDepartment3.setColumns(10);
		
		JButton btnAddDepartment = new JButton("Adicionar");
		btnAddDepartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!tfNameDepartment3.getText().isEmpty()) {
					DepartmentDAO dao = DaoFactory.createDepartmentDAO();
					Department d = new Department(null, tfNameDepartment3.getText());
					dao.insert(d);
					JOptionPane.showMessageDialog(null, "Departamento adicionado com sucesso!");
				} else {
					JOptionPane.showMessageDialog(null, "Erro inesperado, verifique os campos e tente novamente.");
				}
			}
		});
		btnAddDepartment.setBounds(210, 24, 89, 23);
		panel5.add(btnAddDepartment);
		panel4.setLayout(null);
		
		tfId3 = new JTextField();
		tfId3.setBounds(43, 11, 105, 20);
		panel4.add(tfId3);
		tfId3.setColumns(10);
		
		JLabel lblId2 = new JLabel("ID:");
		lblId2.setBounds(10, 14, 30, 14);
		panel4.add(lblId2);
		
		JButton btnDelete = new JButton("Deletar");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SellerDAO dao = DaoFactory.createSellerDAO();
				if(!tfId3.getText().isEmpty()) {
					try {
						dao.deleteByID(Integer.parseInt(tfId3.getText()));
						JOptionPane.showMessageDialog(null, "Vendedor deletado com sucesso!");
					} catch(IllegalArgumentException ex) {
						JOptionPane.showMessageDialog(null, "Erro inesperado, verifique os campos e tente novamente.");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Erro inesperado, verifique os campos e tente novamente.");
				}
			}
		});
		btnDelete.setBounds(174, 10, 89, 23);
		panel4.add(btnDelete);
		panel3.setLayout(null);

		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(10, 11, 46, 14);
		panel3.add(lblId);

		tfId2 = new JTextField();
		tfId2.setBounds(43, 8, 279, 20);
		panel3.add(tfId2);
		tfId2.setColumns(10);

		tfName2 = new JTextField();
		tfName2.setColumns(10);
		tfName2.setBounds(66, 39, 257, 20);
		panel3.add(tfName2);

		tfEmail2 = new JTextField();
		tfEmail2.setColumns(10);
		tfEmail2.setBounds(66, 70, 257, 20);
		panel3.add(tfEmail2);

		tfBirthDate2 = new JTextField();
		tfBirthDate2.setColumns(10);
		tfBirthDate2.setBounds(147, 101, 176, 20);
		panel3.add(tfBirthDate2);

		tfBaseSalary2 = new JTextField();
		tfBaseSalary2.setColumns(10);
		tfBaseSalary2.setBounds(101, 129, 222, 20);
		panel3.add(tfBaseSalary2);

		tfIdDepartment3 = new JTextField();
		tfIdDepartment3.setColumns(10);
		tfIdDepartment3.setBounds(132, 157, 191, 20);
		panel3.add(tfIdDepartment3);

		tfNameDepartment2 = new JTextField();
		tfNameDepartment2.setColumns(10);
		tfNameDepartment2.setBounds(160, 188, 163, 20);
		panel3.add(tfNameDepartment2);

		JLabel lblNameDepartment_1 = new JLabel("Nome do departamento:");
		lblNameDepartment_1.setBounds(10, 191, 145, 14);
		panel3.add(lblNameDepartment_1);

		JLabel lblIdDepartment_1 = new JLabel("ID do departemento:");
		lblIdDepartment_1.setBounds(10, 160, 124, 14);
		panel3.add(lblIdDepartment_1);

		JLabel lblBaseSalary_1 = new JLabel("Sal\u00E1rio Base:");
		lblBaseSalary_1.setBounds(10, 132, 115, 14);
		panel3.add(lblBaseSalary_1);

		JLabel lblBirthDate_1 = new JLabel("Data de Nascimento:");
		lblBirthDate_1.setBounds(10, 104, 127, 14);
		panel3.add(lblBirthDate_1);

		JLabel lblEmail_1 = new JLabel("Email:");
		lblEmail_1.setBounds(10, 73, 46, 14);
		panel3.add(lblEmail_1);

		JLabel lblName_1 = new JLabel("Nome:");
		lblName_1.setBounds(10, 42, 46, 14);
		panel3.add(lblName_1);

		JButton btnUpdate = new JButton("Atualizar Dados");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					SellerDAO dao = DaoFactory.createSellerDAO();
					Seller s = dao.findByID(Integer.parseInt(tfId2.getText()));

					if (tfName2.getText().isEmpty() && tfEmail2.getText().isEmpty() && tfBirthDate2.getText().isEmpty()
							&& tfBaseSalary2.getText().isEmpty() && tfIdDepartment3.getText().isEmpty()
							&& tfNameDepartment2.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null,
								"Erro! Preencha pelo menos um campo que não seja o id para atualizar.");
					} else {
						if (!tfName2.getText().isEmpty()) {
							s.setName(tfName2.getText());
						}
						if (!tfEmail2.getText().isEmpty()) {
							s.setEmail(tfEmail2.getText());
						}
						if (!tfBirthDate2.getText().isEmpty()) {
							SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
							s.setBirthDate(sdf.parse(tfBirthDate2.getText()));
						}
						if (!tfBaseSalary2.getText().isEmpty()) {
							s.setBaseSalary(Double.parseDouble(tfBaseSalary2.getText()));
						}
						if (!tfIdDepartment3.getText().isEmpty() && !tfNameDepartment2.getText().isEmpty()) {
							s.setDepartment(new Department(Integer.parseInt(tfIdDepartment3.getText()),
									tfNameDepartment2.getText()));
							;
						} else {
							if (tfIdDepartment3.getText().isEmpty() && tfNameDepartment2.getText().isEmpty()) {
								// não faz nada
							} else {
								JOptionPane.showMessageDialog(null,
										"Erro! É preciso completar o Id e o Nome do departamento para poder atualiza-lo.");
							}
						}
						
						dao.update(s);
						
						JOptionPane.showMessageDialog(null,
								"Dados atualizados!");
					}
				} catch (IllegalArgumentException | ParseException ex) {
					JOptionPane.showMessageDialog(null, "Erro inesperado, verifique os campos e tente novamente.");
				}
			}
		});
		btnUpdate.setBounds(20, 237, 128, 36);
		panel3.add(btnUpdate);
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

		tfNameDepartment1 = new JTextField();
		tfNameDepartment1.setBounds(160, 160, 163, 20);
		panel2.add(tfNameDepartment1);
		tfNameDepartment1.setColumns(10);

		JLabel lblNameDepartment1 = new JLabel("Nome do departamento:");
		lblNameDepartment1.setBounds(10, 163, 145, 14);
		panel2.add(lblNameDepartment1);

		JButton btnNewButton = new JButton("Inserir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Department dep = new Department(Integer.parseInt(tfIdDepartmentAdd.getText()),
							tfNameDepartment1.getText());
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					SellerDAO dao = DaoFactory.createSellerDAO();
					Seller seller = new Seller(null, tfName.getText(), tfEmail.getText(),
							sdf.parse(tfBirthDate.getText()), Double.parseDouble(tfBaseSalary.getText()), dep);
					dao.insert(seller);
					JOptionPane.showMessageDialog(null, "Vendedor adicionado com sucesso!");
				} catch (IllegalArgumentException | ParseException e1) {
					JOptionPane.showMessageDialog(null, "Erro inesperado, verifique os campos e tente novamente.");
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
				try {
					SellerDAO dao = DaoFactory.createSellerDAO();
					String sellers = "Vendedores: ";

					if (!dao.findAll().isEmpty()) {
						for (Seller s : dao.findAll()) {
							sellers += "\n-> " + s;
						}

						JOptionPane.showMessageDialog(null, sellers);
					} else {
						JOptionPane.showMessageDialog(null, "Nenhum vendedor encontrado!");
					}
				} catch (IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		btnFindAll.setBounds(24, 79, 119, 48);
		panel.add(btnFindAll);

		JButton btnSearch = new JButton("Pesquisar");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					SellerDAO dao = DaoFactory.createSellerDAO();
					Seller seller = dao.findByID(Integer.parseInt(tfID.getText()));
					if (seller != null) {
						JOptionPane.showMessageDialog(null, seller);
					} else {
						JOptionPane.showMessageDialog(null, "Nenhum vendedor encontrado!");
					}
				} catch (IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(null, "Erro de input! Verifique o campo de ID.");
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
		tfIdDepartment.setBounds(143, 51, 98, 20);
		panel.add(tfIdDepartment);

		JButton btnSearch2 = new JButton("Pesquisar");
		btnSearch2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					SellerDAO dao = DaoFactory.createSellerDAO();
					Department dep = new Department(Integer.parseInt(tfIdDepartment.getText()), null);
					String sellers = "Sellers: ";

					if (!dao.findByDepartment(dep).isEmpty()) {
						for (Seller s : dao.findByDepartment(dep)) {
							sellers += "\n-> " + s;
						}

						JOptionPane.showMessageDialog(null, sellers);
					} else {
						JOptionPane.showMessageDialog(null, "Nenhum vendedor encontrado!");
					}
				} catch (IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(null, "Erro de input! Verifique o ID do departamento.");
				}

			}
		});
		btnSearch2.setBounds(269, 50, 105, 23);
		panel.add(btnSearch2);
	}
}
