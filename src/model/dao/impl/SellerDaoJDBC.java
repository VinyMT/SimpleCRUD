package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByID(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findByID(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT seller.*, department.nameDepartment \r\n"
					+ "FROM seller, department \r\n"
					+ "WHERE seller.idDepartment = department.idDepartment AND seller.idSeller = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if(rs.next()) {
				Department dep = new Department();
				dep.setId(rs.getInt("idDepartment"));
				dep.setName(rs.getString("nameDepartment"));
				
				Seller seller = new Seller();
				seller.setId(rs.getInt("idSeller"));
				seller.setName(rs.getString("name"));
				seller.setEmail(rs.getString("email"));
				seller.setBirthDate(rs.getDate("birthDate"));
				seller.setBaseSalary(rs.getDouble("baseSalary"));
				seller.setDepartment(dep);
				
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

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
