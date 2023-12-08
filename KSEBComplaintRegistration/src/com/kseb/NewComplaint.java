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

@WebServlet("/complaint")
public class NewComplaint extends HttpServlet {

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

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			
			HttpSession session=request.getSession(false);
			String name=(String)(session.getAttribute("uname"));
			
			
			out.print("<html>");
			out.print("<head>");
			out.print("<link rel='stylesheet' type='text/css' href='css/complaintreg.css'>");
			out.print("</head>");
			out.print("<body>");
			out.print("<div class='complaint-form'>");
			out.print("<h3><b><u>New Complaint Registration:</u></b><h3>");
			out.print("<form name='complaint-form' action='complaint' method='post' >");
			out.print("<label>Date</label><input type='date' name='complaintdate'><br><br>");
			out.print("<label>Consumer Name</label><input type='text' name='consumername'><br><br>");
			out.print("<label>Address</label> <textarea id='address' name='address' rows='3' cols='30'> </textarea><br><br>");
			out.print("<label>Complaint</label> <textarea id='complaint' name='Complaint' rows='5' cols='50'> </textarea><br><br><br/>");
			out.print("<input type='submit' value='save'/>");
			out.print("</form>");
			out.print("</div>");
			out.print("</body>");
			out.print("</html>");
			
			
			try {
			String date = request.getParameter("complaintdate");
			String consumer = request.getParameter("consumername");
			String address = request.getParameter("address");
			String complaint = request.getParameter("Complaint");
			
			
			con = dbcon.getConnection();
			pst=con.prepareStatement("insert into complaint_details (reg_date,consumer_name,address,complaint) values (?,?,?,?)");
			
				
			pst.setDate(1, new java.sql.Date(new java.util.Date().getTime()));	
			pst.setString(2,consumer);
			pst.setString(3,address);
			pst.setString(4,complaint);
			
			int i=pst.executeUpdate();
			
			/*if(i==1){
				out.println("<h2>values entered</h2>");	
				
			}	
			else{
				out.println("<h2>registration failed</h2");	
			}	*/
			
			con.close();
			
			
			
		}catch (SQLException se) {
			se.printStackTrace();	
		} catch (Exception e) {
			e.printStackTrace();	
		}
		
	}

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		doGet(request,response);
	}

}   
