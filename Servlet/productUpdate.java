package Servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import DAO.ProductDAO;
import DTO.ProductDTO;
import com.oreilly.servlet.*;
import com.oreilly.servlet.multipart.*;


@WebServlet("/productUpdate")
public class productUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String code=request.getParameter("code");
		System.out.println("productUpdate.doGet()���� ���� �з����� Ȯ�� : "+code);
		
		ProductDAO DAO=ProductDAO.getInstance();
		ProductDTO DTO=DAO.selectProduct(code);
		System.out.println("������ Ȯ�� �׽�Ʈ : "+DTO.getName());
		request.setAttribute("product", DTO);
		
		RequestDispatcher dis=request.getRequestDispatcher("productUpdate.jsp");
		dis.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("productUpdate.doPost()����Ȯ��");
		request.setCharacterEncoding("utf-8");
		ServletContext context=getServletContext();
		String path=context.getRealPath("file");
		System.out.println(path);
		
		boolean flag=true;	//�̹����� �ٲٸ� true, �ȹٲٸ� false;
		
		String encType="utf-8";
		int sizeLimit=20*1024*1024;
		
		MultipartRequest multi=new MultipartRequest(request, path, sizeLimit, encType,new DefaultFileRenamePolicy());
		String code=multi.getParameter("code");
		String name=multi.getParameter("name");
		int price=Integer.parseInt(multi.getParameter("price"));
		String description=multi.getParameter("description");
		String pictureurl=multi.getFilesystemName("pictureurl");
		if(pictureurl==null) {
			flag=false;
			pictureurl=multi.getParameter("nomakeImg");
		}
//		System.out.println("�ڵ尪 : "+code);
//		System.out.println("��ǰ�� : "+name);
//		System.out.println("���� : "+price);
//		System.out.println("�������: "+pictureurl);
//		System.out.println("���� : "+description);
		ProductDTO DTO=new ProductDTO();
		DTO.setCode(Integer.parseInt(code));
		DTO.setName(name);
		DTO.setPrice(price);
		DTO.setDescription(description);
		if(flag) {
			DTO.setPictureurl("/file/"+pictureurl);
		}else {
			DTO.setPictureurl(pictureurl);
		}
		ProductDAO DAO=ProductDAO.getInstance();
		DAO.updateProduct(DTO);
		
		response.sendRedirect("productList");
	}

}
