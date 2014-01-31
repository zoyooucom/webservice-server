package com.zoyoou.model;

import java.util.ArrayList;
import java.util.List;

import com.zoyoou.common.entity.User;
import com.zoyoou.common.entity.UserCommunityRelationship;
import com.zoyoou.dao.DataAccessType;
import com.zoyoou.dao.UserDao;
import com.zoyoou.resource.ErrorMessages;

public class  UserModel extends AbstractModel<UserDao,User>{
	
	public UserModel(){
		super(DataAccessType.USER);
	}
	

	/* (non-Javadoc)
	 * @see com.zoyoou.model.AbstractModel#create(com.zoyoou.common.entity.IEntity)
	 */
	@Override
	public User create(User entity) throws Exception {
	
		User user = entity;
		try{
			this.initDao();
			this.connection.setAutoCommit(false);
	
			user = this.create(user, false);
			
			
			UserCommunityModel userCommunityModle = ModelFactory.getModel(ModelType.USER_COMMUNITY);
			userCommunityModle.initDao(this.connection);
			UserCommunityRelationship userCommunity = user.getCommunityRelation();
			
			userCommunity.setUser(new User());
			userCommunity.getUser().setUserId(user.getUserId());
			userCommunity = userCommunityModle.create(userCommunity,false);
			
			this.connection.commit();
			this.connection.setAutoCommit(true);
			
			user.setCommunityRelation(userCommunity);
			return user;
		}catch(Exception e){
			List<String> errors = new ArrayList<String>();
			errors.add(e.toString());
			user.setErrorList(errors);
			return user;
		}finally{
			this.closeDao();
		}
		
	}
	
	public User login(String username, String password) throws Exception{
		User user = new User();
		try{
			this.initDao();
			user = this.dao.findByUserName(username);
			
			if(null == user){
				user = new User();
				user.setUserName(username);
				user.setPwd(password);
				user.setErrorList(getLoginErrors());
				return user;
			}
			
			if(!user.getPwd().equals(password)){
				user.setErrorList(getLoginErrors());
			}
			return user;
		}catch(Exception e){
			
			List<String> errors = new ArrayList<String>();
			errors.add(e.toString());
			user.setErrorList(errors);
			return user;
		}finally{
			this.closeDao();
			
		}
		
	}
	
	private static List<String> errors =null;
	private static List<String> getLoginErrors(){
		if(null==errors){
			errors = new ArrayList<String>();
			errors.add(ErrorMessages.LOGIN_ERROR);
		}
		
		return errors;
		
	}

}
