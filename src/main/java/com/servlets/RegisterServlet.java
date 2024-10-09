package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.catalina.connector.Response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String confirmPassword = req.getParameter("confirm password");
		String email = req.getParameter("email");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c =DriverManager.getConnection("jdbc:mysql://localhost:3306/UserHub","root","Student@321");
			PreparedStatement ps =c.prepareStatement("insert into users(name,email,password) values(?,?,?);");
			ps.setString(1,username);
			ps.setString(2, email);
			ps.setString(3, password);
			int i= ps.executeUpdate();
			if(i>0) {
				System.out.println("Register Successfully!...");
				resp.sendRedirect("login.html");
			}
			else {
				System.out.println("Registration failed!....");
			}
			
		}catch(ClassNotFoundException  e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

	}
}
