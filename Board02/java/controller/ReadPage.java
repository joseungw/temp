package controller;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import DAO.PageDAO;
import DTO.PageDTO;
import java.util.*;

@WebServlet("/ReadPage")
public class ReadPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//currentPage : ����ڰ� ���� ���� �ִ� ��������ȣ
		//recordsPerPage : �� �������� ������ �ڷ��� ����
		int currentPage=Integer.parseInt(request.getParameter("currentPage"));
		int recordsPerPage=Integer.parseInt(request.getParameter("recordsPerPage"));
		System.out.println("���� ������ : "+currentPage+", ������ �� ������ : "+recordsPerPage);
		
		PageDAO pDAO=PageDAO.getInstance();
		List<PageDTO> data=pDAO.findList(currentPage, recordsPerPage);
		request.setAttribute("data", data);
		
		int row=pDAO.getNumberOfRows();
		int nOfPage=row/recordsPerPage;
		if(row%recordsPerPage>0) {
			nOfPage++;
		}
		request.setAttribute("nOfPage", nOfPage);
		request.setAttribute("", recordsPerPage);
		
		
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("recordsPerPage", recordsPerPage);
		
		RequestDispatcher dis=request.getRequestDispatcher("list.jsp");
		dis.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
