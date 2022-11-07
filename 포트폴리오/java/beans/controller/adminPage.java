package controller;

import java.io.*;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import DAO.UserDAO;
import beans.User;


@WebServlet("/adminPage")
public class adminPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		int currentPage=Integer.parseInt(request.getParameter("currentPage"));
		System.out.println(currentPage);
		UserDAO ud=UserDAO.getInstance();
		List<User> UserList=ud.allUser(currentPage);
		request.setAttribute("UserList", UserList);
		
		int row=ud.getUserOfRows();
		int nOfPage=row/10;
		if(row%10>0) {
			nOfPage++;
		}
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("nOfPage", nOfPage);
		
		RequestDispatcher dis=request.getRequestDispatcher("adminPage.jsp");
		dis.forward(request, response);
	}
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
