package DAO;

import java.sql.*;
import beans.User;

public class UserDAO {
	private UserDAO() {
		
	}
	public static UserDAO instance=new UserDAO();
	
	public static UserDAO getInstance() {
		return instance;			//�̱���
	}
	//Ŀ�ؼ� ���� ó����
	public Connection getConnection() throws Exception {
		Connection conn=null;
		String url="jdbc:mysql://127.0.0.1:3306/project";
		String dbid="root";
		String dbpw="iotiot";
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn=DriverManager.getConnection(url,dbid,dbpw);
		return conn;
	}
	//�α��� ���� ó��
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
			if(rs.next()) {	//�ش� ���̵� ���� �Ѵٸ�
				if(rs.getString("password").equals(password) && rs.getString("password")!=null) {	//��й�ȣ�� ��ġ �Ѵٸ�
					result=1;	//���̵� ��й�ȣ ��ġ�ϴ� ���
				}else {
					result=0;	//��й�ȣ�� ��ġ���� ���� ���
				}
			}else {
				result=-1;		//���̵� ��ġ���� ���� ���
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
	//����� ���� ��ȸ
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
	//�Խ��� �ۼ� insert�κ�
//	public User getNotice(String userid, String usernick, String category, String notedate,String title, String contents, String region) {
//		
//	}
}
