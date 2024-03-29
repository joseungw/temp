package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.UserDAO;
import beans.Notice;


@WebServlet("/boardregion.do")
public class boardregion extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String region=request.getParameter("region");
		String category=request.getParameter("category");
		int currentPage=Integer.parseInt(request.getParameter("currentPage"));
		System.out.println(currentPage);
		
		UserDAO ud=UserDAO.getInstance();
		
		ArrayList<Notice> list=ud.selectTwo(category, region, currentPage);
		
		int row=ud.getRegionOfRows(region,category);
		int nOfPage=row/10;
		System.out.println(nOfPage);
		if(row%10>0) {
			nOfPage++;
		}
		request.setAttribute("nPage", nOfPage);
		request.setAttribute("category", category);
		request.setAttribute("region", region);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("list", list);
		RequestDispatcher dis=request.getRequestDispatcher("boardRegion.jsp");
		dis.forward(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
