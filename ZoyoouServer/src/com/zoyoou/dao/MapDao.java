/**
 * 
 */
package com.zoyoou.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.zoyoou.common.entity.Map;

/**
 * @author kaiwu
 *
 */
public class MapDao extends AbstractDataAccess<Map> {

	public MapDao(Connection conn) throws SQLException {
		super(conn);
		
	}
	
	
	static final String MapIP= "MapID";
	static final String MapInfo="MapInfo";
	static final String District= "District";
	static final String City = "City";
	static final String Province="Province";
	static final String Dscription = "Description";
	
	
	static final String INSERT_MAP="{CALL InsertMapInfo(?, ?,?,?,?,?)}";
	
	@Override
	public List<Map> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map> findAllBy(String queryCondition) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map findById(long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map create(Map entity) throws Exception {
		Map map = entity;
		CallableStatement csMap = this.conn.prepareCall(INSERT_MAP);
		csMap.setString(1, map.getMapInfo());
		csMap.setString(2, map.getDisctrict());
		csMap.setString(3, map.getCity());
		csMap.setString(4, map.getProvince());
		csMap.setString(5, map.getDescription());
		csMap.registerOutParameter(6, java.sql.Types.BIGINT);
		csMap.execute();
		long mapId = csMap.getLong(6);
		map.setMapid(mapId);
		return map;
	}

	@Override
	public Map update(Map entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map remove(long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
