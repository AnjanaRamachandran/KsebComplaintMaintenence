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

@WebServlet("/approvalform")
public class EEApprovalFormUpdate extends HttpServlet{
	Connection con = null;
	PreparedStatement pst = null;
	PreparedStatement pst1 = null;
	PreparedStatement pst2 = null;
	ResultSet rs = null;
	
	String usertype = null;
	String username = null;
	RequestDispatcher dis = null;
	DBConnection dbcon1 = new DBConnection();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		    response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			HttpSession session=request.getSession(false);
			
							
				out.print("<html>");
				out.print("<head><link rel='stylesheet' type='text/css' href='css/workallocation.css'></head>");
				out.print("<body>");
				out.print("<h1>Request Approval</h1>");
				try {
					con = dbcon1.getConnection();
					pst = con.prepareStatement("select allocation_no from materialrequest_details");
					
					rs=pst.executeQuery();
				out.print("<div class='reqapproval'>");
				out.print("<form name='req-approval' method='post' action='approvalform'>");
				out.print("<label>Date </label>");
				out.print("<input type='Date' name='approvaldate'/><br><br>");
					
					
				out.print("<label for='anumber'>Allocation No</label>");
				out.print("<select name='anumber' id='anumber'>");
				while(rs.next()){
					 int allocationNo = rs.getInt("allocation_no");
				
				out.print("<option value='"+ allocationNo + "'>" + allocationNo + "</option>");
						}
				out.print("</select><br><br>");
				out.print("<label for='status'>Status</label>");
				out.print("<select name='status'>Status");
				out.print("<option>Pending</option>");
				out.print("<option>Approved</option>");
				out.print("</select><br><br><br>");
				
				out.print("<input type='submit' value='save' />");
				out.print("</form></div>");
				out.print("</body></html>");
				
    } catch (SQLException sql) {
        sql.printStackTrace();
    } catch (Exception e) {
        e.printStackTrace();
    }

	
		 
		try {
                      
            String date =request.getParameter("approvaldate");
    	    java.sql.Date sqlDate=java.sql.Date.valueOf(date);
    	    
    	    int allocationno=Integer.parseInt(request.getParameter("anumber"));
    		//int allocationnumber = Integer.parseInt(request.getParameter("complaintno"));
    			
			String status = request.getParameter("status");
			//int allocationNo = rs.getInt("allocation_no");
			
			
		
		con = dbcon1.getConnection();
		pst1 = con.prepareStatement("insert into request_status(date,allocation_no,req_status) values(?,?,?)");
		
		pst1.setDate(1, sqlDate);
		pst1.setInt(2, allocationno);
		pst1.setString(3, status);
		

		
		int i=pst1.executeUpdate();
		
		if (i == 1) {
            response.sendRedirect("approvalform");
        } else {
            response.getWriter().println("Request approved");
         }
		

    } catch (SQLException sql) {
        sql.printStackTrace();
    } catch (Exception e) {
        e.printStackTrace();
    }
	}		
protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		doGet(request,response);
	}

}	




