package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import DAO.UserDAO;
import java.util.*;
import beans.Comment;


@WebServlet("/recomment")
public class recomment extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String postnum=request.getParameter("postnum");
		String comid=request.getParameter("comid");
		String comnick=request.getParameter("comnick");
		String comcon=request.getParameter("recomcon");
		String comnum=request.getParameter("comnum");
		
		Calendar now = Calendar.getInstance();
		int year=now.get(Calendar.YEAR);
		int month=now.get(Calendar.MONTH)+1;
		int day=now.get(Calendar.DAY_OF_MONTH);
		int hour=now.get(Calendar.HOUR);
		int minute=now.get(Calendar.MINUTE);
		String comdate=""+year+"-"+month+"-"+day+"-"+hour+":"+minute;
		
		UserDAO ud=UserDAO.getInstance();
		Comment c=new Comment();
		
		c.setPostnum(postnum);
		c.setComid(comid);
		c.setComnick(comnick);
		c.setComcon(comcon);
		c.setComdate(comdate);
		c.setRecomnum(comnum);
		
		ud.recommentWrite(c);
		
		response.sendRedirect("showpost?notenum="+postnum);
		
//		RequestDispatcher dis=request.getRequestDispatcher("showpost?notenum="+postnum);
//		dis.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
