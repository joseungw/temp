package DAO;

import java.sql.*;
import java.util.ArrayList;

import beans.User;
import beans.Notice;

public class UserDAO {
	private UserDAO() {
		
	}
	public static UserDAO instance=new UserDAO();
	
	public static UserDAO getInstance() {
		return instance;			//싱글톤
	}
	//커넥션 연결 처리문
	public Connection getConnection() throws Exception {
		Connection conn=null;
		String url="jdbc:mysql://127.0.0.1:3306/project";
		String dbid="root";
		String dbpw="iotiot";
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn=DriverManager.getConnection(url,dbid,dbpw);
		return conn;
	}
	//로그인 인증 처리
	public int userCheak(String id,String password) {
		int result=-1;
		String sql="select password from user where id=?;";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,id);
			rs=pstmt.executeQuery();
			if(rs.next()) {	//해당 아이디가 존재 한다면
				if(rs.getString("password").equals(password) && rs.getString("password")!=null) {	//비밀번호가 일치 한다면
					result=1;	//아이디 비밀번호 일치하는 경우
				}else {
					result=0;	//비밀번호가 일치하지 않은 경우
				}
			}else {
				result=-1;		//아이디가 일치하지 않은 경우
			}
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
		return result;
	}
	//사용자 정보 조회
	public User getUser(String id) {
		User u=new User();
		String sql="select * from user where id=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			rs.next();
			u.setId(rs.getString("id"));
			u.setNick(rs.getString("nick"));
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
		return u;
	}
	//게시판 카테고리로 분류 해서 보여주기 부분
	public ArrayList<Notice> getNotiCate(String category) {
		ArrayList<Notice> list=new ArrayList<Notice>();
		Notice nt=null;
		String sql="select * from Notice where category=?";
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, category);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				nt=new Notice();
				nt.setNotenum(rs.getInt("notenum"));
				nt.setUserid(rs.getString("userid"));
				nt.setUsernick(rs.getString("usernick"));
				nt.setCategory(rs.getString("category"));
				nt.setNotedate(rs.getString("notedate"));
				nt.setTitle(rs.getString("title"));
				nt.setContents(rs.getString("contents"));
				nt.setRegion(rs.getString("region"));
				list.add(nt);
			}
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
		return list;
	}
}
