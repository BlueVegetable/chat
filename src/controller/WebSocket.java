package controller;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import dao.RecorderInfo;
import model.Info;

@ServerEndpoint(value="/WebSocket/{param}")
public class WebSocket {
	private static int onlineCount = 0;
	private static ConcurrentHashMap<String,WebSocket>webSockets=new ConcurrentHashMap<>();
	private Session session=null;
//	private Pair pair=null;
	private String userName=null;
	private String anotherName=null;
	
	@OnOpen
	public void onOpen(@PathParam(value="param")String userName,Session session,EndpointConfig config) {
		this.session=session;
		this.userName=userName;
		webSockets.put(userName, this);
		addCount();
		System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
	}
	
	@OnClose
	public void onClose(Session session) {
		if(userName!=null)
			webSockets.remove(userName);
		subCount();
		System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
	}
	
	@OnMessage
	public void onMessage(String get,Session session) throws IOException {
		System.out.println(get);
		
		String[] messages=get.split(":");
		switch(messages[0]) {
		case "连接"://无具体作用
			break;
		case "发送消息":
			Info info= new Info();
			anotherName=messages[2];
			
			info.setUserOne(userName);
			info.setUserAnother(anotherName);
			info.setTime(new Date(System.currentTimeMillis()));
			info.setContent(messages[3]);
			
			if(anotherName==null||anotherName.equals("")) {
				session.getBasicRemote().sendText("错误:请先选择联系人");
				break;
			}
			if(webSockets.containsKey(anotherName)) {
				Session sessionAnother=webSockets.get(anotherName).getSession();
				sessionAnother.getBasicRemote().sendText(info.getContent());
			}
			RecorderInfo.add(info);
			break;
		}
	}
	
	@OnError
	public void onError(Session session,Throwable error) {
		System.out.println("发生错误");
		error.printStackTrace();
	}
	private static synchronized void addCount() {
		onlineCount++;
	}
	public static int getOnlineCount() {
		return onlineCount;
	}
	private static synchronized void subCount() {
		onlineCount--;
	}
	public Session getSession() {
		return session;
	}
//	@SuppressWarnings("unused")
//	private class Pair{
//		Session sessionMe;
//		Session sessionAnother;
//		public Pair(Session sessionMe,Session sessionAnother) {
//			this.sessionMe=sessionMe;
//			this.sessionAnother=sessionAnother;
//		}
//		public Session getSessionMe() {
//			return sessionMe;
//		}
//		public Session getSessionAnother() {
//			return sessionAnother;
//		}
//		public void setSessionMe(Session sessionMe) {
//			this.sessionMe = sessionMe;
//		}
//		public void setSessionAnother(Session sessionAnother) {
//			this.sessionAnother = sessionAnother;
//		}
//	}
}