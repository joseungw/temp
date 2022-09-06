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
		request.setCharacterEncoding("utf-8");	//한글이 들어가지 않고 int만 받아오기 때문에 안적어도 무관.
		String code=request.getParameter("code");
		System.out.println("데이터 삭제 서블릿 doGet()실행 받아온 코드 값 : "+code);
		
		ProductDAO DAO=ProductDAO.getInstance();
		DAO.deleteProduct(code);
		response.sendRedirect("productList");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
