package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDAO{

	private Connection conn;
	
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}
			
	@Override
	public void insert(Seller d) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO seller (name, email, birthDate, baseSalary, idDepartment)"
					+ "VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			st.setString(1, d.getName());
			st.setString(2, d.getEmail());
			st.setDate(3, new java.sql.Date(d.getBirthDate().getTime()));
			st.setDouble(4, d.getBaseSalary());
			st.setInt(5, d.getDepartment().getId());
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					d.setId(id);
				}
				DB.closeResultSet(rs);
			} else {
				throw new DbException("Erro inesperado, nenhuma linha foi afetada!");
			}
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(Seller d) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE seller "
					+ "SET name = ?, email = ?, birthDate = ?, baseSalary = ?, idDepartment = ? "
					+ "WHERE idSeller = ?");
			st.setString(1, d.getName());
			st.setString(2, d.getEmail());
			st.setDate(3, new java.sql.Date(d.getBirthDate().getTime()));
			st.setDouble(4, d.getBaseSalary());
			st.setInt(5, d.getDepartment().getId());
			st.setInt(6, d.getId());
			st.executeUpdate();
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteByID(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM seller WHERE idSeller = ?");
			st.setInt(1, id);
			st.executeUpdate();
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public Seller findByID(Integer id){
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT seller.*, department.nameDepartment \r\n"
					+ "FROM seller, department \r\n"
					+ "WHERE seller.idDepartment = department.idDepartment AND seller.idSeller = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next()) {
				Department dep = instantiateDepartment(rs);
				Seller seller = instantiateSeller(rs, dep);
				return seller;
			}
			
			return null;
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	public Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("idDepartment"));
		dep.setName(rs.getString("nameDepartment"));
		return dep;
	}
	
	public Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException{
		Seller seller = new Seller();
		seller.setId(rs.getInt("idSeller"));
		seller.setName(rs.getString("name"));
		seller.setEmail(rs.getString("email"));
		seller.setBirthDate(rs.getDate("birthDate"));
		seller.setBaseSalary(rs.getDouble("baseSalary"));
		seller.setDepartment(dep);
		return seller;
	}
	
	@Override
	public List<Seller> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT seller.*, department.nameDepartment\r\n"
					+ "FROM seller, department\r\n"
					+ "WHERE seller.idDepartment = department.idDepartment "
					+ "ORDER BY seller.Name");
			rs = st.executeQuery();
			
			ArrayList<Seller> sellers = new ArrayList<>();
			HashMap<Integer, Department> map = new HashMap<>();
			
			while(rs.next()) {
				Department department = map.get(rs.getInt("idDepartment"));
				
				if(department == null) {
					department = instantiateDepartment(rs);
					map.put(department.getId(), department);
				}
				
				Seller seller = instantiateSeller(rs, department);
				sellers.add(seller);
			}
			
			return sellers;
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Seller> findByDepartment(Department dep) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT seller.*, department.nameDepartment\r\n"
					+ "FROM seller, department\r\n"
					+ "WHERE seller.idDepartment = department.idDepartment AND seller.idDepartment = ?");
			st.setInt(1, dep.getId());
			rs = st.executeQuery();
			
			ArrayList<Seller> sellers = new ArrayList<>();
			HashMap<Integer, Department> map = new HashMap<>();
			
			while(rs.next()) {
				Department department = map.get(rs.getInt("idDepartment"));
				
				if(department == null) {
					department = instantiateDepartment(rs);
					map.put(department.getId(), department);
				}
				
				Seller seller = instantiateSeller(rs, department);
				sellers.add(seller);
			}
			
			return sellers;
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

}
