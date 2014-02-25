<%@ page contentType="text/html; charset=euc-kr" %>
<%@ page import="java.sql.*"%>
<%
	Connection conn=null; 
 Statement stmt = null;
 ResultSet rs = null ;
 
 
 try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String jdbcDriver = "jdbc:oracle:thin:@10.200.2.124:1521:HDSTDS";
		String dbUser = "HDABD";
		String dbPass = "xsw21qaz";
		conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);

		out.println("연결 성공");

		
		stmt = conn.createStatement();

		String query = "SELECT * from ARAP001M_TMP";

		rs = stmt.executeQuery(query);

		out.println(query);

		while (rs.next()) {
			out.println(rs.getString(1));

		}
		

	} catch (Exception e) {
		out.println("연결 실패");
		e.printStackTrace();
	} finally {
		conn.close();
	}
%>   

