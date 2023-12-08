package com.kseb;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/materialmanager")
public class MaterialManagerHome extends HttpServlet {

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
			out.print("<title> material manager login</title>");
			out.print("<head>");
			out.print("<h1><b>");
			out.print("Welcome "+name);
			out.print("</b></h1>");
			out.print("<link rel='stylesheet' type='text/css' href='css/homepage.css'>");
			out.print("</head>");
			out.print("<body>");
			out.print("<nav>");
			out.print("<ul>");
			out.print("<li><a href='MaterialIssue'>Material Issue </a></li>");
			out.print("<li><a href='index.html'>Logout</a></li>");
			out.print("</ul>");
			out.print("</nav>");
			out.print("</body>");
			out.print("</html>");
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}	
}
