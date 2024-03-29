package controller;

import java.io.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import DAO.UserDAO;
import beans.Notice;


@WebServlet("/mynote.do")
public class mynote extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String userid=request.getParameter("id");
//		String category=request.getParameter("sports");
		int currentPage=Integer.parseInt(request.getParameter("currentPage"));
		System.out.println(currentPage);
		
		UserDAO ud=UserDAO.getInstance();
		
		ArrayList<Notice> list=ud.MySelect(userid,currentPage);
		
		int row=ud.getUseridOfRows(userid);
		int nOfPage=row/10;
		System.out.println(nOfPage);
		if(row%10>0) {
			nOfPage++;
		}
		System.out.println(nOfPage);
		request.setAttribute("nPage", nOfPage);
		
		request.setAttribute("userid", userid);
//		request.setAttribute("category", category);
		request.setAttribute("list", list);
		request.setAttribute("currentPage", currentPage);
		
		RequestDispatcher dis=request.getRequestDispatcher("mynote.jsp");
		dis.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
