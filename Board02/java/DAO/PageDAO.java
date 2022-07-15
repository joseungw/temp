package DAO;

import java.sql.*;
import java.util.*;
import DTO.PageDTO;

public class PageDAO {
	private PageDAO() {
		
	}
	private static PageDAO instance=new PageDAO();
	
	public static PageDAO getInstance() {
		return instance;
	}
	//커넥션 기능 처리
	public Connection getConnection() throws Exception{
		Connection conn=null;
		String url="jdbc:mysql://127.0.0.1:3306/study";
		String id="root";
		String pw="iotiot";
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn=DriverManager.getConnection(url,id,pw);
		return conn;
	}
	//목록 출력을 위한 정보 처리
	public List<PageDTO> findList(int currentPage, int recordsPerPage){
		List<PageDTO> List=new ArrayList<PageDTO>();
		int start=currentPage*recordsPerPage-recordsPerPage;
		//현재 페이지 값에서 보여줄 자료 양을 곱한 뒤에 페이지 당 자료 양을 빼주면 시작점을 특정할 수 있다.
		String sql="select * from page limit ?, ?;";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, recordsPerPage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				PageDTO p=new PageDTO();
				p.setNum(rs.getInt("num"));
				p.setTitle(rs.getString("title"));
				p.setContent(rs.getString("content"));
				List.add(p);
			}
		}catch(Exception e) {
			System.out.println("pageDAO.findList() 접속중 오류발생 : "+e);
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception ex) {
				System.out.println("pageDAO.findList() 접속종료중 오류발생 : "+ex);
			}
		}
		
		return List;
	}
	//전체 자료의 갯수를 가져오는 동작
	public int getNumberOfRows() {
		String sql="select count(num) from page;";
		int numberOfRows=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			rs.next();
			numberOfRows=Integer.parseInt(rs.getString(1));
		}catch(Exception e) {
			System.out.println("pDAO.getNumberOfRows 접속 중 오류발생 : "+e);
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception ex) {
				System.out.println("pDAO.getNumberOfRows 종료 중 오류발생 : "+ex);
			}
		}
		return numberOfRows;
	}
}
