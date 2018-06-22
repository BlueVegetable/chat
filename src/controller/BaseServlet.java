package controller;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class BaseServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String methodName=request.getParameter("method");
		
		if(methodName==null||methodName.trim().isEmpty()) {
			doPost(request,response);
		}
		
		String operation=null;
		Method method=null;
		
		try {
			method=this.getClass().getMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("您要调用的方法：" + methodName + "并不存在！", e);
		}
		
		try {
			operation=(String) method.invoke(this,request, response);
			/*返回为空则什么也不做*/
			if(operation==null||operation.trim().isEmpty()) {
				return ;
			}
			/**/
			if(operation.contains(":")) {
				//使用：分割字符串
				int index=operation.indexOf(":");
				String qianZhui=operation.substring(0, index);
				String path=operation.substring(index+1);
				
				if(qianZhui.equalsIgnoreCase("r")) {
					response.sendRedirect(path);
				} else if(qianZhui.equalsIgnoreCase("f")){
					request.getRequestDispatcher(path).forward(request, response);
				} else {
					throw new RuntimeException("您指定的操作:"+qianZhui+",当前版本并不支持");
				}
			}else {
				request.getRequestDispatcher("operation").forward(request, response);
				return ;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("您调用的方法"+methodName+"内部抛出异常");
		}
	}
}