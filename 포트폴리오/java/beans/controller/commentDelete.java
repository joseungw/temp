package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import DAO.UserDAO;


@WebServlet("/commentDelete")
public class commentDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String comnum=request.getParameter("comnum");
		String notenum=request.getParameter("postnum");
		System.out.println(comnum);
		
		UserDAO ud=UserDAO.getInstance();
		ud.commentDelete(comnum);
		
		
		
		RequestDispatcher dis=request.getRequestDispatcher("showpost?notenum="+notenum);
		dis.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
