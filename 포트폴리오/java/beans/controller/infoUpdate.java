package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.UserDAO;
import beans.User;


@WebServlet("/infoUpdate")
public class infoUpdate extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String id=request.getParameter("id");
		
		UserDAO ud=UserDAO.getInstance();
		
		User u=ud.getUser(id);
		
		request.setAttribute("u", u);
		System.out.println(u.getSports());
		System.out.println(u.getCity());
		
		
//		response.sendRedirect("infoUpdate.jsp");
		RequestDispatcher dis=request.getRequestDispatcher("infoUpdate.jsp");
		dis.forward(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		
		String password=request.getParameter("password");
		String city=request.getParameter("city");
		String sports=request.getParameter("sports");
		String name=request.getParameter("name");
		String nick=request.getParameter("nick");
		String id=request.getParameter("id");
		String phone=request.getParameter("phone");
		String num=request.getParameter("num");
		System.out.println(num);
		
		
		User u=new User();
		u.setNum(Integer.parseInt(num));
		u.setName(name);
		u.setPhone(phone);
		u.setId(id);
		u.setPassword(password);
		u.setNick(nick);
		u.setCity(city);
		u.setSports(sports);
		System.out.println(num);
		UserDAO ud=UserDAO.getInstance();
		ud.UserUpdate(u);
		
		HttpSession sess=request.getSession();
		sess.setAttribute("sports", sports);
		sess.setAttribute("city", city);
		
//		response.sendRedirect("index.jsp");
		
//		request.setAttribute("sports", sports);
//		request.setAttribute("city", city);
		request.setAttribute("password", password);
		request.setAttribute("id", id);
		
		
		RequestDispatcher dis=request.getRequestDispatcher("index.jsp");
		dis.forward(request, response);
		
	}

}
