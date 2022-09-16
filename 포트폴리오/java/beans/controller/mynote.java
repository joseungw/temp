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
		String category=request.getParameter("sports");
		System.out.println(category);
		System.out.println(userid);
		
		UserDAO ud=UserDAO.getInstance();
		
		ArrayList<Notice> list=ud.MySelect(userid);
		
		request.setAttribute("userid", userid);
		request.setAttribute("category", category);
		request.setAttribute("list", list);
		
		RequestDispatcher dis=request.getRequestDispatcher("board.jsp");
		dis.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
