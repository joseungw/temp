package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import DAO.UserDAO;
import beans.Notice;


@WebServlet("/noticeUpdate")
public class noticeUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String notenum=request.getParameter("notenum");
		UserDAO ud=UserDAO.getInstance();
		Notice notice=ud.selectNotice(notenum);
		request.setAttribute("notice", notice);
		
		RequestDispatcher dis=request.getRequestDispatcher("postUpdate.jsp");
		dis.forward(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
