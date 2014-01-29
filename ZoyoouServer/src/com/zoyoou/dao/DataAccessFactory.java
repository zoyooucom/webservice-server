/**
 * 
 */
package com.zoyoou.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.zoyoou.common.entity.IEntity;

/**
 * @author kaiwu
 *
 */
public class DataAccessFactory {
	
	@SuppressWarnings("unchecked")
	public static <T extends IDataAccess<? extends IEntity>> T getDataAccess(DataAccessType type, Connection connection) throws SQLException{
		switch(type){
		case USER:
			return (T) new UserDao(connection);
		case COMMUNITY:
			return null;//todo
		case CONTACTINFO:
			return (T) new ContactInfoDao(connection);
		case USERCOMMUNITY:
			return (T) new UserCommunityDao(connection);
		default:
			return null;
		}
	}

}
