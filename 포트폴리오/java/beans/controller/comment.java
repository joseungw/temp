package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.util.*;
import java.sql.*;
import beans.Comment;

@WebServlet("/comment")
public class comment extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String comcon=request.getParameter("comcon");
		String postnum=request.getParameter("notenum");
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
