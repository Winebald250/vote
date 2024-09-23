package servlet3;

import java.io.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Servlet implementation class servlet1
 */
public class servlet3 extends HttpServlet {
	static final String DB_URL ="jdbc:mysql://localhost/Online_Voting_System";
	static final String USER = "root";
	static final String PASS="Utkarsha@06";
	private static final long serialVersionUID = 1L;
  
	public static int id;
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		try{
			
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		Class.forName("com.mysql.jdbc.Driver");
		conn=DriverManager.getConnection(DB_URL,USER,PASS);
		String Email=request.getParameter("mail");
		String p=request.getParameter("lock-closed");
		id= Integer.parseInt(request.getParameter("id"));
		
		PreparedStatement prep_stmt;
		prep_stmt=conn.prepareStatement("select EMAILID from candidate where EMAILID=? and PASSWD=? and candidate_id=?");
		prep_stmt.setString(1, Email);
		prep_stmt.setString(2, p);
		prep_stmt.setInt(3, id);
		ResultSet rs=prep_stmt.executeQuery();
		if(rs.next()) {
			RequestDispatcher rd=request.getRequestDispatcher("aftercandidate.jsp");
			rd.forward(request, response);
			
		}
		else {
			RequestDispatcher rd=request.getRequestDispatcher("loginfailed.jsp");
			rd.forward(request, response);
			
		}
		}
		catch(ClassNotFoundException e)
		{
		e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

}