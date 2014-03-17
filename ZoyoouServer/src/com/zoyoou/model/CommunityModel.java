/**
 * 
 */
package com.zoyoou.model;

import com.zoyoou.common.entity.Community;
import com.zoyoou.dao.CommunityDao;
import com.zoyoou.dao.DataAccessType;

/**
 * @author kaiwu
 *
 */
public class CommunityModel extends AbstractModel<CommunityDao, Community> {

	protected CommunityModel() {
		super(DataAccessType.COMMUNITY);
	}
}
