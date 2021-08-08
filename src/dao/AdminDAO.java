package dao;

import java.util.List;
import entity.Admin;

public interface AdminDAO {
	
	public List<Admin> findAll(int page,int pageSize) throws Exception;
	
	public void add(Admin admin)throws Exception;
	
	public void del(int id) throws Exception;
	
	public Admin findById(int id) throws Exception;
	
	public void update(Admin admin) throws Exception;
	
	public int getTotalPages(int pageSize) throws Exception;
	
	public Admin findByUsername(String username)throws Exception;
}
