package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.sql.*;
import DAO.UserDAO;
import java.util.*;
import beans.*;

@WebServlet("/boardmain")
public class boardmain extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String category=request.getParameter("category");	
		System.out.println(category);
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		UserDAO ud=UserDAO.getInstance();
		
		ArrayList<Notice> list=ud.getNotiCate(category);
		
		
		request.setAttribute("list", list);
		RequestDispatcher dis=request.getRequestDispatcher("board.jsp");
		dis.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}