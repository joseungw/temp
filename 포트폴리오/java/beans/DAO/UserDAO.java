package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;

import beans.User;
import beans.Comment;
import beans.Notice;

public class UserDAO {
	private UserDAO() {
		
	}
	public static UserDAO instance=new UserDAO();
	
	public static UserDAO getInstance() {
		return instance;			//싱글톤으로 새로운 객체 생성제한 
	}
	//거넥션 연결 처리문
	public Connection getConnection() throws Exception {
		Connection conn=null;
		String url="jdbc:mysql://127.0.0.1:3306/project";
		String dbid="root";
		String dbpw="iotiot";
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn=DriverManager.getConnection(url,dbid,dbpw);
		return conn;
	}
	//로그인 인증 처리 기능
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
				if(rs.getString("password").equals(password) && rs.getString("password")!=null) {	//��й�ȣ�� ��ġ �Ѵٸ�
					result=1;	//아이디와 비밀번호 둘다 일치하는 경우
				}else {
					result=0;	//비밀번호가 일치 하지 않은 경우
				}
			}else {
				result=-1;		//아이디가 일치 하지 않은 경우
			}
		}catch(Exception e) {
			System.out.println("UserDAO.usercheak() 데이터베이스 연결 중 오류 : "+e);
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception ex) {
				System.out.println("UserDAO.usercheak() 데이터베이스 연결 해제 중 오류 : "+ex);
			}
			
		}
		return result;
	}
	//회원가입에 아이디 중복체크 구현 기능
	public boolean idCheak(String id) {
		String sql="select * from user where id=?";
		boolean cheak = false;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,id);
			rs=pstmt.executeQuery();
			
			cheak=rs.next();
		}catch(Exception e) {
			System.out.println("UserDAO.idCheak 데이터베이스 연결 중 오류 : "+e);
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception ex) {
				System.out.println("UserDAO.idCheak 데이터베이스 연결 해제 중 오류 : "+ex);
			}
			
		}
		return cheak;
	}
	//회원가입에 닉네임 중복체크 구현 기능 // boolean타입
		public boolean nickCheak(String nick) {
			String sql="select * from user where nick=?";
			boolean check = false;
			Connection conn=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try {
				conn=getConnection();
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1,nick);
				rs=pstmt.executeQuery();
				
				check=rs.next();
			}catch(Exception e) {
				System.out.println("UserDAO.nickCheak");
			}finally {
				try {
					if(rs!=null)rs.close();
					if(pstmt!=null)pstmt.close();
					if(conn!=null)conn.close();
				}catch(Exception ex) {
					ex.printStackTrace();
				}
				
			}
			return check;
		}
	//사용자 정보 조회 // User타입
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
			u.setName(rs.getString("name"));
			u.setPhone(rs.getString("phone"));
			u.setId(rs.getString("id"));
			u.setNick(rs.getString("nick"));
			u.setSports(rs.getString("sports"));
			u.setCity(rs.getString("city"));
			u.setPassword(rs.getString("password"));
			u.setNum(rs.getInt("num"));
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
	//사용자 정보 수정을 위한 기능 부분
	public void UserUpdate(User u) {
		String sql="update User set name=?, phone=?, id=?, password=?, nick=?,";
		sql+=" city=?, sports=? where num=?";
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, u.getName());
			pstmt.setString(2, u.getPhone());
			pstmt.setString(3, u.getId());
			pstmt.setString(4, u.getPassword());
			pstmt.setString(5, u.getNick());
			pstmt.setString(6, u.getCity());
			pstmt.setString(7, u.getSports());
			pstmt.setInt(8, u.getNum());
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	//================관리자 부분=============================
	//회원 목록을 보기위한 기능
	public List<User> allUser(int currentPage) {
		List<User> list=new ArrayList<User>();
		int start=currentPage*10-10;
		String sql="select * from user limit ?,10";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				User u=new User();
				u.setNum(rs.getInt("num"));
				u.setName(rs.getString("name"));
				u.setPhone(rs.getString("phone"));
				u.setId(rs.getString("id"));
				u.setPassword(rs.getString("password"));
				u.setNick(rs.getString("nick"));
				u.setCity(rs.getString("city"));
				u.setSports(rs.getString("sports"));
				list.add(u);
			}
			rs.next();
			
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
	//회원 목록에서 삭제하는 기능 구현부분
	public void UserDelete(String num) {
		String sql="delete from user where num=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, num);
			pstmt.executeUpdate();
			//executeUpdate() : return Integer 변경내역이 여러줄일 경우에 사용
		}catch(Exception e) {
			System.out.println("회원 삭제 중 오류 : "+e);
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception ex) {
				ex.printStackTrace();
				System.out.println("회원 삭제 종료 중 오류 : "+ex);
			}
		}
	}
	
//	=========================게시판에 대한 부분============================
	
	//게시판 카테고리로 분류해서 보여주기 기능 구현 // desc로 내림차순으로 보여주기 구현
	public ArrayList<Notice> getNotiCate(String category, int currentPage) {
		ArrayList<Notice> list=new ArrayList<Notice>();
		Notice nt=null;
		int start=currentPage*10-10;
		String sql="select * from Notice where category=? order by notenum desc limit ?, 10";
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, category);
			pstmt.setInt(2, start);
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
	//게시판 지역과 카테고리로 분류해서 보여주기 기능 구현 // 내림차순으로
		public ArrayList<Notice> selectTwo(String category,String region,int currentPage) {
			ArrayList<Notice> list=new ArrayList<Notice>();
			Notice nt=null;
			int start=currentPage*10-10;
			String sql="select * from Notice where category=? and region=? order by notenum desc limit ?,10";
			
			Connection conn=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			
			try {
				conn=getConnection();
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, category);
				pstmt.setString(2, region);
				pstmt.setInt(3, start);
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
	//게시물 업데이트 기능
	public void noticeUpdate(Notice notice) {
		String sql="update notice set category=?, notedate=?, ";
		sql+="title=?, contents=?, region=? where notenum=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, notice.getCategory());
			pstmt.setString(2, notice.getNotedate());
			pstmt.setString(3, notice.getTitle());
			pstmt.setString(4, notice.getContents());
			pstmt.setString(5, notice.getRegion());
			pstmt.setInt(6, notice.getNotenum());
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	//게시물 삭제하는 기능
	public void noticeDelete(String notenum) {
		String sql="delete from notice where notenum=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, notenum);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	//게시물 수정을 위한 업데이트 기능 구현 부분(데이터 베이스에서 notenum으로 개별조회 기능)
	public Notice selectNotice(String notenum) {
		Notice notice=null;
		String sql="select * from notice where notenum=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, notenum);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				notice=new Notice();
				notice.setNotenum(rs.getInt("notenum"));
				notice.setUserid(rs.getString("userid"));
				notice.setUsernick(rs.getString("usernick"));
				notice.setCategory(rs.getString("category"));
				notice.setNotedate(rs.getString("notedate"));
				notice.setTitle(rs.getString("title"));
				notice.setContents(rs.getString("contents"));
				notice.setRegion(rs.getString("region"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
				if(rs!=null)rs.close();
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		return notice;
	}
	//내가 올린 게시물 보기 기능 (데이터 베이스에서 내 아이디로 검색)
	public ArrayList<Notice> MySelect(String userid, int currentPage) {
		ArrayList<Notice> list=new ArrayList<Notice>();
		Notice nt=null;
		int start=currentPage*10-10;
		String sql="select * from Notice where userid=? order by notenum desc limit ?,10";
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setInt(2, start);
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
	//내가 지정한 카테고리와 지역으로만 게시물 보여주는 기능 
	public ArrayList<Notice> myImport( String category, String region, int currentPage) {
		ArrayList<Notice> list=new ArrayList<Notice>();
		Notice mynt=null;
		int start=currentPage*10-10;
		String sql="select * from Notice where category=? and region=? order by notenum desc limit ?,10";
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, category);
			pstmt.setString(2, region);
			pstmt.setInt(3, start);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				mynt=new Notice();
				mynt.setNotenum(rs.getInt("notenum"));
				mynt.setUserid(rs.getString("userid"));
				mynt.setUsernick(rs.getString("usernick"));
				mynt.setCategory(rs.getString("category"));
				mynt.setNotedate(rs.getString("notedate"));
				mynt.setTitle(rs.getString("title"));
				mynt.setContents(rs.getString("contents"));
				mynt.setRegion(rs.getString("region"));
				list.add(mynt);
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
//		========================댓글에 대한 부분============================
	
//댓글 보여주기 부분
	public ArrayList<Comment> selCom(String postnum) {
		ArrayList<Comment> cmlist=new ArrayList<Comment>();
		Comment cmt=null;
		String sql="select * from comment where postnum=?";
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, postnum);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				cmt=new Comment();
				cmt.setComnum(rs.getInt("comnum"));
				cmt.setPostnum(rs.getString("postnum"));
				cmt.setComid(rs.getString("comid"));
				cmt.setComnick(rs.getString("comnick"));
				cmt.setComcon(rs.getString("comcon"));
				cmt.setComdate(rs.getString("comdate"));
				cmt.setRecomnum(rs.getString("recomnum"));
				cmlist.add(cmt);
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
		return cmlist;
	}
	//댓글 작성 하는 부분
	public void CommentShow(Comment c) {
		String sql="insert into comment (postnum,comid,comnick,comcon,comdate,recomnum) ";
		sql+="values (?,?,?,?,?,?)";
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, c.getPostnum());
			pstmt.setString(2, c.getComid());
			pstmt.setString(3, c.getComnick());
			pstmt.setString(4, c.getComcon());
			pstmt.setString(5, c.getComdate());
			pstmt.setString(6, c.getRecomnum());
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	//답글 보여주는 부분
	public ArrayList<Comment> selectRecomment(int recomnum) {
		ArrayList<Comment> recmtlist=new ArrayList<Comment>();
		Comment recmt=null;
		String sql="select * from comment where recomnum=?";
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, recomnum);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				recmt=new Comment();
				recmt.setComnum(rs.getInt("comnum"));
				recmt.setPostnum(rs.getString("postnum"));
				recmt.setComid(rs.getString("comid"));
				recmt.setComnick(rs.getString("comnick"));
				recmt.setComcon(rs.getString("comcon"));
				recmt.setComdate(rs.getString("comdate"));
				recmt.setRecomnum(rs.getString("recomnum"));
				recmtlist.add(recmt);
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
		return recmtlist;
	}
	//답글 작성 부분
	public void recommentWrite(Comment c) {
		String sql="insert into comment (postnum,comid,comnick,comcon,comdate,recomnum) ";
		sql+="values (?,?,?,?,?,?)";
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, c.getPostnum());
			pstmt.setString(2, c.getComid());
			pstmt.setString(3, c.getComnick());
			pstmt.setString(4, c.getComcon());
			pstmt.setString(5, c.getComdate());
			pstmt.setString(6, c.getRecomnum());
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	//댓글 삭제 기능
	public void commentDelete(String comnum) {
		String sql="delete from comment where comnum=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, comnum);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	//=======================게시판 페이지 넘기기 기능============================
	//카테고리의 갯수를 가져오는 동작
	public int getCategoryOfRows(String category) {
		String sql="select count(notenum) from notice where category=?;";
		int numberOfRows=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,category);
			rs=pstmt.executeQuery();
			rs.next();
			numberOfRows=Integer.parseInt(rs.getString(1));
		}catch(Exception e) {
			System.out.println("UserDAO.getCategoryOfRows 접속 중 오류 발생 : "+e);
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception ex) {
				System.out.println("UserDAO.getCategoryOfRows 종료 중 오류 뱔생 : "+ex);
			}
		}
		return numberOfRows;
	}
	//아이디로 갯수를 가져오는 동작
	public int getUseridOfRows(String userid) {
		String sql="select count(notenum) from notice where userid=?;";
		int numberOfRows=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,userid);
			rs=pstmt.executeQuery();
			rs.next();
			numberOfRows=Integer.parseInt(rs.getString(1));
		}catch(Exception e) {
			System.out.println("UserDAO.getCategoryOfRows 접속 중 오류 발생 : "+e);
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception ex) {
				System.out.println("UserDAO.getCategoryOfRows 종료 중 오류 뱔생 : "+ex);
			}
		}
		return numberOfRows;
	}
	//카테고리와 지역을 같이 검색하는 페이지 갯수를 가져오는 동작
	public int getRegionOfRows(String region,String category) {
		String sql="select count(notenum) from notice where category=? and region=?;";
		int numberOfRows=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,category);
			pstmt.setString(2, region);
			rs=pstmt.executeQuery();
			rs.next();
			numberOfRows=Integer.parseInt(rs.getString(1));
		}catch(Exception e) {
			System.out.println("UserDAO.getRegionOfRows 접속 중 오류 발생 : "+e);
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception ex) {
				System.out.println("UserDAO.getRegionOfRows 종료 중 오류 뱔생 : "+ex);
			}
		}
		return numberOfRows;
	}
	//관리자 페이지에서 회원 목록 검색할 떄 페이지 갯수를 가져오는 동작
	public int getUserOfRows() {
		String sql="select count(num) from user;";
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
			System.out.println("UserDAO.getRegionOfRows 접속 중 오류 발생 : "+e);
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception ex) {
				System.out.println("UserDAO.getRegionOfRows 종료 중 오류 뱔생 : "+ex);
			}
		}
		return numberOfRows;
	}
		
}