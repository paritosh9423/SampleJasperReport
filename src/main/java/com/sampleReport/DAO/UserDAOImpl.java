package com.sampleReport.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Transactional
@Repository
public class UserDAOImpl {
	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private ResourceLoader resourceLoader;
	
	public JasperPrint exportPdfFile() throws SQLException , JRException , IOException {
		
		Connection conn = jdbcTemplate.getDataSource().getConnection();
		String path = resourceLoader.getResource("classpath:rpt_users.jrxml").getURI().getPath().toString();
		JasperReport jasperReport = JasperCompileManager.compileReport(path);
		Map<String, Object> map = new HashMap<>();
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map,conn);
		
		return jasperPrint;
	}
}
