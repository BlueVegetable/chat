package dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;

import model.User;

public class RecorderUser extends Recorder{
	public static boolean alterUser(User user) {
		String sql="UPDATE USER SET NAME=?,age=?,email=?,phoneNumber=?,"
				+ "PASSWORD=MD5(?),gender=?,birthday=? WHERE NAME=?";
		Object[]parameters=new Object[] {user.getName(),user.getAge(),user.getEmail(),user.getPhoneNumber(),
				user.getPassword(),user.getGender(),user.getBirthday(),user.getName()};
		Connection connection=linkedDataBase();
		return updateDataBase(connection,sql,parameters);
	}
	public static boolean isExist(String userName) {
		String sql="SELECT NAME FROM USER WHERE NAME=?";
		Connection connection=linkedDataBase();
		QueryRunner qr=new QueryRunner();
		try {
			Map<String,Object>values=qr.query(connection, sql,new MapHandler(),userName);
			if(values!=null) {
				String value=(String)values.get("name");
				if(userName.equals(value))
					return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public static boolean addUser(User user) {
		Connection connection=linkedDataBase();
		QueryRunner qr=new QueryRunner();
		String sql="insert into user values(?,?,?,?,md5(?),?,?)";
		Object[]parameters=new Object[] {user.getName(),user.getAge(),user.getEmail(),
				user.getPhoneNumber(),user.getPassword(),user.getGender(),
				new java.sql.Time(user.getBirthday().getTime())};
		int rows=0;
		try {
			rows=qr.update(connection, sql, parameters);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDataBase(connection);
		}
		return !(rows==0);
	}
	public static User getUser(String userName) {
		User user=null;
		Connection connection=linkedDataBase();
		String sql="SELECT * FROM USER WHERE NAME=?";
		try {
			Map<String,Object> parameters=new QueryRunner().query(connection,  
			        sql, new MapHandler(),userName);
			user=new User();
			if(parameters.get("birthday")==null)
				parameters.remove("birthday");
			if(parameters.get("age")==null)
				parameters.remove("age");
			BeanUtils.populate(user, parameters);
		} catch (SQLException | IllegalAccessException | InvocationTargetException e) {
			user=null;
			e.printStackTrace();
		} finally {
			closeDataBase(connection);
		}
		return user;
	}
	public static boolean deleteUser(User user) {
		String sql="delete from user where name=?";
		Object[]parameters=new Object[] {user.getName()};
		Connection connection=linkedDataBase();
		return updateDataBase(connection, sql, parameters);
	}
}