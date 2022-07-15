package DAO;
import java.sql.*;
import Beans.Member;

public class MemberDAO {
	private MemberDAO() {
		
	}
	private static MemberDAO instance=new MemberDAO();
	
	public static MemberDAO getInstance() {
		return instance;
	}
	
	public Connection getConnection() throws Exception{
		Connection conn=null;
		String url="jdbc:mysql://127.0.0.1:3306/mydb";
		String dbid="root";
		String dbpw="iotiot";
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn=DriverManager.getConnection(url,dbid,dbpw);
		
		return conn;
	}
	//����� ����ó��(�α���)
	public int userCheck(String userid, String pwd) {
		int result=-1;
		String sql="select pwd from member where userid=?;";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs=pstmt.executeQuery();
			if(rs.next()) {//�ش� ���̵� �����ϴ� ���
				if(rs.getString("pwd").equals(pwd) && rs.getString("pwd")!=null) {
					//���̵� �����ϰ� ��й�ȣ�� ��ġ�ϴ� ���
					result=1;
				}else {
					//��й�ȣ�� ���ų� ��й�ȣ�� ��ġ���� ���� ���
					result=0;
				}
			}else {//�ش� ���̵� �������� �ʴ� ���
				result=-1;
			}
		}catch(Exception e) {
			System.out.println("�α��� �� �����߻� : "+e);
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception ex) {
				System.out.println("�α��� ���� �� �����߻� : "+ex);
			}
		}
		
		return result;//���� ����� return
	}
	//����� ���� ��ȸ
	public Member getMember(String userid) {
		Member m=null;
		String sql="select * from member where userid=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				m=new Member();
				m.setNum(rs.getString("num"));
				m.setName(rs.getString("name"));
				m.setUserid(rs.getString("userid"));
				m.setPwd(rs.getString("pwd"));
				m.setEmail(rs.getString("email"));
				m.setPhone(rs.getString("phone"));
				m.setAdmin(rs.getString("admin"));
			}
		}catch(Exception e) {
			System.out.println("������� ��ȸ�� �����߻� : "+e);
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception ex) {
				System.out.println("������� ��ȸ ���� �� �����߻� : "+ex);
			}
		}
		return m;
	}
	
	//���̵� �ߺ�üũ ó���� ������ �޼���
	public int confirmID(String userid) {
		int result=-1;
		String sql="select userid from member where userid=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=1;	//���̵� �����ϴ� ���
			}else {
				result=-1;	//���̵� �������� �ʴ� ���
			}
		}catch(Exception e) {
			System.out.println("MemberDAO.confirmID()���� ���� �� �����߻� : "+e);
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception ex) {
				System.out.println("MemberDAO.confirmID()���� ���� ���� �� �����߻� : "+ex);
			}
		}
		return result;
	}
	//ȸ�������� ������ �޼���
	public int insertMember(Member m) {
		int result=-1;
		String sql="insert into member (name,userid,pwd,email,phone,admin) values (?,?,?,?,?,?)";
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, m.getName());
			pstmt.setString(2, m.getUserid());
			pstmt.setString(3, m.getPwd());
			pstmt.setString(4, m.getEmail());
			pstmt.setString(5, m.getPhone());
			pstmt.setString(6, m.getAdmin());
			result=pstmt.executeUpdate();	//����� row���� return���̹Ƿ� insert���� �׻� 1�� ��ȯ
		}catch(Exception e) {
			System.out.println("MemberDAO.insertMember() ������ ���� �߻�: "+e);
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception ex) {
				System.out.println("MemberDAO.insertMember() ���� �� ���� �߻�: "+ex);
			}
		}
		return result;
	}
	//ȸ�� ���� ���� ����
	public int updateMember(Member m) {
		int result=-1;
		String sql="update member set pwd=?, email=?, phone=?, admin=? where userid=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, m.getPwd());
			pstmt.setString(2, m.getEmail());
			pstmt.setString(3, m.getPhone());
			pstmt.setString(4, m.getAdmin());
			pstmt.setString(5, m.getUserid());
			System.out.println(m.getPwd()+","+m.getEmail()+","+m.getPhone()+","+m.getAdmin()+","+m.getUserid());
			result=pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("DAO.updateMember() ������ ���� ���� �߻� : "+e);
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception ex) {
				System.out.println("DAO.updateMember() ������ �������� ���� �߻� : "+ex);
			}
		}
		return result;
	}
}
