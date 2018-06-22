package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.RecorderInfo;
import model.Info;
import model.Member;
import net.sf.json.JSONArray;

@WebServlet("/InitInfo")
public class InitInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session=request.getSession();
		Member member = (Member) session.getAttribute("member");
		String myName = member.getUser().getName();
		String anotherName = request.getParameter("anotherName");
		ArrayList<Info> infos = RecorderInfo.getOneAllInfo(myName, anotherName);
		Object[] infosArray=infos.toArray();
		Arrays.sort(infosArray);
		JSONArray map = JSONArray.fromObject(infosArray);
		response.getWriter().println(map);
	}
}