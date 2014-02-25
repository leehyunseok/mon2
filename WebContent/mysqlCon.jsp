<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="java.sql.*"%>
<%
	Connection conn=null; 
 Statement stmt = null;
 ResultSet rs = null ;
 
 
 try{
		Class.forName("com.mysql.jdbc.Driver");
		String jdbcDriver = "jdbc:mysql://localhost:3306/mon";
		String dbUser = "root";
		String dbPass = "shellsn";
		conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);

		out.println("연결 성공");

		stmt = conn.createStatement();

		String query = "SELECT * from mon_list";

		rs = stmt.executeQuery(query);
		String ttt;

		while (rs.next()) {

			out.println(rs.getString(1));
			out.println(rs.getString(2));
			out.println(rs.getString(3));
			out.println(rs.getString(4));
			out.println(rs.getString(5));
			out.println(rs.getString(6));

		}

	} catch (Exception e) {
		out.println("연결 실패");
		e.printStackTrace();
	} finally {
		conn.close();
	}
%>   

