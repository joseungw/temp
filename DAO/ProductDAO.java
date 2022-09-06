package DAO;

import java.sql.*;
import java.util.*;


import DTO.ProductDTO;

public class ProductDAO {
	private ProductDAO() {}
	private static ProductDAO instance=new ProductDAO();
	public static ProductDAO getInstance() {
		return instance;
	}
	//커넥션 기능 처리 메서드
	public Connection getConnection() throws Exception{
		Connection conn=null;
		String url="jdbc:mysql://127.0.0.1:3306/study";
		String id="root";
		String pw="iotiot";
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn=DriverManager.getConnection(url,id,pw);
		return conn;
	}
	//커넥션 연결해제 처리 메서드
	public static void close(Connection conn,Statement stmt, ResultSet rs) {
		try {
			rs.close();
			stmt.close();
			conn.close();
		}catch(Exception e) {
			System.out.println("연결해제중 오류발생 : "+e);
		}
	}
	public static void close(Connection conn, Statement stmt) {
		try {
			stmt.close();
			conn.close();
		}catch(Exception e) {
			System.out.println("연결해제중 오류발생 : "+e);
		}
	}
	//데이터베이스 조회 기능
	public List<ProductDTO> selectAllProduct(){
		List<ProductDTO> list=new ArrayList<ProductDTO>();
		String sql="select * from product order by code desc";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				ProductDTO p=new ProductDTO();
				p.setCode(rs.getInt("code"));
				p.setName(rs.getString("name"));
				p.setPrice(rs.getInt("price"));
				p.setPictureurl(rs.getString("pictureurl"));
				p.setDescription(rs.getString("description"));
				list.add(p);
			}
		}catch(Exception e) {
			System.out.println("전체조회 중 오류발생 : "+e);
		}finally {
			ProductDAO.close(conn, pstmt, rs);
		}
		return list;
	}
	//데이터베이스 입력기능(상품등록)
	public void insertProduct(ProductDTO p) {
		String sql="insert into product (name,price,pictureurl,description) values (?,?,?,?)";
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, p.getName());
			pstmt.setInt(2,p.getPrice());
			pstmt.setString(3, p.getPictureurl());
			pstmt.setString(4, p.getDescription());
			pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("제품입력 중 오류발생 : "+e);
		}finally {
			ProductDAO.close(conn, pstmt);
		}
	}
	//데이터베이스 개별 조회 기능(개별상품 보기)
	public ProductDTO selectProduct(String code) {
		ProductDTO DTO=null;
		String sql="select * from product where code=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, code);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				DTO=new ProductDTO();
				DTO.setCode(rs.getInt("code"));
				DTO.setName(rs.getString("name"));
				DTO.setPrice(rs.getInt("price"));
				DTO.setPictureurl(rs.getString("pictureurl"));
				DTO.setDescription(rs.getString("description"));
				
			}
		}catch(Exception e) {
			System.out.println("개별상품 조회중 오류 발생 : "+e);
		}finally {
			ProductDAO.close(conn, pstmt,rs);
		}
		return DTO;
	}
	//데이터ㅂ베이스 수정 기능(상품 수정)
	public void updateProduct(ProductDTO DTO) {
		String sql="update product set name=?, price=?,pictureurl=?, description=? where code=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, DTO.getName());
			pstmt.setInt(2, DTO.getPrice());
			pstmt.setString(3, DTO.getPictureurl());
			pstmt.setString(4, DTO.getDescription());
			pstmt.setInt(5, DTO.getCode());
			pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("수정 중 오류발생 : "+e);
		}finally {
			ProductDAO.close(conn, pstmt);
		}
	}
	//데이터베이스 삭제 기능(상품제거)
	public void deleteProduct(String code) {
		String sql="delete from product where code=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, code);
			pstmt.executeUpdate();
			//executeUpdate() : return Integer 변경내역이 여러줄일 경우에 사용
		}catch(Exception e) {
			System.out.println("상품 삭제 중 오류 발생 : "+e);
		}finally {
			ProductDAO.close(conn, pstmt);
		}
	}
}
