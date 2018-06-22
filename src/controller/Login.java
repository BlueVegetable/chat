package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.RecorderMember;
import dao.RecorderUser;
import model.Member;
import model.User;
import utils.MD5;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName=request.getParameter("userName");
		String password=request.getParameter("password");
		HttpSession session=request.getSession();
		password=MD5.toMD5(password);
		/****************检验前端数据*******************/
		if(!userName.matches("(.){1,20}")){
			response.getWriter().print("用户名格式错误，应为1-20位任意字符");
			return ;
		} else if (!password.matches("(.){1,40}")){
			response.getWriter().print("密码格式输入错误，应为1-40位任意字符");
			return ;
		}
		/****************检验前端数据*******************/
		if(!RecorderUser.isExist(userName)) {
			response.getWriter().print("用户不存在");
			return ;
		}
		User user=RecorderUser.getUser(userName);
		if(password.equals(user.getPassword())) {
			Member member=RecorderMember.getMemberWithFriends(userName);
			session.setAttribute("member", member);
			response.getWriter().print("success");
		} else {
			response.getWriter().print("密码输入错误");
		}
	}
}