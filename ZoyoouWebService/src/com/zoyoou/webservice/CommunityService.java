package com.zoyoou.webservice;

import javax.ws.rs.Path;
import javax.ws.rs.ext.Provider;

import com.zoyoou.common.entity.Community;
import com.zoyoou.model.CommunityModel;
import com.zoyoou.model.ModelType;



@Provider
@Path(Constants.ServicePath.COMMUNITY_SERVICE_PATH)
public class CommunityService  extends AbstractZuoyouWebservice<CommunityModel, Community> {
	public CommunityService(){
		super(ModelType.COMMUNITY);
	}
	
	

}
