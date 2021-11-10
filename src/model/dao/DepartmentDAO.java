package model.dao;

import java.util.List;

import model.entities.Department;

public interface DepartmentDAO {
	
	public void insert(Department d);
	public void update(Department d);
	public void deleteByID(Integer id);
	public Department findByID(Integer id);
	public List<Department> findAll();
	
	
}
