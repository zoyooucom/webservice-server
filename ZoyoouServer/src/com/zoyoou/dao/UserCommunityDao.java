/**
 * 
 */
package com.zoyoou.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zoyoou.common.entity.Role;
import com.zoyoou.common.entity.User;
import com.zoyoou.common.entity.UserCommunityRelationship;

/**
 * @author kaiwu
 *
 */
public class UserCommunityDao extends AbstractDataAccess<UserCommunityRelationship> {

	static final String ID= "ID";
	
	static final String INSERT_USER_COMMNUNITY="{CALL InsertUserCommunityRelationship(?, ?, ?, ?)}";
	static final String FIND_ALL_USERS="{CALL GetUserInfoByCommunityID(?)}";
	static final String DELETE_USER_COMMUNITY_RELATION="{CALL DeleteUserCommunityRelationship(?)}";


	public UserCommunityDao(Connection conn) throws SQLException {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<UserCommunityRelationship> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserCommunityRelationship> findAllBy(String queryCondition)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserCommunityRelationship findById(long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserCommunityRelationship create(UserCommunityRelationship entity)
			throws Exception {
		
			
			CallableStatement csUserCommunity = this.conn.prepareCall(INSERT_USER_COMMNUNITY) ;
			
			csUserCommunity.setLong(1, entity.getUser().getUserId());
			csUserCommunity.setLong(2, entity.getCommunity().getCommunityId());
			
			
			if(null==entity.getRole()){
				entity.setRole(new Role());
				entity.getRole().setRoleId((short)4);
			}
			csUserCommunity.setShort(3, entity.getRole().getRoleId());
			
			csUserCommunity.registerOutParameter(4, java.sql.Types.BIGINT);
			csUserCommunity.execute();
			long cid = csUserCommunity.getLong(4);
			
			entity.setId(cid);
			return entity; 
	}

	@Override
	public UserCommunityRelationship update(UserCommunityRelationship entity)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserCommunityRelationship remove(long id) throws Exception {
		UserCommunityRelationship ucr = new UserCommunityRelationship();
		ucr.setId(id);
		CallableStatement csUser = this.conn.prepareCall(DELETE_USER_COMMUNITY_RELATION);
		csUser.setLong(1, id);
		csUser.execute();
		return ucr;
	}

	public List<User> getUsers(long communityId) throws Exception{
		List<User> users = new ArrayList<User>();
		
		CallableStatement cs = this.conn.prepareCall(FIND_ALL_USERS);
		cs.setLong(1, communityId);
		ResultSet result = cs.executeQuery();
		while(result.next()){
			users.add(UserDao.getUserFromResult(result, false));
		}
		return users;
		
	}
	
	
	
}
