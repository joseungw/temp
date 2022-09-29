package controller;

import java.io.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import DAO.UserDAO;
import beans.Notice;


@WebServlet("/myimport")
public class myimport extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String category=request.getParameter("sports");
		String region=request.getParameter("city");
		int currentPage=Integer.parseInt(request.getParameter("currentPage"));
		
		UserDAO ud=UserDAO.getInstance();
		ArrayList<Notice> list=ud.myImport( category, region,currentPage);
		
		int row=ud.getRegionOfRows(region,category);
		int nOfPage=row/10;
		System.out.println(nOfPage);
		if(row%10>0) {
			nOfPage++;
		}
		request.setAttribute("nPage", nOfPage);
		request.setAttribute("category", category);
		request.setAttribute("region", region);
		request.setAttribute("list", list);
		
		RequestDispatcher dis=request.getRequestDispatcher("boardRegion.jsp");
		dis.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
