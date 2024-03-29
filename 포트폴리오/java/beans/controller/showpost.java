package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.util.*;
import DAO.UserDAO;
import beans.Comment;
import beans.Notice;

import java.sql.*;


@WebServlet("/showpost")
public class showpost extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String notenum=request.getParameter("notenum");
		String postnum=notenum;
		String sql="select * from notice where notenum=?";
		Notice notice=new Notice();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		UserDAO ud=UserDAO.getInstance();
		
		ArrayList<Comment> comAllList=ud.selCom(postnum);
		
		ArrayList<Comment> cmtlist=new ArrayList<Comment>();
		ArrayList<Comment> recomlist=new ArrayList<Comment>();
		
		for(int i=0; i<comAllList.size();i++) {
			if(comAllList.get(i).getRecomnum()==null) {
				cmtlist.add(comAllList.get(i));
			}
			else {
				recomlist.add(comAllList.get(i));
			}
		}
		
		
		try {
			conn=ud.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, notenum);
			rs=pstmt.executeQuery();
			rs.next();
			notice.setNotenum(rs.getInt("notenum"));
			notice.setUserid(rs.getString("userid"));
			notice.setUsernick(rs.getString("usernick"));
			notice.setCategory(rs.getString("category"));
			notice.setNotedate(rs.getString("notedate"));
			notice.setTitle(rs.getString("title"));
			notice.setContents(rs.getString("contents"));
			notice.setRegion(rs.getString("region"));
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		request.setAttribute("notice",notice);
		request.setAttribute("cmt", cmtlist);
		request.setAttribute("rct",recomlist);
		
		
		//response.sendRedirect("showpost?notenum="+postnum);
		
		RequestDispatcher dis=request.getRequestDispatcher("showpost.jsp");
		dis.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
