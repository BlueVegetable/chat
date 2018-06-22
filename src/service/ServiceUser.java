package service;

import dao.RecorderMember;
import model.Member;
import net.sf.json.JSONObject;

public class ServiceUser {
	public static void main(String[] args) {
//		User user=new User();
//		user.setName("张艺隽");
//		user.setAge((short) 19);
//		user.setBirthday(new Date(System.currentTimeMillis()));
//		user.setEmail("2438062943@qq.com");
//		user.setGender("男");
//		user.setPassword("张艺隽");
//		user.setPhoneNumber("12345678901");
//		System.out.println(user.getBirthday().getTime());
//		System.out.println(RecorderUser.addUser(user));
//		System.out.println(RecorderMember.getMemberWithFriends("蔡荣镔"));
//		for(String value:"me to凌浩:发送".split("to"))
//			System.out.println(value);
//		String get="me to凌浩:发送".split("to")[1];
//		String[] messages=get.split(":");
//		for(String message:messagArrayList<E>	System.out.println(message);
		Member member=RecorderMember.getMemberWithFriends("蔡荣镔");
		JSONObject map=JSONObject.fromObject(member.getFriends());
		System.out.println(map);
	}
}