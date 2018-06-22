package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

import model.Role;
import model.User;

public class RecorderRole extends Recorder{
	public static List<Role> getRolesByUserName(String userName) {
		List<Role>roles=new ArrayList<>();
		Role role=null;
		QueryRunner qr=new QueryRunner();
		Connection connection=linkedDataBase();
		String sql="SELECT role_id roleID FROM user_role WHERE user_name=?";
		try {
			List<Object[]> roleIDs=qr.query(connection, sql, new ArrayListHandler(), userName);
			for(Object[] roleID:roleIDs) {
				role=Role.getRoleById((Integer)(roleID[0]));
				roles.add(role);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDataBase(connection);
		}
		return roles;
	}
	public static boolean addRole(User user,Role role) {
		Connection connection=linkedDataBase();
		String sql="INSERT INTO user_role VALUES(?,?)";
		Object[]parameters=new Object[] {user.getName(),role.getId()};
		return updateDataBase(connection, sql, parameters);
	}
	public static boolean alterRole(User user,Role role) {
		Connection connection=linkedDataBase();
		String sql="UPDATE user_role SET role_id=? WHERE user_name=?";
		Object[]parameters=new Object[] {role.getId(),user.getName()};
		return updateDataBase(connection,sql,parameters);
	}
	public static boolean deleteRole(User user,Role role) {
		Connection connection=linkedDataBase();
		String sql="DELETE FROM user_role WHERE user_name=? AND role_id=?";
		Object[]parameters=new Object[] {user.getName(),role.getId()};
		return updateDataBase(connection, sql, parameters);
	}
}