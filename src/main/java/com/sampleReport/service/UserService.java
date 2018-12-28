package com.sampleReport.service;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sampleReport.DAO.UserDAOImpl;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

@Service
public class UserService {
	@Autowired
	private UserDAOImpl userDAOImpl;
		
	public JasperPrint exportPdf() throws SQLException , IOException , JRException{
		return userDAOImpl.exportPdfFile();
		
	}
	
}
