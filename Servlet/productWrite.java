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
		System.out.println("productWrite.doGet 실행 확인");
		RequestDispatcher dis=request.getRequestDispatcher("productWrite.jsp");
		dis.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("productWrite.doPost() 실행 확인");
		request.setCharacterEncoding("utf-8");
		ServletContext context=getServletContext();
		String path=context.getRealPath("/file");
		System.out.println(path);	//파일이 저장되고 있는 경로 확인
		
		int sizeLimit=20*1024*1024;	//업로드 파일의 20mb제한
		String encType="utf-8";
												//request, 경로, 파일용량(크기),utf-8,동일 파일 재작명규칙
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
