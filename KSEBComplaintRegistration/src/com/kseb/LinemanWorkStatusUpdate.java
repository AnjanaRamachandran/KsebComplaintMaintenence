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

@WebServlet("/updateworkstatus")
public class LinemanWorkStatusUpdate extends HttpServlet {

	
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
			try {
				con = dbcon.getConnection();
				pst = con.prepareStatement("select allocation_id from work_allocation ");
				
				rs=pst.executeQuery();
			out.print("<div class='complaint-form'>");
			out.print("<h3><b><u>Work Status Updation</u></b><h3>");
			out.print("<form name='work-status' action='updateworkstatus' method='post' >");
			out.print("<label>Date</label><input type='date' name='updatedate'><br><br>");
			out.print("<label for='allocationId'>Allocation Id</label>");
			out.print("<select name='allocationId' id='allocationId'>");
			while(rs.next()){
				 int allocationId = rs.getInt("allocation_id");
			
			out.print("<option value='"+ allocationId + "'>" + allocationId + "</option>");
					}
			out.print("</select><br><br>");
			out.print("<label for='workstatus'>work status</label>");
			out.print("<select name='workstatus' id='workstatus'>");
			out.print("<option>Pending</option>");
			out.print("<option>Resolved</option>");
			out.print("</select>");
			out.print("<input type='submit' value='save'/>");
			out.print("</form>");
			out.print("</div>");
			out.print("</body>");
			out.print("</html>");
			
			
			
			String date = request.getParameter("updatedate");
			int allocation=Integer.parseInt(request.getParameter("allocationId"));
			String workstatus = request.getParameter("workstatus");

			
			con = dbcon.getConnection();
			pst=con.prepareStatement("insert into work_status(date,allocation_id,work_status) values (?,?,?)");
			
				
			pst.setDate(1, new java.sql.Date(new java.util.Date().getTime()));	
			pst.setInt(2,allocation);
			pst.setString(3,workstatus);
			
			
			int i=pst.executeUpdate();
			
			if(i==1){
				out.println("<h2>values entered</h2>");	
				
			}	
			else{
				out.println("<h2>registration failed</h2");	
			}	
			
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
