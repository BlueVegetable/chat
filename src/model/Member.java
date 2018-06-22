package model;

import java.util.ArrayList;
import java.util.List;

public class Member {
	private User user;
	private List<Role> roles;
	private ArrayList<Member>friends;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public ArrayList<Member> getFriends() {
		return friends;
	}
	public void setFriends(ArrayList<Member> friends) {
		this.friends = friends;
	}
	public String toString() {
		return "Member [user=" + user + ", roles=" + roles + ", friends=" + friends + "]";
	}
}