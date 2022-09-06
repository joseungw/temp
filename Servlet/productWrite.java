package Servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import DTO.ProductDTO;
import DAO.ProductDAO;
import com.oreilly.servlet.*;
import com.oreilly.servlet.multipart.*;

@WebServlet("/productWrite")
public class productWrite extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("productWrite.doGet ���� Ȯ��");
		RequestDispatcher dis=request.getRequestDispatcher("productWrite.jsp");
		dis.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("productWrite.doPost() ���� Ȯ��");
		request.setCharacterEncoding("utf-8");
		ServletContext context=getServletContext();
		String path=context.getRealPath("/file");
		System.out.println(path);	//������ ����ǰ� �ִ� ��� Ȯ��
		
		int sizeLimit=20*1024*1024;	//���ε� ������ 20mb����
		String encType="utf-8";
												//request, ���, ���Ͽ뷮(ũ��),utf-8,���� ���� ���۸��Ģ
		MultipartRequest multi=new MultipartRequest(
				request,path,sizeLimit,encType,new DefaultFileRenamePolicy());
		String name=multi.getParameter("name");
		int price=Integer.parseInt(multi.getParameter("price"));
		String descriptoin=multi.getParameter("description");
		String pictureurl=multi.getFilesystemName("pictureurl");
		
		ProductDTO DTO=new ProductDTO();
		DTO.setName(name);
		DTO.setPrice(price);
		DTO.setDescription(descriptoin);
		DTO.setPictureurl("/file/"+pictureurl);
		
		ProductDAO DAO=ProductDAO.getInstance();
		DAO.insertProduct(DTO);
		
		response.sendRedirect("productList");
		
		System.out.println(name+", "+price+", "+descriptoin+", "+pictureurl);
	}

}
