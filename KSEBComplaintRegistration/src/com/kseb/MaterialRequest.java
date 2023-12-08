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

@WebServlet("/materialrequest")
public class MaterialRequest extends HttpServlet {

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
			out.print("<link rel='stylesheet' type='text/css' href='css/matrequest.css'>");
			out.print("</head>");
			out.print("<body>");
			out.print("<nav>");
			out.print("<ul>");
			out.print("<li><a href='allocation'>Work Allocation</a></li>");
			out.print("<li><a href='viewmaterialreq'>Material Request List</a></li>");
			out.print("<li><a href='index.html'>LogOut</a></li>");
			out.print("</ul>");
			out.print("</nav>");
			try {
				con = dbcon.getConnection();
				pst = con.prepareStatement("select allocation_id from work_allocation ");
				
				rs=pst.executeQuery();
				
			out.print("<div class='materialrequest'>");
			out.print("<h3><b><u>Material Request</u></b><h3>");
			out.print("<form name='material-request' action='materialrequest' method='post' >");
			out.print("<label>Date</label><input type='date' name='requestdate'><br><br>");
			out.print("<label for='allocationid'>Allocation No</label>");
			out.print("<select name='allocationid' id='allocationid'>");
			while(rs.next()){
				 int allocationId = rs.getInt("allocation_id");
			
			out.print("<option value='"+ allocationId + "'>" + allocationId + "</option>");
					}
			out.print("</select><br><br>");
			
			
			out.print("<label>Material Details</label> <textarea id='material' name='material' rows='5' cols='50'> </textarea><br><br/>");
			/*out.print("<label for='Request Status'>Request Status</label>");
			out.print("<select name='status'>");
			out.print("<option>Pending</option>");
			out.print("<option>Request Granted</option>");
			out.print("</select><br><br><br>");*/
			out.print("<input type='submit' value='save'/>");
			out.print("</form>");
			out.print("</div>");
			out.print("</body>");
			out.print("</html>");
			
			if ("POST".equalsIgnoreCase(request.getMethod())) {
                // Form submission is a POST request
                String requesteddate = request.getParameter("requestdate");
                int allocationId = Integer.parseInt(request.getParameter("allocationid"));
                String materialdetails = request.getParameter("material");
                String status = request.getParameter("status");
               
                // Insert the values into the database
                PreparedStatement insertPst = con.prepareStatement(
                        "INSERT INTO materialrequest_details (date,allocation_no,material_details,status) VALUES (?,?, ?, ?)");

                insertPst.setDate(1, java.sql.Date.valueOf(requesteddate));
                insertPst.setInt(2, allocationId);
                insertPst.setString(3, materialdetails);
                insertPst.setString(4, status);

                int rowsAffected = insertPst.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("<h2>Values inserted into work_allocation table</h2>");
                } else {
                    out.println("<h2>Insert failed</h2>");
                }
          }
			
			
			
			
			} catch (SQLException se) {
				se.printStackTrace();
			
			}catch(Exception e){
				e.printStackTrace();
			}
			
	}	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		doGet(request,response);
	}
}			