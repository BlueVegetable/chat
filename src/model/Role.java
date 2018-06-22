package model;

public class Role {
	private int id;
	private String name;
	private String title;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public static Role getRoleById(int id) {
		Role role=new Role();
		if(id<1||id>4)
			id=1;
		role.setId(id);
		
		switch(id) {
		case 1:role.setName("user");role.setTitle("普通用户");break;
		case 2:role.setName("member");role.setTitle("会员");break;
		case 3:role.setName("superMember");role.setTitle("超级会员");break;
		case 4:role.setName("管理员");role.setTitle("超级管理员");break;
		}
		
		return role;
	}
	public static Role getRoleByName(String name) {
		Role role=new Role();
		switch(name) {
		case "user":role.setId(1);role.setTitle("普通用户");break;
		case "member":role.setId(2);role.setTitle("会员");break;
		case "superMember":role.setId(3);role.setTitle("超级会员");break;
		case "admin":role.setId(4);role.setTitle("超级管理员");break;
		default:return getDefaultRole();
		}
		
		return role;
	}
	public static Role getRoleByTitle(String title) {
		Role role=new Role();
		role.setTitle(title);
		switch(title) {
		case "普通用户":role.setId(1);role.setName("user");break;
		case "会员":role.setId(2);role.setName("member");break;
		case "超级会员":role.setId(3);role.setName("superMember");break;
		case "管理员":role.setId(4);role.setName("admin");break;
		default:return getDefaultRole();
		}
		return role;
	}
	/**
	 * 获取默认的角色类
	 * @return 默认角色类
	 */
	public static Role getDefaultRole() {
		Role role=new Role();
		role.setId(1);
		role.setName("user");
		role.setTitle("普通用户");
		return role;
	}
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", title=" + title + "]";
	}
}