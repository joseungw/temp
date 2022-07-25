package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.MemberDAO;
import Beans.Member;

@WebServlet("/memberUpdate.do")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//http://localhost:8080/Board01/memberUpdate.do?userid=one a태그 또는 method="get"
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid=request.getParameter("userid");
		MemberDAO mDAO=MemberDAO.getInstance();
		Member m=mDAO.getMember(userid);
		request.setAttribute("mem", m);
		RequestDispatcher dis=request.getRequestDispatcher("memberUpdate.jsp");
		dis.forward(request, response);
	}
	//http://localhost:8080/Board01/memberUpdate.do     method="post"
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String userid=request.getParameter("userid");
		System.out.println("서블릿에서 userid확인 "+userid);
		String pwd=request.getParameter("pwd");
		String email=request.getParameter("email");
		String phone=request.getParameter("phone");
		String admin=request.getParameter("admin");
		
		Member m=new Member();
		m.setUserid(userid);
		m.setPwd(pwd);
		m.setEmail(email);
		m.setPhone(phone);
		m.setAdmin(admin);
		
		MemberDAO mDAO=MemberDAO.getInstance();
		mDAO.updateMember(m);
		response.sendRedirect("login.do");
	}

}
