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

@WebServlet("/MaterialIssue")
public class MaterialIssue extends HttpServlet {
	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	String usertype = null;
	String username = null;
	RequestDispatcher dis = null;
	DBConnection dbcon = new DBConnection();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException  {
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			HttpSession session=request.getSession();
			
			String name = (String) request.getSession().getAttribute("uname");
			
			out.print("<html>");
			out.print("<head><link rel='stylesheet' type='text/css' href='css/viewworks.css'></head>");
			out.print("<body>");
			
			out.print("<nav>");
			out.print("<ul>");
			out.print("<li><a href='viewmaterialreq'>Material Request List</a></li>");
			out.print("<li><a href='index.html'>LogOut</a></li>");
			out.print("</ul>");
			out.print("</nav>");
			
			
			
			
			con = dbcon.getConnection();
			pst = con.prepareStatement("SELECT status_id,date,allocation_no,req_status from request_status where req_status = 'Approved'");
			
			rs=pst.executeQuery();
			out.print("<h1>Material Requested Approval Details</h1>");
			out.print("<table border='1'>");
			out.print("<tr>");
			out.print("<th>Sl.No</th>");
			out.print("<th> Date</th>");
			out.print("<th>Allocation No </th>");
			out.print("<th>Request Status </th>");
			
			out.print("<th>Issue </th>");
			out.print("</tr>");
			while(rs.next()){
				out.print("<tr>");
				out.print("<td>"+rs.getInt(1)+"</td>");
				
				out.print("<td>"+rs.getDate(2)+"</td>");
				out.print("<td>"+rs.getInt(3)+"</td>");
				out.print("<td>"+rs.getString(4)+"</td>");
				
			    out.print("<td>");
				out.print("<a href='MaterialGiveIssue?id="+rs.getInt(1)+"'>edit</a>");
				out.print("</td>");
				out.print("</tr>");
			}
			out.print("</body></html>");
		} catch (SQLException sql) {	
			sql.printStackTrace();
			
		}catch(Exception e){
		e.printStackTrace();
		}
	}

}
 





