package Servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.util.*;
import DAO.ProductDAO;
import DTO.ProductDTO;

@WebServlet("/productList")
public class productList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ProductLsit서블릿의 doGet실행");
		ProductDAO DAO=ProductDAO.getInstance();
		List<ProductDTO> productlist=DAO.selectAllProduct();
		request.setAttribute("productlist", productlist);
		RequestDispatcher dis=request.getRequestDispatcher("productList.jsp");
		dis.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
