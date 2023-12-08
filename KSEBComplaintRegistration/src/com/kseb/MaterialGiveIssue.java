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

@WebServlet("/MaterialGiveIssue")
public class MaterialGiveIssue extends HttpServlet{
	Connection con = null;
	PreparedStatement pst = null;
	PreparedStatement pst1 = null;
	
	ResultSet rs = null;
	ResultSet rs1 = null;
	
	String usertype = null;
	String username = null;
	RequestDispatcher dis = null;
	DBConnection dbcon = new DBConnection();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
	
		
		
		out.print("<html>");
		out.print("<head><link rel='stylesheet' type='text/css' href='css/materialgiveissue.css'></head>");
		out.print("<body>");
		
		
		
		out.print("<nav>");
		out.print("<ul>");
	    out.print("<li><a href='MaterialIssue'>Material Issue </a></li>");
		out.print("<li><a href='index.html'>Logout</a></li>");
		out.print("</ul>");
		out.print("</nav>");
		
		out.print("<div class='workallocation'>");
		out.print("<h2>Issue Material  </h2>");
		
			con = dbcon.getConnection();
			pst = con.prepareStatement("select status_id from request_status ");
			
			rs=pst.executeQuery();
			
		
		out.print("<form name='MaterialGiveIssue' method='post' action='MaterialGiveIssue'>");
		out.print("<label>Sl.No </label>");	
		out.print("<select name='statusid'>");	
		while (rs.next()) {
		    String statusid = rs.getString("status_id");
		    out.print("<option value='" + statusid + "'>" + statusid + "</option>");
		}
		out.print("</select><br><br>");
		

		
		out.print("<label>Date </label>");	
			out.print("<input type='Date' name='date' value='date'/><br><br>");
		out.print("<label>Request Status </label>");	
			out.print("<input type='text' name='issuestatus' /><br><br><br>");
		//approved&issued
		
	
		out.print("<input type='submit' value='Issue'/> ");
		out.print("</form></div>");
		out.print("</body></html>");
	 } catch (SQLException sql) {
	        sql.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}		
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		  try {
	           
			int statusid=Integer.parseInt(request.getParameter("statusid"));
			String approvedate =request.getParameter("date");
		    java.sql.Date sqlDate=java.sql.Date.valueOf(approvedate);
			String reqstatus = request.getParameter("issuestatus");
			
			con = dbcon.getConnection();
			pst1 = con.prepareStatement("update request_status set date=?,req_status=? where status_id=?");
			
			pst1.setDate(1, sqlDate);
			pst1.setString(2, reqstatus);
			pst1.setInt(3, statusid);
			int i=pst1.executeUpdate();
			
			if (i == 1) {
	            response.sendRedirect("MaterialIssue");
	        } else {
	            response.getWriter().println("Record not updated");
	        }
			

	    } catch (SQLException sql) {
	        sql.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	}



