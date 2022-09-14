<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>회원가입 처리페이지</title>
	</head>
	<body>
		<%
			Connection conn=null;
			PreparedStatement pstmt=null;
			
			String url="jdbc:mysql://127.0.0.1:3306/project";
			String dbid="root";
			String dbpw="iotiot";
			
			request.setCharacterEncoding("utf-8");
			
			String name=request.getParameter("name");
			String id=request.getParameter("id");
			String pass=request.getParameter("pass");
			String city=request.getParameter("city");
			String sports=request.getParameter("sports");
			String phone=request.getParameter("phone");
			String nick=request.getParameter("nick");
			
			String sql="insert into user(name, phone,id,password,nick,city,sports)";
			sql+="values (?,?,?,?,?,?,?)";
			
			try{
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn=DriverManager.getConnection(url,dbid,dbpw);
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1,name);
				pstmt.setString(2,phone);
				pstmt.setString(3,id);
				pstmt.setString(4,pass);
				pstmt.setString(5,nick);
				pstmt.setString(6,city);
				pstmt.setString(7,sports);
				pstmt.execute();
			}catch(Exception e){
				out.print("<h1>register.jsp에서 데이터베이스 연결 중 오류 발생 : "+e+"</h1>");
			}finally{
				try{
					if(pstmt!=null)pstmt.close();
					if(conn!=null)conn.close();
				}catch(Exception ex){
					out.print("<h1>register.jsp에서 데이터베이스 종료 중 오류 발생 : "+ex+"</h1>");
				}
			}
			response.sendRedirect("login.do");
		%>
		
	</body>
</html>