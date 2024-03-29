package controller;

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import DAO.UserDAO;
import java.util.*;

@WebServlet("/notice.do")
public class notice extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String userid=request.getParameter("userid");
		String usernick=request.getParameter("usernick");
		String category=request.getParameter("category");
		Calendar now = Calendar.getInstance();
		int year=now.get(Calendar.YEAR);
		int month=now.get(Calendar.MONTH)+1;
		int day=now.get(Calendar.DAY_OF_MONTH);
		int hour=now.get(Calendar.HOUR);
		int minute=now.get(Calendar.MINUTE);
		String notedate=""+year+"-"+month+"-"+day+"-"+hour+":"+minute;
		String title=request.getParameter("title");
		String contents=request.getParameter("contents");
		String region=request.getParameter("region");
		System.out.println(notedate);
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="insert into notice (userid, usernick, category, notedate, title, contents ,region) ";
		sql+="values (?,?,?,?,?,?,?)";	//
		UserDAO ud=UserDAO.getInstance();
		
		try {
			conn=ud.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setString(2,usernick);
			pstmt.setString(3,category);
			pstmt.setString(4,notedate);
			pstmt.setString(5,title);
			pstmt.setString(6,contents);
			pstmt.setString(7,region);
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("notice에서 데이터베이스 연결 중 오류 : "+e);
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception ex) {
				System.out.println("notice에서 데이터베이스 연결 해제 중 오류 : "+ex);
			}
		}
		int row=ud.getCategoryOfRows(category);
		int nOfPage=row/10;
		System.out.println(nOfPage);
		if(row%10>0) {
			nOfPage++;
		}
		request.setAttribute("nPage", nOfPage);
		request.setAttribute("category", category);
		request.setAttribute("currentPage", 1);
		
		RequestDispatcher dis=request.getRequestDispatcher("boardmain?category="+category+"&currentPage=1");
		dis.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
