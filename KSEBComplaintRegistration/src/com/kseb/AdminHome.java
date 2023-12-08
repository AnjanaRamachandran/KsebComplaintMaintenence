package com.kseb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/administrator")
public class AdminHome extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		try{
			response.setContentType("text/html");
			PrintWriter out=response.getWriter();
			
				
			HttpSession session=request.getSession();
			String name=(String)(session.getAttribute("uname"));
			
			
			out.print("<html>");
			out.print("<title> admin login</title>");
			out.print("<head>");
			out.print("<h1><b>");
			out.print("Welcome "+name);
			out.print("</b></h1><br>");
			out.print("<link rel='stylesheet' type='text/css' href='css/homepage.css'>");
			out.print("</head>");
			out.print("<body>");
			out.print("<nav>");
			out.print("<ul>");
			out.print("<li><a href='complaint'>Complaint</a></li>");
			out.print("<li><a href='workallocation'>Work Allocation</a></li>");
			out.print("<li><a href='index.html'>Logout</a></li>");
			out.print("<li><a href='viewcomplaint'>View Complaint Allocation</a></li>");
			out.print("</ul>");
			out.print("</nav>");
			out.print("</body>");
			out.print("</html>");
			
		}catch(Exception e){
			e.printStackTrace();
		}
}
}