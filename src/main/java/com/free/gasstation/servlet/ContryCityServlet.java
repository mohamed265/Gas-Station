//package com.assset.onlineacademy.servlet;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.assset.onlineacademy.dto.City;
//import com.assset.onlineacademy.dto.Contry;
//import com.assset.onlineacademy.service.ContryCityService;
//import com.assset.onlineacademy.util.Constants;
//
//@WebServlet("/ContryCityServlet")
//public class ContryCityServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//	private ContryCityService contryCityService = null;
//
//	@Override
//	public void init() throws ServletException {
//		contryCityService = new ContryCityService();
//		super.init();
//	}
//
//	protected void doGet(HttpServletRequest request,
//			HttpServletResponse response) throws ServletException, IOException {
//		String contryId = request.getParameter(Constants.CONTRYID);
//		System.out.println("ContryId: " + contryId);
//		if (contryId == null) { // load all contries
//
//			response.setStatus(HttpServletResponse.SC_OK);
//			response.setContentType("application/json");
//			response.setCharacterEncoding("UTF-8");
//
//			ArrayList<Contry> list = contryCityService.findAllContries();
//			response.getWriter().write("{ \"arr\" : [");
//			for (int i = 0; i < list.size(); i++) {
//				response.getWriter().write('\"' + list.get(i).getName() + '\"');
//				if (i + 1 != list.size())
//					response.getWriter().write(',');
//			}
//			response.getWriter().write("]}");
//		} else { // load cities with contry id
//			response.setStatus(HttpServletResponse.SC_OK);
//			response.setContentType("application/json");
//			response.setCharacterEncoding("UTF-8");
//			
//			Contry contry = new Contry();
//			contry.setId(Integer.parseInt(contryId));
//			ArrayList<City> list = contryCityService.findCitiesByContryId(contry);
//			
//			response.getWriter().write("{ \"arr\" : [");
//			for (int i = 0; i < list.size(); i++) {
//				response.getWriter().write('\"' + list.get(i).getName() + '\"');
//				if (i + 1 != list.size())
//					response.getWriter().write(',');
//			}
//			response.getWriter().write("]}");
//		}
//	}
//}
