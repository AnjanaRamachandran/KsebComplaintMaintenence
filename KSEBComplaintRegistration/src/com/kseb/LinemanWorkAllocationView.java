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

@WebServlet("/allocation")
public class LinemanWorkAllocationView extends HttpServlet {

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

			pw.print("<html>");
			pw.print("<head>");
			pw.print("<link rel='stylesheet' type='text/css' href='css/viewworks.css'>");
			pw.print("<body>");
			pw.print("<nav>");
			pw.print("<ul>");
			pw.print("<li><a href='materialrequest'>Material Request</a></li>");
			pw.print("<li><a href='index.html'>Logout</a></li>");
			pw.print("</ul>");
			pw.print("</nav>");
				

			con = dbcon1.getConnection();
			pst = con.prepareStatement("SELECT wa.complaint_id,wa.allocation_date, cd.consumer_name, cd.address, cd.complaint FROM complaint_details cd JOIN work_allocation wa ON cd.complaint_id = wa.complaint_id where wa.lineman=?");
			pst.setString(1, name);
			rs=pst.executeQuery();
			
			//pw.print("<link rel='stylesheet' type='text/css' href='css/viewcomplaint.css'>");
		
			pw.print("<h1>My Works</h1>");
			pw.print("<table border='1'>");
			pw.print("<tr><th>Complaint_id</th><th>Allocated Date</th><th>Consumer Name</th><th>Address</th><th>Complaint</th>");
			
			pw.print("<th>Work Status</th>");
			pw.print("</tr>");
			
			while(rs.next()){
				pw.print("<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getDate(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td>");
				pw.print("<td>Pending</td>");
				pw.print("<td>");
				pw.print("<a href='updateworkstatus?id="+rs.getInt(1)+"'>Edit</a>");
				pw.print("</td>");
				
				pw.print("</tr>");
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

