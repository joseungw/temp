package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;	//RequestDispatcher���� �ʿ��� import

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
		
		
		MemberDAO md=MemberDAO.getInstance();//�̱����� �����ϱ� ���� new�� ����� ����
		int result=md.userCheck(userid, pwd);//1:�α��μ��� 0:��й�ȣƲ�� -1:���̵� ����
		System.out.println(result);
		if(result==1) {
			Member m=md.getMember(userid);
			HttpSession session=request.getSession();
			session.setAttribute("loginUser",m);
			request.setAttribute("message", "�α��ο� �����߽��ϴ�.");
			url="main.jsp";
			//�ش� ����� ������ �����ͼ� ����
			//url�� ����, �α��� ������ ������������ �̵�
		}else if(result==0) {
			//��й�ȣ�� Ʋ�ȴٴ� �ȳ� �޽����� ����
			request.setAttribute("message", "��й�ȣ�� ���� �ʽ��ϴ�.");
		}else if(result==-1) {
			//���̵� �������� �ʴ´ٴ� �ȳ� �޽����� ����
			request.setAttribute("message", "�������� ���� ȸ���Դϴ�.");
		}
		RequestDispatcher dis= request.getRequestDispatcher(url);
		dis.forward(request,response);
	}

}
