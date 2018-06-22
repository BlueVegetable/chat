package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import model.Member;
import model.Role;
import model.User;

public class RecorderMember extends Recorder{
	public static boolean addMember(Member member) {
		return RecorderUser.addUser(member.getUser())&&
				RecorderRole.addRole(member.getUser(),Role.getDefaultRole());
	}
	public static boolean deleteMember(Member member) {
		boolean flag=true;
		return flag&&RecorderUser.deleteUser(member.getUser());
	}
	private static Member getMemberNoFriends(String userName) {
		Member member=null;
		User user=RecorderUser.getUser(userName);
		List<Role>roles=RecorderRole.getRolesByUserName(userName);
		if(user!=null&&roles.size()>0) {
			member=new Member();
			member.setUser(user);
			member.setRoles(roles);
		}
		return member;
	}
	public static ArrayList<Member>getFriends(String userName){
		ArrayList<Member>friends=new ArrayList<>();
		Connection connection=linkedDataBase();
		String sql="SELECT * FROM friend WHERE userNameOne=? OR userNameOther=?";
		try {
			Object[]parameters=new Object[] {userName,userName};
			List<Map<String,Object>>values=new QueryRunner().query(connection,sql,
					new MapListHandler(),parameters);
			for(Map<String,Object>value:values) {
				if(value.get("userNameOne").equals(userName)) {
					if(value.get("userNameOther").equals(userName))
						continue;
					else {
						String friendName=(String) value.get("userNameOther");
						Member friend=getMemberNoFriends(friendName);
						friends.add(friend);
						continue;
					}
				} else {
					String friendName=(String)value.get("userNameOne");
					Member friend=getMemberNoFriends(friendName);
					friends.add(friend);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDataBase(connection);
		}
		return friends;
	}
	public static Member getMemberWithFriends(String userName) {
		Member member=getMemberNoFriends(userName);
		ArrayList<Member>friends=getFriends(userName);
		member.setFriends(friends);
		return member;
	}
}