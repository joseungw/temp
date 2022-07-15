package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;	//RequestDispatcher사용시 필요한 import

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.MemberDAO;
import Beans.Member;

@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dis=request.getRequestDispatcher("login.jsp");
		dis.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url="login.jsp";
		request.setCharacterEncoding("utf-8");
		String userid=request.getParameter("userid");
		String pwd=request.getParameter("pwd");
		System.out.println(userid+","+pwd);
		
		
		MemberDAO md=MemberDAO.getInstance();//싱글톤을 유지하기 위해 new의 사용을 제한
		int result=md.userCheck(userid, pwd);//1:로그인성공 0:비밀번호틀림 -1:아이디 없음
		System.out.println(result);
		if(result==1) {
			Member m=md.getMember(userid);
			HttpSession session=request.getSession();
			session.setAttribute("loginUser",m);
			request.setAttribute("message", "로그인에 성공했습니다.");
			url="main.jsp";
			//해당 멤버의 정보를 가져와서 저장
			//url값 보정, 로그인 성공시 메인페이지로 이동
		}else if(result==0) {
			//비밀번호가 틀렸다는 안내 메시지를 전달
			request.setAttribute("message", "비밀번호가 맞지 않습니다.");
		}else if(result==-1) {
			//아이디가 존재하지 않는다는 안내 메시지를 전달
			request.setAttribute("message", "존재하지 않은 회원입니다.");
		}
		RequestDispatcher dis= request.getRequestDispatcher(url);
		dis.forward(request,response);
	}

}
