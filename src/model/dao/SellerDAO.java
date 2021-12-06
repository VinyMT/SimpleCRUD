package model.dao;

import java.util.List;

import model.entities.Department;
import model.entities.Seller;

public interface SellerDAO {
	public void insert(Seller d);
	public void update(Seller d);
	public void deleteByID(Integer id);
	public Seller findByID(Integer id);
	public List<Seller> findAll();
	public List<Seller> findByDepartment(Department dep);
}
