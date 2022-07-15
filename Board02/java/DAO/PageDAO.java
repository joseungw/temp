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
	//Ŀ�ؼ� ��� ó��
	public Connection getConnection() throws Exception{
		Connection conn=null;
		String url="jdbc:mysql://127.0.0.1:3306/study";
		String id="root";
		String pw="iotiot";
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn=DriverManager.getConnection(url,id,pw);
		return conn;
	}
	//��� ����� ���� ���� ó��
	public List<PageDTO> findList(int currentPage, int recordsPerPage){
		List<PageDTO> List=new ArrayList<PageDTO>();
		int start=currentPage*recordsPerPage-recordsPerPage;
		//���� ������ ������ ������ �ڷ� ���� ���� �ڿ� ������ �� �ڷ� ���� ���ָ� �������� Ư���� �� �ִ�.
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
			System.out.println("pageDAO.findList() ������ �����߻� : "+e);
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception ex) {
				System.out.println("pageDAO.findList() ���������� �����߻� : "+ex);
			}
		}
		
		return List;
	}
	//��ü �ڷ��� ������ �������� ����
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
			System.out.println("pDAO.getNumberOfRows ���� �� �����߻� : "+e);
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception ex) {
				System.out.println("pDAO.getNumberOfRows ���� �� �����߻� : "+ex);
			}
		}
		return numberOfRows;
	}
}
