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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Connection con=null;
	PreparedStatement pst=null;
	ResultSet rs=null;
	String userType=null;
	String userName=null;
	RequestDispatcher dis=null;
	DBConnection dbcon=new DBConnection();
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		try{
			response.setContentType("text/html");
			PrintWriter out=response.getWriter();
			
			
			String username=request.getParameter("username");
			String password=request.getParameter("pass");
			String usertype=request.getParameter("usertype");
			
			
			
			con=dbcon.getConnection();
			pst=con.prepareStatement("select user_name,user_type from user_login where user_name=? and user_password=? and user_type=?");
			pst.setString(1, username);
			pst.setString(2, password);
			pst.setString(3, usertype);
			rs=pst.executeQuery();
			while(rs.next()){
				//userName=rs.getString(1);
				//userType=rs.getString(2);
				String dbUsername=rs.getString("user_name");
				String dbUsertype=rs.getString("user_type");
				
				HttpSession session=request.getSession();
				session.setAttribute("uname", username);
				
				if(dbUsertype.equals("Administrator")){
					dis=request.getRequestDispatcher("/administrator");
					dis.forward(request, response);
				}else if(dbUsertype.equals("Lineman")){
					dis=request.getRequestDispatcher("/lineman");
					dis.forward(request, response);
				}else if(dbUsertype.equals("Material Dep Manager")){
					dis=request.getRequestDispatcher("/materialmanager");
					dis.forward(request, response);
				}else if(dbUsertype.equals("Executive Engineer")){
					dis=request.getRequestDispatcher("/executiveengineer");
					dis.forward(request, response);
				}else{
					dis=request.getRequestDispatcher("index.html");
					dis.include(request, response);
					out.print("<b>Invalid username,password or usertype</b>");
				}
			
			}
			
			
			
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
