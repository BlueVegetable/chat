package filter;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.RecorderUser;
import model.User;

@WebFilter("/RegisterFilter")
/**
 * @author Administrator
 * 
 * 作用:验证前端数据
 * ① 验证用户名是否已经存在了
 * ② 验证其他数据是否错误
 * 
 * 数据校验完成后就对数据进行封装
 */
public class RegisterFilter implements Filter {
	@SuppressWarnings("unused")
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		String newUserName = request.getParameter("newUserName");
		String password = request.getParameter("password");
		String age = request.getParameter("age");
		String phoneNumber = request.getParameter("phoneNumber");
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		
		User user = new User();
		user.setName(newUserName);
		user.setPassword(password);
		user.setPhoneNumber(phoneNumber);
		user.setGender(gender);
		
		if(RecorderUser.isExist(newUserName)) {
			response.getWriter().print("用户名存在");
			return ;
		}
		if(isTrue(user,age)==null)
			chain.doFilter(req, resp);
	}
	/**
	 * 判断数据格式是否正确
	 * @param user 使用者
	 * @param age 使用者年龄
	 * @return 正确返回null,错误返回相对应的错误信息
	 */
	private String isTrue(User user,String age) {
		Pattern newUserNamePattern = Pattern.compile("(.){1,100}");
		Pattern agePattern = Pattern.compile("\\d{1,3}");
		Pattern emailPattern = Pattern.compile("^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
		Pattern passwordPattern = Pattern.compile("(.){1,}");
		Pattern phoneNumberPattern = Pattern.compile("1[0-9]{10}");
		
		return null;
	}
}