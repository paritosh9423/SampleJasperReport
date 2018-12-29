package com.sampleReport.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sampleReport.model.User;

import java.sql.SQLException;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Transactional
@Repository
public class UserDAOImpl {
	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private ResourceLoader resourceLoader;
	@Autowired
	private UserRepo userrepo;
	
	public JasperPrint exportPdfFile() throws SQLException , JRException , IOException {
		
		
		

System.out.println("Begin");
		List<User> userList = new ArrayList<>();
		userrepo.findAll().forEach(userList::add);
		System.out.println("user list size: "+ userList.size());
		
		String path = resourceLoader.getResource("classpath:sample.jrxml").getURI().getPath().toString();
		JasperReport jasperReport = JasperCompileManager.compileReport(path);
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String,Object> item=new HashMap<String,Object>();
		for(User user:userList)
		{
			
		
		item.put("id", user.getId());
		item.put("userName", user.getUserName());
		item.put("email", user.getEmailId());
		list.add(item);
		
		}
		JRBeanCollectionDataSource jrBeanCollectionDataSource  = new JRBeanCollectionDataSource(list);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null , jrBeanCollectionDataSource);
	
		System.out.println(jasperPrint.getPropertiesMap());
		
		
		System.out.println("EnD");
		
		
		
		
		/*Connection conn = jdbcTemplate.getDataSource().getConnection();
		String path = resourceLoader.getResource("classpath:rpt_users.jrxml").getURI().getPath().toString();
		JasperReport jasperReport = JasperCompileManager.compileReport(path);
		Map<String, Object> map = new HashMap<>();
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map,conn);*/
		
		return jasperPrint;
	}

}
