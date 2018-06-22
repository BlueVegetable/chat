package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UserService")
public class UserService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String operation=request.getParameter("operation");
		if(operation==null)
			operation="默认";
		switch(operation) {
		default:response.getWriter().println("无操作");return ;
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String operation=request.getParameter("operation");
		if(operation==null)
			operation="defalut";
		switch(operation) {
		case "login":request.getRequestDispatcher("Login").forward(request, response);return ;
		case "init":request.getRequestDispatcher("InitUser").forward(request, response);return;
		case "getInfo":request.getRequestDispatcher("InitInfo").forward(request, response);return;
		default:response.getWriter().println("无操作");return ;
		}
	}
}