package DAO;

import java.sql.*;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;

import beans.User;
import beans.Comment;
import beans.Notice;

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
	//ȸ�����Կ� ���̵� �ߺ�üũ ���� ���
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
		return cheak;
	}
	//ȸ�����Կ� �г��� �ߺ�üũ ���� ���
		public boolean nickCheak(String nick) {
			String sql="select * from user where nick=?";
			boolean cheak = false;
			Connection conn=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try {
				conn=getConnection();
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1,nick);
				rs=pstmt.executeQuery();
				
				cheak=rs.next();
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
			return cheak;
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
	
//	=========================�Խ��ǿ� ���� �κ�============================
	
	//�Խ��� ī�װ��� �з� �ؼ� �����ֱ� �κ�
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
	//�Խ��� ������ ī�׷θ��� �з� �ؼ� �����ֱ� �κ�
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
	//�Խù� ������Ʈ ���
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
	//�Խù� �����ϴ� ���
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
	//�Խù� ������ ���� ������Ʈ ��� ���� �κ�(�����ͺ��̽� ���� ��ȸ ���)
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
	//���� �ø� �Խù� ���� ��� (�����ͺ��̽����� �� ���̵�� �˻�)
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
	//�� ����������� ������ ������ �Խù� ���
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
//		=========================��ۿ� ���� �κ�============================
	
//��� �����ֱ� �κ�
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
	//��� �ۼ��ϴ� �κ�
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
	//��� �����ִ� �κ�
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
	//��� �ۼ� �κ�
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
	//��� ���� ��� 
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
	//========================�Խ��� ������ �ѱ�� ���============================
	//ī�װ��� ������ �������� ����
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
			System.out.println("UserDAO.getCategoryOfRows ���� �� �����߻� : "+e);
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception ex) {
				System.out.println("UserDAO.getCategoryOfRows ���� �� �����߻� : "+ex);
			}
		}
		return numberOfRows;
	}
	//���̵�ΰ˻��Ͽ� ������ ������ �������� ����
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
			System.out.println("UserDAO.getCategoryOfRows ���� �� �����߻� : "+e);
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception ex) {
				System.out.println("UserDAO.getCategoryOfRows ���� �� �����߻� : "+ex);
			}
		}
		return numberOfRows;
	}
	//ī�װ��� ������ ���� �˻��ϴ�  ������ ������ �������� ����
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
			System.out.println("UserDAO.getRegionOfRows ���� �� �����߻� : "+e);
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception ex) {
				System.out.println("UserDAO.getRegionOfRows ���� �� �����߻� : "+ex);
			}
		}
		return numberOfRows;
	}
	
		
}