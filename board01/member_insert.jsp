<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>회원정보 입력 페이지</title>
	</head>
	<body>
		<%
			Connection conn=null;
			//Statement stmt=null;	: 회선 내부에서 이동하면서 sql문과 ResultSet을 전달
			PreparedStatement pstmt=null;
			
			String url="jdbc:mysql://127.0.0.1:3306/mydb";
			String dbid="root";
			String dbpw="iotiot";
			
			//PreparedStatement 방식의 query문 제작
			String sql="insert into member(name,userid,pwd,email,phone,admin)";
			sql+="values (?,?,?,?,?,?)";
			
			//String sql="insert into member(name, userid,pwd,email,phone,admin)";
			
			request.setCharacterEncoding("utf-8");
			
			String name=request.getParameter("name");
			String userid=request.getParameter("userid");
			String pwd=request.getParameter("pwd");
			String email=request.getParameter("email");
			String phone=request.getParameter("phone");
			String admin=request.getParameter("admin");
			
			//sql+="values('"+name+"','"+userid+"','"+pwd+"','"+email+"','"+phone+"','"+admin+"');";
			
			try{
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn=DriverManager.getConnection(url,dbid,dbpw);
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1,name);
				pstmt.setString(2,userid);
				pstmt.setString(3,pwd);
				pstmt.setString(4,email);
				pstmt.setString(5,phone);
				pstmt.setString(6,admin);
				pstmt.execute();
				//stmt.execute(sql);
			}catch(Exception e)
			{
				out.print("<h1>member_insert.jsp에서 오류발생 : "+e+"</h1>");
			}finally{
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}
		%>
	</body>
</html>