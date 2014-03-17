package com.zoyoou.webservice;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

import com.zoyoou.common.entity.User;
import com.zoyoou.common.entity.UserCommunityRelationship;
import com.zoyoou.model.ModelType;
import com.zoyoou.model.UserCommunityModel;

@Provider
@Path(Constants.ServicePath.COMMUNITY_USER_SERVICE_PATH)
public class UserCommunityService extends AbstractZuoyouWebservice<UserCommunityModel, UserCommunityRelationship> {
	
	public UserCommunityService(){
		super(ModelType.USER_COMMUNITY);
	}
	
	@GET
	@Path(Constants.ServicePath.USERS+"/{id}")
	@Produces({ MediaType.APPLICATION_JSON})
	public List<User> getUsers(@PathParam("id") long id) throws Exception{
		System.out.println("Get Users");
		return this.model.getUsers(id);
	}
}
