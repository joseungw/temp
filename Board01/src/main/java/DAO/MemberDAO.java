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
	//사용자 인증처리(로그인)
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
			if(rs.next()) {//해당 아이디가 존재하는 경우
				if(rs.getString("pwd").equals(pwd) && rs.getString("pwd")!=null) {
					//아이디가 존재하고 비밀번호가 일치하는 경우
					result=1;
				}else {
					//비밀번호가 없거나 비밀번호가 일치하지 않은 경우
					result=0;
				}
			}else {//해당 아이디가 존재하지 않는 경우
				result=-1;
			}
		}catch(Exception e) {
			System.out.println("로그인 중 오류발생 : "+e);
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception ex) {
				System.out.println("로그인 종료 중 오류발생 : "+ex);
			}
		}
		
		return result;//계산된 결과를 return
	}
	//사용자 정보 조회
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
			System.out.println("멤버정보 조회중 오류발생 : "+e);
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception ex) {
				System.out.println("멤버정보 조회 종료 중 오류발생 : "+ex);
			}
		}
		return m;
	}
	
	//아이디 중복체크 처리를 진행할 메서드
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
				result=1;	//아이디가 존재하는 경우
			}else {
				result=-1;	//아이디가 존재하지 않는 경우
			}
		}catch(Exception e) {
			System.out.println("MemberDAO.confirmID()에서 접속 중 오류발생 : "+e);
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception ex) {
				System.out.println("MemberDAO.confirmID()에서 접속 종료 중 오류발생 : "+ex);
			}
		}
		return result;
	}
	//회원가입을 실행할 메서드
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
			result=pstmt.executeUpdate();	//변경된 row수가 return값이므로 insert문은 항상 1을 반환
		}catch(Exception e) {
			System.out.println("MemberDAO.insertMember() 실행중 오류 발생: "+e);
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception ex) {
				System.out.println("MemberDAO.insertMember() 종료 중 오류 발생: "+ex);
			}
		}
		return result;
	}
	//회원 정보 변경 가능
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
			System.out.println("DAO.updateMember() 수행중 접속 오류 발생 : "+e);
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception ex) {
				System.out.println("DAO.updateMember() 수행중 접속종료 오류 발생 : "+ex);
			}
		}
		return result;
	}
}
