package com.kseb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/materialrequestapproval")
public class ExecutiveEngMaterialRequest extends HttpServlet {

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
	DBConnection dbcon = new DBConnection();
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		
		    response.setContentType("text/html");
			PrintWriter out=response.getWriter();
			
				
			HttpSession session=request.getSession();
			String name=(String)(session.getAttribute("uname"));
						
			out.print("<html>");
			out.print("<title> material request approval</title>");
			out.print("<head>");
			out.print("<h1><b>");
			out.print("Welcome "+name);
			out.print("</b></h1><br>");
			out.print("<link rel='stylesheet' type='text/css' href='css/homepage.css'>");
			out.print("</head>");
			out.print("<body>");
			out.print("<nav>");
			out.print("<ul>");
			out.print("<li><a href='approvalform'>Grant Request</a></li>");
			out.print("<li><a href='index.html'>Logout</a></li>");
			out.print("</ul>");
			out.print("</nav>");
			try {
			con = dbcon.getConnection();
			pst = con.prepareStatement("select request_id,date,allocation_no,material_details from materialrequest_details;");
			rs=pst.executeQuery();
			
			
		
			out.print("<h1>Arrived Requests</h1>");
			out.print("<table border='1'>");
			out.print("<tr><th>Request No</th><th>Date</th><th>Allocation No</th><th>Materials Required</th>");
			
			out.print("</tr>");
			
			while(rs.next()){
				out.print("<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getDate(2)+"</td><td>"+rs.getInt(3)+"</td><td>"+rs.getString(4)+"</td>");
				
				
				
				out.print("</tr>");
			}
			
			out.print("</table>");
			out.print("</body></html>");
		}catch(Exception e){
			e.printStackTrace();
		}
	}		
		
}
	
