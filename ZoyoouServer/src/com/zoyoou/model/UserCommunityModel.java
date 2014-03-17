/**
 * 
 */
package com.zoyoou.model;

import java.util.List;

import com.zoyoou.common.entity.User;
import com.zoyoou.common.entity.UserCommunityRelationship;
import com.zoyoou.dao.DataAccessType;
import com.zoyoou.dao.UserCommunityDao;

/**
 * @author kaiwu
 *
 */
public class UserCommunityModel extends AbstractModel<UserCommunityDao,UserCommunityRelationship> {

	protected UserCommunityModel() {
		super(DataAccessType.USERCOMMUNITY);
	}
	
	public List<User> getUsers(long communityId) throws Exception{
		try{
			this.initDao();
			return this.dao.getUsers(communityId);
		}finally{
			this.closeDao();
		}
	}


}
