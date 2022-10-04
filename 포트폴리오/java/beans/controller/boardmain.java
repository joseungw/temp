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
		String category=request.getParameter("category");	//값 확인
		System.out.println(category);
		
		int currentPage=Integer.parseInt(request.getParameter("currentPage"));
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		UserDAO ud=UserDAO.getInstance();
		ArrayList<Notice> list=ud.getNotiCate(category,currentPage);
		request.setAttribute("category", category);
		request.setAttribute("list", list);
		
		int row=ud.getCategoryOfRows(category);
		int nOfPage=row/10;
		System.out.println(nOfPage);
		if(row%10>0) {
			nOfPage++;
		}
		System.out.println(nOfPage);
		request.setAttribute("nPage", nOfPage);
		request.setAttribute("currentPage", currentPage);
		
		
		RequestDispatcher dis=request.getRequestDispatcher("board.jsp");
		dis.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
