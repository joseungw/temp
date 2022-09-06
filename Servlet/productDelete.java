package Servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import DAO.ProductDAO;

@WebServlet("/productDelete")
public class productDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");	//�ѱ��� ���� �ʰ� int�� �޾ƿ��� ������ ����� ����.
		String code=request.getParameter("code");
		System.out.println("������ ���� ���� doGet()���� �޾ƿ� �ڵ� �� : "+code);
		
		ProductDAO DAO=ProductDAO.getInstance();
		DAO.deleteProduct(code);
		response.sendRedirect("productList");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
