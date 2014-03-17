package com.zoyoou.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zoyoou.common.entity.ActiveStatus;
import com.zoyoou.common.entity.Community;
import com.zoyoou.common.entity.Map;
import com.zoyoou.resource.ErrorMessages;

public class CommunityDao extends AbstractDataAccess<Community> {

	public CommunityDao(Connection conn) throws SQLException {
		super(conn);
	}
	
	static final String MapID= "MapID";
	static final String Name="Name";
	static final String ActiveStatus= "ActiveStatus";
	static final String CommunityID = "CommunityID";
	
	static final String INSERT_COMMUNITY="{CALL InsertCommunityInfo(?, ?,?,?)}";
	static final String UPDATE_COMMUNITY="{CALL UpdateCommunityInfo(?, ?,?,?)}";
	static final String FIND_BY_ID="{CALL GetCommunityInfo(?)}";
	static final String DELETE_COMMUNITY="{CALL DeleteCommunityInfo(?)}";


	@Override
	public List<Community> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Community> findAllBy(String queryCondition) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Community findById(long id) throws Exception {
		Community community= new Community();
		community.setCommunityId(id);

		CallableStatement cs = this.conn.prepareCall(FIND_BY_ID);
		cs.setLong(1, id);
		ResultSet result = cs.executeQuery();
			

		if(result.next()){
			community = getCommunityFromResult(result);
			community.setCommunityId(id);
		}else{
			List<String> errors = new ArrayList<String>();
			errors.add(String.format(ErrorMessages.NO_RECORD_FOUND_BY_ID, id));
			community.setErrorList(errors);
		}
		return community;

	}
	
	
	static Community getCommunityFromResult(ResultSet result) throws SQLException{
		Community community = new Community();
		
	//	community.setCommunityId(result.getLong(CommunityID));
		community.setName(result.getString(Name));
		
		ActiveStatus status = new ActiveStatus();
		status.setActiveId(result.getShort(ActiveStatus));
		community.setActiveStatus(status);
	
		Map map = new Map();
		map.setMapid(result.getLong(MapID));
		
	
		return community;
	}

	@Override
	public Community create(Community entity) throws Exception {
		
		Community community = entity;
		CallableStatement csCommunity = this.conn.prepareCall(INSERT_COMMUNITY);
		csCommunity.setString(1, community.getName());
		if(null == community.getActiveStatus())
			csCommunity.setShort(2, (short)1);
		else 
			csCommunity.setShort(2, community.getActiveStatus().getActiveId());
		if(null == community.getMap())
			csCommunity.setNull(3, java.sql.Types.BIGINT);
		else 
			csCommunity.setLong(3, community.getMap().getMapid());

		
		csCommunity.registerOutParameter(4, java.sql.Types.BIGINT);
		csCommunity.execute();
		long communityId = csCommunity.getLong(4);
		community.setCommunityId(communityId);
		return community;
	}

	@Override
	public Community update(Community entity) throws Exception {
		Community community = entity;
		CallableStatement csCommunity = this.conn.prepareCall(UPDATE_COMMUNITY);
		
		csCommunity.setLong(1, community.getCommunityId());
		csCommunity.setString(2, community.getName());
		if(null == community.getActiveStatus())
			csCommunity.setShort(3, (short)1);
		else 
			csCommunity.setShort(3, community.getActiveStatus().getActiveId());
		if(null == community.getMap())
			csCommunity.setNull(4, java.sql.Types.BIGINT);
		else 
			csCommunity.setLong(4, community.getMap().getMapid());
				
		csCommunity.execute();
		
		return community;	
	}

	@Override
	public Community remove(long id) throws Exception {
		Community community = findById(id);
		CallableStatement csUser = this.conn.prepareCall(DELETE_COMMUNITY);
		csUser.setLong(1, id);
		csUser.execute();
		return community;
	}
	
}
