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

@WebServlet("/viewmaterialreq")
public class ViewMaterialRequest extends HttpServlet {

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
			pst = con.prepareStatement("select * from materialrequest_details");
			rs=pst.executeQuery();
			pw.print("<html><body>");
			pw.print("<link rel='stylesheet' type='text/css' href='css/viewcomplaint.css'>");
			pw.print("<h1>Request List</h1>");
			pw.print("<table border='1'>");
			pw.print("<tr><th>Request_Id</th><th>Date</th><th>Allocation No</th><th>Materials</th></tr>");
			
			while(rs.next()){
				pw.print("<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getDate(2)+"</td><td>"+rs.getInt(3)+"</td><td>"+rs.getString(4)+"</td></tr>");
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


