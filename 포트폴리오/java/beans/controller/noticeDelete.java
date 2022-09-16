package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import DAO.UserDAO;
import beans.Notice;


@WebServlet("/noticeDelete")
public class noticeDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String notenum=request.getParameter("notenum");
		System.out.println(notenum);
		UserDAO ud=UserDAO.getInstance();
		ud.noticeDelete(notenum);
		
		String category=request.getParameter("category");
		request.setAttribute("category", category);
		RequestDispatcher dis=request.getRequestDispatcher("boardmain?category="+category);
		dis.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
