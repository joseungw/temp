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
	
		//1: �α��� ����  0: ��й�ȣ Ʋ�� -1: ���̵� ����
		System.out.println(result);
		if(result==1) {
			User u=ud.getUser(id);
			HttpSession sess=request.getSession();
			sess.setAttribute("id", u.getId());
			sess.setAttribute("nick",u.getNick());
			sess.setAttribute("sports", u.getSports());
			sess.setAttribute("isLogin", true);
			url="index.jsp";
		}else if(result==0) {
			//��й�ȣ�� Ʋ�� ���
			url="error.jsp";
			request.setAttribute("message","<script>alert('��й�ȣ�� ���� �ʽ��ϴ�. �ٽ� �Է� ���ּ���.');location.href='login.do';</script>");
			
		}else if(result==-1) {
			url="error.jsp";
			request.setAttribute("message","<script>alert('���̵� ���� ���� �ʽ��ϴ�.');location.href='login.do';</script>");
		}
		//�α��� ���� ��� main.jsp�� �̵� //���̵� ��й�ȣ�� Ʋ�� ���(result�� 0�̳�-1�ΰ�� error.jsp�� �����Ͽ�
		//�ٽ� �α��� �������� ���ƿ��� ���**(���ΰ�ħ�� ������ ��� request�� �ʱ�ȭ�ϱ� ���ؼ�)
		
		RequestDispatcher dis=request.getRequestDispatcher(url);
		dis.forward(request, response);
	}

}
