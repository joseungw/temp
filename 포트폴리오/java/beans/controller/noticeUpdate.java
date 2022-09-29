package controller;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import DAO.UserDAO;
import beans.Notice;


@WebServlet("/noticeUpdate")
public class noticeUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String notenum=request.getParameter("notenum");
		UserDAO ud=UserDAO.getInstance();
		Notice notice=ud.selectNotice(notenum);
		
		System.out.println(notice.getCategory());
		
		request.setAttribute("notice", notice);
		
		RequestDispatcher dis=request.getRequestDispatcher("postUpdate.jsp");
		dis.forward(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String notenum=request.getParameter("notenum");
		String category=request.getParameter("category");
		String region=request.getParameter("region");
		String title=request.getParameter("title");
		String contents=request.getParameter("contents");
		String notedate=request.getParameter("notedate");
		
		
		
		Notice notice=new Notice();
		notice.setNotenum(Integer.parseInt(notenum));;
		notice.setCategory(category);
		notice.setRegion(region);
		notice.setTitle(title);
		notice.setContents(contents);
		notice.setNotedate(notedate);
		
		
		
		UserDAO ud=UserDAO.getInstance();
		ud.noticeUpdate(notice);
		
		request.setAttribute("notenum", notenum);
		
		RequestDispatcher dis=request.getRequestDispatcher("showpost?notenum="+notenum);
		dis.forward(request, response);
	}

}
