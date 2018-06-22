package dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import model.Info;

public class RecorderInfo extends Recorder{
	public static ArrayList<Info> getOneAllInfo(String userOne,String userAnother){
		ArrayList<Info> infos=new ArrayList<>();
		Info info=null;
		
		String sql="select * from info where userOne = ? and userAnother = ? OR userOne = ? AND userAnother = ?";
		Connection connection=linkedDataBase();
		try {
			List<Map<String,Object>>results=new QueryRunner().query(connection,
					sql,new MapListHandler(),userOne,userAnother,userAnother,userOne);
			if(results==null||results.size()==0)
				return infos;
			for(Map<String,Object>result:results) {
				info=new Info();
				BeanUtils.populate(info, result);
				infos.add(info);
			}
		} catch (SQLException | IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDataBase(connection);
		}
		
		
		return infos;
	}
	public static boolean add(Info info) {
		String sql="INSERT INTO info VALUES(?,?,?,?)";
		Object[] parameters=new Object[] {info.getUserOne(),info.getUserAnother(),
				info.getContent(),new Timestamp(info.getTime().getTime())};
		QueryRunner qr=new QueryRunner();
		Connection connection=linkedDataBase();
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
}