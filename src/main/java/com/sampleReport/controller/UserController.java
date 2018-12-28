package com.sampleReport.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sampleReport.service.UserService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

@Controller
@RequestMapping("/")
public class UserController {
	@Autowired
	private UserService userService  ;
	
	@RequestMapping(value= {"/",""} , method = RequestMethod.GET)
	public ModelAndView homePage() {
		
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
	@RequestMapping(value = "/export", method = RequestMethod.POST)
	public void exportPDF(ModelAndView model , HttpServletResponse httpServletResponse) throws IOException , JRException , SQLException{
		JasperPrint jasperPrint = null;
		httpServletResponse.setContentType("application/x-download");
		httpServletResponse.setHeader("Content-Disposition", String.format("attachment; filename=\"users.pdf\""));
		
		OutputStream outputStream = httpServletResponse.getOutputStream();
		jasperPrint = userService.exportPdf();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
	}
	
}
