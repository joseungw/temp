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
		String url=null;
		int result=0;
		request.setCharacterEncoding("utf-8");
		String id=request.getParameter("id");
		String pw=request.getParameter("pw");
		System.out.println("�з����� �ܷ� : "+id+","+pw);
		UserDAO ud=UserDAO.getInstance();

		result=ud.userCheak(id, pw);
	
		//1: 로그인 성공 0: 비밀번호 틀림 -1 : 아이디 없음
		System.out.println(result);
		if(result==1) {
			User u=ud.getUser(id);
			HttpSession sess=request.getSession();
			sess.setAttribute("id", u.getId());
			sess.setAttribute("nick",u.getNick());
			sess.setAttribute("sports", u.getSports());
			sess.setAttribute("city", u.getCity());
			sess.setAttribute("phone", u.getPhone());
			sess.setAttribute("name", u.getName());
			sess.setAttribute("password", u.getPassword());
			sess.setAttribute("num", u.getNum());
			sess.setAttribute("isLogin", true);
			request.setAttribute("u", u);
			url="index.jsp";
		}else if(result==0) {
			//비밀번호가 틀릴 경우
			url="error.jsp";
			request.setAttribute("message","<script>alert('비밀번호가 틀렸습니다. 다시 입력해 주세요.');location.href='login.do';</script>");
			
		}else if(result==-1) {
			url="error.jsp";
			request.setAttribute("message","<script>alert('아이디가 존재 하지 않습니다.');location.href='login.do';</script>");
		}
		//로그인 성공의 경우 index.jsp로 이동 //아이디 비밀번호가 틀릴경우 (result가 0이나 -1인경우 error.jsp를 경유하여
		//다시 로그인 서블릿으로 돌아오는 방법 **(새로고침을 눌렀을경우 request값 초기화하기 위해서)
		//경유를 안했을경우 로그인 실패시에도 request값이 남아있어 새로고침을 할 경우에도 똑같은 alert이 뜸.
		
		
		RequestDispatcher dis=request.getRequestDispatcher(url);
		dis.forward(request, response);
	}

}
