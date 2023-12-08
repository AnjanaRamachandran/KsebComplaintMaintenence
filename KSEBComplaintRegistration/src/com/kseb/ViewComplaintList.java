package com.kseb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/viewcomplaint")
public class ViewComplaintList extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	String usertype = null;
	String username = null;
	RequestDispatcher dis = null;
	DBConnection dbcon1 = new DBConnection();
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();

			HttpSession session = request.getSession();
			String name = (String) (session.getAttribute("uname"));

					

			con = dbcon1.getConnection();
			pst = con.prepareStatement("select * from complaint_details");
			rs=pst.executeQuery();
			pw.print("<html><body>");
			pw.print("<link rel='stylesheet' type='text/css' href='css/viewcomplaint.css'>");
			pw.print("<h1>Complaint List</h1>");
			pw.print("<table border='1'>");
			pw.print("<tr><th>Complaint_id</th><th>Date</th><th>Consumer</th><th>Address</th><th>Complaint</th></tr>");
			
			while(rs.next()){
				pw.print("<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getDate(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td></tr>");
			}
			pw.print("</table>");
			pw.print("</body></html>");

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

