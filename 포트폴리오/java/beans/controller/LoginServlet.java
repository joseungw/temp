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
		String id=request.getParameter("id");
		String pw=request.getParameter("pw");
		
		UserDAO ud=UserDAO.getInstance();
		int result=ud.userCheak(id, pw);
		//1: 로그인 성공  0: 비밀번호 틀림 -1: 아이디 없음
		if(result==1) {
			User u=ud.getUser(id);
			HttpSession sess=request.getSession();
			sess.setAttribute("id", u);
		}else if(result==0) {
			
		}else if(result==01) {
			
		}
		//if(ud.userCheak(id,pw)==1){
		//session.setAtribute("id",id);			//로그인 성공
		//}else if(ud.userCheak(id,pw)==0){		//비밀번호 틀림
		//}
		RequestDispatcher dis=request.getRequestDispatcher(url);
		dis.forward(request, response);
	}

}
